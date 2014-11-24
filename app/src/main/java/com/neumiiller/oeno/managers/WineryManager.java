package com.neumiiller.oeno.managers;

import android.content.Context;

import android.content.res.AssetManager;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;

import com.neumiiller.oeno.OenoApplication;
import com.neumiiller.oeno.models.Winery;
import com.neumiiller.oeno.models.WineryMeta;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.BufferedInputStream;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.Iterator;

import android.util.Log;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class WineryManager {

	private static final String DATA_DIR = "Data/";
	private static final String IMAGES_DIR = "Images/";
	
	private static final String WINERY_IMAGE_PATH = IMAGES_DIR + "wineries/";
	
	private static final String WINERIES_JSON_PATH = DATA_DIR +  "wineries.json";
	private static final String WINERY_META_JSON_PATH = DATA_DIR +  "wineries-meta.json";

	private final ArrayList<Winery> WINERIES = new ArrayList<Winery>();
	private final HashMap<String, WineryMeta> WINERY_META = new HashMap<String, WineryMeta>(); 
	
	public WineryManager(Context context){
		WineryLoadingManager loadingManager = new WineryLoadingManager();
		WINERY_META.putAll(loadingManager.loadWineryMetaFromFile(context));
		WINERIES.addAll(loadingManager.loadWineriesFromFile(context));
		
		loadingManager.connect(WINERIES, WINERY_META);

        Collections.sort(WINERIES, new Comparator<Winery>() {
            @Override
            public int compare(Winery winery, Winery winery2) {
                int priority = winery.getMeta().getPriority() - winery2.getMeta().getPriority();

                if(priority != 0){
                    return priority;
                }
                return winery.hashCode() - winery2.hashCode();
            }
        });
	}
	
	public ArrayList<Winery> getWineries(){
		return WINERIES;
	}
	
	public WineryMeta getWineryMeta(Winery winery){
		return WINERY_META.get(winery.getName());
	}
	
	public Bitmap getWineryListPicture(Context context, Winery winery){
		return loadBitmap(context, getWineryImagePath(winery) + ".jpg");
	}

    public Bitmap getWineryFullPicture(Context context, Winery winery){
        return loadBitmap(context, getWineryImagePath(winery) + "-full.jpg");
    }

    private String getWineryImagePath(Winery winery){
        String wineryName = winery.getName();

        //lowercase
        wineryName = wineryName.toLowerCase();

        //replace all spaces with `-`
        wineryName = wineryName.replace(" ", "-");

        //Remove all non-(alphanumeric chars and dash)
        wineryName = wineryName.replaceAll("[^a-z0-9-]", "");

        return WINERY_IMAGE_PATH + wineryName;
    }
	
	public Bitmap loadBitmap(Context context, String fileName){
        AssetManager assets = context.getResources().getAssets();
        InputStream buf = null;
        try {
        	 buf = new BufferedInputStream((assets.open(fileName)));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Bitmap bitmap = BitmapFactory.decodeStream(buf);
        return bitmap;
    } 
	
	private class WineryLoadingManager {
		Gson gson = new Gson();
		
		public void connect(ArrayList<Winery> wineries, HashMap<String, WineryMeta> wineryMetaMap){
			for(Winery winery : wineries){
				WineryMeta wineryMeta = wineryMetaMap.get(winery.getName());
				winery.setMeta(wineryMeta);
			}
		}
		
		public ArrayList<Winery> loadWineriesFromFile(Context context){
	    	Type type = new TypeToken<ArrayList<Winery>>(){}.getType();
	    	
	    	JsonElement root = loadJsonElementFromFile(context, WINERIES_JSON_PATH);
	    	JsonArray wineryArray = root.getAsJsonObject().getAsJsonArray("wineries");
	    	return gson.fromJson(wineryArray, type);
	    }
	    
		public HashMap<String, WineryMeta> loadWineryMetaFromFile(Context context){
			HashMap<String, WineryMeta> temp = new HashMap<String, WineryMeta>();
			
	    	Type type = new TypeToken<WineryMeta>(){}.getType();
	    	
	    	JsonElement root = loadJsonElementFromFile(context, WINERY_META_JSON_PATH);
	    	JsonObject rootObject = root.getAsJsonObject();
	    	
	    	for(Map.Entry<String, JsonElement> entry : rootObject.entrySet()){
	    		String key = entry.getKey();
	    		JsonElement value = entry.getValue();
	    		
	    		Log.d("WineryManager", "key "+key);
	    		Log.d("WineryManager", "val "+value);
	    		
	    		WineryMeta meta = gson.fromJson(value, type);
	    		temp.put(key, meta);	    		
	    	}
	    	
	    	return temp;
	    }
	    
	    private JsonElement loadJsonElementFromFile(Context context, String file){
	        AssetManager assetManager = context.getAssets();
	        InputStream inputStream = null;
	        JsonElement root = null;
	        
	        try {
	            inputStream = assetManager.open(file);
	            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
	            root = new JsonParser().parse(reader);            
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	        
	        return root;
	    }
	}
}
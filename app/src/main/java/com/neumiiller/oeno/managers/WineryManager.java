package com.neumiiller.oeno.manager;

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
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Iterator;

import android.util.Log;

public class WineryManager {
	
	private final ArrayList<Winery> WINERIES = new ArrayList<Winery>();
	private final HashMap<String, WineryMeta> WINERY_META = new HashMap<String, WineryMeta>(); 
	
	public WineryManager(OenoApplication app){
		WineryLoadingManager loadingManager = new WineryLoadingManager();
		WINERIES.addAll(loadingManager.loadWineriesFromFile(app));
		WINERY_META.putAll(loadingManager.loadWineryMetaFromFile(app));
	}
	
	public ArrayList<Winery> getWineries(){
		return WINERIES;
	}
	
	private class WineryLoadingManager {
		Gson gson = new Gson();
		
		public ArrayList<Winery> loadWineriesFromFile(Context context){
	    	Type type = new TypeToken<ArrayList<Winery>>(){}.getType();
	    	
	    	JsonElement root = loadJsonElementFromFile(context, "Data/wineries.json");
	    	JsonArray wineryArray = root.getAsJsonObject().getAsJsonArray("wineries");
	    	return gson.fromJson(wineryArray, type);
	    }
	    
		public HashMap<String, WineryMeta> loadWineryMetaFromFile(Context context){
			HashMap<String, WineryMeta> temp = new HashMap<String, WineryMeta>();
			
	    	Type type = new TypeToken<WineryMeta>(){}.getType();
	    	
	    	JsonElement root = loadJsonElementFromFile(context, "Data/wineries-meta.json");
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
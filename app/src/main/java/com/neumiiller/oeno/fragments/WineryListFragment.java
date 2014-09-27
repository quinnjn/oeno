package com.neumiiller.oeno.fragments;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import com.neumiiller.oeno.R;
import com.neumiiller.oeno.models.Winery;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * @author QJN on 2014-09-15.
 */
public class WineryListFragment extends Fragment {

    public static class WineryListAdapter extends ArrayAdapter<Winery>{

        public WineryListAdapter(Context context, List<Winery> objects) {
            super(context, R.layout.view_winery_list_item, objects);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View view = convertView;
            if(view == null){
                view = LayoutInflater.from(getContext()).inflate(R.layout.view_winery_list_item, null);
            }

            Winery winery = getItem(position);

            TextView city = (TextView)view.findViewById(R.id.winery_list_item_city);
            TextView drivingTime = (TextView) view.findViewById(R.id.winery_list_item_driving_time);
            TextView distance = (TextView) view.findViewById(R.id.winery_list_item_distance);
            TextView address = (TextView) view.findViewById(R.id.winery_list_item_address);
            TextView name = (TextView) view.findViewById(R.id.winery_list_item_name);

            city.setText(winery.getCity());
            drivingTime.setText(winery.getDrivingTime());
            distance.setText(""+winery.getDistance());
            address.setText(winery.getAddress());
            name.setText(winery.getName());

            return view;
        }
    }

    private WineryListAdapter listAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        ArrayList<Winery> wineries = getWineriesFromFile(activity);
        listAdapter = new WineryListAdapter(activity, wineries);
    }

    private ArrayList<Winery> getWineriesFromFile(Context context){
        Gson gson = new Gson();
        Type type = new TypeToken<ArrayList<Winery>>(){}.getType();
        AssetManager assetManager = context.getAssets();
        InputStream inputStream = null;
        ArrayList<Winery> wineries = new ArrayList<Winery>();

        try {
            inputStream = assetManager.open("wineries.json");
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

            JsonElement root = new JsonParser().parse(reader);
            JsonArray wineryArray = root.getAsJsonObject().getAsJsonArray("wineries");
            wineries = gson.fromJson(wineryArray, type);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return wineries;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_winery_list, container);
        ListView listView = (ListView) view.findViewById(R.id.list);
        listView.setAdapter(listAdapter);
        return view;
    }
}

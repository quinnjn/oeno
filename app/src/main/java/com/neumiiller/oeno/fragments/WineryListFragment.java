package com.neumiiller.oeno.fragments;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.content.res.AssetManager;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import com.neumiiller.oeno.R;
import com.neumiiller.oeno.OenoApplication;
import com.neumiiller.oeno.adapters.WineryListAdapter;
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

    public interface WineryListFragmentListener {
        public void onViewMapButtonClick();
        public void onWineryClick(Winery winery);
    }

    private WineryListAdapter listAdapter;
    private WineryListFragmentListener wineryListFragmentListener;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        setWineryListFragmentListener(activity);        
        listAdapter = new WineryListAdapter(activity);
        listAdapter.updateLocation(getLocation(activity));
    }

    private void setWineryListFragmentListener(Activity activity) {
        this.wineryListFragmentListener = (WineryListFragmentListener)activity;
    }

    private Location getLocation(Activity activity){
        LocationManager locationManager = (LocationManager) activity.getApplicationContext().getSystemService(Context.LOCATION_SERVICE);
        Location location = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
        return location;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.winerylist, menu);
        MenuItem mapMenuButton = menu.findItem(R.id.menu_winerylist_map);
        mapMenuButton.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                mapButtonClicked();
                return true;
            }
        });
        super.onCreateOptionsMenu(menu, inflater);
    }

    private ArrayList<Winery> getWineriesFromFile(Context context){
        Gson gson = new Gson();
        Type type = new TypeToken<ArrayList<Winery>>(){}.getType();
        AssetManager assetManager = context.getAssets();
        InputStream inputStream = null;
        ArrayList<Winery> wineries = new ArrayList<Winery>();

        try {
            inputStream = assetManager.open("Data/wineries.json");
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
        View view = inflater.inflate(R.layout.fragment_winery_list, null);
        ListView listView = (ListView) view.findViewById(R.id.winery_list);
        Button viewMapButton = (Button) view.findViewById(R.id.view_wineries_button);
        listView.setAdapter(listAdapter);
        viewMapButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mapButtonClicked();
            }
        });
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Winery winery = (Winery)parent.getItemAtPosition(position);
                wineryListFragmentListener.onWineryClick(winery);
            }
        });
        return view;
    }

    private void mapButtonClicked() {
        wineryListFragmentListener.onViewMapButtonClick();
    }
}

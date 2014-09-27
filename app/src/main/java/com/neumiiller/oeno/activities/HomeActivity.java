package com.neumiiller.oeno.activities;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.neumiiller.oeno.R;


public class HomeActivity extends Activity {

    private DrawerLayout drawerLayout;
    private ListView drawerList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        drawerLayout = (DrawerLayout)findViewById(R.id.drawer_layout);
        drawerList = (ListView)findViewById(R.id.drawer_list);

        initializeViews();

        getActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private void initializeViews() {
        initializeDrawerList();
        initializeDrawerLayout();
    }

    private void initializeDrawerList() {
        String[] drawerItems = getResources().getStringArray(R.array.drawer_strings);
        drawerList.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, drawerItems));
        drawerList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(parent.getContext(), ""+position, Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void initializeDrawerLayout() {
    }
}

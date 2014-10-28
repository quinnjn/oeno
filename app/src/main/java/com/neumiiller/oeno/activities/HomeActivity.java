package com.neumiiller.oeno.activities;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
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
import com.neumiiller.oeno.fragments.EventsFragment;
import com.neumiiller.oeno.fragments.WineryFragment;
import com.neumiiller.oeno.fragments.WineryListFragment;
import com.neumiiller.oeno.fragments.FeaturedWineryListFragment;
import com.neumiiller.oeno.fragments.NearbyWineryListFragment;
import com.neumiiller.oeno.fragments.OenoPassFragment;
import com.neumiiller.oeno.fragments.PromotionsFragment;

import com.neumiiller.oeno.models.Winery;


public class HomeActivity extends Activity implements WineryListFragment.WineryListFragmentListener{

    private DrawerLayout drawerLayout;
    private ListView drawerList;
    private Fragment currentFragment;

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

//    private void initializeFragments(){
//        FragmentManager fragmentManager = getFragmentManager();
//        currentFragment = fragmentManager.findFragmentByTag("currentFragment");
//        if(currentFragment == null){
//            currentFragment = new WineryListFragment();
//            fragmentManager.beginTransaction()
//                    .add(currentFragment, "currentFragment")
//                    .commit();
//        }
//        fragmentManager.executePendingTransactions();
//    }

    private void initializeDrawerList() {
        String[] drawerItems = getResources().getStringArray(R.array.drawer_strings);
        drawerList.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, drawerItems));
        drawerList.setOnItemClickListener(drawListClickListener);
    }
    
    private AdapterView.OnItemClickListener drawListClickListener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            FragmentManager fm = getFragmentManager();
            FragmentTransaction transaction = fm.beginTransaction();
            Fragment fragment = null;
            
            switch(position){
	            case 0:
	            	Toast.makeText(parent.getContext(), "FeaturedWineryListFragment "+position, Toast.LENGTH_SHORT).show();
	            	fragment = new FeaturedWineryListFragment();
	        		break;
	            case 1:
	            	Toast.makeText(parent.getContext(), "NearbyWineryListFragment "+position, Toast.LENGTH_SHORT).show();
	            	fragment = new NearbyWineryListFragment();
	        		break;
	            case 2:
	            	Toast.makeText(parent.getContext(), "PromotionsFragment "+position, Toast.LENGTH_SHORT).show();
	            	fragment = new PromotionsFragment();
	        		break;
	            case 3:
	            	Toast.makeText(parent.getContext(), "EventsFragment "+position, Toast.LENGTH_SHORT).show();
	            	fragment = new EventsFragment();
	        		break;
	            case 4:
	            	Toast.makeText(parent.getContext(), "OenoPassFragment "+position, Toast.LENGTH_SHORT).show();
	            	fragment = new OenoPassFragment();
	        		break;
            }
            
            if(fragment == null){
            	return;
            }
            
            transaction.replace(
                    R.id.home_fragment_root,
                    fragment,
                    "currentFragment"
            );
            transaction.addToBackStack("drawer-"+position);
            transaction.commit();
        }
    };


    @Override
    protected void onResume() {
        super.onResume();

        FragmentManager fragmentManager = getFragmentManager();
        if(fragmentManager.findFragmentByTag("currentFragment") == null) {
            Fragment fragment = new WineryListFragment();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.add(R.id.home_fragment_root, fragment, "currentFragment");
            fragmentTransaction.commit();
        }

    }

    private void initializeDrawerLayout() {
    }

    @Override
    public void onViewMapButtonClick() {
        Toast.makeText(this, "onViewMapButtonClick", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onWineryClick(Winery winery) {
        Fragment fragment = WineryFragment.newInstance(winery);

        FragmentManager fm = getFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();
        transaction.setCustomAnimations(
                R.anim.slide_in_left,
                R.anim.slide_out_right,
                R.anim.slide_in_left,
                R.anim.slide_out_right
        );
        transaction.replace(
                R.id.home_fragment_root,
                fragment,
                "currentFragment"
        );
        transaction.addToBackStack("map");
        transaction.commit();
    }
}

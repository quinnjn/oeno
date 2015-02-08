package com.neumiiller.oeno.fragments;

import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.graphics.Point;
import android.net.Uri;
import android.os.Bundle;
import android.text.Html;
import android.text.Spanned;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;

import com.neumiiller.oeno.OenoApplication;
import com.neumiiller.oeno.R;
import com.neumiiller.oeno.models.Winery;
import com.neumiiller.oeno.models.WineryDetails;

import java.util.Locale;

/**
 * @author QJN on 2014-09-27.
 */
public class WineryFragment extends BaseFragment {
    private Winery winery;
    private ScrollView scrollView;

    private ImageView wineryPhoto;
    private View winerySpacer;
    private TextView wineryDrivingTime;
    private TextView wineryDistance;
    private View downIndicator;

    private TextView wineryDetails;
    private TextView wineryTours;
    private TextView wineryTastingRoom;
    private TextView wineryContact;

    private static class Argument {
        public static final String WINERY = "arg_winery";
    }

    public static Fragment newInstance(Winery winery) {
        Fragment fragment = new WineryFragment();
        Bundle args = new Bundle();

        args.putParcelable(Argument.WINERY, winery);

        fragment.setArguments(args);
        return fragment;
    }

    @Override protected String getTitle() {
        return winery.getName();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        loadArguments(getArguments());
    }

    public void loadArguments(Bundle args) {
        winery = args.getParcelable(Argument.WINERY);
    }


    @Override public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.winery_details, menu);
        menu.findItem(R.id.map).setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override public boolean onMenuItemClick(MenuItem menuItem) {
                openWineryInMaps();
                return true;
            }
        });
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Context context = inflater.getContext();

        //Connect Views
        View rootView = inflater.inflate(R.layout.fragment_winery, null);

        connectViews(rootView);
        initializeViews(context);
        updateViews();
        return rootView;
    }

    private void connectViews(View view) {
        scrollView = (ScrollView)view.findViewById(R.id.scroll_view);
        wineryPhoto = (ImageView) view.findViewById(R.id.winery_photo);
        winerySpacer = view.findViewById(R.id.winery_spacer);
        downIndicator = view.findViewById(R.id.down_indicator);

        wineryDetails = (TextView) view.findViewById(R.id.winery_details);
        wineryTours = (TextView) view.findViewById(R.id.winery_tours);
        wineryTastingRoom = (TextView) view.findViewById(R.id.winery_tasting_room);
        wineryContact = (TextView) view.findViewById(R.id.winery_contact);
    }

    private void initializeViews(Context context) {
        initializeWinerySpacer();
        downIndicator.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View view) {
                scrollView.smoothScrollTo(0, view.getBottom());
            }
        });
        wineryPhoto.setImageBitmap(
                OenoApplication.getInstance()
                        .getWineryManager()
                        .getWineryFullPicture(context, winery)
        );
    }

    private void initializeWinerySpacer() {
        scrollView.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override public void onGlobalLayout() {
                if(scrollView.getMeasuredHeight() > 0){
                    scrollView.getViewTreeObserver().removeGlobalOnLayoutListener(this);
                    measure();
                }
            }
        });
    }

    private void measure() {
        ViewGroup.LayoutParams params = winerySpacer.getLayoutParams();
        params.height = scrollView.getMeasuredHeight() - winerySpacer.getMeasuredHeight();
        winerySpacer.setLayoutParams(params);
    }

    private void updateViews() {

        WineryDetails details = winery.getMeta()
                .getDetails();

        Spanned overview = Html.fromHtml(
                details.getOverview()
        );
        Spanned tour = Html.fromHtml(
                details.getTourString()
        );
        String tastingRoom = details.getHourString();
        String contact = winery.getTelephone();

        wineryDetails.setText(overview);
        wineryTours.setText(tour);
        wineryTastingRoom.setText(tastingRoom);
        wineryContact.setText(contact);
    }

    private int getStatusBarHeight() {
        int result = 0;
        int resourceId = getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            result = getResources().getDimensionPixelSize(resourceId);
        }
        return result;
    }



    private void openWineryInMaps(){
        double latitude = winery.getLocation().getLatitude();
        double longitude = winery.getLocation().getLongitude();

        String uri = String.format(
                Locale.ENGLISH,
                "geo:%f,%f",
                latitude,
                longitude
        );

        Intent intent = new Intent(
                Intent.ACTION_VIEW,
                Uri.parse(uri)
        );

        getActivity().startActivity(intent);
    }
}

package com.neumiiller.oeno.fragments;

import android.app.Fragment;
import android.content.Context;
import android.graphics.Point;
import android.os.Bundle;
import android.text.Html;
import android.text.Spanned;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.neumiiller.oeno.OenoApplication;
import com.neumiiller.oeno.R;
import com.neumiiller.oeno.models.Winery;
import com.neumiiller.oeno.models.WineryDetails;

/**
 * @author QJN on 2014-09-27.
 */
public class WineryFragment extends BaseFragment {
    private Winery winery;

    private ImageView wineryPhoto;
    private View winerySpacer;
    private TextView wineryDrivingTime;
    private TextView wineryDistance;

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
        loadArguments(getArguments());
    }

    public void loadArguments(Bundle args) {
        winery = args.getParcelable(Argument.WINERY);
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
        wineryPhoto = (ImageView) view.findViewById(R.id.winery_photo);
        winerySpacer = view.findViewById(R.id.winery_spacer);

        wineryDetails = (TextView) view.findViewById(R.id.winery_details);
        wineryTours = (TextView) view.findViewById(R.id.winery_tours);
        wineryTastingRoom = (TextView) view.findViewById(R.id.winery_tasting_room);
        wineryContact = (TextView) view.findViewById(R.id.winery_contact);
    }

    private void initializeViews(Context context) {
        initializeWinerySpacer();
//        wineryPhoto.setImageBitmap(
//                OenoApplication.getInstance()
//                        .getWineryManager()
//                        .getWineryFullPicture(context, winery)
//        );
    }

    private void initializeWinerySpacer() {
        Point size = new Point();
        getActivity().getWindowManager().getDefaultDisplay().getSize(size);
        int screenHeight = size.y;

        ViewGroup.LayoutParams params = winerySpacer.getLayoutParams();
        params.height = screenHeight;
        params.height -= getStatusBarHeight();
//        params.height -= wineryDrivingTime.getMeasuredHeight();
//        params.height -= wineryDistance.getMeasuredHeight();

        // TODO needs to measure

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
}

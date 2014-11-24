package com.neumiiller.oeno.adapters;

import android.content.Context;
import android.location.Location;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.ImageView;

import com.neumiiller.oeno.R;
import com.neumiiller.oeno.OenoApplication;
import com.neumiiller.oeno.models.Winery;

import java.util.Collections;
import java.util.List;

public class WineryListAdapter extends ArrayAdapter<Winery> {

    public WineryListAdapter(Context context) {
        super(context, R.layout.view_winery_list_item, OenoApplication.getInstance().getWineryManager().getWineries());
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if(view == null){
            view = LayoutInflater.from(getContext()).inflate(R.layout.view_winery_list_item, null);
        }

        Winery winery = getItem(position);

        ImageView photo = (ImageView)view.findViewById(R.id.winery_photo);
        TextView city = (TextView)view.findViewById(R.id.winery_list_item_city);
        TextView drivingTime = (TextView) view.findViewById(R.id.winery_list_item_driving_time);
        TextView distance = (TextView) view.findViewById(R.id.winery_list_item_distance);
        TextView address = (TextView) view.findViewById(R.id.winery_list_item_address);
        TextView name = (TextView) view.findViewById(R.id.winery_list_item_name);

        photo.setImageBitmap(OenoApplication.getInstance().getWineryManager().getWineryListPicture(getContext(), winery));
        city.setText(winery.getCity());
        drivingTime.setText(winery.getDrivingTime());
        distance.setText(""+winery.getDistance());
        address.setText(winery.getAddress());
        name.setText(winery.getName());

        return view;
    }

    public void updateLocation(Location location) {
        for(int i=0; i<getCount(); i++){
            Winery winery = getItem(i);
            winery.updateLocation(location);
        }
        notifyDataSetChanged();
    }
}
package com.neumiiller.oeno.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.neumiiller.oeno.R;

/**
 * @author QJN on 2014-10-28.
 */
public class PromotionsFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_promotions, null);
        return view;
    }
}

package com.neumiiller.oeno.fragments;

import android.app.Fragment;
import android.os.Bundle;

import com.neumiiller.oeno.models.Winery;

/**
 * @author QJN on 2014-09-27.
 */
public class WineryFragment extends Fragment {
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
}

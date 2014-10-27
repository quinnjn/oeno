package com.neumiiller.oeno.fragments;

import android.app.Fragment;
import android.os.Bundle;

/**
 * @author QJN on 2014-10-27.
 */
public class DrawerFragment extends Fragment {
    private static class Argument {
    }

    public static Fragment newInstance() {
        Fragment fragment = new DrawerFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }
}

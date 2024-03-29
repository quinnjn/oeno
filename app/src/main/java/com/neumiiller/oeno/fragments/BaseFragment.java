package com.neumiiller.oeno.fragments;

import android.app.Fragment;
import android.os.Bundle;

/**
 * @author Quinn Neumiiller (quinnjn)
 * @since 15-01-31
 */
public abstract class BaseFragment extends Fragment {

    protected abstract String getTitle();

    @Override public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override public void onResume() {
        super.onResume();
        if(getActivity() != null && getActivity().getActionBar() != null) {
            getActivity().getActionBar()
                    .setTitle(getTitle());
        }
    }

}

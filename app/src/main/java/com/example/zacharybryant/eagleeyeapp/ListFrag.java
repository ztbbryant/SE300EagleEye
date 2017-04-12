package com.example.zacharybryant.eagleeyeapp;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.InflateException;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.android.gms.maps.MapFragment;

import java.sql.Array;
import java.util.ArrayList;


/**
 * Custom {@link Fragment} that integrates google map fragment and list view to display building locations
 * @author Shawn
 * @version 1.0
 */
public class ListFrag extends Fragment {

    private OnFragmentInteractionListener mListener;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }


    private static View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (view != null) {
            ViewGroup parent = (ViewGroup) view.getParent();
            if (parent != null)
                parent.removeView(view);
        }
        try {
            view = inflater.inflate(R.layout.fragment_list, container, false);
        } catch (InflateException e) {}


        ListView lv = (ListView) getActivity().findViewById(R.id.listView);
        Activity a = getActivity();
        Log.d("LV","Test");
//        ArrayList<String> array = new ArrayList<>();
//        array.add("Test1");
//        array.add("Test2");
//        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(),R.layout.fragment_list,R.id.listView,array);
//        lv.setAdapter(adapter);
//        adapter.notifyDataSetChanged();

        return view;
    }


    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }


    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;

    }

    public interface OnFragmentInteractionListener {
        void onFragmentInteraction(Uri uri);
    }
}

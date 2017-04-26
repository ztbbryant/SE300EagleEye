package com.example.zacharybryant.eagleeyeapp;

import android.app.Activity;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.InflateException;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ListView;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.sql.Array;
import java.util.ArrayList;

import static com.example.zacharybryant.eagleeyeapp.MainActivity.db;


/**
 * Custom {@link Fragment} that integrates google map fragment and list view to display building locations
 * @author Shawn
 * @version 1.0
 */
public class ListFrag extends Fragment implements OnMapReadyCallback{

    private OnFragmentInteractionListener mListener;
    private ExpandableListView lv;
    private String[] mainGroups;
    private String[][] children1;
    MapView mapView;
    GoogleMap map;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mainGroups = new String[]{"Buildings", "Food", "Resources"};
        children1 = new String[][]{
                {
                        "Corsair Hall",
                        "North Chiller",
                        "New Residence Hall",
                        "Campus Safety",
                        "Apollo Hall",
                        "Doolittle Hall",
                        "Bookstore",
                        "Mail Services",
                        "Undergraduate Research and Disability Services",
                        "Eagle Fitness Center",
                        "Student Financial Services",
                        "Center for Faith and Spirituality",
                        "Racquetball Court",
                        "Student Support Services, Global Engagement, Honors Center",
                        "Advanced Flight Sim Center",
                        "Fleet Maintenance Hangar",
                        "Flight Operations",
                        "College of Business",
                        "Willie Miller Instructional Center",
                        "Aviation Maintenance Services",
                        "College of Aviation",
                        "AMS Hangar",
                        "Test Cell 1",
                        "Engine Test Cells",
                        "Canaveral Hall",
                        "College of Arts and Sciences",
                        "Wellness Center - Clinic",
                        "Wellness Center - Counseling Center",
                        "ROTC Center",
                        "Print Shop",
                        "Veteran's Affairs",
                        "Eagle Alumni Center",
                        "Crotty Tennis Complex",
                        "Telescope",
                        "Track and Field Concession",
                        "Track and Field Bleachers",
                        "ROTC Obstacle Course",
                        "Multipurpose Artificial Turf Field",
                        "Artificial Turf Softball Field",
                        "Baseball/Softball Clubhouse",
                        "JPR Student and Admissions Visitor Center",
                        "ICI Center",
                        "Jim W. Henderson Administration and Welcome Center",
                        "Sliwa Stadium",
                        "Soccer - Ticker Concession",
                        "Soccer Field Bleachers",
                        "Student Union (Under Construction)",
                        "Lehman College of Engineering",
                        "South Chiller",
                        "Admissions Operations",
                        "Engineering Special Projects and Labs",
                        "Clyde Morris Multipurpose Field",
                        "Student Village (Adams, Wood, Tallman Commons)",
                        "Student Village (O'Connor, Stimpson)",
                        "Richard Petty Multipurpose West Field",
                        "Richard Petty Multipurpose East Field"
                },
                {
                        "Einstein Bros. Bagels",
                        "Freshens Smoothie Company",
                        "Chick-fil-A",
                        "Pacific Traders",
                        "Salsa at the Landing Strip",
                        "Flight Cafe",
                        "Propeller's Food Truck",
                        "UC Food Court (Deli, Grill, Hotline",
                        "The Food Court Deli",
                        "Sicilian Grill",
                        "Starbucks",
                        "Salad Toss"
                },
                {
                        "Honors Program",
                        "Office of Global Engagement",
                        "Fraternity and Sorority Life Office",
                        "WIKD",
                        "The Avion",
                        "Orientation and Family Relations",
                        "Touch-N-Go Productions",
                        "Student Engagement",
                        "Student Government Association",
                        "Office of Undergraduate Research (IGNITE)",
                        "Campus Safety and Security",
                        "Financial Aid",
                        "Student Financial Services (Bursar's Office)",
                        "Student Employment",
                        "Mail Center",
                        "Housing and Residence Life",
                        "Counseling Center",
                        "Dean of Students",
                        "Office of Records and Registration",
                        "EAGLECard",
                        "Career Services",
                        "Chaplain's Office",
                        "Dining Services",
                        "Embry-Riddle Language Institute (ERLI)",
                        "First Year Programs",
                        "Lost and Found",
                        "Intramural and Recreational Sports",
                        "Academic Advancement Center"
                }
        };

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

            mapView = (MapView) view.findViewById(R.id.map);
            mapView.onCreate(savedInstanceState);
            mapView.getMapAsync(this);

        } catch (InflateException e) {}

        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        lv = (ExpandableListView) view.findViewById(R.id.mainList);
        lv.setAdapter(new ListFrag.ExpandableListAdapter(mainGroups, children1));         //will added sub_groups
        lv.setGroupIndicator(null);

        lv.setOnChildClickListener(getChildInfo);
    }

    ExpandableListView.OnChildClickListener getChildInfo =  new ExpandableListView.OnChildClickListener(){

        @Override
        public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
            Log.d("Click",children1[groupPosition][childPosition]);
            Cursor item =  MainActivity.db.rawQuery("SELECT * FROM "+mainGroups[groupPosition].toUpperCase()+" WHERE NAME='"+children1[groupPosition][childPosition]+"'",null);
            //Cursor c = MainActivity.db.query(mainGroups[groupPosition].toUpperCase(),new String[]{"'"+children1[groupPosition][childPosition]+"'"},"LAT",null,null,null,null);
            item.moveToFirst();
            Log.d("Click",item.getString(0)+"\t"+ item.getString(2)+"\t"+item.getString(3));//Can be used for map pins

            return false;
        }
    };

    private class ExpandableListAdapter extends BaseExpandableListAdapter {

        private final LayoutInflater inf;
        private String[] groups;
        private String[][] children;

        private ExpandableListAdapter(String[] groups, String[][] children) {
            this.groups = groups;
            this.children = children;
            inf = LayoutInflater.from(getActivity());
        }

        @Override
        public int getGroupCount() {
            return groups.length;
        }

        @Override
        public int getChildrenCount(int groupPosition) {
            return children[groupPosition].length;
        }

        @Override
        public Object getGroup(int groupPosition) {
            return groups[groupPosition];
        }

        @Override
        public Object getChild(int groupPosition, int childPosition) {
            return children[groupPosition][childPosition];
        }

        @Override
        public long getGroupId(int groupPosition) {
            return groupPosition;
        }

        @Override
        public long getChildId(int groupPosition, int childPosition) {
            return childPosition;
        }

        @Override
        public boolean hasStableIds() {
            return true;
        }

        @Override
        public View getChildView(int groupPosition, final int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {


            ListFrag.ExpandableListAdapter.ViewHolder holder;
            if (convertView == null) {
                convertView = inf.inflate(R.layout.list_item, parent, false);
                holder = new ListFrag.ExpandableListAdapter.ViewHolder();

                holder.text = (TextView) convertView.findViewById(R.id.lblListItem);
                convertView.setTag(holder);
            } else {
                holder = (ListFrag.ExpandableListAdapter.ViewHolder) convertView.getTag();
            }
            //replaced childPosition with subGroupPosition
            holder.text.setText(getChild(groupPosition, childPosition).toString());

            return convertView;
        }

        @Override
        public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
            ListFrag.ExpandableListAdapter.ViewHolder holder;

            if (convertView == null) {
                convertView = inf.inflate(R.layout.list_group, parent, false);

                holder = new ListFrag.ExpandableListAdapter.ViewHolder();
                holder.text = (TextView) convertView.findViewById(R.id.lblListHeader);
                convertView.setTag(holder);
            } else {
                holder = (ListFrag.ExpandableListAdapter.ViewHolder) convertView.getTag();
            }

            holder.text.setText(getGroup(groupPosition).toString());

            return convertView;
        }

        @Override
        public boolean isChildSelectable(int groupPosition, int childPosition) {
            return true;
        }

        private class ViewHolder {
            TextView text;
        }
    }


    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        // Add a marker in Sydney, Australia,
        // and move the map's camera to the same location.
        LatLng sydney = new LatLng(-33.852, 151.211);
        googleMap.addMarker(new MarkerOptions().position(sydney)
                .title("Marker in Sydney"));
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;

    }

    public interface OnFragmentInteractionListener {
        void onFragmentInteraction(Uri uri);
    }

    @Override
    public void onResume() {
        mapView.onResume();
        super.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        mapView.onPause();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mapView.onDestroy();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mapView.onLowMemory();
    }

}
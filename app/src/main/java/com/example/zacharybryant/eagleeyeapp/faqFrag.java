package com.example.zacharybryant.eagleeyeapp;

import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.TextView;

/**
 * Created by Marcell on 4/2/2017.
 */

public class faqFrag extends Fragment {

    View rootView;
    ExpandableListView lv;
    private String[] groups;
    private String[][] sub_groups;
    private String[] children;
    //the child class is acually a 2d string


    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        groups = new String[]{"General Admission Questions",
                                "University FAQs",
                                "International Student FAQ"};

        children = new String[]{ "What are your admissions criteria?",
                                    "When should I apply for admission?",
                                    "Can I change my major or my campus?",
                                    "Do you accept transfer credits from other colleges? What courses should I take if I plan to transfer to Embry-Riddle at a later date?",
                                    "I'm taking Advanced Placement (AP) classes in high school. Will they count at Embry-Riddle?",
                                    "I’m taking A-level Exam. Will they count at Embry-Riddle?",
                                    "What are your Admissions office hours? How can I get a tour?",
                                    "I am taking International Baccalaureate (IB) Exam. Will they count at Embry-Riddle?"};

        sub_groups = new String[][]{
                {"General Admission Question go here"},
                {"University FAQs go here"},
                {"International Student FAQ"}
        };
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        //This method creates the FAQ fragment of the application
        rootView = inflater.inflate(R.layout.fragment_faq, container, false);
        return rootView;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        lv = (ExpandableListView) view.findViewById(R.id.questExpand);
        //lv = (ExpandableListView) view.findViewById(R.id.subQuestExpand);       //Requires another expandable list view
        lv.setAdapter(new ExpandableListAdapter(groups, children));         //will added sub_groups
        lv.setGroupIndicator(null);
    }

    //Creates the View for the children


    public class ExpandableListAdapter extends BaseExpandableListAdapter {

        private final LayoutInflater inf;
        private String[] groups;
        //private String[][] sub_groups;    //attempting to implement nested expand view
        private String[] children;

        public ExpandableListAdapter(String[] groups, String[] children) {
            //Inserted sub_groups String into the argument
            this.groups = groups;
            //this.sub_groups = sub_groups;   //Attempting to create nested expand view
            this.children = children;
            inf = LayoutInflater.from(getActivity());
        }

        @Override
        public int getGroupCount() {
            return groups.length;
        }

        //Created getSubGroupCount


        @Override
        public int getChildrenCount(int groupPosition) {
            /*
            Inserted int subGroupPosition into argument
            added another [] to fill in subGroupPosition into return statement
             */
            return children.length;
        }

        @Override
        public Object getGroup(int groupPosition) {
            return groups[groupPosition];
        }

        //Created Object getSubGroup

        @Override
        public Object getChild(int groupPosition, int childPosition) {
            return children[groupPosition];
        }

        @Override
        public long getGroupId(int groupPosition) {
            return groupPosition;
        }
        //Created subGroupPosition

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

            ViewHolder holder;
            if (convertView == null) {
                convertView = inf.inflate(R.layout.list_item, parent, false);
                holder = new ViewHolder();

                holder.text = (TextView) convertView.findViewById(R.id.lblListItem);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }
                //replaced childPosition with subGroupPosition
            holder.text.setText(getChild(groupPosition, childPosition).toString());

            return convertView;
        }

        @Override
        public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
            ViewHolder holder;

            if (convertView == null) {
                convertView = inf.inflate(R.layout.list_group, parent, false);

                holder = new ViewHolder();
                holder.text = (TextView) convertView.findViewById(R.id.lblListHeader);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }

            holder.text.setText(getGroup(groupPosition).toString());

            return convertView;
        }

        /*
        * If creating a View getSubGroupView, then need
        * subGroupPosition
        * ViewSubGroup
        */

        @Override
        public boolean isChildSelectable(int groupPosition, int childPosition) {
            return true;
        }

        private class ViewHolder {
            TextView text;
        }
    }
}
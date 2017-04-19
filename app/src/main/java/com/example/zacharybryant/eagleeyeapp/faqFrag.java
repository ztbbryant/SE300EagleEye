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
    private String[][] children;
    private String[][][] answers;
    //the child class is acually a 2d string


    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        groups = new String[]{"General Admission Questions",
                                "University FAQs",
                                "International Student FAQ"};

        children = new String[][]{ {"What are your admissions criteria?",
                "When should I apply for admission?",
                "Can I change my major or my campus?",
                "Do you accept transfer credits from other colleges? What courses should I take if I plan to transfer to Embry-Riddle at a later date?",
                "I'm taking Advanced Placement (AP) classes in high school. Will they count at Embry-Riddle?",
                "I’m taking A-level Exam. Will they count at Embry-Riddle?",
                "What are your Admissions office hours? How can I get a tour?",
                "I am taking International Baccalaureate (IB) Exam. Will they count at Embry-Riddle?"},

                {"What majors do you offer?",
                        "Are the career opportunities in aviation promising?",
                        "Do you provide assistance to your graduating students in their job search? Do you have any co-ops or internships available?",
                        "Is the university accredited?",
                        "How many students are enrolled at the Daytona Beach Campus? What is the student/faculty ratio?",
                        "What will it cost to attend?",
                        "Is financial aid available?",
                        "Can I fly at Embry-Riddle if I am not enrolled in a flight program?",
                        "What kind of aircraft and simulators do you have?",
                        "Will I start flying my first year as an Aeronautical Science major?",
                        "Is tutoring available?",
                        "How does ROTC work?",
                        "Do you offer housing on campus? Are cars allowed on campus?",
                        "What kind of extracurricular activities do you offer?",
                        "Where do students eat on campus?",
                        "What are the University's Computer Requirements?"},

                {"When should an application be submitted to Embry-Riddle Aeronautical University?",
                        "What are the next steps after I have applied? When will I receive an admissions decision?"}

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
        lv.setAdapter(new ExpandableListAdapter(groups, children));         //will added sub_groups
        lv.setGroupIndicator(null);
    }

    //Creates the View for the children


    public class ExpandableListAdapter extends BaseExpandableListAdapter {

        private final LayoutInflater inf;
        private String[] groups;
        private String[][] children;

        public ExpandableListAdapter(String[] groups, String[][] children) {
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


        @Override
        public boolean isChildSelectable(int groupPosition, int childPosition) {
            return true;
        }

        private class ViewHolder {
            TextView text;
        }
    }
}
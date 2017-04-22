package com.example.zacharybryant.eagleeyeapp;

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

        children = new String[][]{

                {"What are your admissions criteria?\n\n" +

                        "When evaluating an applicant for admission, Embry-Riddle takes into consideration a student's high school academic record (both courses taken and " +
                        "overall grade-point average), rank in class, and activities. Embry-Riddle values individual academic achievement, initiative, talent and character " +
                        "above standardized testing. Therefore, submission of standardized test scores (SAT or ACT) is optional for admission. If scores are submitted, they " +
                        "will be treated as supplemental information through the evaluation process. If you do not feel that your scores accurately reflect your abilities, you " +
                        "do not need to submit them. Scores will not be used for placement in freshman classes. High school students are advised to prepare for Embry-Riddle by " +
                        "taking three years of math, four years of English, two years of social science, and two years of science with lab. Students should have a background " +
                        "in algebra, geometry, and — for engineering majors — calculus and trigonometry. Chemistry and physics are the preferred science courses.",

                "When should I apply for admission?\n\n" +

                        "We recommend students apply for admission in the fall of their senior year of high school. Students " +
                        "applying for the fall semester should submit applications by Jan. 15 to be eligible for the maximum " +
                        "amount of aid. It is suggested that students apply at least 60 days before the start of any term to " +
                        "allow ample time for processing. International students will want to refer to the International " +
                        "Admissions page for more on application dates.",

                "Can I change my major or my campus?\n\n" +
                        "To change degree programs, you must have permission from the department chair of the program you're in " +
                        "and of the program you wish to enter. There also must be space available in the program. The longer you" +
                        " wait to change programs, the more likelihood there is of losing credits. Generally speaking, you may " +
                        "transfer between campuses if there is space available, as long as you do not break continuing student " +
                        "status.",

                "Do you accept transfer credits from other colleges? What courses should I take if I plan to transfer to" +
                        " Embry-Riddle at a later date?\n\n" +

                        "Students who have earned college credits after graduating from high school and prior to attending" +
                        " Embry-Riddle are transfer students.\n\n" +
                        "Transfer credits will be accepted if they meet all of the following criteria:\n\n" +

                        "\t\tCredits were obtained from an accredited school\n\n" +

                        "\t\tA grade of C or better was achieved in the course\n\n" +

                        "\t\t\tThe course description matches the course description of an equivalent course at Embry-Riddle\n\n" +

                        "\t\t\tThe credits can be applied toward the desired degree program.\n\n" +

                        "\t\tOfficial transfer credit evaluation will be done through the Records and Registration Office, upon acceptance.\n\n" +

                        "If a student plans to attend another college or university before attending Embry-Riddle, a good guideline to follow is" +
                        " to take general education courses such as math, English, humanities, social, and physical sciences. Do not take courses " +
                        "that are technical in nature. The student should use the Embry-Riddle course catalog as a guide or contact the Transfer Counselor" +
                        " for a transfer guide. Students transferring to Embry-Riddle should be in good academic standing with a minimum cumulative grade-point" +
                        " average (GPA) of 2.0 from the last college attended, as well as a combined GPA of 2.0 from all colleges attended in order to be considered" +
                        " for admission. If a student has fewer than 30 college credits at the time of application, a high school transcript and standardized test " +
                        "scores will be required.",

                "I'm taking Advanced Placement (AP) classes in high school. Will they count at Embry-Riddle?\n\n" +

                        "Our University offers Advanced Placement credit toward a degree to those students who submit official test scores of 3, 4, or 5 on any" +
                        " examination. How much credit awarded depends on which exams are taken, the scores, and their relevance to the specific Embry-Riddle degree program.",

                "I’m taking A-level Exam. Will they count at Embry-Riddle?\n\n" +

                        "Our University offers Advanced Level Examination credit toward a degree to those students who submit official test" +
                        " scores. How much credit awarded depends on which exams are taken, the scores, and their relevance to the specific Embry-Riddle degree program.",

                "What are your Admissions office hours? How can I get a tour?\n\n" +

                        "The admissions office is open Monday through Friday from 8 a.m. to 5 p.m. Eastern Standard Time. Counseling sessions" +
                        " and campus visits are available. To set up an appointment, call the Daytona Beach campus toll-free at 800-862-2416 or " +
                        "schedule a campus visit online. The Daytona Beach Campus observes an abbreviated summer schedule; the campus is open " +
                        "Monday-Thursday during the summer months.",

                "I am taking International Baccalaureate (IB) Exam. Will they count at Embry-Riddle?\n\n" +

                        "Our University offers certain International Baccalaureate credit towards degrees. How much credit awarded depends" +
                        " on which exams are taken, the scores, and their relevance to the specific Embry-Riddle degree program."
                },

                {"What majors do you offer?\n\n" +

                        "While Embry-Riddle is best known for our majors in aeronautical science (professional pilot) and aerospace engineering," +
                        " more than 30 majors are offered at the Daytona Beach campus, including Human Factors Psychology, Homeland Security, " +
                        "Engineering Physics, Safety Science, Business and more.",

                        "Are the career opportunities in aviation promising?\n\n" +

                        "Careers in aviation and aerospace are as diverse as the people who work in these industries. Global in nature and continually" +
                                " evolving, air and space travel present exciting challenges to those who are committed to the future of space exploration" +
                                " and the advancement of our air transportation system. It’s been said that for every one pilot flying in the cockpit," +
                                " 10 workers are required to support air service. Managers, safety experts, air traffic controllers, meteorologists, " +
                                "engineers, public relations professionals, financial analysts, civil engineers, and human factors psychologists are just " +
                                "a few of the careers in demand. Looking to the future of commercialized space travel and exploration, the opportunities are " +
                                "limitless. In the Winter 2010-11 Occupational Outlook Quarterly, the U.S. Bureau of Labor Statistics projects faster than" +
                                " average growth and higher than average salaries in transportation fields through 2018.",

                        "Do you provide assistance to your graduating students in their job search? Do you have any co-ops or internships available?\n\n" +

                        "The Career Services Office at Embry-Riddle provides assistance to those seeking employment opportunities. This office serves as a " +
                                "resource for students and helps them secure employment in their field of specialization. Examples of functions the Career" +
                                " Services Office performs include counseling, resume critiques, and arranging on-campus interviews. This office offers seminars" +
                                " on developing job-search skills, such as how to research a company, what questions to ask in an interview, and more. The annual " +
                                "career fair brings industry leaders to campus to recruit Embry-Riddle students and graduates. The Career Services Office administers" +
                                " a web-based job listing and interview database that matches student and alumni job-seekers with openings in the industry. The Career" +
                                " Services Office is also responsible for coordinating the cooperative education program. The co-op program places qualified students in " +
                                "positions with companies while students are still in school. While working, the student receives academic credit as well as valuable " +
                                "on-the-job training. Many co-op students receive a salary, as well. This experience will give students who have done a co-op an edge " +
                                "over other graduates, and will enhance their resumes.",

                        "Is the university accredited?\n\n" +

                        "Yes, Embry-Riddle is fully accredited.",

                        "How many students are enrolled at the Daytona Beach Campus? What is the student/faculty ratio?" +

                        "The overall undergraduate residential campus student-faculty ratio is 16 to 1, with an average class size of 26.",

                        "What will it cost to attend?\n\n" +

                        "Your cost will vary slightly by academic program. Students pursuing a degree program requiring flight training will have additional " +
                                "costs. You will find, though, that Embry-Riddle is by far one of the most affordable private universities for aviation/aerospace " +
                                "education. Keep in mind, just because a private university costs more than a public state university, it doesn't necessarily mean " +
                                "you will pay more out of your pocket. Schools that cost more are authorized to award higher amounts of financial assistance, so your" +
                                " out-of-pocket cost could be the same, or even less.",

                        "Is financial aid available?\n\n" +

                        "More than $113 million in financial aid is awarded to Embry-Riddle students each year. Financial Aid Office staff members work with you to" +
                                " find the right type of aid for which you may be eligible: federal and state grants, institutional and private scholarships, federal and" +
                                " alternative student loans, and campus jobs. Several convenient payment plans also help spread out the financing of tuition, room and " +
                                "board, and other fee costs.",


                        "Can I fly at Embry-Riddle if I am not enrolled in a flight program?\n\n" +

                        "We can accommodate non-flight majors on a space-availability basis only. Summer is a particularly good time to sign up for flight courses.",

                        "What kind of aircraft and simulators do you have?\n\n" +

                        "Please visit the College of Aviation's Flight Department website to learn about our airline-style flight facilities, state-of-the-art" +
                                " equipped aircraft and our flight training devices (FTDs) and simulation equipment. We're proud to say the quality and number of " +
                                "our FTDs provide a level of on-campus training not available at any other university in the world.",

                        "Will I start flying my first year as an Aeronautical Science major?\n\n" +

                        "Yes. Most students begin flight training sometime during their first academic year. The first flight course typically includes basic" +
                                " aircraft control, radio navigation, air traffic control procedures, multi-crew standard operation procedures, cross-country " +
                                "operations, solo flight, and private pilot written exam (ground school). The flight course a student takes first depends on the " +
                                "licenses they have at the time of enrollment. Academic credit is given to students who start at Embry-Riddle with flight licenses.",

                        "Is tutoring available?\n\n" +

                        "The Student Academic Support Center provides many tools to support student success, including tutoring, advising, seminars, and programs. " +
                                "Free tutoring is available in a variety of subjects. Students who excel in certain areas also tutor fellow students on a nominal fee " +
                                "basis. Additionally, professors post office hours during which students may seek help.",

                        "How does ROTC work?\n\n" +

                        "Both two-and four-year Air Force, Navy and Army ROTC programs are available. You can participate on a voluntary basis or make a service " +
                                "commitment, in which case you may be eligible for significant scholarship benefits. Even if you don\\'t take ROTC as a freshman, you" +
                                " can still participate in the two-year program, provided you contact our ROTC Office by the end of the fall semester of your sophomore" +
                                " year. At the beginning of your junior year, you must decide whether you wish to sign a contract that obligates you to military " +
                                "service after college. (Keep in mind that you do not know what your military job assignment will be until the end of your junior " +
                                "year.) If you elect to sign the military contract, you will take advanced ROTC your junior and senior years. After graduation, you " +
                                "are commissioned a second lieutenant.",

                        "Do you offer housing on campus? Are cars allowed on campus?\n\n" +

                        "Embry-Riddle offers housing on campus. First-year students are required to live on campus. If you choose to bring your car to campus, you" +
                                " are required to register your vehicle and obtain a parking permit from Campus Safety and Security.",

                        "What kind of extracurricular activities do you offer?\n\n" +

                        "Embry-Riddle has a full range of extracurricular activities, including intramural and varsity sports, social and professional fraternities " +
                                "and sororities, clubs, student government, a campus newspaper, and campus radio station.",

                        "Where do students eat on campus?\n\n" +

                        "Eating together with other students can be an important and positive part of the college experience. A comprehensive campus food service program" +
                                " is available to students.",

                        "What are the University's Computer Requirements?\n\n" +

                        "As an applicant and a student, you will need to access the Embry-Riddle Network for Information Exchange (ERNIE). The ERNIE portal gives you " +
                                "access to email, the learning management system, the student system, and other important services. While Embry-Riddle offers an array " +
                                "of on-campus computers for student use in classrooms and labs, many students elect to bring their own computers to campus. These " +
                                "students are encouraged to use a system that meets or exceeds the following recommendations:\n\n" +

                                "\t\tRecommended hardware and software for windows systems:\n\n" +

                                "\t\t\ti5 Quad Core, 3.40Ghz, 6MB w/HD2500 Graphics (equivalent processor speeds may vary by manufacturer and chipset)\n\n" +
                                "\t\t\t8GB RAM or higher\n\n" +
                                "\t\t\t500GB hard drive\n\n" +
                                "\t\t\t1GB video card memory\n\n" +
                                "\t\t\tDVD writable drive\n\n" +
                                "\t\t\t100 base-T Ethernet connection\n\n" +
                                "\t\t\t802.11 b/g/n WiFi network interface card\n\n" +
                                "\t\t\tCurrent version of Window\n\n" +
                                "Note: Computer specifications for each program vary. Contact your advisor for details on technology needs for your program.\n\n" +
                                "Free Software Downloads: Students enrolled at the Daytona Beach campus may download Microsoft products, including Office and" +
                                " antivirus software, at no charge. Upon arrival, students will be granted permission to download these programs."},

                {"When should an application be submitted to Embry-Riddle Aeronautical University?\n\n" +
                        "Applications are accepted up to one year prior to the intended entering semester. All applicants are encouraged to send" +
                        " applications and credentials as early as possible. Early applicants increase their chance of admission to competitive programs " +
                        "and can enhance the probability of securing a residence hall room.",

                        "What are the next steps after I have applied? When will I receive an admissions decision?\n\n" +

                        "You will receive an email that will include your student ID number and a list of the required documents. You will need to create" +
                                " an ERNIE account to check your application status and receive email updates. Once we have received all the required documents, " +
                                "your file will be complete and submitted for review. The review may take up to three weeks. Once your file is reviewed, you will " +
                                "be notified in regards to the outcome. If you are admitted into the university, you will receive instructions on how to get your I-20," +
                                " letter of acceptance, and admissions package mailed to you."}
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
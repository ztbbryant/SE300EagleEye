package com.example.zacharybryant.eagleeyeapp;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
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
 * @author Shawn and Zach
 * @version 1.0
 */
public class ListFrag extends Fragment implements OnMapReadyCallback {

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
                        "Corsair Hall\n\nNo description given.",
                        "North Chiller\n\nNo description given.",
                        "New Residence Hall\n\nThe New Residence Hall opened in January 2017 and is located near the center of campus. This five-story facility offers suite-style living to first-year and upperclass students. Students have the option of a double or private room depending on availability. Each wing of the building has a lounge, study room, and laundry room. Half of the ground floor has been designed for community spaces with pool tables, TVs, study areas, and vending machines.",
                        "Campus Safety\n\nEmbry-Riddle Aeronautical University shares the concern of students and parents regarding safety on our campus. Accordingly, the Campus Safety & Security Department is actively engaged in collaborating with students, parents, employees, and the community to provide a safer University environment and to treat every individual with respect, fairness and compassion. ",
                        "Apollo Hall\n\nDesignated for sophomore, junior, senior, and graduate students. The 256-bed residential suite-style facility is located just a short walking distance of the Eagle Fitness Center and Tine W. Davis Swimming Pool. Four students share a two-bedroom suite, two students per bedroom. The suite includes a split bathroom and a living room/dining room and kitchenette with a full-sized refrigerator and microwave.",
                        "Doolittle Hall\n\nDoolittle Hall is a three-story residence hall typical of traditional university housing and is designated for first-year students. It is located near the center of campus near the Eagle Fitness Center and Tine W. Davis Swimming Pool. Basketball, tennis, and volleyball courts are nearby, as well as picnic tables and barbecue grills.",
                        "Bookstore\n\nShow your Embry-Riddle Aeronautical University pride all year long with our authentic assortment of Embry-Riddle Aeronautical University collegiate apparel - including Embry-Riddle Aeronautical University t-shirts, sweatshirts, hats and more. Join the ranks of your fellow Embry-Riddle Aeronautical University students, alumni and team fanatics by sporting the ultimate Embry-Riddle Aeronautical University game day gear We've got Embry-Riddle Aeronautical University gift ideas covered, with options ranging from drinkware to diploma frames to Embry-Riddle Aeronautical University gift cards. Plus, our selection of textbooks, computers, and supplies will ensure every Embry-Riddle Aeronautical University student is prepared for success.",
                        "Mail Services\n\nWe provide convenience and assistance for all mail & shipping service needs to administration, faculty, http://news.erau.edu/news-briefs/virtual-technology-creates-bridge-for-distance-learning/staff & students ~ We sort & deliver all Incoming mail & packages to campus departments and  student mailboxes and receive & process all Outgoing mail & packages from campus departments in a safe, secure, accurate & timely manner ~ We provide our Counter customers with the most cost effective, reliable and secure method for their Outgoing postal and shipping needs ~ We provide our students with excellent counter services by assisting them with package pickup, mailbox assignments, mail forwarding, replacement keys, tracking requests and general questions.",
                        "Undergraduate Research and Disability Services\n\nPersons with disabilities can and do succeed in their pursuit of higher education.\nThe mission of Disability Support Services is to guide and support institutional compliance with Section 504 of the Rehabilitation Act of 1973 and Title III of the Americans with Disabilities Act to ensure equal access for students with disabilities of the Daytona Beach and Worldwide Campuses.\nThe University acknowledges the confidential nature of students’ disability-related information and records and ascribes to the federal guidelines (Section 503 of the Rehabilitation Act and Title I of the Americans with Disabilities Act) that mandate control of and restrict access to such information. Note: Unless specifically contraindicated by another title, the confidentiality requirements of Title I of the ADA apply to all titles.",
                        "Eagle Fitness Center\n\nThe Eagle Fitness Center is located near the center of the Embry-Riddle Aeronautical University Campus, offering students, faculty, and staff a convenient place to get in a workout between classes or take a study break in the sun beside the Tine W. Davis Swimming Pool.\nThe 12,000-square-foot Fitness Center houses state-of-the-art strength-training equipment from FreeMotion Fitness, Life Fitness & Hammer Strength, and Iron Grip. A cardio deck on the upper level houses treadmills, elliptical machines, stationary bikes, cross trainers, and rowing machines with integrated technology features such as TVs and iPod and USB connections with Internet connectivity.\nGroup fitness classes are held in a 2,500-square-foot group fitness room that overlooks the swimming pool. Classes vary per semester and include yoga, cycling, core training, Les Mills BODYPUMP, BODYCOMBAT, Zumba, and Boot Camp.\nFitness equipment available for use during classes includes steps, stability balls, BOSU balls, resistance bands, and free weights, as well as 20 spinner bikes.",
                        "Student Financial Services\n\nThe Student Financial Services Department strives to provide efficient, courteous and excellent customer service to our students, parents, staff and faculty. We offer a variety of services which are accessible online, or in person. Some of these services include: Billing of Tuition and Fees, Student Refunds, Payment Plan and Payroll Check Cashing.",
                        "Center for Faith and Spirituality\n\nThe Center for Faith and Spirituality and the Chaplain’s Office welcome students, staff, and faculty of all backgrounds, encouraging them in their lives of faith and providing them with opportunities to learn more about their own traditions as well as those of their fellow students and colleagues.\nLocated on the west side of campus near McKay Hall, the Center for Faith and Spirituality has five prayer and meditation rooms which are open to everyone from 6:00 a.m. until 10:00 p.m. The work of the Center includes: Helping students, staff, and faculty find a spiritual home on campus, Promoting healthy and respectful interfaith dialogue, Developing opportunities to live out one’s faith through service to the community, and Providing a home for our religious clubs and organizations.",
                        "Racquetball Court\n\nThe Racquetball Complex is located near the center of the Embry-Riddle Aeronautical University Campus, across from the Eagle Fitness Center and Tine W. Davis Swimming Pool. The complex features four indoor, air-conditioned racquetball/handball courts, two with spectator viewing. The complex is also used for intramural Wallyball.\nCourts are open to students, faculty, staff, and accompanied guests at no charge. Racquetball reservations may be made at the front desk of the Eagle Fitness Center or by calling 386-323-8860. Racquetball rackets can be checked out for free at the Control Desk in the ICI Center, located across from the Campus entrance on Clyde Morris Boulevard. Two courts on the west side of the Racquetball Complex have been renovated to house martial arts for club sports.",
                        "Student Support Services, Global Engagement, Honors Center\n\nThe Embry-Riddle Honors Program is highly selective, offering its student members enriched educational experiences. Emphasizing Honors course work in General Education and in the majors, the program involves selected faculty who develop innovative courses and establish mentoring relationships with students. The program is designed to attract and retain top students and to develop their communicative, analytical, critical, and research skills, nurturing a love of life-long learning, leadership and service.  \nAt the Office of Global Engagement, we are deeply passionate about helping students experience the world. Studying Abroad is a once-in-a-lifetime opportunity to not only visit another country, but to truly experience its culture. \nBefore picking the right program for you, here are some highlights of studying abroad: \nOpportunities around the globe \nSummer, semester, or yearlong programs \nTake courses that apply toward your major\nStudy in English or the native language \nJoin the Travelers Club to experience other cultures on campus \nDon't be a tourist, live abroad!",
                        "Advanced Flight Sim Center\n\nNew-generation Flight Training Devices (FTDs) are increasingly valuable in today’s complex flight training environment. Simulators lower cost, improve safety, and have minimal impact on the environment. \nTo meet this growing need, Embry-Riddle continues to expand on the capabilities of its Advanced Flight Simulation Center. The center is home to an array of FAA-qualified Level 6 FTDs, as well as one FAA Level D Full-Flight Simulator (FFS) and a crosswind trainer. \nTraining aids found in our Advanced Flight Simulation Center enable our students to learn aircraft performance, practice Cockpit Resource Management (CRM), experience aerodynamic effects, and perform a variety of flight maneuvers in a safe, effective, and comfortable environment. \nWith 11 state-of-the-art Level 6 Flight Training Devices, a Regional Jet Level D FFS and a crosswind trainer, no other collegiate flight training program can match the capabilities and variety of systems available to our students. Located within Embry-Riddle’s Advanced Flight Simulation Center, our simulator/trainer “fleet” currently includes: \nFrasca Cessna 172S Flight Training Device (FTD) \nFrasca Diamond DA42 L-360 Flight Training Device (FTD) \nFrasca Canadair CRJ-200 FAA Level 6 Flight Training Device (FTD) \nCRJ-200 Level D Full-Flight Simulator (FFS) \nETC GAT-II Spatial Disorientation Training Device \nRedbird Xwind SE Crosswind Trainer",
                        "Fleet Maintenance Hangar\n\nThe responsibility of keeping Embry-Riddle Daytona Beach’s fleet of training aircraft in top mechanical form falls on the university’s team of FAA Certified Airframe and Powerplant (A&P) technicians. \nWorking in the Fleet Maintenance Center, our A&Ps perform all scheduled and unscheduled inspections and maintenance on our fleet. Students in the Aviation Maintenance Science degree program will have the opportunity to work alongside these experienced technicians to gain invaluable experience.",
                        "Flight Operations\n\nLocated adjacent to the ERAU Flight Line, the Flight Operations Center is the central point for all aircraft operations and student pre- and post-flight training activities with their instructors. \nThe Flight Ops Center boasts a state-of-the-art dispatch desk, real-time weather monitoring equipment, computer stations for flight planning, private oral rooms, and many other modern aviation conveniences designed to give our students everything they need for their pre-flight planning and preparation. Embry-Riddle Daytona Beach flight operations run seven days a week, 50 weeks a year (we only shut down for Fall/Spring semester breaks). The Flight Operations Center is open from 6 a.m. to midnight so our flight students have access to the airplane they need when they need it. \nThe Flight Ops Center can also track and record each aircraft’s flight. That information is often used during the student’s debriefing or can be accessed by the student to help reinforce the value of each lesson.",
                        "College of Business\n\nWe provide a world-class Business education in Aviation/Aerospace with state-of-the-art curricula at both the graduate and undergraduate levels.  This education has proven to be successful in preparing our graduates to become leaders in the Aviation and Business industries. Our “Business of Flight®” approach is a strategic vision that will place our graduates on the leading edge of business leadership and innovation.",
                        "Willie Miller Instructional Center\n\nNo description given.",
                        "Aviation Maintenance Services\n\nStudents who thrive at the cutting edge of technology and want to take on the challenge of ensuring the integrity of aircraft carrying pilots, passengers, and precious cargo are right at home in the Department of Aviation Maintenance Science. The department offers several programs, including a Bachelor of Science in Aviation Maintenance, an Associate of Science in Aviation Maintenance, and a minor in Avionics Line Maintenance. \nAt the heart of each program lies the FAA Airframe and Powerplant (A&P) mechanic’s certification program. These highly hands-on degree A.S. and B.S. programs each include 47 credit hours of airframe and powerplant technical courses, plus the specific courses that will prepare graduates for meeting a growing demand for technicians to work for large aircraft manufacturers, airlines or commercial companies with small fleets, or contractors who overhaul and rebuild aircraft. Some also enter the growing field of avionics, working on unmanned aerial vehicles. \nThese students get plenty of real-life experience in the department’s Emil Buehler Aviation Maintenance Science Building. The 48,000-square-foot building is entirely dedicated to the training of airframe and powerplant technicians, with two of its three floors dedicated to labs. The department also has a hangar, turbine engine test cells, and several aircraft.",
                        "College of Aviation\n\nThe College of Aviation degree programs meet the needs of aviation students of the 21st century and set the standard in high-level quality education demanded by the aviation profession. Our 100-plus faculty members and the courses in the COA's five departments — Aeronautical Science, Applied Aviation Science, Aviation Maintenance Science, Flight, and Doctoral and Graduate Studies — will prepare graduates to assume leadership roles in the global aviation industry and related fields.",
                        "AMS Hangar\n\nThe Aviation Maintenance Science (AMS) Hangar is the focal point for a wide variety of hands-on projects that occur on the department’s ramp-utilizing aircraft and powerplant training aides as part of the Aviation Maintenance Science degree program. The AMS Hangar is located adjacent to the Emil Buehler Aviation Maintenance Sciences Building. It houses state-of-the-art functional training aides and a variety of operating aircraft for test and operations. Students will use both the aircraft and hands-on trainers to gain valuable experience in the diverse sets of specialized skills needed to earn their FAA Airframe and Powerplant certificate.",
                        "Test Cell 1\n\nNo description given.",
                        "Engine Test Cells\n\nNo description given.",
                        "Canaveral Hall\n\nNo description given.",
                        "College of Arts and Sciences\n\nThe College of Arts & Sciences is a diverse learning community comprising five academic departments — Human Factors & Systems, Humanities & Communication, Mathematics, Physical Sciences, and Security Studies & International Affairs. The ever-evolving academic programs, state-of-the-art facilities, and bright, enthusiastic students are all part of an outstanding learning environment in COAS. Our faculty — more than 160 strong — are leaders in their fields and consistently recognized for outstanding research and teaching.",
                        "Wellness Center - Clinic\n\nHealth Services is available to currently registered students of the Daytona Beach campus. We have a full-time team of five registered nurses available on a walk-in basis. A Physician Assistant, and Nurse Practitioner are available by appointment Monday through Friday, with limited hours on Sundays during the fall and spring semesters. A university physician is available from 9 a.m. to 4:30 p.m. on Mondays and Wednesdays by appointment. \n Health Services is supported by registration fees, so students can be seen and treated free of charge. There is no fee to be treated at Health Services, however if services outside of the University are needed, such as lab services, the student would be responsible. Embry-Riddle Aeronautical University's Health Services Department is located in the HDS Wellness Center in Building 500, across from Doolittle Hall.",
                        "Wellness Center - Counseling Center\n\nThe mission of the Counseling Center is to enhance the psychological well-being of Embry-Riddle students. We believe that good mental health is a key part of overall health, and good health is essential to academic success. We support students' academic experience by providing brief mental health counseling and consultation services that help them identify barriers, improve coping, and achieve personal and academic goals. \n College life is challenging and can be stressful. At some point during the college years, many students feel anxious, depressed, uncertain, confused, or overwhelmed. They may seek help from friends, family members, significant others, but in some cases, help from a trained professional is beneficial. The Counseling Center provides a calm, friendly, and supportive environment for students to address any issue or concern. \n Counseling is confidential and offered free of charge. All counseling contacts are strictly confidential in accordance with Florida state privacy laws. Counseling records are not available to anyone, either on or off campus, without the student's specific written permission. The only time confidentiality will be broken is in the case of a life threatening situation: when a student is at risk of harming him or herself or someone else, or when there is reason to believe a child, elderly person, or disabled person is being abused. Confidentiality may also be broken if a court order mandates that records be released. NOTE: Counseling notes are not part of a student's University records.",
                        "ROTC Center\n\nReserve Officer Training Corps: ROTC is a four-year college elective program that trains students to become Air Force, Army, Navy, or Marine officers. Air Force, Army, and Navy ROTC programs offer you a chance to develop skills for success needed in both the military and civilian worlds, such as confidence to lead, motivation, strategic planning, and the ability to make decisions. \n Scholarships: Many of our students enroll with ROTC Scholarships, which may cover all or part of tuition, fees, and books, along with a monthly allowance. Some ROTC Scholarships are awarded to students after completion of their first year. Explore the sections below to learn more about what each branch offers.",
                        "Print Shop\n\nNo description given.",
                        "Veteran's Affairs\n\nOur mission is to provide, facilitate, or coordinate programs to meet the needs of veterans, service members, dependents, and survivors of veterans in order to ease the transition to college life and fulfill their educational goals. \n The office is staffed with qualified counselors who provide a broad range of services; provide general and specific information about all benefits available to men and women who are serving or who have served in the armed forces; report enrollment information to authorize appropriate allowances; provide other advisory counseling and referral services; assist in resolving problems that may arise in the student's relations with the University and/or the U.S. Department of Veterans Affairs (VA); foster peer connections; and coordinate university and community support.",
                        "Eagle Alumni Center\n\nNo description given.",
                        "Crotty Tennis Complex\n\nThe Ambassador E. William Crotty Tennis Complex is located north of the Sliwa Stadium and the ICI Center. This hard court features nine lighted tennis courts with an electronic scoreboard that can show set scores for six matches. Each court has bleachers for spectator seating and a newly installed shade structure for spectators on courts 1-6. \n The Crotty Tennis Complex is home to the Embry-Riddle Aeronautical University men’s and women’s tennis teams and hosts many college and amateur tennis tournaments. Courts 1-6 are used for match play and team practices. Courts 7, 8, and 9 are available to students, faculty, and staff on a first-come, first-served basis — except during varsity matches and practices (usually held between 2 p.m. and 7 p.m.). Lights automatically turn off at 11 p.m.",
                        "Telescope\n\nNo description given.",
                        "Track and Field Concession\n\nNo description given.",
                        "Track and Field Bleachers\n\nNo description given.",
                        "ROTC Obstacle Course\n\nNo description given.",
                        "Multipurpose Artificial Turf Field\n\nThe Multipurpose Turf Field is a regulation field for soccer, lacrosse, and intramural sports. The lighted field features a Daktronics electronic scoreboard and has a standard soccer playing area of 120x70 yards. The field is used by the University’s sports clubs and organizations, along with the ROTC. Reservations for this field can be made at the Department of Intramural and Recreational Sports in the ICI Center.",
                        "Artificial Turf Softball Field\n\nThis field was built to accommodate Embry-Riddle’s growing intramural softball program, which has about 25 teams competing. While built primarily as a regulation-sized softball field, it is large enough for flag football and soccer and features dugouts and an electronic scoreboard. The field is often used for kickball games and Ultimate Frisbee practice, and it is used by the radio-controlled model airplane club to fly their planes. Students, faculty, and staff can reserve this field by contacting the Department of Intramural and Recreational Sports in the ICI Center.",
                        "Baseball/Softball Clubhouse\n\nNo description given.",
                        "JPR Student and Admissions Visitor Center\n\nNo description given.",
                        "ICI Center\n\nThe ICI Center houses Embry-Riddle Aeronautical University’s Department of Athletics and the Department of Intramural and Recreational Sports. Located on Clyde Morris Boulevard directly across from the campus main entrance, the 50,000-square-foot facility is the centerpiece for campus recreation, athletics, and University functions such as graduation ceremonies, guest speakers, and concerts. \n The facility is home to Embry-Riddle’s men’s and women’s basketball and women’s volleyball teams, as well as intramural activities, club sports, and cheerleading. The multipurpose gymnasium features three basketball courts, three volleyball courts, and three badminton courts. \n The facility boasts a state-of-the-art sound system, a sports medicine center, and the Fletcher Fitness Center, which is equipped with strength training and cardiovascular equipment. The ICI Center also houses the Department of Athletics and the Department of Intramural and Recreational Sports. \n Surrounding the ICI Center are outdoor sports facilities, including baseball and soccer stadiums, tennis courts, lighted-lane running track, and multiple lighted fields for soccer, softball, lacrosse, and more. \n Students, faculty, and staff can check out sports and recreational equipment at no charge from the ICI Center front desk, and the facility is available for open recreation.",
                        "Jim W. Henderson Administration and Welcome Center\n\nNo description given.",
                        "Sliwa Stadium\n\nThe state-of-the-art facility features a field, which can be fully lit and a 36-foot Daktronics scoreboard. The scoreboard is located in the north corner of the field was installed prior to the 2016 season. \n The ballpark has dimensions of 330 feet down the left field line, 375 feet in the left-center field gap, 400 feet to straight-away center, 375 feet to right-center field, and 330 feet down the right field line. \n In the summer of 2015, Embry-Riddle celebrated the opening of the brand new ERAU Baseball/Softball Clubhouse along with the completion of the renovations at the ERAU Softball Stadium. \n Located between Sliwa Stadium and the ERAU Softball Stadium, the clubhouse primarily serves to support both the baseball and softball programs, with additional amenities for Sports Medicine as well as the athletics department as a whole. \n The 12,000-square foot facility includes offices for the baseball and softball coaching staffs along with locker rooms for both teams, as well as two officials’ locker rooms. In addition to the offices and locker rooms, the facility includes an athletic training room, a multipurpose conference room, laundry room and public restrooms.",
                        "Soccer - Ticket Concession\n\nNo description given.",
                        "Soccer Field Bleachers\n\nNo description given.",
                        "Student Union (Under Construction)\n\nNo description given.",
                        "Lehman College of Engineering\n\nThe administrative offices and five academic departments of the College of Engineering reside in the Lehman Engineering and Technology Center. Additional educational and project labs for the Department of Mechanical Engineering are in the nearby M-building. \n In addition to traditional classrooms and laboratories for instruction and research, the Lehman Center offers computing resources and open workspaces facilitating collaborative development for interdisciplinary student teams. Hands-on learning and learning-by-doing are enabled through a variety of student-accessible laboratories with the tools for a complete engineering design-build-test project cycle. A fully functional Distance Learning and Lecture Capture Studio, for creating high-quality and effective learning modules to enhance teaching at all levels, is being installed on the third floor. \n Research facilities for full-sized aircraft flight test and development are located on the south side of the Daytona Beach airport at the Eagle Flight Research Center. An additional research center, the Next-Generation ERAU Advanced Research (NEAR) Laboratory, is located on the airport property adjacent to the primary campus. Undergraduate and graduate students collaborate with faculty at both of these research centers dedicated to solving real problems brought forward by industry.",
                        "South Chiller\n\nNo description given.",
                        "Admissions Operations\n\nWith access to the world’s most comprehensive collection of academic programs focusing on aviation, aerospace, and security, a degree from Embry-Riddle will launch your career in a successful direction. Your first step to getting there is submitting your application. Our Admissions counselors are here to help you navigate the process of applying to and enrolling at Embry-Riddle, so don’t hesitate to call, email, or chat.",
                        "Engineering Special Projects and Labs\n\nThe space is designed to encourage creativity and collaboration among engineering students and design teams, and provides students with an open work space, tools for manufacturing, and finishing. \n The ESPC is available to all undergraduate engineering students involved in an ERAU engineering club and personal projects. The lab is run by the Engineering Fundamentals Program Coordinator, and is monitored by a graduate lab assistant (GLA); all use of the ESPC must be approved by one of these individuals. The use of any power equipment is only permitted after students have received safety training on the use of the equipment, including the Standard Operating Procedures, after which, the students can operate any equipment while under the supervision of the GLA. \n This laboratory is the primary home of several engineering design teams and student organizations, including the student chapter of the American Institute of Aeronautics and Astronautics (AIAA)’s Design-Build-Fly and the Experimental Rocket Propulsion Lab (ERPL).",
                        "Clyde Morris Multipurpose Field\n\nThe Clyde Morris Multipurpose Fields are located at the south end of the University Sports Complex. This lighted area is the primary location for intercollegiate soccer team practices but is available for other uses upon request.",
                        "Student Village (Adams, Wood, Tallman Commons)\n\nBoth located in the Student Village on campus, Wood and Adams Hall are five-story buildings designated for first-year students. Each student room is furnished with two single beds with extra-long mattresses, a dresser, a desk and chair, a lockable box or drawer, and a small refrigerator and microwave.",
                        "Student Village (O'Connor, Stimpson)\n\nO’Connor Hall is located in the Student Village on campus, and offers apartment-style living for sophomores, juniors, seniors, and graduate students. Students in O’Connor Hall share four-bedroom apartments, with two students per bedroom. Each apartment includes two bathrooms,  living/dining room, and a kitchenette with a microwave and full-size refrigerator.  \nAlso located in the Student Village on Campus, Stimpson Hall features efficiency-style apartment living, primarily for sophomores, juniors, seniors, and graduate students. Each apartment has a bedroom, private bathroom,  separate study/living room, and a kitchen with a stove, microwave, and full-size refrigerator.",
                        "Richard Petty Multipurpose West Field\n\nThis field is located west of Richard Petty Boulevard, across from the Student Village. The lighted, Bermuda grass facility is large enough to host three intramural games simultaneously, and each year it is used by 36-42 teams for fall flag football and spring soccer. It is the primary site for intramural flag football and soccer and has been used for Ultimate Frisbee tournaments and sorority sporting events. The Department of Intramural and Recreational Sports accepts requests for a limited number of special events.",
                        "Richard Petty Multipurpose East Field\n\nThis field, located just north of the Student Village, is an open recreation field used primarily by individuals and groups for soccer, flag football, open recreation, or intramural practices, with reservations though the Department of Intramural and Recreational Sports taking priority. It is the main playing space for rugby and cricket and is used by the Navy and Air Force for ROTC leadership labs."
                },
                {
                        "Einstein Bros. Bagels\n\nEinstein Bros®, known for their bagels, cream cheese schmears and coffee heritage, will introduce an exceptional morning alternative that's fast, hot, and packed with flavor. But it doesn't stop there. \nEinstein Bros. also offers bistro salads, soups and sandwiches, which  will be a great lunch attraction, while oversized brownies, cookies, pound cake and muffins will make for delicious anytime snacking.\n Regular Hours of Operations\n Monday - Friday: 7:00 a.m. - 1:00 a.m.\n Saturday - Sunday: 12:00 p.m. - 12:00 a.m.\n",
                        "Freshens Smoothie Company\n\nEnergy zone gives customers the variety they want with great tasting yogurt, fruit juice and orange smoothies. Even more nutritious and better than before, Freshens offers low calorie smoothies with over 20 fresh blended flavors. Kosher certified and fully energized.\n Regular Hours of Operations\n Monday - Friday: 10:00 a.m. - 5:00 p.m.\n Saturday - Sunday: Closed\n",
                        "Chick-fil-A\n\nChick-fil-A® is a proven hit on the campus. Whether for breakfast, lunch or dinner, patrons can always depend on Chick-fil-A to deliver high-quality taste in a sandwich or salad\nBreakfast served until 10:30am:\n Monday - Tuesday, Thursday: 7:30 a.m. - 9:00 p.m.\n Friday: 7:30 a.m. - 6:00 p.m.\n Saturday - Sunday: Closed\n",
                        "Pacific Traders\n\nFlavors of the Orient prepared before your eyes. You pick one delicious entree like Sweet and Sour Chicken or Beef and Broccoli plus your choice of our steamed or fried rice. With 'just prepared' cooking techniques and authentic flavor, you won’t want to miss.\n Regular Hours of Operations\n Monday - Friday: 10:00 a.m. - 5:00 p.m.\n Saturday - Sunday: Closed\n",
                        "Salsa at the Landing Strip\n\nBurritos, nachos, tacos and quesadillas.  House made salsas prepared fresh daily with authentic Mexican recipes.\n Regular Hours of Operations\n Monday - Friday: 10:00 a.m. - 5:00 p.m.\n Saturday - Sunday: Closed\n",
                        "Flight Cafe\n\nCafé is a popular stop wherever it travels, providing quality prepackaged foods as well as complimentary hot and cold beverages.\n Regular Hours of Operations\n Breakfast served until 10:45am:\n Monday - Friday: 7:15 a.m. - 2:30 p.m.\n Saturday - Sunday: Closed\n",
                        "Propeller's Food Truck\n\nBest Burger on Campus Homemade hamburgers, grilled chicken Caesar salads, fried potato wedges, fried chicken... getting hungry yet? Go to Propellers and find out why they call it 'the Best Burger on Campus.'\n Breakfast served until 10am:\n Monday - Friday: 7:00 a.m. - 3:30 p.m.\n Saturday  -Sunday: Closed\n",
                        "UC Food Court (Deli, Grill, Hotline\n\nERAU Classics Line at The Food Court\n A hot buffet of homemade favorites offered daily for breakfast, lunch and dinner.\n Simply to Go\n'Simply To Go' is a comprehensive convenient meal solutions program targeted at customers that need a food fix in a hurry. The grab and go menu consists of sandwiches, salads, parfaits, fresh fruit and desserts.\n Mega Salad Bar\n Made fresh daily with over 50 items including tuna salad, potato salad and spinach with a variety of dressings including low fat options. Three soups made fresh daily including homemade chili and a vegetarian option.\n\n",
                        "The Food Court Deli\n\nLooking for the best sandwich on Campus? This is the place, bring a big appetite!\n",
                        "Sicilian Grill\n\nOur grill features omelets made to order, gourmet burgers, grilled cheese, french fries,  the best all beef hot dogs on campus, veggie burgers and daily specials.  Sicilian style pizza, stromboli's, calzones, casserettes, and your favorite grilled items prepared the most authentic Italian way. \n Breakfast until 10:45am:\n Monday - Thursday: 7:15 a.m. - 9:00 p.m.\n Friday: 7:15 a.m. - 7:00 p.m.\n Brunch until 2pm:\n Saturday - Sunday: 9:00 a.m. - 7:00 p.m.\n",
                        "Starbucks\n\nStarbucks purchases and roasts high-quality whole bean coffees and sells them along with fresh, rich-brewed, Italian style espresso beverages, a variety of pastries and confections, and coffee-related accessories and equipment. Starbucks also offers a variety of retail items.\n Regular Hours of Operations\n Monday - Thursday: 7:00 a.m. - 9:00 p.m.\n Friday: 7:00 a.m. - 6:00 p.m.\n Saturday: 2:00 p.m. - 6:00 p.m.\n Sunday: 2:00 p.m. - 9:00 p.m.\n",
                        "Salad Toss\n\nOver 30 freshly prepared items, vegetables, lettuce and other toppings for you to choose from.  Tossed in front of you with your favorite dressing.  You can add grilled chicken, shrimp, grilled steak and our vegetarian friendly option of tofu if you choose...it's all up to you!\n Regular Hours of Operations\n Monday - Friday: 10:00 a.m. - 5:00 p.m.\n Saturday - Sunday: Closed\n"
                },
                {
                        "Honors Program\n\nThe Embry-Riddle Honors Program is highly selective, offering its student members enriched educational experiences. Emphasizing Honors course work in General Education and in the majors, the program involves selected faculty who develop innovative courses and establish mentoring relationships with students. The program is designed to attract and retain top students and to develop their communicative, analytical, critical, and research skills, nurturing a love of life-long learning, leadership and service.",
                        "Office of Global Engagement\n\nHere at the Office of Global Engagement, we are deeply passionate about helping students experience the world. Studying Abroad is a once-in-a-lifetime opportunity to not only visit another country, but to truly experience its culture. Before picking the right program for you, here are some highlights of studying abroad: Opportunities around the globe, Summer, semester, or yearlong programs, Take courses that apply toward your major, Study in English or the native language, Join the Travelers Club to experience other cultures on campus, Don't be a tourist, live abroad",
                        "Fraternity and Sorority Life Office\n\nFraternity & Sorority Life at Embry-Riddle Aeronautical University is part of the Student Engagement and Student Union Office within the Division of Student Affairs. \n Our community consists of fourteen national fraternities and sororities that are supported by three governing councils.",
                        "WIKD\n\nThe WIKD 102.5 FM is a college radio station owned by Embry-Riddle Aeronautical University. It is Daytona Beach’s only free format radio station playing top 40, alt rock, electronic music, and indie.\n The station is completely student run and funded by students allowing it to be commercial free. In 2013, it launched on iHeart Radio becoming one of the very few college stations to stream on the nation’s most popular streaming app.\n For more information, visit our at www.wikd1025.com. Our meetings are every Wednesday at 7 p.m. in COAS 206.",
                        "The Avion\n\nFounded in the spring of 1969 under the editorship of Ms. Linda Colgan, The Avion Newspaper has a legacy of over 4 decades in the aviation and aerospace industry. Our reporters and photographers have been covering events since the Apollo program and continue to cover routine launches and major events such as advancements in the Orion/SLS program. With connections to NASA, Cape Canaveral Air Force Station, Daytona International Speedway, multiple aviation and air show authorities, and many departments on campus, The Avion provides coverage of all things Embry-Riddle students love.\n Interested in contributing to The Avion Newspaper? Check out our “Join” page to found out how!\n The Avion Newspaper is a division of the Student Government Association and receives all benefits entitled to it under its affiliation.",
                        "Orientation and Family Relations\n\nThrough university-wide collaboration, Orientation programming is designed to set university expectations and guidelines up front, while providing students information on valuable resources, services, support and social networking opportunities that will assist them through their time at ERAU. \n Family Relations serves as a partnership between a student’s support network and the university. The Department of Student Activities & Campus Events provides programs and services designed to enhance the support and retention of students by meeting the educational, informational and involvement needs of their parents, families and guardians.",
                        "Touch-N-Go Productions\n\nTouch-N-Go Productions is the student-run entertainment division of the Student Government Association. As a division of the SGA, we strive toward bringing many different forms of entertainment to campus including magicians, hypnotists, comedians, and more. Touch-N-Go is also pleased to offer support to student organizations and faculty departments on campus. Our organization is comprised of hard working students that constantly work to provide Embry-Riddle with top-of-the-line entertainment. \n Several artists that Touch-N-Go Productions have brought to Embry-Riddle in the past are:\n Gabriel Iglesias, Theory of a Deadman, Stephen Lynch, Daniel Tosh, Bowling for Soup, Carlos Mencia, Bo Burnham, Third Eye Blind, All American Rejects, Lifehouse, and Augustana",
                        "Student Engagement\n\nThe Department of Student Engagement and Student Union offers educational, cultural, intellectual, recreational, and spiritual programming, as well as opportunities for leadership development, self-governance, and campus entertainment. \n Our department serves the students of ERAU by providing unique co-curricular opportunities, resources, and services to maximize their educational experiences to allow for holistic growth and development. We believe that the involved student demonstrates academic success, is connection to the University through community, and is prepared to obtain their real-world goals. \n Within the department, there are many different offices in which students can be involved. These include: Campus Activities, Civic Engagement/The Volunteer Network, Leadership Development, Club Sports, Fraternity & Sorority Life, International Student Programming, Orientation, Student Government Association, Student Organizations, Student Union.",
                        "Student Government Association\n\nThe Student Government Association is committed to serving the ERAU student body through a variety of projects and services. These projects and services are carried out and/or provided by one or more of the following: \n SGA Branches: Student Finance Board, Student Representative Board, Student Court \n SGA Divisions: The Avion (campus newspaper), WIKD 102.5 FM (campus radio station), Touch-N-Go Productions (student entertainment board) \n You can be a part of the SGA through several ways. First-year students are encouraged to join First Year Initiative. Any student can serve on an SGA committee as a Member-At-Large. All students are encouraged to join one of the SGA Divisions. \n For those students interested in holding an elected position, the SGA elections are held during the spring semester. Students can run for positions on the SGA Executive Board, the Student Representative Board, and the Student Finance Board. \n The SGA is always interested in hearing your opinion and can often help you find solutions to campus challenges. Feel free to submit a suggestion in any of the yellow 'Campus Suggestion' boxes or come by the Student Representative Board meetings held on Tuesdays at 12:45 p.m. in the IC Auditorium during the fall and spring semesters.",
                        "Office of Undergraduate Research (IGNITE)\n\nNo description given.",
                        "Campus Safety and Security\n\nEmbry-Riddle Aeronautical University shares the concern of students and parents regarding safety on our campus. Accordingly, the Campus Safety & Security Department is actively engaged in collaborating with students, parents, employees, and the community to provide a safer University environment and to treat every individual with respect, fairness and compassion. \n While no campus can guarantee the complete safety of its community, we can work together to provide the safest possible environment. We encourage all University community members to start taking responsibility for their own safety and security. By accepting this responsibility, members of the University community assist in maintaining a safer and more secure academic environment. \n The University complies with the federally mandated Jeanne Clery Disclosure of Campus Security Police and Campus Statistics Act, state requirements for an Annual Assessment of Physical Plant Safety and the three-year compilation of annual crime statistics. A report is sent to the State Department of Education and the U.S. Department of Education. Crime Statistics are compiled from Campus Safety & Security incident reports and monitoring with Daytona Beach Police Department. The statistics are prepared on a calendar year basis and then used to produce the tables found in this report. \n A copy of this crime statistics report can be obtained in the Campus Safety & Security Administrative Office.",
                        "Financial Aid\n\nWhen it comes to educational funding, you have options. You don’t have to let tuition fees deter you from pursuing your goals or living your dreams. A degree from Embry-Riddle Aeronautical University is more than a piece of paper. It is an investment into your future. Our Financial Aid representatives are here to help you and your family leverage the variety of scholarships, loans, and financial aid programs designed to fund that investment.",
                        "Student Financial Services (Bursar's Office)\n\nThe Student Financial Services Department (formerly Bursar Department) strives to provide efficient, courteous and excellent customer service to our students, parents, staff and faculty. We offer a variety of services which are accessible online, or in person. Some of these services include: Billing of Tuition and Fees , Student Refunds, Payment Plan, Payroll Check Cashing.",
                        "Student Employment\n\nThe Student Employment Office provides assistance to students seeking part-time employment on or off campus. On-campus employment is available to students regardless of financial need. Working on or off campus not only gives students more financial support, but also helps them develop self-confidence, gain valuable employment and credit references, establish a work record, and acquire useful skills in time management, financial planning, and communication. Once students are registered at the Daytona Beach Campus they may seek employment by visiting our office or by viewing all available positions via our online system. Students must provide original documentation to prove identity and employment eligibility prior to employment.",
                        "Mail Center\n\nWe provide convenience and assistance for all mail & shipping service needs to administration, faculty, staff & students ~ We sort & deliver all Incoming mail & packages to campus departments and student mailboxes and receive & process all Outgoing mail & packages from campus departments in a safe, secure, accurate & timely manner ~ We provide our Counter customers with the most cost effective, reliable and secure method for their Outgoing postal and shipping needs ~ We provide our students with excellent counter services by assisting them with package pickup, mailbox assignments, mail forwarding, replacement keys, tracking requests and general questions. \n The Mail Center Staff is a committed and dedicated group and would like to thank you for allowing us to provide you with your postal & shipping needs.........",
                        "Housing and Residence Life\n\nThe Department of Housing & Residence Life at Embry-Riddle Aeronautical University Daytona Beach is committed to supporting students' personal and professional growth as part of their residential experience. We create positive, safe, and inclusive residence hall communities that promote a culture of academic achievement. \n Living on campus is an exciting opportunity for Embry-Riddle students. All first-year students live on campus with a lot of sophomores, juniors, and seniors. Our seven residence halls and one off-campus housing complex are a great way to make positive and lasting friendships. In the words of a student of Class 2016, 'Without a doubt, living on campus is my favorite thing about being in college.' \n Living with other students in the residence halls can also be an important part of your academic success at Embry-Riddle. It's a fact that students who live in campus housing have a higher grade point average and are more likely to be successful overall in college than students who live off campus. \n Housing & Residence Life staff strive to support a positive living and learning environment through programs and events in the residence halls. Living on campus will keep you involved with the university and connected to new friends.",
                        "Counseling Center\n\nThe mission of the Counseling Center is to enhance the psychological well-being of Embry-Riddle students. We believe that good mental health is a key part of overall health, and good health is essential to academic success. We support students' academic experience by providing brief mental health counseling and consultation services that help them identify barriers, improve coping, and achieve personal and academic goals. \n College life is challenging and can be stressful. At some point during the college years, many students feel anxious, depressed, uncertain, confused, or overwhelmed. They may seek help from friends, family members, significant others, but in some cases, help from a trained professional is beneficial. The Counseling Center provides a calm, friendly, and supportive environment for students to address any issue or concern. \n Counseling is confidential and offered free of charge. All counseling contacts are strictly confidential in accordance with Florida state privacy laws. Counseling records are not available to anyone, either on or off campus, without the student's specific written permission. The only time confidentiality will be broken is in the case of a life threatening situation: when a student is at risk of harming him or herself or someone else, or when there is reason to believe a child, elderly person, or disabled person is being abused. Confidentiality may also be broken if a court order mandates that records be released. NOTE: Counseling notes are not part of a student's University records.",
                        "Dean of Students\n\nThe Dean of Students Office has responsibility for developing and implementing a comprehensive student development program to strengthen students experience outside the classroom. Areas reporting to the Dean of Students Office are: Student Activities & Campus Events, University Student Center, MyVets, Counseling Center, Health Services, Disability Services, and Conference Services. Three Pillars form the foundation for our work: Safe Eagle, Honor Code, and Soar. Using the foundation of these pillars we encourage empowerment through leadership, civility, integrity, respect and inclusion. “Students First” is our motto. That belief is our guide as we provide advocacy, guidance and resources for all Embry-Riddle Students. \n Campus Awareness, Response, and Evaluation Team (C.A.R.E) \n The mission of the C.A.R.E. Team is to collaboratively address and respond to issues concerning the health, safety, and well-being of ERAU students. The C.A.R.E. Team meets regularly to identify, assess, and respond to students of concern and/or potential threats to the campus community. Toward that end, Embry-Riddle should support a culture of reporting “see something, say something” and utilize the C.A.R.E. Team appropriately for a safer community.",
                        "Office of Records and Registration\n\nThe Office of Records and Registration provides academic support for students from matriculation through graduation and beyond. Our team ensures accuracy and confidentiality of academic records while providing appropriate access to students, faculty, and other internal and external constituents. We strive for continuous improvement by embracing emerging technologies and best practices in enrollment, records maintenance, reporting, and policy interpretation and implementation. Our focus on exemplary student service contributes to the success of our students in their academic pursuits and in their future careers as global citizens and leaders in the aerospace industry. \n Spring & Fall hours: 8:00am to 4:00pm \n Summer hours: 8:00am to 5:00pm",
                        "EAGLECard\n\nYour EAGLE Card is your Embry-Riddle Aeronautical University identification card, access card and debit card. It's an easy and safe way to make purchases and use services on campus and at select off-campus merchants. Students can manage their EAGLEcard accounts in Blackboard. Just log in to ERNIE, click on ‘Go to Blackboard home’, and select the EAGLEcard tab. Guest Deposits can be made by going to Guest Deposit - ERAU EAGLEcard. \n IT'S AN ID CARD \n Your EAGLEcard will identify you as an affiliate of the University, whether as a student, visitor, contractor or faculty/staff member. The ID will be required to be presented whenever you attend any school activity, library, events, games, voting, or department for services. \n IT'S AN ACCESS CARD \n If you reside in on-campus University Housing, your will proEAGLEcard vide you access to the residence halls. Also, certain labs and buildings require the use of an EAGLEcard for entry. \n IT'S A DEBIT CARD \n Your EAGLEcard has several debit accounts on it and they are managed by the University. It has EAGLE Dollars, Meal Plan, and Riddle Bucks all on one Card. Debit Card purchases can be made at: the University Bookstore, vending machines, copiers, Food Courts, laundry machines, the Einstein Brothers Bagel Shop in the Student Village, the Flight Payment Station and at select off-campus merchants.",
                        "Career Services\n\nExplore all the ways in which you can develop your career insight, co-op/internship prowess and job search expertise. \n One-on-one career advising: schedule an appointment to meet your Program Manager to collaborate on your career development \n EagleHire access: activate your account as soon as you start classes, all new ERAU students; for our continuing students, actively utilize the system for your co-op/internship and/or full-time job search with single sign-on access through ERNIE! \n Cooperative Education/Internship Program: statistics have proven the importance of completing co-ops and internships; start the process of preparing for the experiential opportunity by meeting with your Program Manager, even if you are only just considering a co-op or internship \n On-campus interviews and information sessions: great companies come to campus year-around to host events and conduct interviews \n Annual Industry/Career Expo: this exhibition brings many employers to campus in the fall and serves as a springboard for additional recruiting events such as information sessions and interviews\n Resume and cover letter critiques: get feedback on your job search documentation, which is imperative to do before you submit anything to potential employers.",
                        "Chaplain's Office\n\nThe Center for Faith and Spirituality hosts two spiritual leaders, Reverend David Keck and Father Tim Daly, on campus. The Chaplains support all students, faculty, and staff regardless of their religious beliefs. Students can visit the Chaplains when they want to discuss spirituality, moral questions, or life's challenges.",
                        "Dining Services\n\nWhile you’re busy attending classes, studying, working or participating in activities, finding great food is never a problem with Dining Services at the Embry-Riddle Daytona Beach campus. Dining options ranging from Einstein Bagels to an all-you-can-eat buffet are conveniently located near dorms and classrooms and open from 7 a.m. to as late as 1 a.m. Low-fat, vegetarian, vegan, and kosher meals are available, and a variety of dining plans are available to fit every student’s needs.",
                        "Embry-Riddle Language Institute (ERLI)\n\nThe Embry-Riddle Language Institute is an intensive English program providing English language instruction and cultural orientation to non-native speakers of English with 6 starting points per year (2 in spring, 2 in summer, and 2 in fall) \n If you desire to become more proficient in listening, speaking, reading, and writing the English language, this intensive English program is for you. \n Students can be granted full admission to the university pending completion of the program and/or a passing TOEFL or IELTS score, assuming they meet all other university admission requirements. Concurrent enrollment in ERLI and the university is also available for eligible students. \n Other benefits of our program include field trips and social events through the International Student Programming Council, full access to Embry-Riddle Aeronautical University facilities, and special topics courses such as Aviation Topics and Academic Topics. \n ERLI provides English language instruction to students who are not college bound.",
                        "First Year Programs\n\nFirst Year Programs consists of a team of academic advisors, peer mentors and student ambassadors, dedicated to helping new students at Embry-Riddle. We work together with faculty, staff and parents, focusing on student success: academically, professionally and socially. \n Through various programs and activities, we guide students through their transition to university life. First Year Programs helps students find what they need to be successful at Embry-Riddle and in life.",
                        "Lost and Found\n\nNo description given.",
                        "Intramural and Recreational Sports\n\nThe Intramural and Recreational Sports Department is made up of Intramural Sports, Fitness Center, Sports Complex Facilities (ICI Center & Outdoor Sports Facilities). \n In addition to those areas we also sell discount theme park tickets. Such as: Disney, Universal, Busch Gardens, Sea World, Wet n' Wild, Daytona Lagoon, Kennedy Space Center, Medieval Times, Sleuths Mystery Dinner Show, Blue Man Group, Legoland, Crayola Crayon \n Ticket availability is limited. We accept cash & checks from faculty & staff. We only cash from students. Bring your Eagle Card! To check on ticket availability & prices call 386-226-6530. \n Lastly, at our front desk area you can check out sports equipment. We have: Basketballs, Camping Tents, Coolers, Footballs, Racquetball Racquets, Soccer Balls, Tennis Racquets, Tug of War Rope. \n To check something out all you have to do is go to the front desk of the ICI Center during business hours with your Eagle Card. For more information call 386-226-7731.",
                        "Academic Advancement Center\n\nThe Academic Advancement Center offers free tutoring in math, physics, chemistry and writing to undergraduate students in general education courses. We are now located on the 1st floor of the College of Arts and Sciences (under the dome)."
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

        } catch (InflateException e) {
        }

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

    ExpandableListView.OnChildClickListener getChildInfo = new ExpandableListView.OnChildClickListener() {

        @Override
        public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {

            LatLng latLng;
            map.clear();

            switch (children1[groupPosition][childPosition]) {
                case "Student Union (Under Construction)\n\nNo description given.":
                    latLng = new LatLng(29.188001, -81.049333);
                    map.addMarker(new MarkerOptions()
                            .position(latLng)
                            .title("Student Union (Under Construction)"));
                    map.moveCamera(CameraUpdateFactory.newLatLng(latLng));
                    break;
                case "Corsair Hall\n\nNo description given.":
                    latLng = new LatLng(29.195885, -81.056100);
                    map.addMarker(new MarkerOptions()
                            .position(latLng)
                            .title("Corsair Hall"));
                    map.moveCamera(CameraUpdateFactory.newLatLng(latLng));
                    break;
                case "North Chiller\n\nNo description given.":
                    latLng = new LatLng(29.191023, -81.053850);
                    map.addMarker(new MarkerOptions()
                            .position(latLng)
                            .title("North Chiller"));
                    map.moveCamera(CameraUpdateFactory.newLatLng(latLng));
                    break;
                case "Campus Safety\n\nEmbry-Riddle Aeronautical University shares the concern of students and parents regarding safety on our campus. Accordingly, the Campus Safety & Security Department is actively engaged in collaborating with students, parents, employees, and the community to provide a safer University environment and to treat every individual with respect, fairness and compassion. ":
                    latLng = new LatLng(29.190572, -81.053161);
                    map.addMarker(new MarkerOptions()
                            .position(latLng)
                            .title("Campus Safety"));
                    map.moveCamera(CameraUpdateFactory.newLatLng(latLng));
                    break;
                case "New Residence Hall\n\nThe New Residence Hall opened in January 2017 and is located near the center of campus. This five-story facility offers suite-style living to first-year and upperclass students. Students have the option of a double or private room depending on availability. Each wing of the building has a lounge, study room, and laundry room. Half of the ground floor has been designed for community spaces with pool tables, TVs, study areas, and vending machines.":
                    latLng = new LatLng(29.190627, -81.052970);
                    map.addMarker(new MarkerOptions()
                            .position(latLng)
                            .title("New Residence Hall"));
                    map.moveCamera(CameraUpdateFactory.newLatLng(latLng));
                    break;
                case "Apollo Hall\n\nDesignated for sophomore, junior, senior, and graduate students. The 256-bed residential suite-style facility is located just a short walking distance of the Eagle Fitness Center and Tine W. Davis Swimming Pool. Four students share a two-bedroom suite, two students per bedroom. The suite includes a split bathroom and a living room/dining room and kitchenette with a full-sized refrigerator and microwave.":
                    latLng = new LatLng(29.189270, -81.052410);
                    map.addMarker(new MarkerOptions()
                            .position(latLng)
                            .title("Apollo Hall"));
                    map.moveCamera(CameraUpdateFactory.newLatLng(latLng));
                    break;
                case "Doolittle Hall\n\nDoolittle Hall is a three-story residence hall typical of traditional university housing and is designated for first-year students. It is located near the center of campus near the Eagle Fitness Center and Tine W. Davis Swimming Pool. Basketball, tennis, and volleyball courts are nearby, as well as picnic tables and barbecue grills.":
                    latLng = new LatLng(29.190665, -81.051184);
                    map.addMarker(new MarkerOptions()
                            .position(latLng)
                            .title("Doolittle Hall"));
                    map.moveCamera(CameraUpdateFactory.newLatLng(latLng));
                    break;
                case "Bookstore\n\nShow your Embry-Riddle Aeronautical University pride all year long with our authentic assortment of Embry-Riddle Aeronautical University collegiate apparel - including Embry-Riddle Aeronautical University t-shirts, sweatshirts, hats and more. Join the ranks of your fellow Embry-Riddle Aeronautical University students, alumni and team fanatics by sporting the ultimate Embry-Riddle Aeronautical University game day gear We've got Embry-Riddle Aeronautical University gift ideas covered, with options ranging from drinkware to diploma frames to Embry-Riddle Aeronautical University gift cards. Plus, our selection of textbooks, computers, and supplies will ensure every Embry-Riddle Aeronautical University student is prepared for success.":
                    latLng = new LatLng(29.189892, -81.051171);
                    map.addMarker(new MarkerOptions()
                            .position(latLng)
                            .title("Bookstore"));
                    map.moveCamera(CameraUpdateFactory.newLatLng(latLng));
                    break;
                case "Mail Services\n\nWe provide convenience and assistance for all mail & shipping service needs to administration, faculty, http://news.erau.edu/news-briefs/virtual-technology-creates-bridge-for-distance-learning/staff & students ~ We sort & deliver all Incoming mail & packages to campus departments and  student mailboxes and receive & process all Outgoing mail & packages from campus departments in a safe, secure, accurate & timely manner ~ We provide our Counter customers with the most cost effective, reliable and secure method for their Outgoing postal and shipping needs ~ We provide our students with excellent counter services by assisting them with package pickup, mailbox assignments, mail forwarding, replacement keys, tracking requests and general questions.":
                    latLng = new LatLng(29.188602, -81.053185);
                    map.addMarker(new MarkerOptions()
                            .position(latLng)
                            .title("Mail Services"));
                    map.moveCamera(CameraUpdateFactory.newLatLng(latLng));
                    break;
                case "Undergraduate Research and Disability Services\n\nPersons with disabilities can and do succeed in their pursuit of higher education.\nThe mission of Disability Support Services is to guide and support institutional compliance with Section 504 of the Rehabilitation Act of 1973 and Title III of the Americans with Disabilities Act to ensure equal access for students with disabilities of the Daytona Beach and Worldwide Campuses.\nThe University acknowledges the confidential nature of students’ disability-related information and records and ascribes to the federal guidelines (Section 503 of the Rehabilitation Act and Title I of the Americans with Disabilities Act) that mandate control of and restrict access to such information. Note: Unless specifically contraindicated by another title, the confidentiality requirements of Title I of the ADA apply to all titles.":
                    latLng = new LatLng(29.190060, -81.050970);
                    map.addMarker(new MarkerOptions()
                            .position(latLng)
                            .title("Undergraduate Research and Disability Services"));
                    map.moveCamera(CameraUpdateFactory.newLatLng(latLng));
                    break;
                case "Eagle Fitness Center\n\nThe Eagle Fitness Center is located near the center of the Embry-Riddle Aeronautical University Campus, offering students, faculty, and staff a convenient place to get in a workout between classes or take a study break in the sun beside the Tine W. Davis Swimming Pool.\nThe 12,000-square-foot Fitness Center houses state-of-the-art strength-training equipment from FreeMotion Fitness, Life Fitness & Hammer Strength, and Iron Grip. A cardio deck on the upper level houses treadmills, elliptical machines, stationary bikes, cross trainers, and rowing machines with integrated technology features such as TVs and iPod and USB connections with Internet connectivity.\nGroup fitness classes are held in a 2,500-square-foot group fitness room that overlooks the swimming pool. Classes vary per semester and include yoga, cycling, core training, Les Mills BODYPUMP, BODYCOMBAT, Zumba, and Boot Camp.\nFitness equipment available for use during classes includes steps, stability balls, BOSU balls, resistance bands, and free weights, as well as 20 spinner bikes.":
                    latLng = new LatLng(29.190238, -81.050429);
                    map.addMarker(new MarkerOptions()
                            .position(latLng)
                            .title("Eagle Fitness Center"));
                    map.moveCamera(CameraUpdateFactory.newLatLng(latLng));
                    break;
                case "Student Financial Services\n\nThe Student Financial Services Department strives to provide efficient, courteous and excellent customer service to our students, parents, staff and faculty. We offer a variety of services which are accessible online, or in person. Some of these services include: Billing of Tuition and Fees, Student Refunds, Payment Plan and Payroll Check Cashing.":
                    latLng = new LatLng(29.188305, -81.053166);
                    map.addMarker(new MarkerOptions()
                            .position(latLng)
                            .title("Student Financial Services"));
                    map.moveCamera(CameraUpdateFactory.newLatLng(latLng));
                    break;
                case "Center for Faith and Spirituality\n\nThe Center for Faith and Spirituality and the Chaplain’s Office welcome students, staff, and faculty of all backgrounds, encouraging them in their lives of faith and providing them with opportunities to learn more about their own traditions as well as those of their fellow students and colleagues.\nLocated on the west side of campus near McKay Hall, the Center for Faith and Spirituality has five prayer and meditation rooms which are open to everyone from 6:00 a.m. until 10:00 p.m. The work of the Center includes: Helping students, staff, and faculty find a spiritual home on campus, Promoting healthy and respectful interfaith dialogue, Developing opportunities to live out one’s faith through service to the community, and Providing a home for our religious clubs and organizations.":
                    latLng = new LatLng(29.189352, -81.051039);
                    map.addMarker(new MarkerOptions()
                            .position(latLng)
                            .title("Center for Faith and Spirituality"));
                    map.moveCamera(CameraUpdateFactory.newLatLng(latLng));
                    break;
                case "Racquetball Court\n\nThe Racquetball Complex is located near the center of the Embry-Riddle Aeronautical University Campus, across from the Eagle Fitness Center and Tine W. Davis Swimming Pool. The complex features four indoor, air-conditioned racquetball/handball courts, two with spectator viewing. The complex is also used for intramural Wallyball.\nCourts are open to students, faculty, staff, and accompanied guests at no charge. Racquetball reservations may be made at the front desk of the Eagle Fitness Center or by calling 386-323-8860. Racquetball rackets can be checked out for free at the Control Desk in the ICI Center, located across from the Campus entrance on Clyde Morris Boulevard. Two courts on the west side of the Racquetball Complex have been renovated to house martial arts for club sports.":
                    latLng = new LatLng(29.189512, -81.050646);
                    map.addMarker(new MarkerOptions()
                            .position(latLng)
                            .title("Racquetball Court"));
                    map.moveCamera(CameraUpdateFactory.newLatLng(latLng));
                    break;
                case "Student Support Services, Global Engagement, Honors Center\n\nThe Embry-Riddle Honors Program is highly selective, offering its student members enriched educational experiences. Emphasizing Honors course work in General Education and in the majors, the program involves selected faculty who develop innovative courses and establish mentoring relationships with students. The program is designed to attract and retain top students and to develop their communicative, analytical, critical, and research skills, nurturing a love of life-long learning, leadership and service.  \nAt the Office of Global Engagement, we are deeply passionate about helping students experience the world. Studying Abroad is a once-in-a-lifetime opportunity to not only visit another country, but to truly experience its culture. \nBefore picking the right program for you, here are some highlights of studying abroad: \nOpportunities around the globe \nSummer, semester, or yearlong programs \nTake courses that apply toward your major\nStudy in English or the native language \nJoin the Travelers Club to experience other cultures on campus \nDon't be a tourist, live abroad!":
                    latLng = new LatLng(29.189552, -81.050230);
                    map.addMarker(new MarkerOptions()
                            .position(latLng)
                            .title("Student Support Services, Global Engagement, Honors Center"));
                    map.moveCamera(CameraUpdateFactory.newLatLng(latLng));
                    break;
                case "Advanced Flight Sim Center\n\nNew-generation Flight Training Devices (FTDs) are increasingly valuable in today’s complex flight training environment. Simulators lower cost, improve safety, and have minimal impact on the environment. \nTo meet this growing need, Embry-Riddle continues to expand on the capabilities of its Advanced Flight Simulation Center. The center is home to an array of FAA-qualified Level 6 FTDs, as well as one FAA Level D Full-Flight Simulator (FFS) and a crosswind trainer. \nTraining aids found in our Advanced Flight Simulation Center enable our students to learn aircraft performance, practice Cockpit Resource Management (CRM), experience aerodynamic effects, and perform a variety of flight maneuvers in a safe, effective, and comfortable environment. \nWith 11 state-of-the-art Level 6 Flight Training Devices, a Regional Jet Level D FFS and a crosswind trainer, no other collegiate flight training program can match the capabilities and variety of systems available to our students. Located within Embry-Riddle’s Advanced Flight Simulation Center, our simulator/trainer “fleet” currently includes: \nFrasca Cessna 172S Flight Training Device (FTD) \nFrasca Diamond DA42 L-360 Flight Training Device (FTD) \nFrasca Canadair CRJ-200 FAA Level 6 Flight Training Device (FTD) \nCRJ-200 Level D Full-Flight Simulator (FFS) \nETC GAT-II Spatial Disorientation Training Device \nRedbird Xwind SE Crosswind Trainer":
                    latLng = new LatLng(29.187741, -81.051061);
                    map.addMarker(new MarkerOptions()
                            .position(latLng)
                            .title("Advanced Flight Sim Center"));
                    map.moveCamera(CameraUpdateFactory.newLatLng(latLng));
                    break;
                case "Fleet Maintenance Hangar\n\nThe responsibility of keeping Embry-Riddle Daytona Beach’s fleet of training aircraft in top mechanical form falls on the university’s team of FAA Certified Airframe and Powerplant (A&P) technicians. \nWorking in the Fleet Maintenance Center, our A&Ps perform all scheduled and unscheduled inspections and maintenance on our fleet. Students in the Aviation Maintenance Science degree program will have the opportunity to work alongside these experienced technicians to gain invaluable experience.":
                    latLng = new LatLng(29.187266, -81.051747);
                    map.addMarker(new MarkerOptions()
                            .position(latLng)
                            .title("Fleet Maintenance Center"));
                    map.moveCamera(CameraUpdateFactory.newLatLng(latLng));
                    break;
                case "Flight Operations\n\nLocated adjacent to the ERAU Flight Line, the Flight Operations Center is the central point for all aircraft operations and student pre- and post-flight training activities with their instructors. \nThe Flight Ops Center boasts a state-of-the-art dispatch desk, real-time weather monitoring equipment, computer stations for flight planning, private oral rooms, and many other modern aviation conveniences designed to give our students everything they need for their pre-flight planning and preparation. Embry-Riddle Daytona Beach flight operations run seven days a week, 50 weeks a year (we only shut down for Fall/Spring semester breaks). The Flight Operations Center is open from 6 a.m. to midnight so our flight students have access to the airplane they need when they need it. \nThe Flight Ops Center can also track and record each aircraft’s flight. That information is often used during the student’s debriefing or can be accessed by the student to help reinforce the value of each lesson.":
                    latLng = new LatLng(29.186863, -81.051232);
                    map.addMarker(new MarkerOptions()
                            .position(latLng)
                            .title("Flight Operations"));
                    map.moveCamera(CameraUpdateFactory.newLatLng(latLng));
                    break;
                //Need to update latLng from here
                case "College of Business\n\nWe provide a world-class Business education in Aviation/Aerospace with state-of-the-art curricula at both the graduate and undergraduate levels.  This education has proven to be successful in preparing our graduates to become leaders in the Aviation and Business industries. Our “Business of Flight®” approach is a strategic vision that will place our graduates on the leading edge of business leadership and innovation.":
                    latLng = new LatLng(29.187923, -81.050242);
                    map.addMarker(new MarkerOptions()
                            .position(latLng)
                            .title("College of Business"));
                    map.moveCamera(CameraUpdateFactory.newLatLng(latLng));
                    break;
                case "Willie Miller Instructional Center\n\nNo description given.":
                    latLng = new LatLng(29.188630, -81.049986);
                    map.addMarker(new MarkerOptions()
                            .position(latLng)
                            .title("Willie Miller Instructional Center"));
                    map.moveCamera(CameraUpdateFactory.newLatLng(latLng));
                    break;
                case "Aviation Maintenance Services\n\nStudents who thrive at the cutting edge of technology and want to take on the challenge of ensuring the integrity of aircraft carrying pilots, passengers, and precious cargo are right at home in the Department of Aviation Maintenance Science. The department offers several programs, including a Bachelor of Science in Aviation Maintenance, an Associate of Science in Aviation Maintenance, and a minor in Avionics Line Maintenance. \nAt the heart of each program lies the FAA Airframe and Powerplant (A&P) mechanic’s certification program. These highly hands-on degree A.S. and B.S. programs each include 47 credit hours of airframe and powerplant technical courses, plus the specific courses that will prepare graduates for meeting a growing demand for technicians to work for large aircraft manufacturers, airlines or commercial companies with small fleets, or contractors who overhaul and rebuild aircraft. Some also enter the growing field of avionics, working on unmanned aerial vehicles. \nThese students get plenty of real-life experience in the department’s Emil Buehler Aviation Maintenance Science Building. The 48,000-square-foot building is entirely dedicated to the training of airframe and powerplant technicians, with two of its three floors dedicated to labs. The department also has a hangar, turbine engine test cells, and several aircraft.":
                    latLng = new LatLng(29.186464, -81.050544);
                    map.addMarker(new MarkerOptions()
                            .position(latLng)
                            .title("Aviation Maintenance Services"));
                    map.moveCamera(CameraUpdateFactory.newLatLng(latLng));
                    break;
                case "College of Aviation\n\nThe College of Aviation degree programs meet the needs of aviation students of the 21st century and set the standard in high-level quality education demanded by the aviation profession. Our 100-plus faculty members and the courses in the COA's five departments — Aeronautical Science, Applied Aviation Science, Aviation Maintenance Science, Flight, and Doctoral and Graduate Studies — will prepare graduates to assume leadership roles in the global aviation industry and related fields.":
                    latLng = new LatLng(29.187321, -81.049852);
                    map.addMarker(new MarkerOptions()
                            .position(latLng)
                            .title("College of Aviation"));
                    map.moveCamera(CameraUpdateFactory.newLatLng(latLng));
                    break;
                case "AMS Hangar\n\nThe Aviation Maintenance Science (AMS) Hangar is the focal point for a wide variety of hands-on projects that occur on the department’s ramp-utilizing aircraft and powerplant training aides as part of the Aviation Maintenance Science degree program. The AMS Hangar is located adjacent to the Emil Buehler Aviation Maintenance Sciences Building. It houses state-of-the-art functional training aides and a variety of operating aircraft for test and operations. Students will use both the aircraft and hands-on trainers to gain valuable experience in the diverse sets of specialized skills needed to earn their FAA Airframe and Powerplant certificate.":
                    latLng = new LatLng(29.186210, -81.050075);
                    map.addMarker(new MarkerOptions()
                            .position(latLng)
                            .title("AMS Hangar"));
                    map.moveCamera(CameraUpdateFactory.newLatLng(latLng));
                    break;
                case "Test Cell 1\n\nNo description given.":
                    latLng = new LatLng(29.186034, -81.050420);
                    map.addMarker(new MarkerOptions()
                            .position(latLng)
                            .title("Test Cell 1"));
                    map.moveCamera(CameraUpdateFactory.newLatLng(latLng));
                    break;
                case "Engine Test Cells\n\nNo description given.":
                    latLng = new LatLng(29.185893, -81.050569);
                    map.addMarker(new MarkerOptions()
                            .position(latLng)
                            .title("Engine Test Cells"));
                    map.moveCamera(CameraUpdateFactory.newLatLng(latLng));
                    break;
                case "Canaveral Hall\n\nNo description given.":
                    latLng = new LatLng(29.186849, -81.048835);
                    map.addMarker(new MarkerOptions()
                            .position(latLng)
                            .title("Canaveral Hall"));
                    map.moveCamera(CameraUpdateFactory.newLatLng(latLng));
                    break;
                case "College of Arts and Sciences\n\nThe College of Arts & Sciences is a diverse learning community comprising five academic departments — Human Factors & Systems, Humanities & Communication, Mathematics, Physical Sciences, and Security Studies & International Affairs. The ever-evolving academic programs, state-of-the-art facilities, and bright, enthusiastic students are all part of an outstanding learning environment in COAS. Our faculty — more than 160 strong — are leaders in their fields and consistently recognized for outstanding research and teaching.":
                    latLng = new LatLng(29.188231, -81.048173);
                    map.addMarker(new MarkerOptions()
                            .position(latLng)
                            .title("College of Arts and Sciences"));
                    map.moveCamera(CameraUpdateFactory.newLatLng(latLng));
                    break;
                case "Wellness Center - Clinic\n\nHealth Services is available to currently registered students of the Daytona Beach campus. We have a full-time team of five registered nurses available on a walk-in basis. A Physician Assistant, and Nurse Practitioner are available by appointment Monday through Friday, with limited hours on Sundays during the fall and spring semesters. A university physician is available from 9 a.m. to 4:30 p.m. on Mondays and Wednesdays by appointment. \n Health Services is supported by registration fees, so students can be seen and treated free of charge. There is no fee to be treated at Health Services, however if services outside of the University are needed, such as lab services, the student would be responsible. Embry-Riddle Aeronautical University's Health Services Department is located in the HDS Wellness Center in Building 500, across from Doolittle Hall.":
                    latLng = new LatLng(29.191924, -81.050668);
                    map.addMarker(new MarkerOptions()
                            .position(latLng)
                            .title("Wellness Center - Clinic"));
                    map.moveCamera(CameraUpdateFactory.newLatLng(latLng));
                    break;
                case "Wellness Center - Counseling Center\n\nThe mission of the Counseling Center is to enhance the psychological well-being of Embry-Riddle students. We believe that good mental health is a key part of overall health, and good health is essential to academic success. We support students' academic experience by providing brief mental health counseling and consultation services that help them identify barriers, improve coping, and achieve personal and academic goals. \n College life is challenging and can be stressful. At some point during the college years, many students feel anxious, depressed, uncertain, confused, or overwhelmed. They may seek help from friends, family members, significant others, but in some cases, help from a trained professional is beneficial. The Counseling Center provides a calm, friendly, and supportive environment for students to address any issue or concern. \n Counseling is confidential and offered free of charge. All counseling contacts are strictly confidential in accordance with Florida state privacy laws. Counseling records are not available to anyone, either on or off campus, without the student's specific written permission. The only time confidentiality will be broken is in the case of a life threatening situation: when a student is at risk of harming him or herself or someone else, or when there is reason to believe a child, elderly person, or disabled person is being abused. Confidentiality may also be broken if a court order mandates that records be released. NOTE: Counseling notes are not part of a student's University records.":
                    latLng = new LatLng(29.191704, -81.050432);
                    map.addMarker(new MarkerOptions()
                            .position(latLng)
                            .title("Wellness Center - Counseling Center"));
                    map.moveCamera(CameraUpdateFactory.newLatLng(latLng));
                    break;
                case "ROTC Center\n\nReserve Officer Training Corps: ROTC is a four-year college elective program that trains students to become Air Force, Army, Navy, or Marine officers. Air Force, Army, and Navy ROTC programs offer you a chance to develop skills for success needed in both the military and civilian worlds, such as confidence to lead, motivation, strategic planning, and the ability to make decisions. \n Scholarships: Many of our students enroll with ROTC Scholarships, which may cover all or part of tuition, fees, and books, along with a monthly allowance. Some ROTC Scholarships are awarded to students after completion of their first year. Explore the sections below to learn more about what each branch offers.":
                    latLng = new LatLng(29.192319, -81.049683);
                    map.addMarker(new MarkerOptions()
                            .position(latLng)
                            .title("ROTC Center"));
                    map.moveCamera(CameraUpdateFactory.newLatLng(latLng));
                    break;
                case "Print Shop\n\nNo description given.":
                    latLng = new LatLng(29.193871, -81.051530);
                    map.addMarker(new MarkerOptions()
                            .position(latLng)
                            .title("Print Shop"));
                    map.moveCamera(CameraUpdateFactory.newLatLng(latLng));
                    break;
                case "Veteran's Affairs\n\nOur mission is to provide, facilitate, or coordinate programs to meet the needs of veterans, service members, dependents, and survivors of veterans in order to ease the transition to college life and fulfill their educational goals. \n The office is staffed with qualified counselors who provide a broad range of services; provide general and specific information about all benefits available to men and women who are serving or who have served in the armed forces; report enrollment information to authorize appropriate allowances; provide other advisory counseling and referral services; assist in resolving problems that may arise in the student's relations with the University and/or the U.S. Department of Veterans Affairs (VA); foster peer connections; and coordinate university and community support.":
                    latLng = new LatLng(29.193337, -81.047628);
                    map.addMarker(new MarkerOptions()
                            .position(latLng)
                            .title("Veteran's Affairs"));
                    map.moveCamera(CameraUpdateFactory.newLatLng(latLng));
                    break;
                case "Eagle Alumni Center\n\nNo description given.":
                    latLng = new LatLng(29.193470, -81.050927);
                    map.addMarker(new MarkerOptions()
                            .position(latLng)
                            .title("Eagle Alumni Center"));
                    map.moveCamera(CameraUpdateFactory.newLatLng(latLng));
                    break;
                case "Crotty Tennis Complex\n\nThe Ambassador E. William Crotty Tennis Complex is located north of the Sliwa Stadium and the ICI Center. This hard court features nine lighted tennis courts with an electronic scoreboard that can show set scores for six matches. Each court has bleachers for spectator seating and a newly installed shade structure for spectators on courts 1-6. \n The Crotty Tennis Complex is home to the Embry-Riddle Aeronautical University men’s and women’s tennis teams and hosts many college and amateur tennis tournaments. Courts 1-6 are used for match play and team practices. Courts 7, 8, and 9 are available to students, faculty, and staff on a first-come, first-served basis — except during varsity matches and practices (usually held between 2 p.m. and 7 p.m.). Lights automatically turn off at 11 p.m.":
                    latLng = new LatLng(29.192958, -81.047267);
                    map.addMarker(new MarkerOptions()
                            .position(latLng)
                            .title("Crotty Tennis Complex"));
                    map.moveCamera(CameraUpdateFactory.newLatLng(latLng));
                    break;
                case "Telescope\n\nNo description given.":
                    latLng = new LatLng(29.193536, -81.047164);
                    map.addMarker(new MarkerOptions()
                            .position(latLng)
                            .title("Telescope"));
                    map.moveCamera(CameraUpdateFactory.newLatLng(latLng));
                    break;
                case "Track and Field Concession\n\nNo description given.":
                    latLng = new LatLng(29.194255, -81.050862);
                    map.addMarker(new MarkerOptions()
                            .position(latLng)
                            .title("Track and Field Concession"));
                    map.moveCamera(CameraUpdateFactory.newLatLng(latLng));
                    break;
                case "Track and Field Bleachers\n\nNo description given.":
                    latLng = new LatLng(29.194816, -81.049928);
                    map.addMarker(new MarkerOptions()
                            .position(latLng)
                            .title("Track and Field Bleachers"));
                    map.moveCamera(CameraUpdateFactory.newLatLng(latLng));
                    break;
                case "ROTC Obstacle Course\n\nNo description given.":
                    latLng = new LatLng(29.193680, -81.049328);
                    map.addMarker(new MarkerOptions()
                            .position(latLng)
                            .title("ROTC Obstacle Course"));
                    map.moveCamera(CameraUpdateFactory.newLatLng(latLng));
                    break;
                case "Multipurpose Artificial Turf Field\n\nThe Multipurpose Turf Field is a regulation field for soccer, lacrosse, and intramural sports. The lighted field features a Daktronics electronic scoreboard and has a standard soccer playing area of 120x70 yards. The field is used by the University’s sports clubs and organizations, along with the ROTC. Reservations for this field can be made at the Department of Intramural and Recreational Sports in the ICI Center.":
                    latLng = new LatLng(29.194372, -81.047894);
                    map.addMarker(new MarkerOptions()
                            .position(latLng)
                            .title("Multipurpose Artificial Turf Field"));
                    map.moveCamera(CameraUpdateFactory.newLatLng(latLng));
                    break;
                case "Artificial Turf Softball Field\n\nThis field was built to accommodate Embry-Riddle’s growing intramural softball program, which has about 25 teams competing. While built primarily as a regulation-sized softball field, it is large enough for flag football and soccer and features dugouts and an electronic scoreboard. The field is often used for kickball games and Ultimate Frisbee practice, and it is used by the radio-controlled model airplane club to fly their planes. Students, faculty, and staff can reserve this field by contacting the Department of Intramural and Recreational Sports in the ICI Center.":
                    latLng = new LatLng(29.193225, -81.046108);
                    map.addMarker(new MarkerOptions()
                            .position(latLng)
                            .title("Artificial Turf Softball Field"));
                    map.moveCamera(CameraUpdateFactory.newLatLng(latLng));
                    break;
                case "Baseball/Softball Clubhouse\n\nNo description given.":
                    latLng = new LatLng(29.191623, -81.045858);
                    map.addMarker(new MarkerOptions()
                            .position(latLng)
                            .title("Baseball/Softball Clubhouse"));
                    map.moveCamera(CameraUpdateFactory.newLatLng(latLng));
                    break;
                case "JPR Student and Admissions Visitor Center\n\nNo description given.":
                    latLng = new LatLng(29.189457, -81.049506);
                    map.addMarker(new MarkerOptions()
                            .position(latLng)
                            .title("JPR Student and Admissions Visitor Center"));
                    map.moveCamera(CameraUpdateFactory.newLatLng(latLng));
                    break;
                case "ICI Center\n\nThe ICI Center houses Embry-Riddle Aeronautical University’s Department of Athletics and the Department of Intramural and Recreational Sports. Located on Clyde Morris Boulevard directly across from the campus main entrance, the 50,000-square-foot facility is the centerpiece for campus recreation, athletics, and University functions such as graduation ceremonies, guest speakers, and concerts. \n The facility is home to Embry-Riddle’s men’s and women’s basketball and women’s volleyball teams, as well as intramural activities, club sports, and cheerleading. The multipurpose gymnasium features three basketball courts, three volleyball courts, and three badminton courts. \n The facility boasts a state-of-the-art sound system, a sports medicine center, and the Fletcher Fitness Center, which is equipped with strength training and cardiovascular equipment. The ICI Center also houses the Department of Athletics and the Department of Intramural and Recreational Sports. \n Surrounding the ICI Center are outdoor sports facilities, including baseball and soccer stadiums, tennis courts, lighted-lane running track, and multiple lighted fields for soccer, softball, lacrosse, and more. \n Students, faculty, and staff can check out sports and recreational equipment at no charge from the ICI Center front desk, and the facility is available for open recreation.":
                    latLng = new LatLng(29.191261, -81.046744);
                    map.addMarker(new MarkerOptions()
                            .position(latLng)
                            .title("ICI Center"));
                    map.moveCamera(CameraUpdateFactory.newLatLng(latLng));
                    break;
                case "Jim W. Henderson Administration and Welcome Center\n\nNo description given.":
                    latLng = new LatLng(29.189825, -81.048372);
                    map.addMarker(new MarkerOptions()
                            .position(latLng)
                            .title("Jim W. Henderson Administration and Welcome Center"));
                    map.moveCamera(CameraUpdateFactory.newLatLng(latLng));
                    break;
                case "Sliwa Stadium\n\nThe state-of-the-art facility features a field, which can be fully lit and a 36-foot Daktronics scoreboard. The scoreboard is located in the north corner of the field was installed prior to the 2016 season. \n The ballpark has dimensions of 330 feet down the left field line, 375 feet in the left-center field gap, 400 feet to straight-away center, 375 feet to right-center field, and 330 feet down the right field line. \n In the summer of 2015, Embry-Riddle celebrated the opening of the brand new ERAU Baseball/Softball Clubhouse along with the completion of the renovations at the ERAU Softball Stadium. \n Located between Sliwa Stadium and the ERAU Softball Stadium, the clubhouse primarily serves to support both the baseball and softball programs, with additional amenities for Sports Medicine as well as the athletics department as a whole. \n The 12,000-square foot facility includes offices for the baseball and softball coaching staffs along with locker rooms for both teams, as well as two officials’ locker rooms. In addition to the offices and locker rooms, the facility includes an athletic training room, a multipurpose conference room, laundry room and public restrooms.":
                    latLng = new LatLng(29.191932, -81.046306);
                    map.addMarker(new MarkerOptions()
                            .position(latLng)
                            .title("Sliwa Stadium"));
                    map.moveCamera(CameraUpdateFactory.newLatLng(latLng));
                    break;
                case "Soccer - Ticket Concession\n\nNo description given.":
                    latLng = new LatLng(29.190943, -81.045819);
                    map.addMarker(new MarkerOptions()
                            .position(latLng)
                            .title("Soccer - Ticket Concession"));
                    map.moveCamera(CameraUpdateFactory.newLatLng(latLng));
                    break;
                case "Soccer Field Bleachers\n\nNo description given.":
                    latLng = new LatLng(29.190561, -81.046043);
                    map.addMarker(new MarkerOptions()
                            .position(latLng)
                            .title("Soccer Field Bleachers"));
                    map.moveCamera(CameraUpdateFactory.newLatLng(latLng));
                    break;
                case "Lehman College of Engineering\n\nThe administrative offices and five academic departments of the College of Engineering reside in the Lehman Engineering and Technology Center. Additional educational and project labs for the Department of Mechanical Engineering are in the nearby M-building. \n In addition to traditional classrooms and laboratories for instruction and research, the Lehman Center offers computing resources and open workspaces facilitating collaborative development for interdisciplinary student teams. Hands-on learning and learning-by-doing are enabled through a variety of student-accessible laboratories with the tools for a complete engineering design-build-test project cycle. A fully functional Distance Learning and Lecture Capture Studio, for creating high-quality and effective learning modules to enhance teaching at all levels, is being installed on the third floor. \n Research facilities for full-sized aircraft flight test and development are located on the south side of the Daytona Beach airport at the Eagle Flight Research Center. An additional research center, the Next-Generation ERAU Advanced Research (NEAR) Laboratory, is located on the airport property adjacent to the primary campus. Undergraduate and graduate students collaborate with faculty at both of these research centers dedicated to solving real problems brought forward by industry.":
                    latLng = new LatLng(29.189201, -81.047005);
                    map.addMarker(new MarkerOptions()
                            .position(latLng)
                            .title("Lehman College of Engineering"));
                    map.moveCamera(CameraUpdateFactory.newLatLng(latLng));
                    break;
                case "South Chiller\n\nNo description given.":
                    latLng = new LatLng(29.188669, -81.046387);
                    map.addMarker(new MarkerOptions()
                            .position(latLng)
                            .title("South Chiller"));
                    map.moveCamera(CameraUpdateFactory.newLatLng(latLng));
                    break;
                case "Admissions Operations\n\nWith access to the world’s most comprehensive collection of academic programs focusing on aviation, aerospace, and security, a degree from Embry-Riddle will launch your career in a successful direction. Your first step to getting there is submitting your application. Our Admissions counselors are here to help you navigate the process of applying to and enrolling at Embry-Riddle, so don’t hesitate to call, email, or chat.":
                    latLng = new LatLng(29.189258, -81.049097);
                    map.addMarker(new MarkerOptions()
                            .position(latLng)
                            .title("Admissions Operations"));
                    map.moveCamera(CameraUpdateFactory.newLatLng(latLng));
                    break;
                case "Engineering Special Projects and Labs\n\nThe space is designed to encourage creativity and collaboration among engineering students and design teams, and provides students with an open work space, tools for manufacturing, and finishing. \n The ESPC is available to all undergraduate engineering students involved in an ERAU engineering club and personal projects. The lab is run by the Engineering Fundamentals Program Coordinator, and is monitored by a graduate lab assistant (GLA); all use of the ESPC must be approved by one of these individuals. The use of any power equipment is only permitted after students have received safety training on the use of the equipment, including the Standard Operating Procedures, after which, the students can operate any equipment while under the supervision of the GLA. \n This laboratory is the primary home of several engineering design teams and student organizations, including the student chapter of the American Institute of Aeronautics and Astronautics (AIAA)’s Design-Build-Fly and the Experimental Rocket Propulsion Lab (ERPL).":
                    latLng = new LatLng(29.188589, -81.045235);
                    map.addMarker(new MarkerOptions()
                            .position(latLng)
                            .title("Engineering Special projects and Labs"));
                    map.moveCamera(CameraUpdateFactory.newLatLng(latLng));
                    break;
                case "Clyde Morris Multipurpose Field\n\nThe Clyde Morris Multipurpose Fields are located at the south end of the University Sports Complex. This lighted area is the primary location for intercollegiate soccer team practices but is available for other uses upon request.":
                    latLng = new LatLng(29.189565, -81.044879);
                    map.addMarker(new MarkerOptions()
                            .position(latLng)
                            .title("Clyde Morris Multipurpose Field"));
                    map.moveCamera(CameraUpdateFactory.newLatLng(latLng));
                    break;
                case "Student Village (Adams, Wood, Tallman Commons)\n\nBoth located in the Student Village on campus, Wood and Adams Hall are five-story buildings designated for first-year students. Each student room is furnished with two single beds with extra-long mattresses, a dresser, a desk and chair, a lockable box or drawer, and a small refrigerator and microwave.":
                    latLng = new LatLng(29.192778, -81.053016);
                    map.addMarker(new MarkerOptions()
                            .position(latLng)
                            .title("Student Village (Adams, Wood, Tallman Commons)"));
                    map.moveCamera(CameraUpdateFactory.newLatLng(latLng));
                    break;
                case "Student Village (O'Connor, Stimpson)\n\nO’Connor Hall is located in the Student Village on campus, and offers apartment-style living for sophomores, juniors, seniors, and graduate students. Students in O’Connor Hall share four-bedroom apartments, with two students per bedroom. Each apartment includes two bathrooms,  living/dining room, and a kitchenette with a microwave and full-size refrigerator.  \nAlso located in the Student Village on Campus, Stimpson Hall features efficiency-style apartment living, primarily for sophomores, juniors, seniors, and graduate students. Each apartment has a bedroom, private bathroom,  separate study/living room, and a kitchen with a stove, microwave, and full-size refrigerator.":
                    latLng = new LatLng(29.193677, -81.053633);
                    map.addMarker(new MarkerOptions()
                            .position(latLng)
                            .title("Student Village (O'Connor, Stimpson)"));
                    map.moveCamera(CameraUpdateFactory.newLatLng(latLng));
                    break;
                case "Richard Petty Multipurpose West Field\n\nThis field is located west of Richard Petty Boulevard, across from the Student Village. The lighted, Bermuda grass facility is large enough to host three intramural games simultaneously, and each year it is used by 36-42 teams for fall flag football and spring soccer. It is the primary site for intramural flag football and soccer and has been used for Ultimate Frisbee tournaments and sorority sporting events. The Department of Intramural and Recreational Sports accepts requests for a limited number of special events.":
                    latLng = new LatLng(29.193335, -81.054936);
                    map.addMarker(new MarkerOptions()
                            .position(latLng)
                            .title("Richard petty Multipurpose West Field"));
                    map.moveCamera(CameraUpdateFactory.newLatLng(latLng));
                    break;
                case "Richard Petty Multipurpose East Field\n\nThis field, located just north of the Student Village, is an open recreation field used primarily by individuals and groups for soccer, flag football, open recreation, or intramural practices, with reservations though the Department of Intramural and Recreational Sports taking priority. It is the main playing space for rugby and cricket and is used by the Navy and Air Force for ROTC leadership labs.":
                    latLng = new LatLng(29.195624, -81.054100);
                    map.addMarker(new MarkerOptions()
                            .position(latLng)
                            .title("Richard Petty Multipurpose East Field"));
                    map.moveCamera(CameraUpdateFactory.newLatLng(latLng));
                    break;
                case "Einstein Bros. Bagels\n\nEinstein Bros®, known for their bagels, cream cheese schmears and coffee heritage, will introduce an exceptional morning alternative that's fast, hot, and packed with flavor. But it doesn't stop there. \nEinstein Bros. also offers bistro salads, soups and sandwiches, which  will be a great lunch attraction, while oversized brownies, cookies, pound cake and muffins will make for delicious anytime snacking.\n Regular Hours of Operations\n Monday - Friday: 7:00 a.m. - 1:00 a.m.\n Saturday - Sunday: 12:00 p.m. - 12:00 a.m.\n":
                    latLng = new LatLng(29.192722, -81.052790);
                    map.addMarker(new MarkerOptions()
                            .position(latLng)
                            .title("Einstein Bros. Bagels"));
                    map.moveCamera(CameraUpdateFactory.newLatLng(latLng));
                    break;
                case "Freshens Smoothie Company\n\nEnergy zone gives customers the variety they want with great tasting yogurt, fruit juice and orange smoothies. Even more nutritious and better than before, Freshens offers low calorie smoothies with over 20 fresh blended flavors. Kosher certified and fully energized.\n Regular Hours of Operations\n Monday - Friday: 10:00 a.m. - 5:00 p.m.\n Saturday - Sunday: Closed\n":
                    latLng = new LatLng(29.189451, -81.049732);
                    map.addMarker(new MarkerOptions()
                            .position(latLng)
                            .title("Freshens Smoothie Company"));
                    map.moveCamera(CameraUpdateFactory.newLatLng(latLng));
                    break;
                case "Chick-fil-A\n\nChick-fil-A® is a proven hit on the campus. Whether for breakfast, lunch or dinner, patrons can always depend on Chick-fil-A to deliver high-quality taste in a sandwich or salad\nBreakfast served until 10:30am:\n Monday - Tuesday, Thursday: 7:30 a.m. - 9:00 p.m.\n Friday: 7:30 a.m. - 6:00 p.m.\n Saturday - Sunday: Closed\n":
                    latLng = new LatLng(29.189492, -81.049793);
                    map.addMarker(new MarkerOptions()
                            .position(latLng)
                            .title("Chick-fil-A"));
                    map.moveCamera(CameraUpdateFactory.newLatLng(latLng));
                    break;
                case "Pacific Traders\n\nFlavors of the Orient prepared before your eyes. You pick one delicious entree like Sweet and Sour Chicken or Beef and Broccoli plus your choice of our steamed or fried rice. With 'just prepared' cooking techniques and authentic flavor, you won’t want to miss.\n Regular Hours of Operations\n Monday - Friday: 10:00 a.m. - 5:00 p.m.\n Saturday - Sunday: Closed\n":
                    latLng = new LatLng(29.189421, -81.049736);
                    map.addMarker(new MarkerOptions()
                            .position(latLng)
                            .title("Pacific Traders"));
                    map.moveCamera(CameraUpdateFactory.newLatLng(latLng));
                    break;
                case "Salsa at the Landing Strip\n\nBurritos, nachos, tacos and quesadillas.  House made salsas prepared fresh daily with authentic Mexican recipes.\n Regular Hours of Operations\n Monday - Friday: 10:00 a.m. - 5:00 p.m.\n Saturday - Sunday: Closed\n":
                    latLng = new LatLng(29.189511, -81.049668);
                    map.addMarker(new MarkerOptions()
                            .position(latLng)
                            .title("Salsa at the Landing Strip"));
                    map.moveCamera(CameraUpdateFactory.newLatLng(latLng));
                    break;
                case "Flight Cafe\n\nCafé is a popular stop wherever it travels, providing quality prepackaged foods as well as complimentary hot and cold beverages.\n Regular Hours of Operations\n Breakfast served until 10:45am:\n Monday - Friday: 7:15 a.m. - 2:30 p.m.\n Saturday - Sunday: Closed\n":
                    latLng = new LatLng(29.187136, -81.050347);
                    map.addMarker(new MarkerOptions()
                            .position(latLng)
                            .title("Flight Cafe"));
                    map.moveCamera(CameraUpdateFactory.newLatLng(latLng));
                    break;
                case "Propeller's Food Truck\n\nBest Burger on Campus Homemade hamburgers, grilled chicken Caesar salads, fried potato wedges, fried chicken... getting hungry yet? Go to Propellers and find out why they call it 'the Best Burger on Campus.'\n Breakfast served until 10am:\n Monday - Friday: 7:00 a.m. - 3:30 p.m.\n Saturday  -Sunday: Closed\n":
                    latLng = new LatLng(29.187136, -81.050347);
                    map.addMarker(new MarkerOptions()
                            .position(latLng)
                            .title("Propellor's Food Truck"));
                    map.moveCamera(CameraUpdateFactory.newLatLng(latLng));
                    break;
                case "UC Food Court (Deli, Grill, Hotline\n\nERAU Classics Line at The Food Court\n A hot buffet of homemade favorites offered daily for breakfast, lunch and dinner.\n Simply to Go\n'Simply To Go' is a comprehensive convenient meal solutions program targeted at customers that need a food fix in a hurry. The grab and go menu consists of sandwiches, salads, parfaits, fresh fruit and desserts.\n Mega Salad Bar\n Made fresh daily with over 50 items including tuna salad, potato salad and spinach with a variety of dressings including low fat options. Three soups made fresh daily including homemade chili and a vegetarian option.\n\n":
                    latLng = new LatLng(29.189532, -81.049403);
                    map.addMarker(new MarkerOptions()
                            .position(latLng)
                            .title("UC Food Court (Deli, Grill, Hotline"));
                    map.moveCamera(CameraUpdateFactory.newLatLng(latLng));
                    break;
                case "The Food Court Deli\n\nLooking for the best sandwich on Campus? This is the place, bring a big appetite!\n":
                    latLng = new LatLng(29.189532, -81.049403);
                    map.addMarker(new MarkerOptions()
                            .position(latLng)
                            .title("The Food Court Deli"));
                    map.moveCamera(CameraUpdateFactory.newLatLng(latLng));
                    break;
                case "Sicilian Grill\n\nOur grill features omelets made to order, gourmet burgers, grilled cheese, french fries,  the best all beef hot dogs on campus, veggie burgers and daily specials.  Sicilian style pizza, stromboli's, calzones, casserettes, and your favorite grilled items prepared the most authentic Italian way. \n Breakfast until 10:45am:\n Monday - Thursday: 7:15 a.m. - 9:00 p.m.\n Friday: 7:15 a.m. - 7:00 p.m.\n Brunch until 2pm:\n Saturday - Sunday: 9:00 a.m. - 7:00 p.m.\n":
                    latLng = new LatLng(29.189518, -81.049327);
                    map.addMarker(new MarkerOptions()
                            .position(latLng)
                            .title("Sicilian Grill"));
                    map.moveCamera(CameraUpdateFactory.newLatLng(latLng));
                    break;
                case "Starbucks\n\nStarbucks purchases and roasts high-quality whole bean coffees and sells them along with fresh, rich-brewed, Italian style espresso beverages, a variety of pastries and confections, and coffee-related accessories and equipment. Starbucks also offers a variety of retail items.\n Regular Hours of Operations\n Monday - Thursday: 7:00 a.m. - 9:00 p.m.\n Friday: 7:00 a.m. - 6:00 p.m.\n Saturday: 2:00 p.m. - 6:00 p.m.\n Sunday: 2:00 p.m. - 9:00 p.m.\n":
                    latLng = new LatLng(29.189751, -81.049679);
                    map.addMarker(new MarkerOptions()
                            .position(latLng)
                            .title("Starbucks"));
                    map.moveCamera(CameraUpdateFactory.newLatLng(latLng));
                    break;
                case "Salad Toss\n\nOver 30 freshly prepared items, vegetables, lettuce and other toppings for you to choose from.  Tossed in front of you with your favorite dressing.  You can add grilled chicken, shrimp, grilled steak and our vegetarian friendly option of tofu if you choose...it's all up to you!\n Regular Hours of Operations\n Monday - Friday: 10:00 a.m. - 5:00 p.m.\n Saturday - Sunday: Closed\n":
                    latLng = new LatLng(29.189478, -81.049288);
                    map.addMarker(new MarkerOptions()
                            .position(latLng)
                            .title("Salad Toss"));
                    map.moveCamera(CameraUpdateFactory.newLatLng(latLng));
                    break;
                case "Honors Program\n\nThe Embry-Riddle Honors Program is highly selective, offering its student members enriched educational experiences. Emphasizing Honors course work in General Education and in the majors, the program involves selected faculty who develop innovative courses and establish mentoring relationships with students. The program is designed to attract and retain top students and to develop their communicative, analytical, critical, and research skills, nurturing a love of life-long learning, leadership and service.":
                    latLng = new LatLng(29.189627,-81.050403);
                    map.addMarker(new MarkerOptions()
                            .position(latLng)
                            .title("Honors Program"));
                    map.moveCamera(CameraUpdateFactory.newLatLng(latLng));
                    break;
                case "Office of Global Engagement\n\nHere at the Office of Global Engagement, we are deeply passionate about helping students experience the world. Studying Abroad is a once-in-a-lifetime opportunity to not only visit another country, but to truly experience its culture. Before picking the right program for you, here are some highlights of studying abroad: Opportunities around the globe, Summer, semester, or yearlong programs, Take courses that apply toward your major, Study in English or the native language, Join the Travelers Club to experience other cultures on campus, Don't be a tourist, live abroad":
                    latLng = new LatLng(29.189512,-81.050300);
                    map.addMarker(new MarkerOptions()
                            .position(latLng)
                            .title("Office of Global Engagement"));
                    map.moveCamera(CameraUpdateFactory.newLatLng(latLng));
                    break;
                case "Fraternity and Sorority Life Office\n\nFraternity & Sorority Life at Embry-Riddle Aeronautical University is part of the Student Engagement and Student Union Office within the Division of Student Affairs. \n Our community consists of fourteen national fraternities and sororities that are supported by three governing councils.":
                    latLng = new LatLng(29.189125,-81.049416);
                    map.addMarker(new MarkerOptions()
                            .position(latLng)
                            .title("Fraternity and Sorority Life Office"));
                    map.moveCamera(CameraUpdateFactory.newLatLng(latLng));
                    break;
                case "WIKD\n\nThe WIKD 102.5 FM is a college radio station owned by Embry-Riddle Aeronautical University. It is Daytona Beach’s only free format radio station playing top 40, alt rock, electronic music, and indie.\n The station is completely student run and funded by students allowing it to be commercial free. In 2013, it launched on iHeart Radio becoming one of the very few college stations to stream on the nation’s most popular streaming app.\n For more information, visit our at www.wikd1025.com. Our meetings are every Wednesday at 7 p.m. in COAS 206.":
                    latLng = new LatLng(29.189100,-81.049418);
                    map.addMarker(new MarkerOptions()
                            .position(latLng)
                            .title("WIKD"));
                    map.moveCamera(CameraUpdateFactory.newLatLng(latLng));
                    break;
                case "The Avion\n\nFounded in the spring of 1969 under the editorship of Ms. Linda Colgan, The Avion Newspaper has a legacy of over 4 decades in the aviation and aerospace industry. Our reporters and photographers have been covering events since the Apollo program and continue to cover routine launches and major events such as advancements in the Orion/SLS program. With connections to NASA, Cape Canaveral Air Force Station, Daytona International Speedway, multiple aviation and air show authorities, and many departments on campus, The Avion provides coverage of all things Embry-Riddle students love.\n Interested in contributing to The Avion Newspaper? Check out our “Join” page to found out how!\n The Avion Newspaper is a division of the Student Government Association and receives all benefits entitled to it under its affiliation.":
                    latLng = new LatLng(29.189084,-81.049334);
                    map.addMarker(new MarkerOptions()
                            .position(latLng)
                            .title("The Avion"));
                    map.moveCamera(CameraUpdateFactory.newLatLng(latLng));
                    break;
                case "Orientation and Family Relations\n\nThrough university-wide collaboration, Orientation programming is designed to set university expectations and guidelines up front, while providing students information on valuable resources, services, support and social networking opportunities that will assist them through their time at ERAU. \n Family Relations serves as a partnership between a student’s support network and the university. The Department of Student Activities & Campus Events provides programs and services designed to enhance the support and retention of students by meeting the educational, informational and involvement needs of their parents, families and guardians.":
                    latLng = new LatLng(29.189104,-81.049271);
                    map.addMarker(new MarkerOptions()
                            .position(latLng)
                            .title("Orientation and Family Relations"));
                    map.moveCamera(CameraUpdateFactory.newLatLng(latLng));
                    break;
                case "Touch-N-Go Productions\n\nTouch-N-Go Productions is the student-run entertainment division of the Student Government Association. As a division of the SGA, we strive toward bringing many different forms of entertainment to campus including magicians, hypnotists, comedians, and more. Touch-N-Go is also pleased to offer support to student organizations and faculty departments on campus. Our organization is comprised of hard working students that constantly work to provide Embry-Riddle with top-of-the-line entertainment. \n Several artists that Touch-N-Go Productions have brought to Embry-Riddle in the past are:\n Gabriel Iglesias, Theory of a Deadman, Stephen Lynch, Daniel Tosh, Bowling for Soup, Carlos Mencia, Bo Burnham, Third Eye Blind, All American Rejects, Lifehouse, and Augustana":
                    latLng = new LatLng(29.189132,-81.049269);
                    map.addMarker(new MarkerOptions()
                            .position(latLng)
                            .title("Touch-N-Go Productions"));
                    map.moveCamera(CameraUpdateFactory.newLatLng(latLng));
                    break;
                case "Student Engagement\n\nThe Department of Student Engagement and Student Union offers educational, cultural, intellectual, recreational, and spiritual programming, as well as opportunities for leadership development, self-governance, and campus entertainment. \n Our department serves the students of ERAU by providing unique co-curricular opportunities, resources, and services to maximize their educational experiences to allow for holistic growth and development. We believe that the involved student demonstrates academic success, is connection to the University through community, and is prepared to obtain their real-world goals. \n Within the department, there are many different offices in which students can be involved. These include: Campus Activities, Civic Engagement/The Volunteer Network, Leadership Development, Club Sports, Fraternity & Sorority Life, International Student Programming, Orientation, Student Government Association, Student Organizations, Student Union.":
                    latLng = new LatLng(29.189132,-81.049269);
                    map.addMarker(new MarkerOptions()
                            .position(latLng)
                            .title("Student Engagement"));
                    map.moveCamera(CameraUpdateFactory.newLatLng(latLng));
                    break;
                case "Student Government Association\n\nThe Student Government Association is committed to serving the ERAU student body through a variety of projects and services. These projects and services are carried out and/or provided by one or more of the following: \n SGA Branches: Student Finance Board, Student Representative Board, Student Court \n SGA Divisions: The Avion (campus newspaper), WIKD 102.5 FM (campus radio station), Touch-N-Go Productions (student entertainment board) \n You can be a part of the SGA through several ways. First-year students are encouraged to join First Year Initiative. Any student can serve on an SGA committee as a Member-At-Large. All students are encouraged to join one of the SGA Divisions. \n For those students interested in holding an elected position, the SGA elections are held during the spring semester. Students can run for positions on the SGA Executive Board, the Student Representative Board, and the Student Finance Board. \n The SGA is always interested in hearing your opinion and can often help you find solutions to campus challenges. Feel free to submit a suggestion in any of the yellow 'Campus Suggestion' boxes or come by the Student Representative Board meetings held on Tuesdays at 12:45 p.m. in the IC Auditorium during the fall and spring semesters.":
                    latLng = new LatLng(29.189132,-81.049269);
                    map.addMarker(new MarkerOptions()
                            .position(latLng)
                            .title("Student Government Association"));
                    map.moveCamera(CameraUpdateFactory.newLatLng(latLng));
                    break;
                case "Office of Undergraduate Research (IGNITE)\n\nNo description given.":
                    latLng = new LatLng(29.190021,-81.051073);
                    map.addMarker(new MarkerOptions()
                            .position(latLng)
                            .title("Office of Undergraduate Research (IGNITE)"));
                    map.moveCamera(CameraUpdateFactory.newLatLng(latLng));
                    break;
                case "Campus Safety and Security\n\nEmbry-Riddle Aeronautical University shares the concern of students and parents regarding safety on our campus. Accordingly, the Campus Safety & Security Department is actively engaged in collaborating with students, parents, employees, and the community to provide a safer University environment and to treat every individual with respect, fairness and compassion. \n While no campus can guarantee the complete safety of its community, we can work together to provide the safest possible environment. We encourage all University community members to start taking responsibility for their own safety and security. By accepting this responsibility, members of the University community assist in maintaining a safer and more secure academic environment. \n The University complies with the federally mandated Jeanne Clery Disclosure of Campus Security Police and Campus Statistics Act, state requirements for an Annual Assessment of Physical Plant Safety and the three-year compilation of annual crime statistics. A report is sent to the State Department of Education and the U.S. Department of Education. Crime Statistics are compiled from Campus Safety & Security incident reports and monitoring with Daytona Beach Police Department. The statistics are prepared on a calendar year basis and then used to produce the tables found in this report. \n A copy of this crime statistics report can be obtained in the Campus Safety & Security Administrative Office.":
                    latLng = new LatLng(29.190515,-81.053175);
                    map.addMarker(new MarkerOptions()
                            .position(latLng)
                            .title("Campus Safety and Security"));
                    map.moveCamera(CameraUpdateFactory.newLatLng(latLng));
                    break;
                case "Financial Aid\n\nWhen it comes to educational funding, you have options. You don’t have to let tuition fees deter you from pursuing your goals or living your dreams. A degree from Embry-Riddle Aeronautical University is more than a piece of paper. It is an investment into your future. Our Financial Aid representatives are here to help you and your family leverage the variety of scholarships, loans, and financial aid programs designed to fund that investment.":
                    latLng = new LatLng(29.188323,-81.053174);
                    map.addMarker(new MarkerOptions()
                            .position(latLng)
                            .title("Financial Aid"));
                    map.moveCamera(CameraUpdateFactory.newLatLng(latLng));
                    break;
                case "Student Financial Services (Bursar's Office)\n\nThe Student Financial Services Department (formerly Bursar Department) strives to provide efficient, courteous and excellent customer service to our students, parents, staff and faculty. We offer a variety of services which are accessible online, or in person. Some of these services include: Billing of Tuition and Fees , Student Refunds, Payment Plan, Payroll Check Cashing.":
                    latLng = new LatLng(29.188344,-81.053047);
                    map.addMarker(new MarkerOptions()
                            .position(latLng)
                            .title("Student Financial Services"));
                    map.moveCamera(CameraUpdateFactory.newLatLng(latLng));
                    break;
                case "Student Employment\n\nThe Student Employment Office provides assistance to students seeking part-time employment on or off campus. On-campus employment is available to students regardless of financial need. Working on or off campus not only gives students more financial support, but also helps them develop self-confidence, gain valuable employment and credit references, establish a work record, and acquire useful skills in time management, financial planning, and communication. Once students are registered at the Daytona Beach Campus they may seek employment by visiting our office or by viewing all available positions via our online system. Students must provide original documentation to prove identity and employment eligibility prior to employment.":
                    latLng = new LatLng(29.188344,-81.053047);
                    map.addMarker(new MarkerOptions()
                            .position(latLng)
                            .title("Student Employment"));
                    map.moveCamera(CameraUpdateFactory.newLatLng(latLng));
                    break;
                case "Mail Center\n\nWe provide convenience and assistance for all mail & shipping service needs to administration, faculty, staff & students ~ We sort & deliver all Incoming mail & packages to campus departments and student mailboxes and receive & process all Outgoing mail & packages from campus departments in a safe, secure, accurate & timely manner ~ We provide our Counter customers with the most cost effective, reliable and secure method for their Outgoing postal and shipping needs ~ We provide our students with excellent counter services by assisting them with package pickup, mailbox assignments, mail forwarding, replacement keys, tracking requests and general questions. \n The Mail Center Staff is a committed and dedicated group and would like to thank you for allowing us to provide you with your postal & shipping needs.........":
                    latLng = new LatLng(29.188616,-81.053158);
                    map.addMarker(new MarkerOptions()
                            .position(latLng)
                            .title("Mail Center"));
                    map.moveCamera(CameraUpdateFactory.newLatLng(latLng));
                    break;
                case "Housing and Residence Life\n\nThe Department of Housing & Residence Life at Embry-Riddle Aeronautical University Daytona Beach is committed to supporting students' personal and professional growth as part of their residential experience. We create positive, safe, and inclusive residence hall communities that promote a culture of academic achievement. \n Living on campus is an exciting opportunity for Embry-Riddle students. All first-year students live on campus with a lot of sophomores, juniors, and seniors. Our seven residence halls and one off-campus housing complex are a great way to make positive and lasting friendships. In the words of a student of Class 2016, 'Without a doubt, living on campus is my favorite thing about being in college.' \n Living with other students in the residence halls can also be an important part of your academic success at Embry-Riddle. It's a fact that students who live in campus housing have a higher grade point average and are more likely to be successful overall in college than students who live off campus. \n Housing & Residence Life staff strive to support a positive living and learning environment through programs and events in the residence halls. Living on campus will keep you involved with the university and connected to new friends.":
                    latLng = new LatLng(29.192801,-81.053131);
                    map.addMarker(new MarkerOptions()
                            .position(latLng)
                            .title("Housing and Residence Life"));
                    map.moveCamera(CameraUpdateFactory.newLatLng(latLng));
                    break;
                case "Counseling Center\n\nThe mission of the Counseling Center is to enhance the psychological well-being of Embry-Riddle students. We believe that good mental health is a key part of overall health, and good health is essential to academic success. We support students' academic experience by providing brief mental health counseling and consultation services that help them identify barriers, improve coping, and achieve personal and academic goals. \n College life is challenging and can be stressful. At some point during the college years, many students feel anxious, depressed, uncertain, confused, or overwhelmed. They may seek help from friends, family members, significant others, but in some cases, help from a trained professional is beneficial. The Counseling Center provides a calm, friendly, and supportive environment for students to address any issue or concern. \n Counseling is confidential and offered free of charge. All counseling contacts are strictly confidential in accordance with Florida state privacy laws. Counseling records are not available to anyone, either on or off campus, without the student's specific written permission. The only time confidentiality will be broken is in the case of a life threatening situation: when a student is at risk of harming him or herself or someone else, or when there is reason to believe a child, elderly person, or disabled person is being abused. Confidentiality may also be broken if a court order mandates that records be released. NOTE: Counseling notes are not part of a student's University records.":
                    latLng = new LatLng(29.191671,-81.050520);
                    map.addMarker(new MarkerOptions()
                            .position(latLng)
                            .title("Counseling Center"));
                    map.moveCamera(CameraUpdateFactory.newLatLng(latLng));
                    break;
                case "Dean of Students\n\nThe Dean of Students Office has responsibility for developing and implementing a comprehensive student development program to strengthen students experience outside the classroom. Areas reporting to the Dean of Students Office are: Student Activities & Campus Events, University Student Center, MyVets, Counseling Center, Health Services, Disability Services, and Conference Services. Three Pillars form the foundation for our work: Safe Eagle, Honor Code, and Soar. Using the foundation of these pillars we encourage empowerment through leadership, civility, integrity, respect and inclusion. “Students First” is our motto. That belief is our guide as we provide advocacy, guidance and resources for all Embry-Riddle Students. \n Campus Awareness, Response, and Evaluation Team (C.A.R.E) \n The mission of the C.A.R.E. Team is to collaboratively address and respond to issues concerning the health, safety, and well-being of ERAU students. The C.A.R.E. Team meets regularly to identify, assess, and respond to students of concern and/or potential threats to the campus community. Toward that end, Embry-Riddle should support a culture of reporting “see something, say something” and utilize the C.A.R.E. Team appropriately for a safer community.":
                    latLng = new LatLng(29.189193,-81.049250);
                    map.addMarker(new MarkerOptions()
                            .position(latLng)
                            .title("Dean of Students"));
                    map.moveCamera(CameraUpdateFactory.newLatLng(latLng));
                    break;
                case "Office of Records and Registration\n\nThe Office of Records and Registration provides academic support for students from matriculation through graduation and beyond. Our team ensures accuracy and confidentiality of academic records while providing appropriate access to students, faculty, and other internal and external constituents. We strive for continuous improvement by embracing emerging technologies and best practices in enrollment, records maintenance, reporting, and policy interpretation and implementation. Our focus on exemplary student service contributes to the success of our students in their academic pursuits and in their future careers as global citizens and leaders in the aerospace industry. \n Spring & Fall hours: 8:00am to 4:00pm \n Summer hours: 8:00am to 5:00pm":
                    latLng = new LatLng(29.189857,-81.049729);
                    map.addMarker(new MarkerOptions()
                            .position(latLng)
                            .title("Office of Records and Registration"));
                    map.moveCamera(CameraUpdateFactory.newLatLng(latLng));
                    break;
                case "EAGLECard\n\nYour EAGLE Card is your Embry-Riddle Aeronautical University identification card, access card and debit card. It's an easy and safe way to make purchases and use services on campus and at select off-campus merchants. Students can manage their EAGLEcard accounts in Blackboard. Just log in to ERNIE, click on ‘Go to Blackboard home’, and select the EAGLEcard tab. Guest Deposits can be made by going to Guest Deposit - ERAU EAGLEcard. \n IT'S AN ID CARD \n Your EAGLEcard will identify you as an affiliate of the University, whether as a student, visitor, contractor or faculty/staff member. The ID will be required to be presented whenever you attend any school activity, library, events, games, voting, or department for services. \n IT'S AN ACCESS CARD \n If you reside in on-campus University Housing, your will proEAGLEcard vide you access to the residence halls. Also, certain labs and buildings require the use of an EAGLEcard for entry. \n IT'S A DEBIT CARD \n Your EAGLEcard has several debit accounts on it and they are managed by the University. It has EAGLE Dollars, Meal Plan, and Riddle Bucks all on one Card. Debit Card purchases can be made at: the University Bookstore, vending machines, copiers, Food Courts, laundry machines, the Einstein Brothers Bagel Shop in the Student Village, the Flight Payment Station and at select off-campus merchants.":
                    latLng = new LatLng(29.189344,-81.049011);
                    map.addMarker(new MarkerOptions()
                            .position(latLng)
                            .title("EAGLECard"));
                    map.moveCamera(CameraUpdateFactory.newLatLng(latLng));
                    break;
                case "Career Services\n\nExplore all the ways in which you can develop your career insight, co-op/internship prowess and job search expertise. \n One-on-one career advising: schedule an appointment to meet your Program Manager to collaborate on your career development \n EagleHire access: activate your account as soon as you start classes, all new ERAU students; for our continuing students, actively utilize the system for your co-op/internship and/or full-time job search with single sign-on access through ERNIE! \n Cooperative Education/Internship Program: statistics have proven the importance of completing co-ops and internships; start the process of preparing for the experiential opportunity by meeting with your Program Manager, even if you are only just considering a co-op or internship \n On-campus interviews and information sessions: great companies come to campus year-around to host events and conduct interviews \n Annual Industry/Career Expo: this exhibition brings many employers to campus in the fall and serves as a springboard for additional recruiting events such as information sessions and interviews\n Resume and cover letter critiques: get feedback on your job search documentation, which is imperative to do before you submit anything to potential employers.":
                    latLng = new LatLng(29.189100,-81.049443);
                    map.addMarker(new MarkerOptions()
                            .position(latLng)
                            .title("Career Services"));
                    map.moveCamera(CameraUpdateFactory.newLatLng(latLng));
                    break;
                case "Chaplain's Office\n\nThe Center for Faith and Spirituality hosts two spiritual leaders, Reverend David Keck and Father Tim Daly, on campus. The Chaplains support all students, faculty, and staff regardless of their religious beliefs. Students can visit the Chaplains when they want to discuss spirituality, moral questions, or life's challenges.":
                    latLng = new LatLng(29.189470,-81.051047);
                    map.addMarker(new MarkerOptions()
                            .position(latLng)
                            .title("Chaplain's Office"));
                    map.moveCamera(CameraUpdateFactory.newLatLng(latLng));
                    break;
                case "Dining Services\n\nWhile you’re busy attending classes, studying, working or participating in activities, finding great food is never a problem with Dining Services at the Embry-Riddle Daytona Beach campus. Dining options ranging from Einstein Bagels to an all-you-can-eat buffet are conveniently located near dorms and classrooms and open from 7 a.m. to as late as 1 a.m. Low-fat, vegetarian, vegan, and kosher meals are available, and a variety of dining plans are available to fit every student’s needs.":
                    latLng = new LatLng(29.189415,-81.04952);
                    map.addMarker(new MarkerOptions()
                            .position(latLng)
                            .title("Dining Services"));
                    map.moveCamera(CameraUpdateFactory.newLatLng(latLng));
                    break;
                case "Embry-Riddle Language Institute (ERLI)\n\nThe Embry-Riddle Language Institute is an intensive English program providing English language instruction and cultural orientation to non-native speakers of English with 6 starting points per year (2 in spring, 2 in summer, and 2 in fall) \n If you desire to become more proficient in listening, speaking, reading, and writing the English language, this intensive English program is for you. \n Students can be granted full admission to the university pending completion of the program and/or a passing TOEFL or IELTS score, assuming they meet all other university admission requirements. Concurrent enrollment in ERLI and the university is also available for eligible students. \n Other benefits of our program include field trips and social events through the International Student Programming Council, full access to Embry-Riddle Aeronautical University facilities, and special topics courses such as Aviation Topics and Academic Topics. \n ERLI provides English language instruction to students who are not college bound.":
                    latLng = new LatLng(29.189442,-81.050196);
                    map.addMarker(new MarkerOptions()
                            .position(latLng)
                            .title("Embry-Riddle Language Institute"));
                    map.moveCamera(CameraUpdateFactory.newLatLng(latLng));
                    break;
                case "First Year Programs\n\nFirst Year Programs consists of a team of academic advisors, peer mentors and student ambassadors, dedicated to helping new students at Embry-Riddle. We work together with faculty, staff and parents, focusing on student success: academically, professionally and socially. \n Through various programs and activities, we guide students through their transition to university life. First Year Programs helps students find what they need to be successful at Embry-Riddle and in life.":
                    latLng = new LatLng(29.188246,-81.050438);
                    map.addMarker(new MarkerOptions()
                            .position(latLng)
                            .title("First Year Programs"));
                    map.moveCamera(CameraUpdateFactory.newLatLng(latLng));
                    break;
                case "Lost and Found\n\nNo description given.":
                    latLng = new LatLng(29.189344,-81.049011);
                    map.addMarker(new MarkerOptions()
                            .position(latLng)
                            .title("Lost and Found"));
                    map.moveCamera(CameraUpdateFactory.newLatLng(latLng));
                    break;
                case "Intramural and Recreational Sports\n\nThe Intramural and Recreational Sports Department is made up of Intramural Sports, Fitness Center, Sports Complex Facilities (ICI Center & Outdoor Sports Facilities). \n In addition to those areas we also sell discount theme park tickets. Such as: Disney, Universal, Busch Gardens, Sea World, Wet n' Wild, Daytona Lagoon, Kennedy Space Center, Medieval Times, Sleuths Mystery Dinner Show, Blue Man Group, Legoland, Crayola Crayon \n Ticket availability is limited. We accept cash & checks from faculty & staff. We only cash from students. Bring your Eagle Card! To check on ticket availability & prices call 386-226-6530. \n Lastly, at our front desk area you can check out sports equipment. We have: Basketballs, Camping Tents, Coolers, Footballs, Racquetball Racquets, Soccer Balls, Tennis Racquets, Tug of War Rope. \n To check something out all you have to do is go to the front desk of the ICI Center during business hours with your Eagle Card. For more information call 386-226-7731.":
                    latLng = new LatLng(29.191090,-81.046958);
                    map.addMarker(new MarkerOptions()
                            .position(latLng)
                            .title("Intramural and Recreational Sports"));
                    map.moveCamera(CameraUpdateFactory.newLatLng(latLng));
                    break;
                case "Academic Advancement Center\n\nThe Academic Advancement Center offers free tutoring in math, physics, chemistry and writing to undergraduate students in general education courses. We are now located on the 1st floor of the College of Arts and Sciences (under the dome).":
                    latLng = new LatLng(29.188442,-81.048199);
                    map.addMarker(new MarkerOptions()
                            .position(latLng)
                            .title("Academic Advancement Center"));
                    map.moveCamera(CameraUpdateFactory.newLatLng(latLng));
                    break;
            }
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
                convertView.setClickable(false);
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
                convertView.setClickable(false);
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
        this.map = googleMap;
        if (ContextCompat.checkSelfPermission(MainActivity.context, Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED) {
            map.setMyLocationEnabled(true);
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
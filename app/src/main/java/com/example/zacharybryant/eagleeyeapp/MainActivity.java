package com.example.zacharybryant.eagleeyeapp;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;

/**
 * Main activity for the application
 * @author Shawn
 * @version 1.0

 */
public class MainActivity extends AppCompatActivity {

    private BottomNavigationView mBottomNav;
    private int mSelectedItem;

    /**
    * Listener to change active fragment
     */
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment fragment = null;
            if(getSupportFragmentManager().getFragments()!=null && getSupportFragmentManager().getFragments().get(0)!=null) {
                Log.d("Fragments", getSupportFragmentManager().getFragments().toString());
                getSupportFragmentManager().beginTransaction().remove(getSupportFragmentManager().findFragmentById(R.id.container)).commit();
            }
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    fragment = new ListFrag();
                    break;
                case R.id.navigation_dashboard:
                    fragment = new scheduleFrag();
                    break;
                case R.id.navigation_notifications:
                    break;
            }

            mSelectedItem = item.getItemId();

            for(int i=0;i<mBottomNav.getMenu().size();i++){
                MenuItem menuItem = mBottomNav.getMenu().getItem(i);
                menuItem.setChecked(menuItem.getItemId() == item.getItemId());
            }


            if(fragment!=null){
                FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                ft.add(R.id.container,fragment,fragment.getTag());
                ft.commit();
            }

            return true;
        }

    };

    /**
     * Sets up main activity, starting on the list fragment.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mBottomNav = (BottomNavigationView) findViewById(R.id.navigation);
        mBottomNav.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        mOnNavigationItemSelectedListener.onNavigationItemSelected(mBottomNav.getMenu().getItem(0));
        mBottomNav.getMenu().getItem(0).setChecked(true);
    }

}

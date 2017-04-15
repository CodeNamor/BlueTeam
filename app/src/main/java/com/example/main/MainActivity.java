package com.example.main;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.app.SearchManager;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.addition.AdditionFragment;
import com.example.main.R;
import com.example.multiplication.MultiplicationFragment;
import com.example.temperature.TemperatureFragment;

import java.util.Locale;

/**
 * Created by JacobKapp on 4/8/2017.
 */

public class MainActivity extends AppCompatActivity {

    // Variable Declaration

    private DrawerLayout mDrawerLayout;
    private ListView mDrawerList;
    private CharSequence mDrawerTitle;
    private CharSequence mTitle;
    private ActionBarDrawerToggle mDrawerToggle;


    private FragmentManager.OnBackStackChangedListener mOnBackStackChangedListener = new FragmentManager.OnBackStackChangedListener() {
        @Override
        public void onBackStackChanged() {
            int stackHeight = getSupportFragmentManager().getBackStackEntryCount();
            if (stackHeight > 0) { // if we have something on the stack (doesn't include the current shown fragment)
                getSupportActionBar().setHomeButtonEnabled(true);
                getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            } else {
                getSupportActionBar().setDisplayHomeAsUpEnabled(false);
                getSupportActionBar().setHomeButtonEnabled(false);
            }

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_ACTION_BAR);
        setContentView(R.layout.activity_main);

        mTitle = mDrawerTitle = getTitle();

        String[] calculatorArray = getResources().getStringArray(R.array.calculator_array);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerList = (ListView) findViewById(R.id.navList);

        // set a custom shadow that overlays the main content when the drawer opens
        mDrawerLayout.setDrawerShadow(R.drawable.drawer_shadow, GravityCompat.START);
        // Set the adapter for the list view
        mDrawerList.setAdapter(new ArrayAdapter<String>(this, R.layout.nav_list_item, calculatorArray));
        // Set the list's click listener
        mDrawerList.setOnItemClickListener(new MainActivity.DrawerItemClickListener());

        ActionBar actionBar = getSupportActionBar();

        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeButtonEnabled(true);

        // ActionBarDrawerToggle ties together the the proper interactions
        // between the sliding drawer and the action bar app icon

        mDrawerToggle = new ActionBarDrawerToggle(
                this,                  /* host Activity */
                mDrawerLayout,         /* DrawerLayout object */
                R.string.drawer_open,  /* "open drawer" description for accessibility */
                R.string.drawer_close  /* "close drawer" description for accessibility */
        ) {
            @Override
            public void onDrawerClosed(View view) {

                syncActionBarArrowState();
                invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
            }
            @Override
            public void onDrawerOpened(View drawerView) {

                mDrawerToggle.setDrawerIndicatorEnabled(true);
                invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
            }
        };


        Button additionButton = (Button) findViewById(R.id.additionButton);
        additionButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                selectItem(((TextView)v).getText().toString());
            }
        });

        Button divisionButton = (Button) findViewById(R.id.divisionButton);
        divisionButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                selectItem(((TextView)v).getText().toString());
            }
        });
        mDrawerLayout.addDrawerListener(mDrawerToggle);
        getFragmentManager().addOnBackStackChangedListener(mOnBackStackChangedListener);
        selectItem("Menu");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.addition, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (mDrawerToggle.isDrawerIndicatorEnabled() &&
                mDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        switch (item.getItemId()) {
            // Handle home button in non-drawer mode
            case android.R.id.home:
                // Use getSupportFragmentManager() to support older devices
                FragmentManager fragmentManager = getFragmentManager();
                fragmentManager.popBackStack();
                // Make sure transactions are finished before reading backstack count
                fragmentManager.executePendingTransactions();
                if (fragmentManager.getBackStackEntryCount() < 1) {
                    mDrawerToggle.setDrawerIndicatorEnabled(true);
                }
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    protected void onDestroy() {
        getFragmentManager().removeOnBackStackChangedListener(mOnBackStackChangedListener);
        super.onDestroy();
    }

    private void syncActionBarArrowState() {
        int backStackEntryCount =
                getSupportFragmentManager().getBackStackEntryCount();
        mDrawerToggle.setDrawerIndicatorEnabled(backStackEntryCount == 0);
    }

    private class DrawerItemClickListener implements ListView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            String selectedText = ((TextView)view).getText().toString();
            selectItem(selectedText);
        }
    }

    /* Called whenever we call invalidateOptionsMenu() */
    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        // If the nav drawer is open, hide action items related to the content view
        boolean drawerOpen = mDrawerLayout.isDrawerOpen(mDrawerList);
        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        // Sync the toggle state after onRestoreInstanceState has occurred.
        mDrawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        // Pass any configuration change to the drawer toggle
        mDrawerToggle.onConfigurationChanged(newConfig);
    }
    /** Swaps fragments in the main content view */
    private void selectItem(String selectedText) {
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction ft = fragmentManager.beginTransaction();

        ft.hide(fragmentManager.findFragmentById(R.id.addition_fragment));
        ft.hide(fragmentManager.findFragmentById(R.id.division_fragment));
        ft.hide(fragmentManager.findFragmentById(R.id.multiplication_fragment));
        ft.hide(fragmentManager.findFragmentById(R.id.temperature_fragment));
        ft.hide(fragmentManager.findFragmentById(R.id.menu_fragment));
        switch (selectedText){
            case "Addition":
                ft.show(fragmentManager.findFragmentById(R.id.addition_fragment));
                break;
            case "Division":
                ft.show(fragmentManager.findFragmentById(R.id.division_fragment));
                break;
            case "Subtraction":
                //ft.show(fragmentManager.findFragmentById(R.id.subtraction_fragment));
                break;
            case "Menu":
                ft.show(fragmentManager.findFragmentById(R.id.menu_fragment));
                break;
            default:
                ft.show(fragmentManager.findFragmentById(R.id.menu_fragment));
                break;
        }
        setTitle(selectedText);
        ft.commit();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public void setTitle(CharSequence title) {
        mTitle = title;
        getSupportActionBar().setTitle(mTitle);
    }


}

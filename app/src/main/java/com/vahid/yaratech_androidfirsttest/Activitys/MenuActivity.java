package com.vahid.yaratech_androidfirsttest.Activitys;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentManager;
import android.view.Menu;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.LinearLayout;

import com.vahid.yaratech_androidfirsttest.Fragments.AboutFragment;
import com.vahid.yaratech_androidfirsttest.Fragments.CategoryFragment;
import com.vahid.yaratech_androidfirsttest.Fragments.ContactFragment;
import com.vahid.yaratech_androidfirsttest.Fragments.RegisterFragment;
import com.vahid.yaratech_androidfirsttest.R;
import com.vahid.yaratech_androidfirsttest.Retrofit.MainActivity;

public class MenuActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    BottomNavigationView navigation;
    LinearLayout linearLayout;
    FragmentManager fragmentManager;
    Toolbar toolbar;
    Menu menu;
    MenuItem menuItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        getWindow().getDecorView().setLayoutDirection(View.LAYOUT_DIRECTION_RTL);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        linearLayout = (LinearLayout) findViewById(R.id.fragment_container);
        navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        menu = navigation.getMenu();

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_profile) {

            toolbar.setTitle(R.string.profile);
        } else if (id == R.id.nav_about_us) {
            //change title of screen
            toolbar.setTitle(R.string.about_us);
            AboutFragment aboutFragment = new AboutFragment();
            fragmentManager = getSupportFragmentManager();
            //replace fragment in menuscreen
            fragmentManager.beginTransaction()
                    .replace(R.id.menu_container, aboutFragment)
                    .commit();
        } else if (id == R.id.nav_connect_with_us) {
            toolbar.setTitle(R.string.connect_with_us);
            ContactFragment contactFragment = new ContactFragment();
            fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction()
                    .replace(R.id.menu_container, contactFragment)
                    .commit();


        } else if (id == R.id.form_activity) {
            Intent intent = new Intent(MenuActivity.this, FormActivity.class);
            startActivity(intent);
        } else if (id == R.id.retrofit_activity) {
            Intent intent = new Intent(MenuActivity.this, MainActivity.class);
            startActivity(intent);
        }


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_menu:
                    toolbar.setTitle(R.string.title_menu);
                    menuItem = menu.getItem(0);
                    menuItem.setChecked(true);
                    RegisterFragment registerFragment = new RegisterFragment();
                    FragmentManager fragmentManager1 = getSupportFragmentManager();
                    fragmentManager1.beginTransaction()
                            .replace(R.id.fragment_container, registerFragment)
                            .commit();

                    break;
                case R.id.navigation_category:
                    menuItem = menu.getItem(1);
                    menuItem.setChecked(true);
                    toolbar.setTitle(R.string.title_category);
                    CategoryFragment categoryFragment = new CategoryFragment();
                    FragmentManager fragmentManager2 = getSupportFragmentManager();
                    fragmentManager2.beginTransaction()
                            .replace(R.id.fragment_container, categoryFragment)
                            .commit();

                    break;
            }
            return false;
        }
    };
}

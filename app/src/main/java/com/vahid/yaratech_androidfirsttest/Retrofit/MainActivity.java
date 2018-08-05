package com.vahid.yaratech_androidfirsttest.Retrofit;

import android.app.Activity;
import android.support.v4.app.Fragment;
import android.content.SharedPreferences;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.vahid.yaratech_androidfirsttest.R;

public class MainActivity extends AppCompatActivity implements UsersFragment.OnClick {

    FragmentManager manager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("Retrofit Activity");
        setFragment(UsersFragment.newInstance());
    }

    public void setFragment(Fragment fragment) {
        manager = getSupportFragmentManager();
        if (fragment instanceof UsersFragment) {
            manager.beginTransaction()
                    .replace(R.id.fragment_container, fragment)
                    .commit();
        }
        if (fragment instanceof CommentsFragment) {
            manager.beginTransaction()
                    .replace(R.id.fragment_container, fragment)
                    .commit();
        }
    }

    @Override
    public void showComments(int id) {
        setFragment(CommentsFragment.newInstance(id));
    }
}

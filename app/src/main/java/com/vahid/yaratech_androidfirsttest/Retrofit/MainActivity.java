package com.vahid.yaratech_androidfirsttest.Retrofit;

import android.app.Activity;
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
        UsersFragment usersFragment = new UsersFragment();
        manager = getSupportFragmentManager();
        manager.beginTransaction()
                .replace(R.id.fragment_container, usersFragment)
                .commit();

    }

    @Override
    public void showComments(int id) {

        Log.e("main",id+"");
        SharedPreferences sp = getSharedPreferences("user_id", Activity.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putInt("id_value", id);
        editor.commit();

        CommentsFragment commentsFragment = new CommentsFragment();
        manager.beginTransaction()
                .replace(R.id.fragment_container, commentsFragment)
                .commit();

    }
}

package com.vahid.yaratech_androidfirsttest.retrofit;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.vahid.yaratech_androidfirsttest.R;
import com.vahid.yaratech_androidfirsttest.retrofit.ui.comment.CommentsFragment;
import com.vahid.yaratech_androidfirsttest.retrofit.ui.user.UsersFragment;

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

package com.vahid.yaratech_androidfirsttest.Activitys;

import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.vahid.yaratech_androidfirsttest.Fragments.ButtonFragment;
import com.vahid.yaratech_androidfirsttest.Fragments.FamilyFragment;
import com.vahid.yaratech_androidfirsttest.Fragments.NameFragment;
import com.vahid.yaratech_androidfirsttest.R;

public class FormActivity extends AppCompatActivity implements NameFragment.name, FamilyFragment.family, ButtonFragment.button {

    String name, family;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form);
        getWindow().getDecorView().setLayoutDirection(View.LAYOUT_DIRECTION_RTL);

        NameFragment nameFragment = new NameFragment();
        FamilyFragment familyFragment = new FamilyFragment();
        ButtonFragment buttonFragment = new ButtonFragment();
        FragmentManager manager = getSupportFragmentManager();
        manager.beginTransaction().add(R.id.name_container, nameFragment)
                .commit();
        manager.beginTransaction().add(R.id.family_container, familyFragment)
                .commit();
        manager.beginTransaction().add(R.id.button_container, buttonFragment)
                .commit();

    }

    @Override
    public void buttonPressed() {
        Toast.makeText(getApplicationContext(),name + " " + family, Toast.LENGTH_LONG ).show();
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public void setFamily(String family) {
        this.family = family;
    }
}

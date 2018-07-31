package com.vahid.yaratech_androidfirsttest.Activitys;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.vahid.yaratech_androidfirsttest.Models.User;
import com.vahid.yaratech_androidfirsttest.R;

import org.parceler.Parcels;

public class InfoActivity extends AppCompatActivity {

    TextView username, family, email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);

        username = (TextView) findViewById(R.id.name);
        family = (TextView) findViewById(R.id.family);
        email = (TextView) findViewById(R.id.email);

        Intent intent = getIntent();

        User user = Parcels.unwrap(intent.getParcelableExtra("user_data"));

//        User user = Parcels.unwrap(getIntent().getParcelableExtra("user_data"));
        username.setText("نام : " + user.getName());
        family.setText("فامیل : " + user.getFamilyName());
        email.setText("ایمیل : " + user.getEmail());
    }
}

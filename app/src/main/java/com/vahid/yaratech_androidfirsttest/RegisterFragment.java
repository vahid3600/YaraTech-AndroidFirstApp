package com.vahid.yaratech_androidfirsttest;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import org.parceler.Parcels;

public class RegisterFragment extends Fragment {

    EditText name, family, email;
    Button enter, cancel;
    ImageView logo;

    public RegisterFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_register, container, false);
        logo = (ImageView)view.findViewById(R.id.logo);
        name = (EditText)view.findViewById(R.id.name);
        family = (EditText)view.findViewById(R.id.family);
        email = (EditText)view.findViewById(R.id.email);
        enter = (Button)view.findViewById(R.id.enter);
        cancel = (Button)view.findViewById(R.id.cancel);


        if (isOnline()) {
            Glide.with(this).load("http://yaramobile.com/templates/sj_hexagon/images/styling/blue/logo.png")
                    .into(logo);
        }
        else
            Toast.makeText(view.getContext(),"برای نمایش عکس اینترنت خود را فعال کنید!", Toast.LENGTH_LONG).show();

        enter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), InfoActivity.class);
                User user = new User(
                        name.getText().toString(),
                        family.getText().toString(),
                        email.getText().toString());
                intent.putExtra("user_data", Parcels.wrap(user));
                startActivity(intent);
            }
        });
        return view;

    }

    protected boolean isOnline() {
        ConnectivityManager connectivityManager = (ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = connectivityManager.getActiveNetworkInfo();
        return netInfo != null && netInfo.isConnectedOrConnecting();
    }



}

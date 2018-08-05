package com.vahid.yaratech_androidfirsttest.Retrofit;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.vahid.yaratech_androidfirsttest.R;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class UsersFragment extends Fragment implements UserRecyclerViewAdapter.ItemClickListener {

    ProgressBar progressBar;
    RecyclerView recyclerView;
    List<User> usersList = new ArrayList<>();

    public UsersFragment() {
        // Required empty public constructor
    }

    public static UsersFragment newInstance() {
        Bundle bundle = new Bundle();
        UsersFragment usersFragment = new UsersFragment();
        usersFragment.setArguments(bundle);
        return usersFragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_users, container, false);
        recyclerView = view.findViewById(R.id.users_recyclerview);
        //set a progress bar when get data from net
        progressBar = view.findViewById(R.id.progressbar);
        progressBar.setVisibility(View.GONE);
        // set rows of recycleview orientation vertical
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(
                getContext(),
                LinearLayoutManager.VERTICAL,
                false);
        recyclerView.setLayoutManager(linearLayoutManager);
        //method to get data and show in recyclerview
        getData();
        return view;
    }

    private void getData() {
        progressBar.setVisibility(View.VISIBLE);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://jsonplaceholder.typicode.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        Services services = retrofit.create(Services.class);
        Call<List<User>> users = services.listUsers();
        users.enqueue(new Callback<List<User>>() {
            @Override
            public void onResponse(Call<List<User>> call, Response<List<User>> response) {
                progressBar.setVisibility(View.GONE);
                if (response.isSuccessful()) {
                    usersList = response.body();
                    UserRecyclerViewAdapter userRecyclerViewAdapter = new UserRecyclerViewAdapter(
                            getContext(),
                            UsersFragment.this,
                            usersList);
                    recyclerView.setAdapter(userRecyclerViewAdapter);
                } else {
                    Log.e("tag", response.errorBody() + "");
                }
            }

            @Override
            public void onFailure(Call<List<User>> call, Throwable t) {
                Log.e("tag", t.toString() + "");
            }
        });
    }

    @Override
    public void onItemClick(View view, int position) {
        ((UsersFragment.OnClick) getContext()).showComments(usersList.get(position).getId());
        Log.e("fragment", position + "");

    }

    public interface OnClick {
        void showComments(int id);
    }
}

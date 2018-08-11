package com.vahid.yaratech_androidfirsttest.Retrofit.ui.user;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.vahid.yaratech_androidfirsttest.R;
import com.vahid.yaratech_androidfirsttest.Retrofit.data.model.User;

import java.util.ArrayList;
import java.util.List;

public class UsersFragment extends Fragment implements UserContract.View, UserRecyclerViewAdapter.ItemClickListener {

    ProgressBar progressBar;
    RecyclerView recyclerView;
    UserRecyclerViewAdapter userRecyclerViewAdapter;
    UserContract.Presenter presenter;
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
        presenter = new UserPresenter(getContext(), this);
        recyclerView.setLayoutManager(linearLayoutManager);
        userRecyclerViewAdapter = new UserRecyclerViewAdapter(
                getContext(),
                UsersFragment.this);
        recyclerView.setAdapter(userRecyclerViewAdapter);
        getData();
        return view;
    }

    private void getData() {
        presenter.fetchUserFromRemote();

    }

    @Override
    public void onItemClick(View view, int position) {
        ((UsersFragment.OnClick) getContext()).showComments(usersList.get(position).getId());
        Log.e("fragment", position + "");

    }

    @Override
    public void showListUser(List<User> users) {
        userRecyclerViewAdapter.addData(users);
    }

    @Override
    public void showMessage(String msg) {
        Toast.makeText(getContext(), msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showLoading() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoading() {
        progressBar.setVisibility(View.GONE);
    }

    public interface OnClick {
        void showComments(int id);
    }
}

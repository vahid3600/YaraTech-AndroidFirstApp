package com.vahid.yaratech_androidfirsttest.Retrofit;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
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

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CommentsFragment extends Fragment {

    ProgressBar progressBar;
    RecyclerView recyclerView;

    public CommentsFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_comments, container, false);
        progressBar = view.findViewById(R.id.progressbar);
        progressBar.setVisibility(View.GONE);
        SharedPreferences sp = this.getActivity().getSharedPreferences("user_id", Activity.MODE_PRIVATE);
        int id = sp.getInt("id_value", -1);
        recyclerView = view.findViewById(R.id.comments_recyclerview);
        getData(id);
        return view;
    }

    private void getData(int id) {
        progressBar.setVisibility(View.VISIBLE);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://jsonplaceholder.typicode.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        Services service = retrofit.create(Services.class);
        Call<List<Comment>> comments = service.listComments(id+"");
        comments.enqueue(new Callback<List<Comment>>() {
            @Override
            public void onResponse(Call<List<Comment>> call, Response<List<Comment>> response) {
                progressBar.setVisibility(View.GONE);
                if (response.isSuccessful()){
                    List<Comment> commentList = response.body();
                    CommentRecyclerViewAdapter commentRecyclerViewAdapter = new CommentRecyclerViewAdapter(
                            getContext(),
                            commentList
                    );
                    recyclerView.setAdapter(commentRecyclerViewAdapter);
                }
                else {
                    Log.e("Tag", response.errorBody().toString());
                }
            }

            @Override
            public void onFailure(Call<List<Comment>> call, Throwable t) {

            }
        });
    }

}

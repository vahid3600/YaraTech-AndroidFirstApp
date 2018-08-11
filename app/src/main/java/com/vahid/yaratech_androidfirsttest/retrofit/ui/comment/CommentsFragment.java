package com.vahid.yaratech_androidfirsttest.retrofit.ui.comment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.vahid.yaratech_androidfirsttest.R;
import com.vahid.yaratech_androidfirsttest.retrofit.data.model.Comment;

import java.util.List;

public class CommentsFragment extends Fragment implements CommentContract.View {

    CommentContract.Presenter presenter;
    ProgressBar progressBar;
    RecyclerView recyclerView;
    CommentRecyclerViewAdapter commentRecyclerViewAdapter;

    public CommentsFragment() {
        // Required empty public constructor
    }

    public static Fragment newInstance(int user_id) {
        Bundle bundle = new Bundle();
        bundle.putInt("userID", user_id);
        CommentsFragment commentsFragment = new CommentsFragment();
        commentsFragment.setArguments(bundle);
        return commentsFragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_comments, container, false);
        progressBar = view.findViewById(R.id.progressbar);
        progressBar.setVisibility(View.GONE);
        presenter = new CommentPresenter(getContext(), this);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(),
                LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);
        commentRecyclerViewAdapter = new CommentRecyclerViewAdapter(
                getContext()
        );
        recyclerView = view.findViewById(R.id.comments_recyclerview);
        recyclerView.setAdapter(commentRecyclerViewAdapter);
        int id = getArguments().getInt("userID");
        getData(id);
        return view;
    }

    private void getData(int id) {
        presenter.fetchCommentFromRemote(id);
    }

    @Override
    public void showListComment(List<Comment> comments) {
        commentRecyclerViewAdapter.addData(comments);
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
}

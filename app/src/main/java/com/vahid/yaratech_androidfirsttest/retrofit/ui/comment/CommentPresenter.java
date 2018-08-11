package com.vahid.yaratech_androidfirsttest.retrofit.ui.comment;

import android.content.Context;

import com.vahid.yaratech_androidfirsttest.retrofit.data.model.Comment;
import com.vahid.yaratech_androidfirsttest.retrofit.data.sourse.DataSource;
import com.vahid.yaratech_androidfirsttest.retrofit.data.sourse.Repository;
import com.vahid.yaratech_androidfirsttest.retrofit.data.sourse.remote.RemoteDataSource;

import java.util.List;

/**
 * Created by Vah on 8/11/2018.
 */

public class CommentPresenter implements CommentContract.Presenter {
    CommentContract.View view;
    Repository repository;

    public CommentPresenter(Context context, CommentContract.View view){
        this.repository = Repository.getINSTANCE(new RemoteDataSource(context));
        this.view = view;
    }

    @Override
    public void fetchCommentFromRemote(int id) {
        view.showLoading();

        repository.getComment(id, new DataSource.LoadCommentCallback() {
            @Override
            public void onCommentLoaded(List<Comment> comments) {
                view.hideLoading();
            }

            @Override
            public void onError(String msg) {

            }
        });

    }
}

package com.vahid.yaratech_androidfirsttest.retrofit.ui.comment;

import com.vahid.yaratech_androidfirsttest.retrofit.data.model.Comment;

import java.util.List;

/**
 * Created by Vah on 8/6/2018.
 */

public interface CommentContract {

    interface View{

        void showListComment(List<Comment> comments);

        void showMessage(String msg);

        void showLoading();

        void hideLoading();
    }
    interface Presenter{

        void fetchCommentFromRemote(int id);
    }
}

package com.vahid.yaratech_androidfirsttest.retrofit.data.sourse;

import com.vahid.yaratech_androidfirsttest.retrofit.data.model.Comment;
import com.vahid.yaratech_androidfirsttest.retrofit.data.model.User;

import java.util.List;

/**
 * Created by Vah on 8/8/2018.
 */

public interface DataSource {

    interface LoadUserCallback {
        void onUserLoaded(List<User> users);

        void onError(String msg);
    }

    interface LoadCommentCallback {
        void onCommentLoaded(List<Comment> comments);

        void onError(String msg);
    }

    void getUser(LoadUserCallback callback);

    void getComment(int id, LoadCommentCallback callback);

}

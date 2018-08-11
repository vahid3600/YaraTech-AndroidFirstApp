package com.vahid.yaratech_androidfirsttest.retrofit.data.sourse;

import com.vahid.yaratech_androidfirsttest.retrofit.data.model.Comment;
import com.vahid.yaratech_androidfirsttest.retrofit.data.model.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Vah on 8/4/2018.
 */

public interface Services {

        @GET("users")
        Call<List<User>> listUsers();

        @GET("comments")
        Call<List<Comment>> listComments(@Query("postId") String postId);

}

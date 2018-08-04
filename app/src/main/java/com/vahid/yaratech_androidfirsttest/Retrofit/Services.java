package com.vahid.yaratech_androidfirsttest.Retrofit;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

/**
 * Created by Vah on 8/4/2018.
 */

public interface Services {

        @GET("users")
        Call<List<User>> listUsers();

        @GET("comments")
        Call<List<Comment>> listComments(@Query("postId") String postId);

}

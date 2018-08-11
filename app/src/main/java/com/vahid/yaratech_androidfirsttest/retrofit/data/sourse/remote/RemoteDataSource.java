package com.vahid.yaratech_androidfirsttest.retrofit.data.sourse.remote;

import android.content.Context;
import android.util.Log;

import java.util.Collection;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Vah on 8/8/2018.
 */

public class RemoteDataSource implements com.vahid.yaratech_androidfirsttest.Retrofit.data.sourse.DataSource {

    private Context context;
    private List<Collection> collections;
    public RemoteDataSource(Context context){
        this.context = context;
    }

    @Override
    public void getUser(final com.vahid.yaratech_androidfirsttest.Retrofit.data.sourse.DataSource.LoadUserCallback callback) {
        if (com.vahid.yaratech_androidfirsttest.Retrofit.utils.Util.isOnline(context)) {
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl("https://jsonplaceholder.typicode.com/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            Services services = retrofit.create(Services.class);
            Call<List<User>> users = services.listUsers();
            users.enqueue(new Callback<List<User>>() {
                @Override
                public void onResponse(Call<List<User>> call, Response<List<User>> response) {
                    if (response.isSuccessful()) {
                        List<User> usersList = response.body();
                        callback.onUserLoaded(usersList);
                    } else {
                        callback.onError("عملیات با خطا مواجه شد!");
                    }
                }

                @Override
                public void onFailure(Call<List<User>> call, Throwable t) {
                    callback.onError("عملیات با خطا مواجه شد!");
                }
            });
        }else
            callback.onError("ارتباط اینترنت شما قطع است");
    }

    @Override
    public void getComment(int id, final LoadCommentCallback callback) {
        if (Util.isOnline(context)) {
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl("https://jsonplaceholder.typicode.com/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            Services service = retrofit.create(Services.class);
            final Call<List<Comment>> comments = service.listComments(id + "");
            comments.enqueue(new Callback<List<Comment>>() {
                @Override
                public void onResponse(Call<List<Comment>> call, Response<List<Comment>> response) {
                    if (response.isSuccessful()) {
                        List<Comment> commentList = response.body();
                        callback.onCommentLoaded(commentList);

                    } else {
                        Log.e("Tag", response.errorBody().toString());
                    }
                }

                @Override
                public void onFailure(Call<List<Comment>> call, Throwable t) {
                    callback.onError("عملیات با خطا مواجه شد!");
                }
            });
        }else
            callback.onError("ارتباط اینترنت شما قطع است");

    }
}

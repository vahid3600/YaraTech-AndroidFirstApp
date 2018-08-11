package com.vahid.yaratech_androidfirsttest.Retrofit.ui.user;

import android.content.Context;

import com.vahid.yaratech_androidfirsttest.Retrofit.data.model.User;
import com.vahid.yaratech_androidfirsttest.Retrofit.data.sourse.DataSource;
import com.vahid.yaratech_androidfirsttest.Retrofit.data.sourse.Repository;
import com.vahid.yaratech_androidfirsttest.Retrofit.data.sourse.remote.RemoteDataSource;

import java.util.List;

/**
 * Created by Vah on 8/11/2018.
 */

public class UserPresenter implements UserContract.Presenter {
    Context context;
    UserContract.View view;
    Repository repository;

    public UserPresenter(Context context, UserContract.View view){
        this.repository = Repository.getINSTANCE(new RemoteDataSource(context));
        this.view = view;
    }

    @Override
    public void fetchUserFromRemote() {
        view.showLoading();

        repository.getUser(new DataSource.LoadUserCallback() {
            @Override
            public void onUserLoaded(List<User> users) {
                view.hideLoading();
                view.showListUser(users);
            }

            @Override
            public void onError(String msg) {
                view.hideLoading();
                view.showMessage(msg);
            }
        });
    }
}

package com.vahid.yaratech_androidfirsttest.Retrofit.ui.user;

import com.vahid.yaratech_androidfirsttest.Retrofit.data.model.User;

import java.util.List;

/**
 * Created by Vah on 8/6/2018.
 */

public interface UserContract {

    interface View{

        void showListUser(List<User> users);

        void showMessage(String msg);

        void showLoading();

        void hideLoading();
    }

    interface Presenter{

        void fetchUserFromRemote();
    }

}

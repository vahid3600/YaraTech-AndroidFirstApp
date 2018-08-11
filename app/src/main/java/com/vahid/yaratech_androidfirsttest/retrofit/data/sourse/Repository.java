package com.vahid.yaratech_androidfirsttest.retrofit.data.sourse;

import com.vahid.yaratech_androidfirsttest.retrofit.data.sourse.remote.RemoteDataSource;

public class Repository implements DataSource {

    private static Repository INSTANCE = null;
    private RemoteDataSource remoteDataSource;

    private Repository(DataSource remoteDataSource) {
        //no instance
        if (remoteDataSource instanceof RemoteDataSource) {
            this.remoteDataSource = (RemoteDataSource) remoteDataSource;
        } else {
        }

    }

    public static Repository getINSTANCE(DataSource remoteDataSource) {
        if (INSTANCE == null) {
            INSTANCE = new Repository(remoteDataSource);
        }
        return INSTANCE;
    }

    @Override
    public void getUser(LoadUserCallback callback) {
        remoteDataSource.getUser(callback);
    }

    @Override
    public void getComment(int id, LoadCommentCallback callback) {
        remoteDataSource.getComment(id, callback);
    }
}

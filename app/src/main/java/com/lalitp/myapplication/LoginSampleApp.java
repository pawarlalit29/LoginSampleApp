package com.lalitp.myapplication;

import android.content.Context;
import android.support.multidex.MultiDexApplication;


import com.j256.ormlite.android.apptools.OpenHelperManager;
import com.lalitp.myapplication.Database.DatabaseHelper;
import com.lalitp.myapplication.Utils.SecurePreferences;

/**
 * Created by atulsia on 17/2/16.
 */
public class LoginSampleApp extends MultiDexApplication {

    private static LoginSampleApp sInstance;

    public static LoginSampleApp getInstance() {
        return sInstance;
    }

    public static Context getAppContext() {
        return sInstance.getApplicationContext();
    }


    // Reference of DatabaseHelper class to access its DAOs and other components
    public static DatabaseHelper databaseHelper = null;

    public static Context context;


    @Override
    public void onCreate() {

        super.onCreate();
        sInstance = this;
        context = this;

    }

    public static DatabaseHelper getHelper() {
        if (databaseHelper == null) {
            databaseHelper = OpenHelperManager.getHelper(getAppContext(), DatabaseHelper.class);
        }
        return databaseHelper;
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
        if (databaseHelper != null) {
            OpenHelperManager.releaseHelper();
            databaseHelper = null;
        }
    }


}

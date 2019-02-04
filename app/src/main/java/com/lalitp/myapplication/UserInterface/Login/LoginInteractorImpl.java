package com.lalitp.myapplication.UserInterface.Login;


import com.j256.ormlite.dao.Dao;
import com.lalitp.myapplication.LoginSampleApp;
import com.lalitp.myapplication.Pojo.Login.LoginParam;
import com.lalitp.myapplication.Pojo.LogsResult;
import com.lalitp.myapplication.Pojo.ServerResponse_Item.CommonResponseData;
import com.lalitp.myapplication.Pojo.ServerResponse_Item.Status;
import com.lalitp.myapplication.Utils.AppConstant;
import com.lalitp.myapplication.Utils.Common_Utils;
import com.lalitp.myapplication.Webapi.RestClient;

import java.sql.SQLException;
import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class LoginInteractorImpl implements LoginInteractor {

    private String TAG = LoginInteractorImpl.class.getSimpleName();


    @Override
    public void getRememberMeData(ViewChangeListener listener) {

    }

    @Override
    public void login(final LoginParam loginParam, final ViewChangeListener listener) {
        // Mock getProductList. I'm creating a handler to delay the answer a couple of seconds


        // we write condition here because of we store the error logs
        if (!loginParam.getuName().equalsIgnoreCase("Admin") ||
                !loginParam.getPassword().equalsIgnoreCase("Admin")) {
            logLoginState(loginParam, AppConstant.uname_password_not_match);
            listener.onError(AppConstant.uname_password_not_match);
            return;
        } else if (!Common_Utils.isNetworkAvailable()) {
            logLoginState(loginParam, AppConstant.no_internet_connection);
            listener.noInternetConnection();
            return;
        }


        Call<CommonResponseData> loginDataCall = RestClient.getClient().postLogin(getHeader(loginParam));

        loginDataCall.enqueue(new Callback<CommonResponseData>() {
                                  @Override
                                  public void onResponse(Call<CommonResponseData> call, Response<CommonResponseData> response) {
                                      if (response.isSuccessful()) {
                                          CommonResponseData loginData = response.body();
                                          Status status = loginData.getStatus();
                                          if (status.getResponse().equalsIgnoreCase("success")) {
                                              listener.onSuccess(status.getMessage());
                                          } else {
                                              listener.onError(status.getMessage());
                                          }
                                      } else {
                                          listener.onError(RestClient.parseLoginError(response));
                                          logLoginState(loginParam, RestClient.parseLoginError(response));
                                      }
                                  }

                                  @Override
                                  public void onFailure(Call<CommonResponseData> call, Throwable t) {
                                      listener.onError(t.getMessage());
                                      logLoginState(loginParam, t.getMessage());
                                  }
                              }
        );

    }

    private HashMap<String, String> getHeader(LoginParam loginParam) {
        HashMap<String, String> stringHashMap = new HashMap<>();
        stringHashMap.put("uname", loginParam.getuName());
        stringHashMap.put("password", loginParam.getPassword());

        return stringHashMap;
    }


    private void logLoginState(LoginParam loginParam, String msg) {
        LogsResult logsResult = new LogsResult();
        logsResult.setUsername(loginParam.getuName());
        logsResult.setPassword(loginParam.getPassword());
        logsResult.setMessage(msg);
        logsResult.setTimestamp(System.currentTimeMillis());

        try {
            Dao<LogsResult, Integer> notificationDetailses = LoginSampleApp.getHelper().getStoreLogsDao();
            notificationDetailses.create(logsResult);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
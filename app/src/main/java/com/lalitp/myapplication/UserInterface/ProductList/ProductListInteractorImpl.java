package com.lalitp.myapplication.UserInterface.ProductList;


import com.lalitp.myapplication.Pojo.ProductList.ProductListData;
import com.lalitp.myapplication.Pojo.ServerResponse_Item.Status;
import com.lalitp.myapplication.Webapi.RestClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class ProductListInteractorImpl implements ProductListInteractor {

    private String TAG = ProductListInteractorImpl.class.getSimpleName();


    @Override
    public void getProductList(final ViewChangeListener listener) {
        Call<ProductListData> loginDataCall = RestClient.getClient().getProductListData();

        loginDataCall.enqueue(new Callback<ProductListData>() {
                                  @Override
                                  public void onResponse(Call<ProductListData> call, Response<ProductListData> response) {
                                      if (response.isSuccessful()) {
                                          ProductListData loginData = response.body();
                                          Status status = loginData.getStatus();
                                          if (status.getResponse().equalsIgnoreCase("success")) {
                                              listener.getProductList(loginData.getData().getProductList());
                                          } else {
                                              listener.onError(status.getMessage());
                                          }
                                      } else {
                                          listener.onError(RestClient.parseLoginError(response));
                                      }
                                  }

                                  @Override
                                  public void onFailure(Call<ProductListData> call, Throwable t) {
                                      listener.onError(t.getMessage());
                                  }
                              }
        );

    }

}
package com.lalitp.myapplication.Webapi;


import com.lalitp.myapplication.Pojo.ProductList.ProductListData;
import com.lalitp.myapplication.Pojo.ServerResponse_Item.CommonResponseData;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.HeaderMap;
import retrofit2.http.POST;


/**
 * Created by atulsia on 19/2/16.
 */
public interface RestInterface {


    /*----------------------- Without Login --------------------------------------*/


    /*----------------------- User Profile --------------------------------------*/

    @POST("Login")
    Call<CommonResponseData> postLogin(@HeaderMap Map<String, String> headers);

    @GET("ProductList")
    Call<ProductListData> getProductListData();
}

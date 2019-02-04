package com.lalitp.myapplication.Webapi;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.lalitp.myapplication.BuildConfig;
import com.lalitp.myapplication.LoginSampleApp;
import com.lalitp.myapplication.Pojo.ServerResponse_Item.CommonResponseData;
import com.lalitp.myapplication.Utils.Common_Utils;

import java.lang.annotation.Annotation;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.ResponseBody;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Converter;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static okhttp3.logging.HttpLoggingInterceptor.Level.HEADERS;
import static okhttp3.logging.HttpLoggingInterceptor.Level.NONE;

/**
 * Created by atulsia on 19/2/16.
 */
public class RestClient {

    public static final String ErrorMessage = "Servers cannot be reached. Please try again";
    public static final String SERVER_APIURL = "http://demo7522367.mockable.io/";


    private static final String CACHE_CONTROL = "Cache-Control";

    private static RestInterface apiService = null;

    public static RestInterface getClient() {
        return getRetrofit().create(RestInterface.class);
    }



    public static Retrofit getRetrofit() {
        GsonBuilder gBuilder = new GsonBuilder();

        Gson gson = gBuilder.create();

        return new Retrofit.Builder()
                .baseUrl(SERVER_APIURL)
                .client(provideOkHttpClient())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
    }

    private static OkHttpClient provideOkHttpClient() {

        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .readTimeout(60, TimeUnit.SECONDS)
                .connectTimeout(60, TimeUnit.SECONDS)
                .addInterceptor(provideHttpLoggingInterceptor())
                .retryOnConnectionFailure(true)
                .build();

        return okHttpClient;
    }

    private static HttpLoggingInterceptor provideHttpLoggingInterceptor() {

        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);

        HttpLoggingInterceptor httpLoggingInterceptor =
                new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
                    @Override
                    public void log(String message) {
                        //LogUtils.LOGD("provideHttpLoggingInterceptor", message);
                    }
                });
        httpLoggingInterceptor.setLevel(BuildConfig.DEBUG ? HEADERS : NONE);
        return logging;
    }

    public static String parseLoginError(Response<?> response) {

        Converter<ResponseBody, CommonResponseData> converter =
                RestClient.getRetrofit()
                        .responseBodyConverter(CommonResponseData.class, new Annotation[0]);

        //LogUtils.LOGD("test data ", converter.toString());

        CommonResponseData error;
        try {
            error = converter.convert(response.errorBody());
            return error.getStatus().getMessage();
        } catch (Exception e) {
            //LogUtils.LOGD(ErrorUtils.class.getSimpleName(), e.toString());
            return ErrorMessage;
        }
    }

    public static void parseErrorThrow(Throwable throwable) {
        if (throwable == null)
            return;

        if(Common_Utils.isNotNullOrEmpty(throwable.getMessage())){
            Common_Utils.showToast(throwable.getMessage());
        }
    }


}

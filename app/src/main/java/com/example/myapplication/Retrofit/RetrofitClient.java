package com.example.myapplication.Retrofit;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {
    Gson gson = new GsonBuilder()
            .setDateFormat("yyyy-MM-dd HH:mm:ss")
            .create();

//    ApiService apiService = new Retrofit.Builder()
//            .baseUrl("https://tv360.vn/")
//            .addConverterFactory(GsonConverterFactory.create(gson))
//            .build()
//            .create(ApiService.class);


    private static Retrofit retrofit = null;
    private static Retrofit retrofit2 = null;

    public static Retrofit getClient() {

        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder()
                .connectTimeout(10, TimeUnit.SECONDS)
                .readTimeout(10, TimeUnit.SECONDS)
                .writeTimeout(10, TimeUnit.SECONDS)
                .addInterceptor(interceptor)
                .retryOnConnectionFailure(true)
                .addInterceptor(new Interceptor() {
                    @Override
                    public Response intercept(Chain chain) throws IOException {
                        Request original = chain.request();
                        String userAgent = System.getProperty("http.agent");
                        Request.Builder builder = original.newBuilder();
                        builder.addHeader("authorization", "bearer kadfkasfksf");
                        builder.addHeader("Content-Type", "application/json");
                        builder.addHeader("lang","vi");
                        builder.addHeader("zoneid","1");
                        builder.addHeader("osapptype","WAP");
                        builder.addHeader("osappversion","3.4");
                        builder.addHeader("devicetype","WAP");

                        Request request = builder.method(original.method(), original.body()).build();
                        return chain.proceed(request);
                    }
                }).build();


        retrofit = new Retrofit.Builder()
                .baseUrl("http://local-a.tivi360.vn:30900/")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(client)
                .build();

        return retrofit;

    }
    public static Retrofit getlink(String profileid,String userId,String deviceid,String authorization) {

        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder()
                .connectTimeout(10, TimeUnit.SECONDS)
                .readTimeout(10, TimeUnit.SECONDS)
                .writeTimeout(10, TimeUnit.SECONDS)
                .addInterceptor(interceptor)
                .retryOnConnectionFailure(true)
                .addInterceptor(new Interceptor() {
                    @Override
                    public Response intercept(Chain chain) throws IOException {
                        Request original = chain.request();
                        Request.Builder builder = original.newBuilder();
                        builder.addHeader("lang","vi");
                        builder.addHeader("zoneid","1");
                        builder.addHeader("osapptype","ANDROIDBOX");
                        builder.addHeader("osappversion","1.9.5");
                        builder.addHeader("devicetype","ANDROIDBOX");
                        builder.addHeader("profileid",profileid);
                        builder.addHeader("userid",userId);
                        builder.addHeader("deviceid",deviceid);
                        builder.addHeader("Authorization",authorization);
                        Request request = builder.method(original.method(), original.body()).build();
                        return chain.proceed(request);
                    }
                }).build();


        retrofit2 = new Retrofit.Builder()
                .baseUrl("http://local-a.tivi360.vn:30900/")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(client)
                .build();

        return retrofit2;

    }
}

package com.byted.chapter5;

import java.util.Observable;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface ApiService {

    // https://wanandroid.com/wxarticle/chapters/json
    @GET("wxarticle/chapters/json")
    Call<ArticleResponse> getArticles();

    // todo 添加api
    //方法：POST
    //	username,password,repassword
    @FormUrlEncoded
    // https://www.wanandroid.com/user/register
    @POST("user/register")
    Call<UserResponse> postArticles(@Field("username")String username,
                                       @Field("password")String password,
                                       @Field("repassword")String repassword);
    @GET("user/register/json")
    Call<UserResponse> getusers();


}


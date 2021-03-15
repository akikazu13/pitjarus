package com.projects.pitjarus_tracking.connections.networks.implementations;

import okhttp3.MultipartBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

/**
 * Created by Sohibun Nawawi on 14/03/2021.
 */

public interface IRetrofitAPI {

    @Multipart
    @POST("login/loginTest")
    Call<ResponseBody> postLogin(
            @Part MultipartBody.Part username,
            @Part MultipartBody.Part password
            );
}

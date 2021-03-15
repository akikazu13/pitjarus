package com.projects.pitjarus_tracking.features.login;

import android.app.Application;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.projects.pitjarus_tracking.AppConfiguration;
import com.projects.pitjarus_tracking.connections.databases.IPitjarusTrackingDatabase;
import com.projects.pitjarus_tracking.connections.requests.DatabaseErrorListener;
import com.projects.pitjarus_tracking.connections.requests.DatabaseSuccessListener;
import com.projects.pitjarus_tracking.connections.requests.PostLoginRequest;
import com.projects.pitjarus_tracking.connections.responses.PostLoginResponse;
import com.projects.pitjarus_tracking.features.BaseViewModel;
import com.projects.pitjarus_tracking.models.StoreModel;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.MultipartBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Sohibun Nawawi on 12/03/2021.
 */

public class LoginViewModel extends BaseViewModel {

    private final MutableLiveData<List<StoreModel>> loginLiveData = new MutableLiveData<>();

    public MutableLiveData<List<StoreModel>> getLoginLiveData() {
        return loginLiveData;
    }

    public LoginViewModel(@NonNull Application application) {
        super(application);
    }

    public void postLogin(PostLoginRequest request) {

        MultipartBody.Part username = MultipartBody.Part.createFormData( "username", request.getUsername());
        MultipartBody.Part password = MultipartBody.Part.createFormData("password", request.getPassword());

        Call<ResponseBody> loginRespon = IRetrofitAPI.postLogin(
                username, password
        );

        loginRespon.enqueue(
                new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        PostLoginResponse apiResponse = new PostLoginResponse(response);
                        loginLiveData.postValue(apiResponse.getStoreModelList());
                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {

                    }
                }
        );
    }

    public void insertResponseToDatabase(List<StoreModel> models){
        for (StoreModel model : models){
            Log.v("responseModel", model.toString());
            database.insertStore(
                    model,
                    new DatabaseSuccessListener<Long>() {
                        @Override
                        public void onSuccessResponse(Long results) {
                            Toast.makeText(getApplication(), "Data Berhasil Disimpan", Toast.LENGTH_SHORT).show();
                        }
                    },
                    new DatabaseErrorListener() {
                        @Override
                        public void onErrorResponseListener() {

                        }
                    }
            );
        }
    }
}

package com.projects.pitjarus_tracking.connections.responses;

import com.google.gson.Gson;
import com.projects.pitjarus_tracking.models.StoreModel;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import okhttp3.ResponseBody;
import retrofit2.Response;

/**
 * Created by Sohibun Nawawi on 12/03/2021.
 */

@Getter

public class PostLoginResponse {

    private List<StoreModel> storeModelList = new ArrayList<>();

    public PostLoginResponse(Response<ResponseBody> response){
        try {
            JSONObject jsonObject = new JSONObject(response.body().string());
            if (jsonObject.has("stores")){
                JSONArray responseArray = jsonObject.getJSONArray("stores");
                for (int index = 0 ; index < responseArray.length() ; index++){
                    JSONObject responseObject = responseArray.getJSONObject(index);
                    StoreModel storeModel = new Gson().fromJson(responseObject.toString(), StoreModel.class);
                    storeModelList.add(storeModel);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

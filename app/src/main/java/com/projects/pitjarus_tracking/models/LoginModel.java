package com.projects.pitjarus_tracking.models;

import com.google.gson.annotations.SerializedName;

import org.json.JSONObject;

import lombok.Data;

/**
 * Created by Sohibun Nawawi on 12/03/2021.
 */

@Data

public class LoginModel extends BaseModel{

    @SerializedName("username")
    private String username;

    @SerializedName("password")
    private String password;

    @Override
    public JSONObject getAsJSONObject() {
        return null;
    }
}

package com.projects.pitjarus_tracking.connections.networks;


import android.util.Log;

import androidx.annotation.Nullable;

import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by Sohibun Nawawi on 12/03/2021.
 */

public class VolleyJSONObjectRequest extends JsonObjectRequest {

    private final JSONObject jsonParameters;

    public VolleyJSONObjectRequest(int method, String url, @Nullable JSONObject jsonParameter, Response.Listener<JSONObject> successListener, @Nullable Response.ErrorListener errorListener) {
        super(method, generateUrl(method, url, jsonParameter), jsonParameter, successListener, errorListener);

        this.jsonParameters = jsonParameter;
        String formattedUrl = generateUrl(method,url, jsonParameter);
        Log.v("API url", formattedUrl);
        Log.v("API Parameter", jsonParameters.toString());
    }


    @Override
    public Map<String, String> getHeaders(){
        Map<String, String> headers = new HashMap<>();
        headers.put("Content-Type", "application/json");
        return headers;
    }

    private Map<String, String> jsonToStringMap(JSONObject object) throws JSONException {
        Map<String, String> map = new HashMap<>();

        Iterator<String> keysItr = object.keys();
        while (keysItr.hasNext()){
            String key = keysItr.next();
            String value = (String) object.get(key);

            map.put(key, value);
        }
        return map;
    }

    @Override
    protected Map<String, String> getParams() throws AuthFailureError {
        try {
            return jsonToStringMap(this.jsonParameters);
        } catch (JSONException e) {
            e.printStackTrace();

            return super.getParams();
        }
    }

    private static String generateUrl(int method, String url, JSONObject jsonRequest){
        if (method == Method.GET) {
            if (jsonRequest != null) {
                StringBuilder params = new StringBuilder();
                Iterator<String> keys = jsonRequest.keys();
                while (keys.hasNext()) {
                    String key = keys.next();
                    try {
                        String value = jsonRequest.getString(key);
                        params.append(key).append("=").append(value);
                        if (keys.hasNext()) {
                            params.append("&");
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                return url + "?" + params;
            }
            else {
                return url;
            }
        } else {
            return url;
        }
    }
}

package com.projects.pitjarus_tracking.connections.networks.implementations;

import android.content.Context;

import com.android.volley.Response;
import com.projects.pitjarus_tracking.AppConfiguration;
import com.projects.pitjarus_tracking.BuildConfig;
import com.projects.pitjarus_tracking.Helpers.JSONHelper;
import com.projects.pitjarus_tracking.connections.networks.VolleyJSONObjectRequest;
import com.projects.pitjarus_tracking.connections.networks.VolleySingleton;
import com.projects.pitjarus_tracking.connections.requests.BaseRequest;

import org.json.JSONObject;

/**
 * Created by Sohibun Nawawi on 12/03/2021.
 */

public abstract class BaseVolleyAPI implements IVolleyAPI {

    protected Context context;

    public BaseVolleyAPI(Context context){
        this.context = context;
    }

    protected String getUrl(String routeName){
        JSONObject object = AppConfiguration.getOurInstance().getApiUrl(context);
        return BuildConfig.BASE_URL + JSONHelper.getOurInstance().readString(object, routeName);
    }

    protected void executeJSONObjectRequest(
            String url,
            int method,
            BaseRequest requestParameter,
            Response.Listener<JSONObject> successListener,
            Response.ErrorListener errorListener) {


        requestParameter.setUrl(url);
        requestParameter.setMethod(method);
        JSONObject jsonParameter = requestParameter.generateJSONParameter();
        VolleyJSONObjectRequest request= new VolleyJSONObjectRequest(requestParameter.getMethod(), url, jsonParameter, successListener, errorListener);
        VolleySingleton.getOurInstance().addToRequestQueue(request, context);
    }
}

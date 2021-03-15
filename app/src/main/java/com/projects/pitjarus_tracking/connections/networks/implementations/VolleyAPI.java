package com.projects.pitjarus_tracking.connections.networks.implementations;

import android.content.Context;

import com.android.volley.Response;
import com.projects.pitjarus_tracking.connections.requests.PostLoginRequest;

import org.json.JSONObject;

import static com.android.volley.Request.Method.POST;

/**
 * Created by Sohibun Nawawi on 12/03/2021.
 */

public class VolleyAPI extends BaseVolleyAPI{
    public VolleyAPI(Context context) {
        super(context);
    }

    @Override
    public void postLogin(PostLoginRequest request, Response.Listener<JSONObject> successListener, Response.ErrorListener errorListener) {
        String url = getUrl("postLogin");
        executeJSONObjectRequest(url, POST, request, successListener, errorListener);
    }
}

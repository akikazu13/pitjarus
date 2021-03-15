package com.projects.pitjarus_tracking.connections.networks.implementations;

import com.android.volley.Response;
import com.projects.pitjarus_tracking.connections.requests.PostLoginRequest;

import org.json.JSONObject;

/**
 * Created by Sohibun Nawawi on 12/03/2021.
 */
public interface IVolleyAPI {

    void postLogin(
            PostLoginRequest request,
            Response.Listener<JSONObject> successListener,
            Response.ErrorListener errorListener
    );
}

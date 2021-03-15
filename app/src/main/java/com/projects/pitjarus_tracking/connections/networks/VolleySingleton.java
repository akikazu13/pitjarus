package com.projects.pitjarus_tracking.connections.networks;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

/**
 * Created by Sohibun Nawawi on 12/03/2021.
 */

public class    VolleySingleton {
    public static VolleySingleton ourInstance = new VolleySingleton();

    private RequestQueue requestQueue;

    public static VolleySingleton getOurInstance(){
        return ourInstance;
    }

    public <T> void addToRequestQueue(Request<T> req, Context context){
        getRequestQueue(context).add(req);
    }

    public  RequestQueue getRequestQueue(final Context context){
        if (requestQueue == null){
            this.requestQueue = Volley.newRequestQueue(context);
        }
        return requestQueue;
    }
}

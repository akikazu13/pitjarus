package com.projects.pitjarus_tracking.connections.requests;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

/**
 * Created by Sohibun Nawawi on 12/03/2021.
 */

public abstract class BaseRequest {
    protected Map<String, Object> parameters;
    protected abstract void populateParameters();
    protected int method;
    protected String url;

    public BaseRequest() {
        parameters = new HashMap<>();
    }

}

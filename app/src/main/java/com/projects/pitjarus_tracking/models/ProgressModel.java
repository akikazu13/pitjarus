package com.projects.pitjarus_tracking.models;

import org.json.JSONObject;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Created by Sohibun Nawawi on 17/03/2021.
 */

@Data
@AllArgsConstructor

public class ProgressModel extends BaseModel{

    private String name;
    private int target;
    private int progress;

    @Override
    public JSONObject getAsJSONObject() {
        return null;
    }
}

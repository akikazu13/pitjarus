package com.projects.pitjarus_tracking.connections.requests;

import com.projects.pitjarus_tracking.helpers.ValidatorHelper;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

/**
 * Created by Sohibun Nawawi on 16/03/2021.
 */

@Data
@Setter
@Getter

public class GetStoreDetailRequest extends BaseRequest{

    private String id;

    @Override
    protected void populateParameters() {
        if (ValidatorHelper.checkNotNullOrEmpty(getId()))
            parameters.put("id", getId());
    }
}

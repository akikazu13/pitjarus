package com.projects.pitjarus_tracking.connections.requests;

import com.projects.pitjarus_tracking.helpers.ValidatorHelper;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;

/**
 * Created by Sohibun Nawawi on 12/03/2021.
 */

@Data
@Getter
@Builder

public class PostLoginRequest extends BaseRequest{
    private String username;
    private String password;

    @Override
    protected void populateParameters() {
        if (ValidatorHelper.checkNotNullOrEmpty(getUsername()))
            parameters.put("username", getUsername());
        if (ValidatorHelper.checkNotNullOrEmpty(getPassword()))
            parameters.put("password", getPassword());
    }
}

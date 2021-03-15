package com.projects.pitjarus_tracking.connections.requests;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/**
 * Created by Sohibun Nawawi on 13/03/2021.
 */

@Builder
@Getter
@Setter

public class GetStoreRequest {

    private int pageNo;
    private int pageSize;
}

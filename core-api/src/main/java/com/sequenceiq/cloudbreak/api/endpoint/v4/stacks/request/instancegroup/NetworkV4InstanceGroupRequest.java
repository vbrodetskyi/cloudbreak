package com.sequenceiq.cloudbreak.api.endpoint.v4.stacks.request.instancegroup;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.sequenceiq.cloudbreak.api.endpoint.v4.stacks.base.InstanceGroupNetworkV4Base;

import io.swagger.annotations.ApiModel;

@ApiModel
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(Include.NON_NULL)
public class NetworkV4InstanceGroupRequest extends InstanceGroupNetworkV4Base {

}

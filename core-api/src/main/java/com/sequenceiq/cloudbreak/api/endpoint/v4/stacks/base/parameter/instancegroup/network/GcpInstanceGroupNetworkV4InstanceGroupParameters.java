package com.sequenceiq.cloudbreak.api.endpoint.v4.stacks.base.parameter.instancegroup.network;

import java.util.Map;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.sequenceiq.cloudbreak.common.mappable.CloudPlatform;
import com.sequenceiq.cloudbreak.common.mappable.MappableBase;
import com.sequenceiq.common.model.JsonEntity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(Include.NON_NULL)
public class GcpInstanceGroupNetworkV4InstanceGroupParameters extends MappableBase implements JsonEntity {

    @ApiModelProperty
    private String subnetId;

    @ApiModelProperty
    private Boolean noPublicIp;

    @ApiModelProperty
    private Boolean noFirewallRules;

    public String getSubnetId() {
        return subnetId;
    }

    public void setSubnetId(String subnetId) {
        this.subnetId = subnetId;
    }

    public Boolean getNoPublicIp() {
        return noPublicIp;
    }

    public void setNoPublicIp(Boolean noPublicIp) {
        this.noPublicIp = noPublicIp;
    }

    public Boolean getNoFirewallRules() {
        return noFirewallRules;
    }

    public void setNoFirewallRules(Boolean noFirewallRules) {
        this.noFirewallRules = noFirewallRules;
    }

    @Override
    public Map<String, Object> asMap() {
        Map<String, Object> map = super.asMap();
        putIfValueNotNull(map, "subnetId", subnetId);
        putIfValueNotNull(map, "noFirewallRules", noFirewallRules);
        putIfValueNotNull(map, "noPublicIp", noPublicIp);
        return map;
    }

    @Override
    @JsonIgnore
    @ApiModelProperty(hidden = true)
    public CloudPlatform getCloudPlatform() {
        return CloudPlatform.GCP;
    }

    @Override
    public void parse(Map<String, Object> parameters) {
        subnetId = getParameterOrNull(parameters, "subnetId");
        noFirewallRules = getBoolean(parameters, "noFirewallRules");
        noPublicIp = getBoolean(parameters, "noPublicIp");
    }
}

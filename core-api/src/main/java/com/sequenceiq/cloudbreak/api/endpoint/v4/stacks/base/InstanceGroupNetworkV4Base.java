package com.sequenceiq.cloudbreak.api.endpoint.v4.stacks.base;

import com.sequenceiq.cloudbreak.api.endpoint.v4.stacks.base.parameter.instancegroup.network.AwsNetworkV4InstanceGroupParameters;
import com.sequenceiq.cloudbreak.api.endpoint.v4.stacks.base.parameter.instancegroup.network.AzureNetworkV4InstanceGroupParameters;
import com.sequenceiq.cloudbreak.api.endpoint.v4.stacks.base.parameter.instancegroup.network.GcpNetworkV4InstanceGroupParameters;
import com.sequenceiq.cloudbreak.api.endpoint.v4.stacks.base.parameter.instancegroup.network.MockNetworkV4InstanceGroupParameters;
import com.sequenceiq.cloudbreak.api.endpoint.v4.stacks.base.parameter.instancegroup.network.OpenStackNetworkV4InstanceGroupParameters;
import com.sequenceiq.cloudbreak.api.endpoint.v4.stacks.base.parameter.instancegroup.network.YarnNetworkV4InstanceGroupParameters;
import com.sequenceiq.cloudbreak.common.mappable.ProviderParametersBase;
import com.sequenceiq.cloudbreak.doc.ModelDescriptions.NetworkModelDescription;
import com.sequenceiq.common.model.JsonEntity;

import io.swagger.annotations.ApiModelProperty;

public class InstanceGroupNetworkV4Base extends ProviderParametersBase implements JsonEntity {

    @ApiModelProperty(NetworkModelDescription.AWS_PARAMETERS)
    private AwsNetworkV4InstanceGroupParameters aws;

    @ApiModelProperty(NetworkModelDescription.GCP_PARAMETERS)
    private GcpNetworkV4InstanceGroupParameters gcp;

    @ApiModelProperty(NetworkModelDescription.AZURE_PARAMETERS)
    private AzureNetworkV4InstanceGroupParameters azure;

    @ApiModelProperty(NetworkModelDescription.OPEN_STACK_PARAMETERS)
    private OpenStackNetworkV4InstanceGroupParameters openstack;

    @ApiModelProperty(hidden = true)
    private MockNetworkV4InstanceGroupParameters mock;

    @ApiModelProperty(hidden = true)
    private YarnNetworkV4InstanceGroupParameters yarn;

    @Override
    public MockNetworkV4InstanceGroupParameters createMock() {
        if (mock == null) {
            mock = new MockNetworkV4InstanceGroupParameters();
        }
        return mock;
    }

    public void setMock(MockNetworkV4InstanceGroupParameters mock) {
        this.mock = mock;
    }

    public AwsNetworkV4InstanceGroupParameters createAws() {
        if (aws == null) {
            aws = new AwsNetworkV4InstanceGroupParameters();
        }
        return aws;
    }

    public void setAws(AwsNetworkV4InstanceGroupParameters aws) {
        this.aws = aws;
    }

    public GcpNetworkV4InstanceGroupParameters createGcp() {
        if (gcp == null) {
            gcp = new GcpNetworkV4InstanceGroupParameters();
        }
        return gcp;
    }

    public void setGcp(GcpNetworkV4InstanceGroupParameters gcp) {
        this.gcp = gcp;
    }

    public AzureNetworkV4InstanceGroupParameters createAzure() {
        if (azure == null) {
            azure = new AzureNetworkV4InstanceGroupParameters();
        }
        return azure;
    }

    public void setAzure(AzureNetworkV4InstanceGroupParameters azure) {
        this.azure = azure;
    }

    public OpenStackNetworkV4InstanceGroupParameters createOpenstack() {
        if (openstack == null) {
            openstack = new OpenStackNetworkV4InstanceGroupParameters();
        }
        return openstack;
    }

    public void setOpenstack(OpenStackNetworkV4InstanceGroupParameters openstack) {
        this.openstack = openstack;
    }

    @Override
    public YarnNetworkV4InstanceGroupParameters createYarn() {
        if (yarn == null) {
            yarn = new YarnNetworkV4InstanceGroupParameters();
        }
        return yarn;
    }

    public void setYarn(YarnNetworkV4InstanceGroupParameters yarn) {
        this.yarn = yarn;
    }

    public AwsNetworkV4InstanceGroupParameters getAws() {
        return aws;
    }

    public GcpNetworkV4InstanceGroupParameters getGcp() {
        return gcp;
    }

    public AzureNetworkV4InstanceGroupParameters getAzure() {
        return azure;
    }

    public OpenStackNetworkV4InstanceGroupParameters getOpenstack() {
        return openstack;
    }

    public MockNetworkV4InstanceGroupParameters getMock() {
        return mock;
    }

    public YarnNetworkV4InstanceGroupParameters getYarn() {
        return yarn;
    }
}

package com.sequenceiq.distrox.api.v1.distrox.model.network;

import java.io.Serializable;

import com.sequenceiq.cloudbreak.doc.ModelDescriptions.NetworkModelDescription;
import com.sequenceiq.distrox.api.v1.distrox.model.network.aws.AwsInstanceGroupNetworkV1Parameters;
import com.sequenceiq.distrox.api.v1.distrox.model.network.azure.AzureInstanceGroupNetworkV1Parameters;
import com.sequenceiq.distrox.api.v1.distrox.model.network.mock.MockInstanceGroupNetworkV1Parameters;

import io.swagger.annotations.ApiModelProperty;

public class InstanceGroupNetworkV1Base implements Serializable {

    @ApiModelProperty(NetworkModelDescription.AWS_PARAMETERS)
    private AwsInstanceGroupNetworkV1Parameters aws;

    @ApiModelProperty(NetworkModelDescription.AZURE_PARAMETERS)
    private AzureInstanceGroupNetworkV1Parameters azure;

    @ApiModelProperty(NetworkModelDescription.MOCK_PARAMETERS)
    private MockInstanceGroupNetworkV1Parameters mock;

    public void setAws(AwsInstanceGroupNetworkV1Parameters aws) {
        this.aws = aws;
    }

    public void setAzure(AzureInstanceGroupNetworkV1Parameters azure) {
        this.azure = azure;
    }

    public AwsInstanceGroupNetworkV1Parameters getAws() {
        return aws;
    }

    public AzureInstanceGroupNetworkV1Parameters getAzure() {
        return azure;
    }

    public MockInstanceGroupNetworkV1Parameters getMock() {
        return mock;
    }

    public void setMock(MockInstanceGroupNetworkV1Parameters mock) {
        this.mock = mock;
    }

}
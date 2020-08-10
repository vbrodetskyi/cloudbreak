package com.sequenceiq.cloudbreak.service.network;

import org.springframework.stereotype.Service;

import com.google.common.base.Strings;
import com.sequenceiq.cloudbreak.api.endpoint.v4.stacks.request.instancegroup.NetworkV4InstanceGroupRequest;
import com.sequenceiq.cloudbreak.common.mappable.CloudPlatform;

@Service
public class DefaultInstanceGroupNetworkRequiredService {

    public boolean shouldAddNetwork(String cloudPlatform, NetworkV4InstanceGroupRequest network) {
        if (network == null) {
            return true;
        } else if (isAwsNetworkNullAndProviderIsAws(cloudPlatform, network)) {
            return true;
        } else if (isAzureNetworkNullAndProviderIsAzure(cloudPlatform, network)) {
            return true;
        }
        return false;
    }

    private boolean isAwsNetworkNullAndProviderIsAws(String cloudPlatform, NetworkV4InstanceGroupRequest network) {
        return CloudPlatform.AWS.name().equals(cloudPlatform)
                && (network.getAws() == null || allTheAwsValueNull(network));
    }

    private boolean allTheAwsValueNull(NetworkV4InstanceGroupRequest network) {
        return Strings.isNullOrEmpty(network.getAws().getSubnetId());
    }

    private boolean isAzureNetworkNullAndProviderIsAzure(String cloudPlatform, NetworkV4InstanceGroupRequest network) {
        return CloudPlatform.AZURE.name().equals(cloudPlatform)
                && (network.getAzure() == null || allTheAzureValueNull(network));
    }

    private boolean allTheAzureValueNull(NetworkV4InstanceGroupRequest network) {
        return Strings.isNullOrEmpty(network.getAzure().getSubnetId());
    }

}

package com.sequenceiq.cloudbreak.reactor.api.event.cluster.upgrade;

import com.sequenceiq.cloudbreak.reactor.api.event.StackEvent;

public class ClusterUpgradeRequest extends StackEvent {

    public ClusterUpgradeRequest(Long stackId) {
        super(stackId);
    }
}

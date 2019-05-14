package com.sequenceiq.flow.core;

import com.sequenceiq.cloudbreak.common.event.Payload;

public class TestPayload implements Payload {
    private Long stackId;

    public TestPayload(Long stackId) {
        this.stackId = stackId;
    }

    @Override
    public Long getResourceId() {
        return stackId;
    }
}

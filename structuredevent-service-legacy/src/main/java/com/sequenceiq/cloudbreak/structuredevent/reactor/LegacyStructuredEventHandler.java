package com.sequenceiq.cloudbreak.structuredevent.reactor;

import javax.inject.Inject;

import org.springframework.stereotype.Component;

import com.sequenceiq.flow.reactor.api.handler.EventHandler;
import com.sequenceiq.cloudbreak.structuredevent.StructuredEventService;
import com.sequenceiq.cloudbreak.structuredevent.event.StructuredEvent;

import reactor.bus.Event;

@Component
public class LegacyStructuredEventHandler<T extends StructuredEvent> implements EventHandler<T> {
    @Inject
    private StructuredEventService structuredEventService;

    @Override
    public String selector() {
        return StructuredEventAsyncNotifier.EVENT_LOG;
    }

    @Override
    public void accept(Event<T> structuredEvent) {
        structuredEventService.create(structuredEvent.getData());
    }
}

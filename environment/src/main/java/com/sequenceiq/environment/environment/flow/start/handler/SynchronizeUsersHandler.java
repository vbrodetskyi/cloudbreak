package com.sequenceiq.environment.environment.flow.start.handler;

import org.springframework.stereotype.Component;

import com.sequenceiq.environment.environment.EnvironmentStatus;
import com.sequenceiq.environment.environment.dto.EnvironmentDto;
import com.sequenceiq.environment.environment.flow.start.event.EnvStartEvent;
import com.sequenceiq.environment.environment.flow.start.event.EnvStartFailedEvent;
import com.sequenceiq.environment.environment.flow.start.event.EnvStartHandlerSelectors;
import com.sequenceiq.environment.environment.flow.start.event.EnvStartStateSelectors;
import com.sequenceiq.environment.environment.service.freeipa.FreeIpaPollerService;
import com.sequenceiq.environment.environment.service.freeipa.FreeIpaService;
import com.sequenceiq.environment.exception.FreeIpaOperationFailedException;
import com.sequenceiq.flow.reactor.api.event.EventSender;
import com.sequenceiq.flow.reactor.api.handler.EventSenderAwareHandler;

import reactor.bus.Event;

@Component
public class SynchronizeUsersHandler extends EventSenderAwareHandler<EnvironmentDto> {

    private final FreeIpaPollerService freeIpaPollerService;

    private final FreeIpaService freeIpaService;

    protected SynchronizeUsersHandler(EventSender eventSender, FreeIpaPollerService freeIpaPollerService, FreeIpaService freeIpaService) {
        super(eventSender);
        this.freeIpaPollerService = freeIpaPollerService;
        this.freeIpaService = freeIpaService;
    }

    @Override
    public String selector() {
        return EnvStartHandlerSelectors.SYNCHRONIZE_USERS_HANDLER_EVENT.name();
    }

    @Override
    public void accept(Event<EnvironmentDto> environmentDtoEvent) {
        EnvironmentDto environmentDto = environmentDtoEvent.getData();
        try {
            freeIpaService.describe(environmentDto.getResourceCrn()).ifPresent(freeIpa -> {
                if (freeIpa.getStatus() != null && !freeIpa.getStatus().isAvailable()) {
                    throw new FreeIpaOperationFailedException("FreeIPA is not in AVAILABLE state to synchronize users! Current state is: " +
                            freeIpa.getStatus().name());
                }
            });

            freeIpaPollerService.waitForSynchronizeUsers(environmentDto.getId(), environmentDto.getResourceCrn());
            EnvStartEvent envStartEvent = EnvStartEvent.EnvStartEventBuilder.anEnvStartEvent()
                    .withSelector(EnvStartStateSelectors.ENV_START_DATALAKE_EVENT.selector())
                    .withResourceId(environmentDto.getId())
                    .withResourceName(environmentDto.getName())
                    .build();
            eventSender().sendEvent(envStartEvent, environmentDtoEvent.getHeaders());
        } catch (Exception e) {
            EnvStartFailedEvent failedEvent = new EnvStartFailedEvent(environmentDto, e, EnvironmentStatus.START_SYNCHRONIZE_USERS_FAILED);
            eventSender().sendEvent(failedEvent, environmentDtoEvent.getHeaders());
        }
    }

}

package com.proj.KPO_DZ_2.infrastructure.event;

import com.proj.KPO_DZ_2.domain.event.EventPublisher;
import com.proj.KPO_DZ_2.domain.event.DomainEvent;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@Component
public class SpringEventPublisher implements EventPublisher {
    private final ApplicationEventPublisher springPublisher;

    public SpringEventPublisher(ApplicationEventPublisher springPublisher) {
        this.springPublisher = springPublisher;
    }

    @Override
    public void publish(DomainEvent event) {
        springPublisher.publishEvent(event);
    }
}
package com.alina.springmodulithcourse.order.dto;

import com.alina.springmodulithcourse.eventaction.action.Action;
import com.alina.springmodulithcourse.eventaction.action.CustomEventMarker;
import jakarta.validation.constraints.NotBlank;
import org.jmolecules.event.types.DomainEvent;

@CustomEventMarker(eventAction = Action.COMPLETE_PAYMENT)
public record CompleteOrder(
        @NotBlank(message = "orderIdentifier is required")
        String orderIdentifier,
        boolean paymentComplete) implements DomainEvent {
}

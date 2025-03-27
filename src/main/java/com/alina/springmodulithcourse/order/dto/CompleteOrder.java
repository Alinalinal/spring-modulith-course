package com.alina.springmodulithcourse.order.dto;

import com.alina.springmodulithcourse.eventaction.action.Action;
import com.alina.springmodulithcourse.eventaction.action.CustomEventMarker;
import org.jmolecules.event.types.DomainEvent;

@CustomEventMarker(eventAction = Action.COMPLETE_PAYMENT)
public record CompleteOrder(String orderIdentifier, boolean paymentComplete) implements DomainEvent {
}

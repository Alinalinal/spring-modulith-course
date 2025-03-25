package com.alina.springmodulithcourse.order.dto;

import com.alina.springmodulithcourse.eventaction.action.Action;
import com.alina.springmodulithcourse.eventaction.action.CustomEventMarker;
import org.jmolecules.event.types.DomainEvent;

@CustomEventMarker(eventAction = Action.PAYMENT)
public record OrderPaymentDto(String orderId, long amount) implements DomainEvent {
}

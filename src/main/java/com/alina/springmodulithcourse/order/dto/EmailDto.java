package com.alina.springmodulithcourse.order.dto;

import com.alina.springmodulithcourse.eventaction.action.Action;
import com.alina.springmodulithcourse.eventaction.action.CustomEventMarker;
import org.jmolecules.event.types.DomainEvent;

@CustomEventMarker(eventAction = Action.EMAIL)
public record EmailDto(String email, String customerName, String orderIdentifier, long totalAmount, boolean orderComplete)
        implements DomainEvent {
}

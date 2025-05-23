package com.alina.springmodulithcourse.order;

import com.alina.springmodulithcourse.order.dto.CompleteOrder;
import com.alina.springmodulithcourse.order.dto.EmailDto;
import com.alina.springmodulithcourse.order.dto.OrderPaymentDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class OrderEventService {

    public final ApplicationEventPublisher eventPublisher;

    @Transactional
    public void completeOrder(final OrderPaymentDto orderPaymentDto, EmailDto emailDto) {
        log.info("Completing order payment with details {}", orderPaymentDto);
        eventPublisher.publishEvent(orderPaymentDto);

        log.info("Sending email for order {}", emailDto);
        eventPublisher.publishEvent(emailDto);
    }

    @Transactional
    public void completePayment(CompleteOrder completeOrder, EmailDto emailDto) {
        log.info("Attempting to complete payment {}", completeOrder);
        eventPublisher.publishEvent(completeOrder);

        log.info("Completed payment Email {}", emailDto);
        eventPublisher.publishEvent(emailDto);
    }
}

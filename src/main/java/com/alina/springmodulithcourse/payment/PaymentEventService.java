package com.alina.springmodulithcourse.payment;

import com.alina.springmodulithcourse.order.dto.OrderPaymentDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.modulith.events.ApplicationModuleListener;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class PaymentEventService {

    private final PaymentRepository paymentRepository;

    @ApplicationModuleListener
    public void on(final OrderPaymentDto paymentDto) {
        log.info("Order payment received in listener: {}", paymentDto);
    }
}

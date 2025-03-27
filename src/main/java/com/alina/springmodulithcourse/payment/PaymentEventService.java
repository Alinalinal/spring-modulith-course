package com.alina.springmodulithcourse.payment;

import com.alina.springmodulithcourse.order.dto.CompleteOrder;
import com.alina.springmodulithcourse.order.dto.OrderPaymentDto;
import com.alina.springmodulithcourse.payment.type.PaymentStatus;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.modulith.events.ApplicationModuleListener;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class PaymentEventService {

    private final PaymentRepository paymentRepository;

    @ApplicationModuleListener
    public void on(final OrderPaymentDto paymentDto) {
        log.info("Order payment received in listener: {}", paymentDto);

        Payment payment = new Payment();
        payment.setOrderId(paymentDto.orderId());
        payment.setAmount(paymentDto.amount());
        paymentRepository.save(payment);
    }

    @ApplicationModuleListener
    public void completeOrder(final CompleteOrder completeOrder) {
        log.info("Complete Order received in listener: {}", completeOrder);

        Optional<Payment> optionalPayment = paymentRepository.getPaymentsByOrderId(completeOrder.orderIdentifier());

        if (optionalPayment.isPresent()) {
            Payment payment = optionalPayment.get();
            payment.setStatus(PaymentStatus.COMPLETED);
            paymentRepository.save(payment);
        }
    }
}

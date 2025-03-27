package com.alina.springmodulithcourse.email;

import com.alina.springmodulithcourse.order.dto.EmailDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.modulith.events.ApplicationModuleListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class EmailService {

    @ApplicationModuleListener
    void sendEmail(final EmailDto emailDto) {
        log.info("Email properties received");

        if (!emailDto.orderComplete()) {
            String body = "Dear " + emailDto.customerName() + ",\n" +
                    "An Order was initialized with Id " + emailDto.orderIdentifier() + " and total sum of: N" +
                    emailDto.totalAmount()/100;
            log.info("Initial Email order details: {}", body);
        } else {
            String body = "Dear " + emailDto.customerName() + ",\n" +
                    "Your Order with Id " + emailDto.orderIdentifier() + " and total sum of: N" +
                    emailDto.totalAmount()/100 + " was completed successfully";
            log.info("Email completion details: {}", body);
        }

        log.info("Email sent to: {}", emailDto.email());
    }
}

package com.alina.springmodulithcourse.order;

import com.alina.springmodulithcourse.order.dto.CompleteOrder;
import com.alina.springmodulithcourse.order.dto.CompleteOrderResponse;
import com.alina.springmodulithcourse.order.dto.OrderDto;
import com.alina.springmodulithcourse.order.dto.OrderResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "order")
public class OrderController {

    private final OrderService orderService;

    @PostMapping
    public ResponseEntity<OrderResponseDto> createOrder(@RequestBody OrderDto orderDto) {
        return new ResponseEntity<>(orderService.createOrder(orderDto), HttpStatus.OK);
    }

    @PostMapping(path = "complete")
    public ResponseEntity<CompleteOrderResponse> completeOrder(@RequestBody CompleteOrder completeOrder) {
        return new ResponseEntity<>(orderService.completePayment(completeOrder), HttpStatus.OK);
    }
}

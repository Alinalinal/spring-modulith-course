package com.alina.springmodulithcourse.order;

import com.alina.springmodulithcourse.inventory.exposed.InventoryDto;
import com.alina.springmodulithcourse.inventory.exposed.InventoryService;
import com.alina.springmodulithcourse.order.dto.CompleteOrder;
import com.alina.springmodulithcourse.order.dto.CompleteOrderResponse;
import com.alina.springmodulithcourse.order.dto.EmailDto;
import com.alina.springmodulithcourse.order.dto.InventoryRequestDto;
import com.alina.springmodulithcourse.order.dto.OrderDto;
import com.alina.springmodulithcourse.order.dto.OrderPaymentDto;
import com.alina.springmodulithcourse.order.dto.OrderResponseDto;
import com.alina.springmodulithcourse.order.type.Status;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicLong;

@Slf4j
@Service
@RequiredArgsConstructor
public class OrderService {

    private final InventoryService inventoryService;
    private final OrderRepository orderRepository;
    private final OrderInventoryRepository orderInventoryRepository;
    private final OrderEventService orderEventService;

    public OrderResponseDto createOrder(OrderDto orderDto) {

        // get Inventories by names
        List<String> inventoryNames = orderDto.inventoryRequestDtoList().stream()
                .map(InventoryRequestDto::inventoryName)
                .toList();

        // get Inventories
        List<InventoryDto> inventories = inventoryService.fetchAllInName(inventoryNames);

        final AtomicLong amount = new AtomicLong();
        // persist the order
        Order order = buildAndPersistOrder(orderDto);
        log.info("Order created: {}", order);

        // build and persist OrderInventory
        buildAndPersistOrderInventories(orderDto, inventories,order.getId(), amount);

        OrderPaymentDto orderPaymentDto = new OrderPaymentDto(order.getOrderIdentifier(), amount.get());
        EmailDto emailDto =
                new EmailDto(orderDto.customerEmail(), orderDto.customerName(), order.getOrderIdentifier(),
                        orderPaymentDto.amount(), false);

        orderEventService.completeOrder(orderPaymentDto, emailDto);

        return new OrderResponseDto("Order Currently processed", 102);
    }

    public CompleteOrderResponse completePayment(CompleteOrder completeOrder) {
        Optional<Order> optionalOrder = orderRepository.getOrderByOrderIdentifier(completeOrder.orderIdentifier());

        if (optionalOrder.isEmpty()) {
            throw new RuntimeException("Order not found");
        }

        Order order = optionalOrder.get();

        final long amount = orderInventoryRepository.orderIdAmount(order.getId());
        EmailDto emailDto =
                new EmailDto(order.getCustomerEmail(), order.getCustomerName(), order.getOrderIdentifier(), amount,true);

        orderEventService.completePayment(completeOrder, emailDto);
        return new CompleteOrderResponse(true);
    }

    private void buildAndPersistOrderInventories(
            OrderDto orderDto, List<InventoryDto> inventories, long orderId, AtomicLong amount) {
        List<OrderInventory> orderInventories = new ArrayList<>(inventories.size());

        inventories.forEach(inventoryDto -> {
            OrderInventory orderInventory = new OrderInventory();

            InventoryRequestDto inventoryRequestDto = getInventoryRequestDtoByNames(inventoryDto.name(), orderDto.inventoryRequestDtoList());
            orderInventory.setOrderId(orderId);
            orderInventory.setInventoryId(inventoryDto.id());
            orderInventory.setQty(inventoryRequestDto.qty());

            long totalPrice = inventoryDto.price() * inventoryRequestDto.qty();
            orderInventory.setTotalQtyPrice(totalPrice);

            orderInventories.add(orderInventory);
            amount.addAndGet(totalPrice);
        });

        orderInventoryRepository.saveAll(orderInventories);
        log.info("Order Inventories persisted: {}", orderInventories);
    }

    private static InventoryRequestDto getInventoryRequestDtoByNames(String inventoryName, List<InventoryRequestDto> inventoryRequestDtoList) {
        return inventoryRequestDtoList
                .stream()
                .filter(inv -> inv.inventoryName().equals(inventoryName))
                .findFirst().orElse(null);
    }

    private Order buildAndPersistOrder(OrderDto orderDto) {
        Order order = new Order();

        order.setOrderIdentifier(UUID.randomUUID().toString());
        order.setCustomerName(orderDto.customerName());
        order.setCustomerEmail(orderDto.customerEmail());
        order.setStatus(Status.COMPLETED);

        return orderRepository.save(order);
    }
}

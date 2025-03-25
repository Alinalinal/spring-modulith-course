package com.alina.springmodulithcourse.order.dto;

import java.util.List;

public record OrderDto(String customerName, String customerEmail, List<InventoryRequestDto> inventoryRequestDtoList) { }

package com.alina.springmodulithcourse.order.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;

import java.util.List;

public record OrderDto(
        @NotBlank(message = "customerName is required")
        String customerName,
        @NotBlank(message = "customerEmail is required")
        String customerEmail,
        @Valid
        List<InventoryRequestDto> inventoryRequestDtoList) {
}

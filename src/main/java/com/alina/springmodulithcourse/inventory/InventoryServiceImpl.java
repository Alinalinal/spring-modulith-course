package com.alina.springmodulithcourse.inventory;

import com.alina.springmodulithcourse.exception.ModulithException;
import com.alina.springmodulithcourse.inventory.exposed.InventoryDto;
import com.alina.springmodulithcourse.inventory.exposed.InventoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
class InventoryServiceImpl implements InventoryService {

    private final InventoryRepository inventoryRepository;

    @Override
    public InventoryDto fetchInventoryByName(String name) {
        return inventoryRepository.getInventoryByName(name)
                .map(InventoryUtil::mapInventoryDto)
                .orElseThrow(() -> new ModulithException("Could not find inventory be name: " + name));
    }

    @Override
    public List<InventoryDto> fetchAllInName(List<String> inventoryNames) {
        return inventoryRepository.getInventoryByNameIn(inventoryNames)
                .stream()
                .map(InventoryUtil::mapInventoryDto)
                .toList();
    }
}

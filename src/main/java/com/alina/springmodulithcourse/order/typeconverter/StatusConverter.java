package com.alina.springmodulithcourse.order.typeconverter;

import com.alina.springmodulithcourse.exception.ModulithException;
import com.alina.springmodulithcourse.order.type.Status;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import java.util.Arrays;

@Converter(autoApply = true)
public class StatusConverter implements AttributeConverter<Status, String> {

    @Override
    public String convertToDatabaseColumn(Status status) {
        return Arrays.stream(Status.values())
                .filter(s -> s == status)
                .map(Status::getCode)
                .findFirst().orElseThrow(() -> new ModulithException("No status found"));
    }

    @Override
    public Status convertToEntityAttribute(String code) {
        return Arrays.stream(Status.values())
                .filter(s -> s.getCode().equals(code))
                .findFirst().orElseThrow(() -> new ModulithException("No status found"));
    }
}

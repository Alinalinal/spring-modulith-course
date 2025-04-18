package com.alina.springmodulithcourse.eventaction.action;

import com.alina.springmodulithcourse.exception.ModulithException;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import java.util.Arrays;

@Converter(autoApply = true)
public class ActionConverter implements AttributeConverter<Action, String> {

    @Override
    public String convertToDatabaseColumn(Action action) {
        if (action == null) {
            throw new ModulithException("action is null");
        }
        return action.getCode();
    }

    @Override
    public Action convertToEntityAttribute(String code) {
        if (code == null) {
            throw new ModulithException("code is null");
        }
        return Arrays.stream(Action.values())
                .filter(action -> action.getCode().equalsIgnoreCase(code))
                .findFirst().orElseThrow(() -> new ModulithException("action not found"));
    }
}

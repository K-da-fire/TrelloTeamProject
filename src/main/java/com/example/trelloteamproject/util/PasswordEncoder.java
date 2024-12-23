package com.example.trelloteamproject.util;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import org.springframework.stereotype.Component;

@Converter
@Component
public class PasswordEncoder implements AttributeConverter<String, String> {
    @Override
    public String convertToDatabaseColumn(String string) {
        return "";
    }

    @Override
    public String convertToEntityAttribute(String string) {
        return "";
    }
}

package com.example.trelloteamproject.util;

import at.favre.lib.crypto.bcrypt.BCrypt;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import org.springframework.stereotype.Component;

@Converter
@Component
public class PasswordEncoder implements AttributeConverter<String, String> {

    public String encoder(String rawPassword) {
        return BCrypt.withDefaults().hashToString(BCrypt.MIN_COST, rawPassword.toCharArray());
    }

    public boolean matches(String rawPassword, String encodedPassword) {
        BCrypt.Result result = BCrypt.verifyer().verify(rawPassword.toCharArray(), encodedPassword);
        return result.verified;
    }

    @Override
    public String convertToDatabaseColumn(String password) {
        return encoder(password);
    }

    @Override
    public String convertToEntityAttribute(String password) {
        return password;
    }


}

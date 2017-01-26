package com.example.entity;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import com.example.util.EncryptUtil;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Converter(autoApply = false)
public class CryptoConverter implements AttributeConverter<String, String> {

    private static EncryptUtil encryptUtil;

    @Override
    public String convertToDatabaseColumn(String attribute) {
        return getEncryptUtilInstance().encrypt(attribute);
    }

    @Override
    public String convertToEntityAttribute(String dbData) {
        return getEncryptUtilInstance().decrypt(dbData);
    }
    
    public EncryptUtil getEncryptUtilInstance() {
        return encryptUtil == null ? EncryptUtil.getInstance() : encryptUtil;
    }

}

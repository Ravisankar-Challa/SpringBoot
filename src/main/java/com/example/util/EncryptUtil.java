package com.example.util;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;
import java.security.GeneralSecurityException;
import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Component;

import com.example.configuration.ApplicationConfiguration;
import com.example.exception.ApplicationException;
import com.example.exception.ErrorCodes;

@Slf4j
@Component
public class EncryptUtil {

    private static final String UTF_8 = "UTF-8";
    private static final String AES = "AES";
    private static final String AES_CBC_PKCS5PADDING = "AES/CBC/PKCS5PADDING";

    private ApplicationConfiguration config;

    public EncryptUtil(ApplicationConfiguration config) {
        this.config = config;
        removedJavaCryrptographyRestrictions();
    }

    public String encrypt(String value) {
        try {
            IvParameterSpec iv = new IvParameterSpec(config.getInitVector().getBytes(UTF_8));
            SecretKeySpec skeySpec = new SecretKeySpec(config.getEncryptionKey().getBytes(UTF_8), AES);
            Cipher cipher = Cipher.getInstance(AES_CBC_PKCS5PADDING);
            cipher.init(Cipher.ENCRYPT_MODE, skeySpec, iv);
            byte[] encrypted = cipher.doFinal(value.getBytes());
            return Base64.getEncoder().encodeToString(encrypted);
        } catch (GeneralSecurityException | UnsupportedEncodingException exp) {
            log.error(exp.getMessage(), exp);
            throw new ApplicationException(ErrorCodes.AUTHENTICATION_EXCEPTION.getErrorCode(),
                                           ErrorCodes.AUTHENTICATION_EXCEPTION.getErrorMessage());
        }
    }

    public String decrypt(String encrypted) {
        try {
            IvParameterSpec iv = new IvParameterSpec(config.getInitVector().getBytes(UTF_8));
            SecretKeySpec skeySpec = new SecretKeySpec(config.getEncryptionKey().getBytes(UTF_8), AES);
            Cipher cipher = Cipher.getInstance(AES_CBC_PKCS5PADDING);
            cipher.init(Cipher.DECRYPT_MODE, skeySpec, iv);
            byte[] original = cipher.doFinal(Base64.getDecoder().decode(encrypted));
            return new String(original);
        } catch (IllegalArgumentException | GeneralSecurityException | UnsupportedEncodingException exp) {
            log.error("Failed to decrypt : {}", encrypted);
            log.error(exp.getMessage(), exp);
            throw new ApplicationException(ErrorCodes.AUTHENTICATION_EXCEPTION.getErrorCode(),
                                           ErrorCodes.AUTHENTICATION_EXCEPTION.getErrorMessage());
        }
    }

    private void removedJavaCryrptographyRestrictions() {
        try {
            Field field = Class.forName("javax.crypto.JceSecurity").getDeclaredField("isRestricted");
            field.setAccessible(true);
            field.set(null, false);
        } catch (SecurityException | NoSuchFieldException | ClassNotFoundException | IllegalArgumentException
                | IllegalAccessException exp) {
            log.error(exp.getMessage(), exp);
        }
    }

}
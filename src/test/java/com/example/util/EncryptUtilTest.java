package com.example.util;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;

import org.junit.Before;
import org.junit.Test;

import com.example.configuration.ApplicationConfiguration;
import com.example.exception.ApplicationException;

public class EncryptUtilTest {
	
	private String encryptionKey = "UmF2aSBTYW5rYXIgUmVkZHkgQ2hhbGxh"; // 256 bit key
    private String initVector = "UWFudGFzIFBheW1l"; // 16 bytes IV
    
    private EncryptUtil encryptUtil;

    @Before
    public void setup() {
        ApplicationConfiguration config = new ApplicationConfiguration();
        config.setEncryptionKey(encryptionKey);
        config.setInitVector(initVector);
        encryptUtil = new EncryptUtil(config);
    }
    
	@Test
	public void test_encyption() {
		assertThat(encryptUtil.encrypt("Hello World"), equalTo("8OyMYGQYgCkrXey7dwCP0Q=="));
	}
	
	@Test
	public void test_decyption() {
		assertThat(encryptUtil.decrypt("8OyMYGQYgCkrXey7dwCP0Q=="), equalTo("Hello World"));
	}
	
	@Test(expected = ApplicationException.class)
	public void should_throw_application_exeption_for_invalid_input_when_decrypting_message() {
	    assertThat(encryptUtil.decrypt("Invalid Input"), equalTo("Hello World"));
	}
}

package com.example.util;

import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Matchers;
import org.mockito.Mockito;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.example.configuration.ApplicationConfiguration;
import com.example.exception.ApplicationException;

public class NetworkUtilTest {
    
    private NetworkUtil networkUtil;
    private RestTemplate restClient;
    
    @Before
    public void setup() {
        restClient = mock(RestTemplate.class);
        networkUtil = new NetworkUtil(restClient);
    }
    
    @Test(expected = ApplicationException.class)
    public void should_throw_application_exception_put_request_with_empty_url() {
        networkUtil.put(null, null, null, null, null);
    }
    
    @Test
    public void should_initlize_with_default_time_outs_if_not_supplied_throw_application_config() {
        ApplicationConfiguration config = new ApplicationConfiguration();
        RestTemplate restClientTest = mock(RestTemplate.class);
        RestTemplateBuilder restTemplateBuilder = mock(RestTemplateBuilder.class);
        NetworkUtil networkUtilTest = new NetworkUtil(restClient);
        //verify(restTemplateBuilder, times(1)).setReadTimeout(60000);
    }
    
    @Test
    public void test_put_request_with_just_url() {
        ResponseEntity<String> respEntity = new ResponseEntity<String>(
                "helo", HttpStatus.ACCEPTED);
        when(restClient.exchange(eq("http://test.com"), eq(HttpMethod.PUT), Matchers.<HttpEntity<Object>> any(), Mockito.<Class<String>>any(), Mockito.<Map<String,?>>any())).thenReturn(respEntity);
        networkUtil.put("http://test.com", null, null, null, String.class);
        //ArgumentCaptor<String> captor = ArgumentCaptor.forClass(String.class);
        //verify(restClient).exchange(startsWith("http://newRootUrl"), any(HttpMethod.class), any(HttpEntity.class), Matchers.<Class<Object>>any(), (Object[]) anyVararg());
        //assertThat(captor.getValue(), equalTo("http://test.com"));
    }
    
}

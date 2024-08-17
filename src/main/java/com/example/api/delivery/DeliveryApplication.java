package com.example.api.delivery;

import com.example.api.delivery.handling.VoucherErrorHandling;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class DeliveryApplication {

    public static void main(String[] args) {
        SpringApplication.run(DeliveryApplication.class, args);
    }

    @Autowired
    private VoucherErrorHandling voucherErrorHandling;

    @Bean
    public RestTemplate appRestTemplate() {
        HttpComponentsClientHttpRequestFactory factory = new HttpComponentsClientHttpRequestFactory();
        factory.setConnectionRequestTimeout(10000);
        factory.setConnectTimeout(10000);
        var restTemplate = new RestTemplate(factory);
        restTemplate.setErrorHandler(voucherErrorHandling);
        return new RestTemplate(factory);
    }
}

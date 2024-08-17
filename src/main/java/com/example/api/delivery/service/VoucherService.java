package com.example.api.delivery.service;

import com.example.api.delivery.handling.VoucherErrorHandling;
import com.example.api.delivery.model.VoucherResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;

@Service
public class VoucherService {

    private static final Logger log = LoggerFactory.getLogger(VoucherService.class);
    @Value("${voucher.apikey}")
    private String apiKey;

    @Value("${voucher.url}")
    private String url;
    @Autowired
    private RestTemplate appRestTemplate;

    public VoucherResponse getVoucherInfo(String voucherCode) {
        try{
            ResponseEntity<VoucherResponse> result = appRestTemplate.getForEntity(url + voucherCode + "?key=" + apiKey, VoucherResponse.class);
            return result.getBody();
        }catch(ResourceAccessException ex){
            log.error(ex.getMessage());
            throw new HttpClientErrorException(HttpStatus.GATEWAY_TIMEOUT);
        }catch (HttpClientErrorException ex){
            if(ex.getStatusCode()== HttpStatus.NOT_FOUND){
                return new VoucherResponse();
            }else{
                throw ex;
            }
        }catch (Exception ex1){
            log.error(ex1.getMessage());
            throw ex1;
        }
    }
}

package com.example.api.delivery.handling;

import com.example.api.delivery.model.VoucherResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.ResponseErrorHandler;
import org.springframework.web.client.HttpServerErrorException;


import java.io.IOException;

@Component
public class VoucherErrorHandling implements ResponseErrorHandler {

    private static final Logger log = LoggerFactory.getLogger(VoucherErrorHandling.class);

    public boolean hasError(ClientHttpResponse httpResponse) throws IOException {
        return httpResponse.getStatusCode().is5xxServerError() ||
                httpResponse.getStatusCode().is4xxClientError();
    }

    public void handleError(ClientHttpResponse httpResponse) throws IOException {
        log.error(httpResponse.getStatusText());
        if (httpResponse.getStatusCode().is5xxServerError()) {
            throw new HttpServerErrorException(httpResponse.getStatusCode());
        } else if (httpResponse.getStatusCode().is4xxClientError()) {
            throw new HttpClientErrorException(httpResponse.getStatusCode());
        }
    }

}

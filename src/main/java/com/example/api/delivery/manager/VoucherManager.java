package com.example.api.delivery.manager;

import com.example.api.delivery.model.VoucherResponse;
import com.example.api.delivery.service.VoucherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;


@Component
public class VoucherManager {
	@Autowired
	private VoucherService voucherService;
	public VoucherResponse getVoucherDiscount(String voucherCode){
		return voucherService.getVoucherInfo(voucherCode);
	}
}

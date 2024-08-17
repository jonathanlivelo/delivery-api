package com.example.api.delivery.service;

import com.example.api.delivery.manager.RuleManager;
import com.example.api.delivery.manager.VoucherManager;
import com.example.api.delivery.model.DeliveryInfo;
import com.example.api.delivery.model.VoucherResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;

import java.time.LocalDate;

@Service
public class DeliveryService {
	@Autowired
	private VoucherManager voucherManager;
	@Autowired
	private RuleManager ruleManger;
	public Double computeCostOfDeliver(Double weight, Double height, Double width, Double length) throws Exception {
		return computeCostOfDeliver(weight,height,width,length,null);
	}

	public Double computeCostOfDeliver(Double weight, Double height, Double width, Double length, String voucherCode) throws Exception {

		var voucherResponse =new VoucherResponse();
		if (voucherCode !=null){
			voucherResponse = voucherManager.getVoucherDiscount(voucherCode);
		}
		DeliveryInfo deliveryInfo = new DeliveryInfo(false,0.0,weight,height,width,length,voucherCode,voucherResponse.getDiscount(),voucherResponse.getExpiry());
		ruleManger.executeRules(deliveryInfo);
		if (deliveryInfo.isRejected()) {
			throw new HttpClientErrorException("Weight not acceptable",HttpStatus.BAD_REQUEST,"",null,null,null);
		}
		return deliveryInfo.getTotalCost();
	}
}

package com.example.api.delivery.controller;

import com.example.api.delivery.service.DeliveryService;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;

import java.io.FileNotFoundException;

@RestController
@RequestMapping("delivery")
public class DeliveryController {
	@Autowired
	private DeliveryService deliveryService;
	@GetMapping("/charge/{weight}/{height}/{width}/{length}")
	public ResponseEntity<String> calculate(@PathVariable("weight") double weight, @PathVariable("height") double height,
			@PathVariable("width") double width, @PathVariable("length") double length,
			@RequestParam(name = "voucher-code", required = false, defaultValue = "") String voucherCode) {
		try {
			Double result=0.0;
			if (voucherCode.isEmpty()) {
				result = deliveryService.computeCostOfDeliver(weight, height, width, length);
			}else {
				result = deliveryService.computeCostOfDeliver(weight, height, width, length, voucherCode);
			}
			return ResponseEntity.ok(result.toString());
        } catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }

    }
}

package com.example.api.delivery.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DeliveryInfo {
	private boolean isRejected;
	private Double totalCost;
	private Double weight;
	private Double height;
	private Double width;
	private Double length;
	private String voucherCode;
	private Double discount;
	private Date expiry;
}

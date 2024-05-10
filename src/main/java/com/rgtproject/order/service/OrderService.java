package com.rgtproject.order.service;

import com.rgtproject.order.dto.RequestOrderDto;
import com.rgtproject.order.dto.ResponseOrderDto;

public interface OrderService {
	public ResponseOrderDto orderSave(RequestOrderDto dto);
}

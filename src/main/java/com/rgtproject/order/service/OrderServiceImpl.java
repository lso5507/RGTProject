package com.rgtproject.order.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentMap;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.rgtproject.global.util.utils.CustomTokenUtil;
import com.rgtproject.member.service.MemberService;
import com.rgtproject.order.dto.RequestOrderDto;
import com.rgtproject.order.dto.ResponseOrderDto;
import com.rgtproject.order.entity.Order;
import com.rgtproject.order.repository.OrderRepository;

import io.jsonwebtoken.lang.Assert;
import lombok.RequiredArgsConstructor;

/**
 * 주문신청 서비스는 임의로 Mock 데이터를 제공합니다.
 */
@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements  OrderService{
	private final OrderRepository orderRepository;
	private final MemberService memberService;

	/**
	 * 실제 장바구니 Entity 미구현으로 프로세스만 정의
	 * 결제 시 WHERE STATUS == "complete" 이며,
	 * STORE_NUM, STORE_ID, USER_ID 가 같은 ORDER 결제
	 * @param dto
	 * @return
	 */
	@Override
	public ResponseOrderDto orderSave(RequestOrderDto dto) {
		orderSaveValidation(dto);
		dto.setUserId(getMemberId(dto));
		//장바구니 엔티티 미구현으로 BasketDetailId 가상데이터
		orderRepository.save(new Order(dto,0L));
		return new ResponseOrderDto(HttpStatus.OK.value(), "주문완료 되었습니다.");
	}

	private Long getMemberId(RequestOrderDto dto) {
		//get Member PK
		return  memberService.selectId(dto.getUserAccount());
	}

	private static void orderSaveValidation(RequestOrderDto dto) {
		Assert.notNull(dto.getStoreId(),"###ORDER_SAVE_StoreIdIsNull###");
		Assert.notNull(dto.getStoreNum(),"###ORDER_SAVE_StoreNumIsNull###");
	}
}

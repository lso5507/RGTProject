package com.rgtproject.order.entity;

import java.io.Serializable;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import com.rgtproject.global.common.BasicEntity;
import com.rgtproject.order.dto.RequestOrderDto;
import com.rgtproject.order.enums.OrderStatus;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@Table(name = "TB_ORDER  ")
@Getter
@DynamicUpdate
@DynamicInsert
public class Order extends BasicEntity implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long orderId;
	@Column(nullable = false)
	private String storeId;
	@Column(nullable = false)
	private Long storeNum;
	@Column(nullable = false)
	private Long userId;
	@Column(nullable = false)
	private String status;
	@Column(nullable = false)
	private Long basketDetailId;

	@Builder
	public Order(RequestOrderDto dto,Long basketDetailId) {
		this.storeId = dto.getStoreId();
		this.storeNum = dto.getStoreNum();
		this.userId = dto.getUserId();
		//Default Confirm
		this.status = OrderStatus.CONFIRM.toString();
		this.basketDetailId=basketDetailId;
	}
}

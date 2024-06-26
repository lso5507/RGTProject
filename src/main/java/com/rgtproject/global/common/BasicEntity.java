package com.rgtproject.global.common;

import java.io.Serializable;
import java.time.LocalDateTime;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@MappedSuperclass
@Getter
@EntityListeners(AuditingEntityListener.class)
public abstract class BasicEntity implements Serializable {

	@CreatedDate
	@Column(nullable = false, length = 20, updatable = false)
	protected LocalDateTime createdAt;

	@LastModifiedDate
	@Column(length = 20)
	protected LocalDateTime updatedAt;

	@Setter
	@Column(nullable = false)
	protected Boolean isEnable = true;

}

package com.shop.entity;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@EntityListeners(value = {AuditingEntityListener.class})
@MappedSuperclass //공통 매핑 정보 필요시 부모클래스 상속받는 자식 클래스에 매핑정보만 제공
@Getter @Setter
public abstract class BaseEntity extends BaseTimeEntity {

    @CreatedBy
    @Column(updatable = false) //계속 업데이트 되는걸 막는다.
    private String createdBy;

    @LastModifiedBy
    private String modifiedBy;

}

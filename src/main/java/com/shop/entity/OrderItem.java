package com.shop.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.cglib.core.Local;

import java.time.LocalDateTime;

@Entity
@Getter@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderItem {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_item_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "item_id")
    private Item item; //하나의 상품은 여러 주문 상품으로 들어갈수있음.

    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order; //한번의 주문에 여러개의 상품을 주문할수 있음.

    private int orderPrice; // 주문가격

    private int count; // 수량

    private LocalDateTime regTime;

    private LocalDateTime updateTime;



}

package com.shop.entity;

import com.shop.constant.ItemSellStatus;
import com.shop.dto.ItemFormDto;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity //클래스를 엔티티로 선언
@Getter @Setter @ToString //lombook
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "item")
public class Item extends BaseEntity{

    @Id //테이블의 기본키에 사용할 속성을 정함. entity클래스는 반드시 기본키를 가져야한다.
    @Column(name="item_id") //필드와 컬럼 매핑
    @GeneratedValue(strategy = GenerationType.IDENTITY) //기본키 생성을 데이터 베이스에 위임한다. Mysql 이기 떄문에 오토인크리먼트 사용.
    private Long id;          //상품 코드

    @Column(nullable = false, length = 50)
    private String itemNm;  // 상품명

    @Column(nullable = false)
    private int price;      // 가격

    @Column(nullable = false)
    private int stockNumber; // 재고수량

    @Lob // 사이즈가 큰 데이터 타입이다.
    @Column(nullable = false)
    private String itemDetail; // 상품 상세 설명

    @Enumerated(EnumType.STRING) //enum 타입을 매핑한다.
    private ItemSellStatus itemSellStatus; // 상품 판매 상태

    public void updateItem(ItemFormDto itemFormDto){
        this.itemNm = itemFormDto.getItemNm();
        this.price = itemFormDto.getPrice();
        this.stockNumber = itemFormDto.getStockNumber();
        this.itemDetail = itemFormDto.getItemDetail();
        this.itemSellStatus = itemFormDto.getItemSellStatus();
    }

}

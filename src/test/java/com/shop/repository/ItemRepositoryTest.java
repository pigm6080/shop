package com.shop.repository;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.shop.constant.ItemSellStatus;
import com.shop.entity.Item;
import com.shop.entity.QItem;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.thymeleaf.util.StringUtils;

import java.time.LocalDateTime;
import java.util.List;

@SpringBootTest
public class ItemRepositoryTest {

    @Autowired
    ItemRepository itemRepository;

    @Autowired
    EntityManager em; //queryDSL

    public void createItemList2(){

        for(int i = 1; i<=5; i++){
            Item item = Item.builder()
                    .itemNm("테스트 상품" + i)
                    .price(10000 + i)
                    .itemDetail("테스트 상품 상세 설명" + i)
                    .itemSellStatus(ItemSellStatus.SELL)
                    .stockNumber(100)
                    .regTime(LocalDateTime.now())
                    .updateTime(LocalDateTime.now())
                    .build();

            Item saveItem = itemRepository.save(item);
        }

        for(int i = 6; i<=10; i++){
            Item item = Item.builder()
                    .itemNm("테스트 상품" + i)
                    .price(10000 + i)
                    .itemDetail("테스트 상품 상세 설명" + i)
                    .itemSellStatus(ItemSellStatus.SOLD_OUT)
                    .stockNumber(0)
                    .regTime(LocalDateTime.now())
                    .updateTime(LocalDateTime.now())
                    .build();

            Item saveItem = itemRepository.save(item);
        }

    }


    @Test
    @DisplayName("Querydsl 조회 테스트1")
    public void queryDslTest(){
        this.createItemList();
        JPAQueryFactory query = new JPAQueryFactory(em);
        QItem qItem = QItem.item;

        List<Item> itemList = query.selectFrom(QItem.item)
                .where(qItem.itemSellStatus.eq(ItemSellStatus.SELL))
                .where(qItem.itemDetail.like("%" + "테스트 상품 상세 설명" + "%"))
                .orderBy(qItem.price.desc())
                .fetch();
/*
		for(Item item : itemList){
			System.out.println(item.toString());
		}
 		*/
        itemList.forEach(item -> System.out.println(item));

    }



    public void createItemList(){
        for(int i = 1; i<=10; i++){
            Item item = Item.builder()
                    .itemNm("테스트 상품" + i)
                    .price(10000 + i)
                    .itemDetail("테스트 상품 상세 설명" + i)
                    .itemSellStatus(ItemSellStatus.SELL)
                    .stockNumber(100)
                    .regTime(LocalDateTime.now())
                    .updateTime(LocalDateTime.now())
                    .build();

            Item saveItem = itemRepository.save(item);
        }
    }




    @Test
    @DisplayName("nativeQuery 속성을 이용한 상품 조회 테스트")
    public void findByItemDetailByNative() {
        this.createItemList(); //데이터 10개 저장
        List<Item> itemList = itemRepository.findByItemDetailByNative("테스트 상품 상세 설명");
    /*
        for (Item item : itemList){
            System.out.println(item.toString());
        }ㄴ
    */
        itemList.forEach(item -> System.out.println(item));
    }

    @Test
    @DisplayName("@Query를 이용한 상품 조회 테스트")
    public void findByItemDetailTest() {
        this.createItemList(); //데이터 10개 저장
        List<Item> itemList = itemRepository.findByItemDetail("테스트 상품 상세 설명");
    /*
        for (Item item : itemList){
            System.out.println(item.toString());
        }ㄴ
    */
        itemList.forEach(item -> System.out.println(item));
    }

    @Test
    @DisplayName("가격 내림차순 조회 테스트")
    public void findByPriceLessThanOrderByPriceDescTest() {
        this.createItemList(); //데이터 10개 저장
        List<Item> itemList = itemRepository.findByPriceLessThanOrderByPriceDesc(10005);
    /*
        for (Item item : itemList){
            System.out.println(item.toString());
        }ㄴ
    */
        itemList.forEach(item -> System.out.println(item));
    }

    @Test
    @DisplayName("가격 LessThan 테스트")
    public void findByPriceLessThanTest() {
        this.createItemList(); //데이터 10개 저장
        List<Item> itemList = itemRepository.findByPriceLessThan(10005);
    /*
        for (Item item : itemList){
            System.out.println(item.toString());
        }ㄴ
    */
        itemList.forEach(item -> System.out.println(item));
    }

    @Test
    @DisplayName("상품명, 상품 상세 설명 or 테스트")
    public void findByItemNmOrItemDetailTest() {
        this.createItemList(); //데이터 10개 저장
        List<Item> itemList = itemRepository.findByItemNmOrItemDetail("테스트 상품1","테스트 상품 상세 설명5");
    /*
        for (Item item : itemList){
            System.out.println(item.toString());
        }
    */
        itemList.forEach(item -> System.out.println(item));
    }

    @Test
    @DisplayName("상품 조회 테스트")
    public void findByItemTest() {
        this.createItemList(); //데이터 10개 저장
        List<Item> itemList = itemRepository.findByItemNm("테스트 상품1");
    /*
        for (Item item : itemList){
            System.out.println(item.toString());
        }
    */
        itemList.forEach(item -> System.out.println(item));
    }



    @Test
    @DisplayName("상품 저장 테스트")
    public void createItemTest() {
        Item item = Item.builder()
                .itemNm("테스트 상품")
                .price(10000)
                .itemDetail("테스트 상품 상세 설명")
                .itemSellStatus(ItemSellStatus.SELL)
                .stockNumber(100)
                .regTime(LocalDateTime.now())
                .updateTime(LocalDateTime.now())
                .build();
        System.out.println("=========> item " + item);
        Item saveItem = itemRepository.save(item);
        System.out.println("=========> item " + saveItem);
    }


    @Test
    @DisplayName("상품 Querydsl 조회 테스트 2")
    public void queryDslTest2(){

        this.createItemList2();

        BooleanBuilder builder = new BooleanBuilder();
        String itemDetail = "테스트";
        int price = 10003;
        String itemSellStatus = "SELL";

        QItem item = QItem.item;

        builder.and(item.itemDetail.like("%" + itemDetail + "%"));
        builder.and(item.price.gt(price));

        if(StringUtils.equals(itemSellStatus, ItemSellStatus.SELL)){
            builder.and(item.itemSellStatus.eq(ItemSellStatus.SELL));
        }

        Pageable pageable = PageRequest.of(0, 5);
        Page<Item> page = itemRepository.findAll(builder, pageable);

        System.out.println("total elements : " +
                page.getTotalElements());

        List<Item> content = page.getContent();
        content.stream().forEach((e) ->{
            System.out.println(e);
        });

    }




}

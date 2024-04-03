package com.shop.repository;

import com.shop.constant.ItemSellStatus;
import com.shop.entity.Item;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.List;

@SpringBootTest
public class ItemRepositoryTest {

    @Autowired
    ItemRepository itemRepository;

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




}

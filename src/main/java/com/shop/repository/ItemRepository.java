package com.shop.repository;

import com.shop.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ItemRepository extends JpaRepository<Item, Long> ,
        QuerydslPredicateExecutor<Item> {

    List<Item> findByItemNm(String itemNm); //쿼리 메소드

    List<Item> findByItemNmOrItemDetail(String itemNm,String itemDetail);

    List<Item> findByPriceLessThan(Integer price);

    List<Item> findByPriceLessThanOrderByPriceDesc(Integer price);

    @Query("select i from Item i where i.itemDetail like %:itemDetail% order by i.price desc") //객체로 한것이다.
    List<Item> findByItemDetail(@Param("itemDetail") String itemDetail); //객체로 한것

    @Query(value = "select * from item i where i.item_detail like %:itemDetail% order by i.price desc", nativeQuery = true) //데이터베이스로함
    List<Item> findByItemDetailByNative(@Param("itemDetail") String itemDetail); //데이터 베이스 noaml한것. , nativeQuery =true 이반적인 db쿼리쓰겠다.
    //: <-- 은 변수로 받아달라.

}

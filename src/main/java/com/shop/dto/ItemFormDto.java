package com.shop.dto;

import com.shop.constant.ItemSellStatus;
import com.shop.entity.Item;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.modelmapper.ModelMapper;
import org.springframework.ui.ModelMap;

import java.util.ArrayList;
import java.util.List;

@Getter @Setter @AllArgsConstructor @NoArgsConstructor
@Builder
public class ItemFormDto {

    private Long id;

    @NotBlank(message = "상품명은 필수 입력 값입니다.")//해당 필드가 null이 아니고, 빈 문자열이 아닌지 검사합니다.
    private String itemNm;

    @NotNull(message = "가격은 필수 입력 값입니다.")//해당 필드가 null이 아닌지만 검사합니다.
    private Integer price;

    @NotBlank(message = "이름은 필수 입력 값이다.")
    private String itemDetail;

    @NotNull(message = "재고는 필수 입력 값입니다.")
    private Integer stockNumber;

    private ItemSellStatus itemSellStatus;

    private List<ItemImgDto> itemImgDtoList = new ArrayList<>(); //상품 이미지 정보 저장하는 리스트

    private List<Long> itemImgIds = new ArrayList<>(); //상품의이미지 아이디를 저장하는 리스트이다.

    private static ModelMapper modelMapper = new ModelMapper();

    public Item createItem(){
        return modelMapper.map(this, Item.class);
    }

    public static ItemFormDto of(Item item){
        return modelMapper.map(item, ItemFormDto.class);
    }
}

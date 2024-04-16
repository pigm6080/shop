package com.shop.service;

import com.shop.entity.ItemImg;
import com.shop.repository.ItemImgRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.thymeleaf.util.StringUtils;

@Service
@RequiredArgsConstructor
@Transactional
public class ItemImgService {

    @Value("${itemImgLocation}")
    private String itemImgLocation;

    private final ItemImgRepository itemImgRepository;

    private final FileService fileService;

    public void saveItemImg(ItemImg itemImg, MultipartFile itemImgFile) throws Exception{
        String oriImgName = itemImgFile.getOriginalFilename(); //원본파일 이름 가져온다.
        String imgName = "";
        String imgUrl = "";

        //파일 업로드

        if(!StringUtils.isEmpty(oriImgName)){ //비어있지 않으면.
            imgName = fileService.uploadFile(itemImgLocation,oriImgName,itemImgFile.getBytes());

            imgUrl = "/images/item/" + imgName;
        }

        itemImg.updateItemImg(oriImgName,imgName,imgUrl);
        // 실제 로컬에 저장한 이름, 원래 이름, 경로
        itemImgRepository.save(itemImg);
    }

}

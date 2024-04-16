package com.shop.service;

import com.shop.entity.ItemImg;
import com.shop.repository.ItemImgRepository;
import jakarta.persistence.EntityExistsException;
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

    public void updateItemImg(Long itemImgId, MultipartFile itemImgFile) throws Exception{
        if(!itemImgFile.isEmpty()){ //파일이 존재하는경우

            //주어진 아이템 이미지 ID 를 사용하여 저장된 아이템 이미지를 찾는다.
            ItemImg savedItemImg = itemImgRepository.findById(itemImgId)
                    .orElseThrow(EntityExistsException::new);

            //기존 이미지 파일 삭제
            if(!StringUtils.isEmpty(savedItemImg.getImgName())){
                //파일 서비스 사ㅛㅇㅇ해서 이미지 파일 삭제
                fileService.deleteFile(itemImgLocation+"/"+
                        savedItemImg.getImgName());
            }
            //원본파일 이름 가져오기.
            String oriImgName = itemImgFile.getOriginalFilename();

            //파일서비스 사용 새로운 이미지 파일 업로드, 파일 이름 가져옴
            String imgName = fileService.uploadFile(itemImgLocation, oriImgName, itemImgFile.getBytes());

            //이미지 파일 URL 생성
            String imgUrl = "/images/item/" + imgName;

            //저장된 아이템 이미지를 업데이트 합니다. 새로운 이미지 파일 의 정보사용
            savedItemImg.updateItemImg(oriImgName,imgName,imgUrl);

        }
    }

}

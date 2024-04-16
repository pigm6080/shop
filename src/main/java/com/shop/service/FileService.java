package com.shop.service;

import lombok.extern.java.Log;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileOutputStream;
import java.util.UUID;

@Service
@Log
public class FileService {

    public String uploadFile(String uploadPath, String originalFileName,
                             byte[] fileData) throws Exception {

        UUID uuid = UUID.randomUUID(); //중복 문제 해결한다.
        //확장자를 얻는다.
        String extension = originalFileName.substring(originalFileName.lastIndexOf("."));

        //uuid + 확장자를 결합해서 새로운 파일 이름 생성한다.
        String savedFileName = uuid.toString() + extension;

        // 업로드할 경로랑 새로운 파일 이름 결합해서 전체 적인 파일 경로 생성.
        String fileUploadFullUrl = uploadPath + "/" + savedFileName;

        //생성된 파일 경로에 대한 파일 출력 스트림 생성.
        FileOutputStream fos = new FileOutputStream(fileUploadFullUrl);

        //파일 출력 스트림을 사용하여 업로드 된 파일 데이터를 해당 파일에 쓴다.
        fos.write(fileData);

        //파일 출력 스트림 닫는다/
        fos.close();

        //저장된 파일 이름 반환한다.
        return savedFileName;

    }

    public void deleteFile(String filePath)throws Exception{

        File deleteFile = new File(filePath);

        if(deleteFile.exists()){
            deleteFile.delete();
            log.info("파일을 삭제 하였습니다.");
        }else{
            log.info("파일이 존재 하지 않습니다.");
        }
    }


}

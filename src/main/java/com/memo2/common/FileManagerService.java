package com.memo2.common;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class FileManagerService {

	public static final String FILE_UPLOAD_PATH = "D:\\문병권\\6_spring_project\\memo_2\\images/";
	
	//	input: file 원본, userLoginId
	//	output: path
	public String saveFile(String userLoginId, MultipartFile file) {
		//	폴더 생성
		String directoryName = userLoginId + "_" + System.currentTimeMillis() + "/";
		String filePath = FILE_UPLOAD_PATH + directoryName;
		
		File directory = new File(filePath);
		if (directory.mkdir() == false) {
			//	폴더 생성 실패시 이미지 경로 null 리턴
			log.info("[파일매니저 업로드] 폴더 생성 실패. path:{}", filePath);
			return null;
		}
		
		//	실제 파일 업로드: byte 단위 업로드
		
		try {
			byte[] bytes = file.getBytes();
			//	★★★★★ 한글명 파일은 업로드 불가이므로 나중에 영문자로 바꾸기
			Path path = Paths.get(filePath + file.getOriginalFilename());
			Files.write(path, bytes);	//	실제 파일 업로드
		} catch (IOException e) {
			log.error("[파일 업로드] 파일 업로드 실패. path:{}", filePath);
			return null;
		}
		
		return "/images/" + directoryName + file.getOriginalFilename();
		
	}
	
	
}

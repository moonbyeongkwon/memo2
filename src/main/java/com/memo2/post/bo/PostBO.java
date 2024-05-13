package com.memo2.post.bo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.memo2.common.FileManagerService;
import com.memo2.post.domain.Post;
import com.memo2.post.mapper.PostMapper;

@Service
public class PostBO {

	@Autowired
	private PostMapper postMapper;
	
	@Autowired
	private FileManagerService fileManager;
	
	public List<Post> getPostList() {
		return postMapper.selectPostList();
	}
	
	//	input: subject, content, userId, userLoginId, MultipartFile
	//	output: int rowCount
	public int addPost(int userId, String userLoginId, String subject,
			String content, MultipartFile file) {
		
		String imagePath = null;
		
		//	이미지 있을 때
		if (file != null) {
			imagePath = fileManager.saveFile(userLoginId, file);
		}
		return postMapper.insertPost(userId, subject, content, imagePath);
	}
}

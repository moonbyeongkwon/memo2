package com.memo2.post.bo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.memo2.common.FileManagerService;
import com.memo2.post.domain.Post;
import com.memo2.post.domain.PostView;
import com.memo2.post.mapper.PostMapper;
import com.memo2.user.bo.UserBO;

@Service
public class PostBO {
	
	@Autowired
	private UserBO userBO;

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
	
	public Post getPostByPostId(int postId) {
		return postMapper.selectPostByPostId(postId);
	}
	
	public List<PostView> generatePostViewList() {
		List<PostView> postViewList = new ArrayList<>();
		
		List<Post> postList = postMapper.selectPostList();
		
		
		return postViewList;
	}
}

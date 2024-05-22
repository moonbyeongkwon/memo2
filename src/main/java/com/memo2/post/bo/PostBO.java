package com.memo2.post.bo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.memo2.common.FileManagerService;
import com.memo2.post.domain.Post;
import com.memo2.post.domain.PostView;
import com.memo2.post.mapper.PostMapper;
import com.memo2.user.bo.UserBO;
import com.memo2.user.entity.UserEntity;

@Service
public class PostBO {
	
	private Logger logger = LoggerFactory.getLogger(PostBO.class);
	
	private static final int POST_MAX_SIZE = 3;
	
	@Autowired
	private UserBO userBO;

	@Autowired
	private PostMapper postMapper;
	
	@Autowired
	private FileManagerService fileManager;
	
	public List<Post> getPostList(Integer nextId) {
		
		String direction = null;
		
		
		
		return postMapper.selectPostList(direction, POST_MAX_SIZE);
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
	
	public List<PostView> generatePostViewList(Integer userId, Integer nextId) {
		List<PostView> postViewList = new ArrayList<>();
		
		List<Post> postList = postMapper.selectPostList(POST_MAX_SIZE);
		
		for (Post post : postList) {
			PostView postView = new PostView();
			
			postView.setPost(post);
			
			UserEntity user = userBO.getUserEntityById(post.getUserId());
			postView.setUser(user);
			
			postViewList.add(postView);
		}
		
		
		return postViewList;
	}
	public Post getPostByPostIdUserId(int postId, int userId) {
		return postMapper.selectPostByPostIdUserId(postId, userId);
	}
	
	public void updatePostByIdUserId(int userId, String userLoginId,
			int postId, String subject, String content, MultipartFile file) {
		
		//	기존글을 가져온다(기존 이미지 교체시 삭제, 업데이트 대상이 있는지 확인)
		Post post = getPostByPostIdUserId(postId, userId);
		if (post == null) {
			logger.info("[### post update] Post is null. postId:{}, userId:{}", postId, userId);
			return;
		}
		
		//	파일이 존재하면
		//	1) 새 이미지를 업로드한다.
		//	2) 1번 단계가 성공하면 기존 이미지 제거(기존 이미지가 있었다면), 1번 실패시 기존 이미지 그대로
		String imagePath = null;
		if (file != null) {
			//	업로드
			imagePath = fileManager.saveFile(userLoginId, file);
			
			//	업로드 성공 시 기존 이미지 있으면 제거
			if (imagePath != null && post.getImagePath() != null) {
				//	서버의 파일 제거
				fileManager.deleteFile(post.getImagePath());
			}
		}
		
		//	db 
		postMapper.updatePostByPostId(postId, subject, content, imagePath);
	}
	
	public void deletePostByPostIdUserId(int postId, int userId) {
		//	기존글 가져오고, 이미지가 있다면 삭제
		Post post = getPostByPostIdUserId(postId, userId);
		if (post == null) {
			logger.info("[글 삭제] post is null. postId:{}, userId:{}", postId, userId);
			return;
		}
		
		//	db 삭제
		int rowCount = postMapper.deletePostById(postId);
		
		//	이미지 있으면 삭제 && db 삭제도 잘 됐을 때
		if (post.getImagePath() != null && rowCount > 0) {
			fileManager.deleteFile(post.getImagePath());
		}
	}
}

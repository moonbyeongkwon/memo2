package com.memo2.post.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.memo2.post.domain.Post;

@Mapper
public interface PostMapper {
	
	

	public List<Post> selectPostList(
			int limit);
	
	public List<Post> selectPostListNext(
			@Param("limit") int limit,
			@Param("nextId") int nextId);
	
	public int insertPost(
			@Param("userId") int userId,
			@Param("subject") String subject,
			@Param("content") String content,
			@Param("imagePath") String imagePath);
	
	public Post selectPostByPostId(int postId);
	
	public Post selectPostByPostIdUserId(
			@Param("postId") int postId,
			@Param("userId") int userId);
	
	public void updatePostByPostId(
			@Param("postId") int postId,
			@Param("subject") String subject,
			@Param("content") String content,
			@Param("imagePath") String imagePath);
	
	public int deletePostById(int postId);
}

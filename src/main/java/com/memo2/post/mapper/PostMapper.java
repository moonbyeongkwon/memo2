package com.memo2.post.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.memo2.post.domain.Post;
import com.memo2.post.domain.PostView;

@Mapper
public interface PostMapper {
	
	

	public List<Post> selectPostList(
			int limit);
	
	public List<Post> selectPostListPrev(
			@Param("limit") int limit,
			@Param("prevId") int prevId);
	
	public List<Post> selectPostListNext(
			@Param("limit") int limit,
			@Param("nextId") int nextId);
	
	public int selectPostIdBySort(String sort);
	
	public int insertPost(
			@Param("userId") int userId,
			@Param("subject") String subject,
			@Param("content") String content,
			@Param("imagePath") String imagePath);
	
	public Post selectPostByPostId(int postId);
	
	//public PostView selectPostDetailByPostId(int postId);
	
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

package com.memo2.comment.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface CommentMapper {

	public void insertComment(
			@Param("userId") int userId,
			@Param("postId") int postId,
			@Param("content") String content);
}

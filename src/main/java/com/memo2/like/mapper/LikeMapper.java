package com.memo2.like.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface LikeMapper {

	public int selectLikeCountByPostIdOrUserId(
			@Param("postId") int postId,
			@Param("userId") int userId);
}

package com.memo2.post.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.memo2.post.domain.Post;

@Mapper
public interface PostMapper {

	public List<Post> selectPostList();
}

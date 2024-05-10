package com.memo2.post.bo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.memo2.post.domain.Post;
import com.memo2.post.mapper.PostMapper;

@Service
public class PostBO {

	@Autowired
	private PostMapper postMapper;
	
	public List<Post> getPostList() {
		return postMapper.selectPostList();
	}
}

package com.memo2.comment.bo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.memo2.comment.mapper.CommentMapper;

@Service
public class CommentBO {

	@Autowired
	private CommentMapper commentMapper;

	public void addComment(int userId, int postId, String content) {
		commentMapper.insertComment(userId, postId, content);
	}
	
}

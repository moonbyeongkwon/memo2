package com.memo2.comment.bo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.memo2.comment.domain.Comment;
import com.memo2.comment.domain.CommentView;
import com.memo2.comment.mapper.CommentMapper;
import com.memo2.user.bo.UserBO;
import com.memo2.user.entity.UserEntity;

@Service
public class CommentBO {

	@Autowired
	private UserBO userBO;
	
	@Autowired
	private CommentMapper commentMapper;

	public void addComment(int userId, int postId, String content) {
		commentMapper.insertComment(userId, postId, content);
	}
	
	public List<CommentView> generateCommentViewList(int postId) {
		List<CommentView> commentViewList = new ArrayList<>();
		
		List<Comment> commentList = commentMapper.selectCommentListByPostId(postId);
		
		for (Comment comment : commentList) {
			CommentView commentView = new CommentView();
			
			commentView.setComment(comment);
			
			UserEntity user = userBO.getUserEntityById(comment.getUserId());
			commentView.setUser(user);
			
			commentViewList.add(commentView);
		}
		return commentViewList;
	}
	
}

package com.memo2.comment.domain;

import com.memo2.user.entity.UserEntity;

import lombok.Data;
import lombok.ToString;

@ToString
@Data
public class CommentView {

	private Comment comment;
	
	private UserEntity user;
}

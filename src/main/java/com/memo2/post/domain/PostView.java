package com.memo2.post.domain;

import com.memo2.user.entity.UserEntity;

import lombok.Data;
import lombok.ToString;

@ToString
@Data
public class PostView {

	private Post post;
	
	private UserEntity user;
}

package com.memo2.like.bo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.memo2.like.mapper.LikeMapper;

@Service
public class LikeBO {

	@Autowired
	private LikeMapper likeMapper;
	
	public int getLikeCountByPostIdUserId(int postId, int userId) {
		return likeMapper.selectLikeCountByPostIdOrUserId(postId, userId);
	}
	
	public void likeToggle(int postId, int userId) {
		if (getLikeCountByPostIdUserId(postId, userId) > 0) {
			//	행이 존재하면 => 삭제
			//likeMapper.deleteLikeByPostIdUserId(postId, userId);
		} else {
			//	존재하면 => 추가
			//likeMapper.insertLike(postId, userId);
		}//
	}
	
}

package com.memo2.like;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.memo2.like.bo.LikeBO;

import jakarta.servlet.http.HttpSession;

@RestController
public class LikeRestController {

	@Autowired
	private LikeBO likeBO;
	
	@RequestMapping("/like/{postId}")
	public Map<String, Object> LikeToggle(
			@PathVariable(name = "postId") int postId,
			HttpSession session) {
		Map<String, Object> result = new HashMap<>();
		
		//	로그인 여부
		Integer userId = (Integer)session.getAttribute("userId");
		if (userId == null) {
			result.put("code", 500);
			result.put("error_message", "로그인이 필요한 기능입니다");
			return result;
		}
		
		likeBO.likeToggle(postId, userId);
		
		result.put("code", 200);
		result.put("result", "성공");
		return result;
	}
}

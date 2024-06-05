package com.memo2.user.bo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.memo2.post.bo.PostBO;
import com.memo2.user.entity.UserEntity;
import com.memo2.user.repository.UserRepository;

@Service
public class UserBO {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PostBO postBO;
	
	
	public UserEntity getUserEntityByLoginId(String loginId) {
		return userRepository.findByLoginId(loginId);
	}
	
	public Integer addUser(String loginId, String password, String name, String email) {
		UserEntity user = userRepository.save(UserEntity.builder()
														.loginId(loginId)
														.password(password)
														.name(name)
														.email(email)
														.build());
		return user == null ? null : user.getId();
	}
	
	public UserEntity getUserEntityByLoginIdPassword(String loginId, String password) {
		return userRepository.findByLoginIdAndPassword(loginId, password);
	}
	
	public UserEntity getUserEntityById(int userId) {
		return userRepository.findById(userId).orElse(null);
	}
	
	
}

package com.memo2.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.memo2.user.entity.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity, Integer>{

	public UserEntity findByLoginId(String loginId);
	public UserEntity findByLoginIdAndPassword(String loginId, String password);
}

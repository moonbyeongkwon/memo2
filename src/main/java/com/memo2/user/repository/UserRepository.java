package com.memo2.user.repository;

import com.memo2.user.entity.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity, Integer>{

	public UserEntity findByLoginId(String loginId);
}

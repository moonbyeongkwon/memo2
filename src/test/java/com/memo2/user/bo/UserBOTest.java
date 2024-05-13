package com.memo2.user.bo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest
class UserBOTest {

	@Autowired
	UserBO userBO;
	
	@Transactional	//	rollback
	@Test
	void 회원가입() {
		Integer id = userBO.addUser("test111", "password222", "dd", "ddd");
		log.info("##### userId: {}", id);
	}
	

}

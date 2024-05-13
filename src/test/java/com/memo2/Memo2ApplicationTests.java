package com.memo2;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import lombok.extern.slf4j.Slf4j;

@Slf4j
//@SpringBootTest
class Memo2ApplicationTests {

//	@Test
//	void contextLoads() {
//		log.info("테스트");
//	}
	//@Test
	void 더하기() {
		int a = 10;
		int b = 5;
		assertEquals(16, a + b);
	}

}

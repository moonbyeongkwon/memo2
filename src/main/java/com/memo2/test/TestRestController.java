package com.memo2.test;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.memo2.test.bo.UsedGoodsBO;
import com.memo2.test.domain.UsedGoods;


@RequestMapping("/test1")
@RestController
public class TestRestController {
	
	@Autowired
	private UsedGoodsBO usedGoodsBO;

	@RequestMapping("/3")
	public String test1_3() {
		return "@RestController를 사용해 String을 리턴해본다";
	}
	
	@RequestMapping("/4")
	public Map<String, Object> test1_4() {
		Map<String, Object> map = new HashMap<>();
		map.put("asd", 123);
		return map;
	}
	
	@RequestMapping("/5")
	public Data test1_5() {
		Data data = new Data();
		data.setId(10);
		data.setName("병권");
		
		return data;
	}
	
	@RequestMapping("/6")
	public ResponseEntity<Data> test1_6() {
		Data data = new Data();
		data.setId(2);
		data.setName("빙건");
		
		return new ResponseEntity<>(data, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@RequestMapping("/8")
	public List<UsedGoods> test1() {
		return usedGoodsBO.getUsedGoodsList();
	}
}

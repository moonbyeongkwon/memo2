package com.memo2.test.bo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.memo2.test.domain.UsedGoods;
import com.memo2.test.mapper.UsedGoodsMapper;

@Service
public class UsedGoodsBO {
	
	@Autowired
	private UsedGoodsMapper usedGoodsMapper;

	public List<UsedGoods> getUsedGoodsList() {
		return usedGoodsMapper.selectUsedGoodsList();
	}
}

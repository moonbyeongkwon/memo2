package com.memo2.test.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.memo2.test.domain.UsedGoods;

@Mapper
public interface UsedGoodsMapper {

	public List<UsedGoods> selectUsedGoodsList();
}

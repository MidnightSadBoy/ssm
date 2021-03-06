package com.hssj.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.hssj.pojo.Goods;
import com.hssj.pojo.Link;

public interface GoodsMapper {

	// 根据页码查询
	public List<Goods> findByAll();

	// 根据条件查询
	public List<Goods> findByLink(Link link);

	// 根据条件筛选货物
	public List<Goods> findByGoodsAndLink(@Param("goods") List<Goods> goods, @Param("link") Link link);

	// 根据货物id查询
	public Goods findByGid(int gid);

	// 根据名称查询商品
	public List<Goods> findByName(String name);
}

package com.hssj.service;

import java.util.List;

import com.hssj.pojo.Goods;
import com.hssj.pojo.Link;

public interface GoodsService {

	// 查询全部
	public List<Goods> findAllGoods(int pageNum);

	// 展示轮播图商品
	public List<Goods> findCalShow();

	// 展示主页商品
	public List<Goods> findIndexGoods();

	/**
	 * 根据条件筛选
	 */
	public List<Goods> findGoodsbyLink(List<Link> links, int pageNum);

	// 根据名字查询商品
	public List<Goods> findGoodsbyName(String name, int pageNum);

	// 根据名字和分类查询商品
	public List<Goods> findGoodsbyLinkAndName(String name, List<Link> links, int pageNum);
}

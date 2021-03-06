package com.hssj.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.hssj.mapper.GoodsMapper;
import com.hssj.pojo.Goods;
import com.hssj.pojo.Link;
import com.hssj.service.GoodsService;

@Service
public class GoodsServiceImpl implements GoodsService {

	@Autowired
	private GoodsMapper mapper;

	@Override
	public List<Goods> findAllGoods(int pageNum) {
		// TODO Auto-generated method stub
		PageHelper.startPage(pageNum, 8);
		return mapper.findByAll();
	}

	@Override
	public List<Goods> findCalShow() {
		// TODO Auto-generated method stub
		int size = mapper.findByAll().size();
		int[] a = new int[4];
		int index = 0;
		Random random = new Random();
		ArrayList<Goods> goods = new ArrayList<Goods>();
		// 生成4个不重复的商品id
		while (index < a.length) {
			int nextInt = random.nextInt(size);
			if (judge(a, nextInt)) {
				a[index++] = nextInt;
			}
		}
		for (int i = 0; i < a.length; i++) {
			Goods findByGid = mapper.findByGid(a[i]);
			goods.add(findByGid);
		}
		return goods;
	}

	@Override
	public List<Goods> findIndexGoods() {
		// TODO Auto-generated method stub
		int size = mapper.findByAll().size();
		int[] a = new int[16];
		int index = 0;
		Random random = new Random();
		ArrayList<Goods> goods = new ArrayList<Goods>();
		// 生成16个不重复的商品id
		while (index < a.length) {
			int nextInt = random.nextInt(size);
			if (judge(a, nextInt)) {
				a[index++] = nextInt;
			}
		}
		for (int i = 0; i < a.length; i++) {
			Goods findByGid = mapper.findByGid(a[i]);
			goods.add(findByGid);
		}
		return goods;
	}

	/**
	 * 根据条件筛选
	 */
	@Override
	public List<Goods> findGoodsbyLink(List<Link> links, int pageNum) {
		// TODO findGoodsbyLink
		List<Goods> findByLink = mapper.findByLink(links.get(0));
		if (findByLink.isEmpty()) {
			return findByLink;
		}
		for (int i = 1; i < links.size(); i++) {
			Link link = links.get(i);
			findByLink = mapper.findByGoodsAndLink(findByLink, link);
			if (findByLink.isEmpty()) {
				return findByLink;
			}
		}
		PageHelper.startPage(pageNum, 8);
		findByLink = mapper.findByGoodsAndLink(findByLink, null);
		return findByLink;
	}

	// 判断数组中是否有重复的数
	public boolean judge(int[] a, int b) {
		for (int j = 0; j < a.length; j++) {
			if (a[j] == b) {
				return false;
			}
		}
		return true;
	}

	@Override
	public List<Goods> findGoodsbyName(String name, int pageNum) {
		// TODO Auto-generated method stub
		PageHelper.startPage(pageNum, 8);
		List<Goods> findByName = mapper.findByName(name);
		return findByName;
	}

	@Override
	public List<Goods> findGoodsbyLinkAndName(String name, List<Link> links, int pageNum) {
		// TODO Auto-generated method stub
		List<Goods> findByLink = mapper.findByName(name);
		for (int i = 0; i < links.size(); i++) {
			Link link = links.get(i);
			findByLink = mapper.findByGoodsAndLink(findByLink, link);
			if (findByLink.isEmpty()) {
				return findByLink;
			}
		}
		PageHelper.startPage(pageNum, 8);
		findByLink = mapper.findByGoodsAndLink(findByLink, null);
		return findByLink;
	}

}

package com.hssj.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hssj.mapper.CategoryMapper;
import com.hssj.pojo.Category;
import com.hssj.pojo.Link;
import com.hssj.pojo.LinkHead;
import com.hssj.service.CategoryService;
import com.hssj.util.JedisUtils;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.Tuple;

@Service("categoryService")
public class CategoryServiceImpl implements CategoryService {

	@Autowired
	private CategoryMapper mapper;

	// 查询所有分类
	@Override
	public List<Category> findAll() {
		// 1获取redis客户端
		Jedis jedis = JedisUtils.getJedis();
		// 2使用sortedset查询
//		Set<String> categorySet = jedis.zrange("category", 0, -1);
		Set<Tuple> categorySet = jedis.zrangeWithScores("category", 0, -1);
		List<Category> cs = null;

		// 3检查返回是否为空
		if (categorySet == null || categorySet.size() == 0) {// 为空
			// 3.1空直接查询数据库，并将数据存至redis中
			cs = mapper.findAll();
			for (int i = 0; i < cs.size(); i++) {
				jedis.zadd("category", cs.get(i).getCid(), cs.get(i).getCname());
			}
		} else {// 不为空
			// 3.2不为空直接返回该数据
			cs = new ArrayList<Category>();
			for (Tuple name : categorySet) {
				Category category = new Category();
				category.setCname(name.getElement());
				category.setCid((int) name.getScore());
				cs.add(category);
			}
		}
		return cs;
	}

	// 查询商品分类
	@Override
	public List<Link> findLink(int cid) {
		// 1从数据库获取商品分类
		return mapper.findLink(cid);
	}

	@Override
	public List<LinkHead> findLinkHead(int cid) {
		return mapper.findLinkHead(cid);
	}

}

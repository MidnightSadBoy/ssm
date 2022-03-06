package com.hssj.controller;

import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hssj.mapper.CategoryMapper;
import com.hssj.pojo.Category;
import com.hssj.pojo.Link;
import com.hssj.pojo.LinkHead;
import com.hssj.pojo.ResultInfo;
import com.hssj.service.CategoryService;
import com.hssj.util.JedisUtils;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.Tuple;

@Controller
@RequestMapping("/category")
public class CategoryController {

	@Autowired
	private CategoryService categoryService;

	@Autowired
	private CategoryMapper mapper;

	@PostConstruct
	public void init() {
		// 1获取redis客户端
		Jedis jedis = JedisUtils.getJedis();
		// 2使用sortedset查询
		Set<Tuple> categorySet = jedis.zrangeWithScores("category", 0, -1);
		List<Category> cs = mapper.findAll();
//		for (Tuple name : categorySet) {
//			name.getElement();
//			(int)name.getScore();
//		}
		Iterator<Tuple> it = categorySet.iterator();
		if (categorySet.size() != cs.size()) {
			jedis.del("category");
		} else {
			for (int i = 0; i < cs.size() && it.hasNext(); i++) {
				String element = it.next().getElement();
				if (!cs.get(i).getCname().equals(element)) {
					jedis.del("category");
					break;
				}
			}
		}
	}

	// 商城分类
	@ResponseBody
	@RequestMapping(value = "/findAll", produces = "application/json;charset=UTF-8")
	public ResultInfo findAll() {
		ResultInfo resultInfo = new ResultInfo();
		resultInfo.setFlag(true);
		// 1获取分类
		List<Category> cs = categoryService.findAll();
		resultInfo.setData(cs);
		return resultInfo;
	}

	// 商品分类
	@ResponseBody
	@RequestMapping(value = "/findLink", produces = "application/json;charset=UTF-8")
	public ResultInfo findLink(int cid) {
		ResultInfo resultInfo = new ResultInfo();
		resultInfo.setFlag(true);
		// 通过cid获取相应物品的分类
		List<Link> lk = categoryService.findLink(cid);
		// 将数据序列化为json返回
		resultInfo.setData(lk);
		return resultInfo;
	}

	@ResponseBody
	@RequestMapping(value = "/findLinkHead", produces = "application/json;charset=UTF-8")
	public ResultInfo findLinkHead(int cid) {
		ResultInfo resultInfo = new ResultInfo();
		resultInfo.setFlag(true);
		// 通过cid获取相应物品的分类
		List<LinkHead> lk = categoryService.findLinkHead(cid);
		// 将数据序列化为json返回
		resultInfo.setData(lk);
		return resultInfo;
	}
}

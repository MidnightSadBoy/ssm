package com.hssj.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.PageInfo;
import com.hssj.pojo.Goods;
import com.hssj.pojo.Link;
import com.hssj.pojo.ResultInfo;
import com.hssj.service.GoodsService;

@RestController
@RequestMapping("/goods")
public class GoodsController {

	@Autowired
	private GoodsService goodsService;

	@RequestMapping("first")
	public ResultInfo first() {
		ResultInfo resultInfo = new ResultInfo();
		List<Goods> findAllGoods = goodsService.findAllGoods(1);
		resultInfo.setFlag(true);
		resultInfo.setData(findAllGoods);
		PageInfo<Goods> info = new PageInfo<Goods>(findAllGoods);
		resultInfo.setPage(info);
		return resultInfo;
	}

	@RequestMapping("findPage")
	public ResultInfo findPage(int pageNum, int[] links) {
		ArrayList<Link> list = new ArrayList<Link>();
		List<Goods> findAllGoods;
		for (int string : links) {
			if (string != 0) {
				list.add(new Link(string, null, 0, 0));
			}
		}
		ResultInfo resultInfo = new ResultInfo();
		if (links == null || links.length == 0) {
			findAllGoods = goodsService.findAllGoods(pageNum);
		} else {
			findAllGoods = goodsService.findGoodsbyLink(list, pageNum);
		}
		resultInfo.setFlag(true);
		resultInfo.setData(findAllGoods);
		PageInfo<Goods> info = new PageInfo<Goods>(findAllGoods);
		resultInfo.setPage(info);
		return resultInfo;
	}

	@RequestMapping("cal")
	public ResultInfo cal() {
		ResultInfo resultInfo = new ResultInfo();
		List<Goods> findCalShow = goodsService.findCalShow();
		resultInfo.setFlag(true);
		resultInfo.setData(findCalShow);
		return resultInfo;
	}

	@RequestMapping("index")
	public ResultInfo index() {
		ResultInfo resultInfo = new ResultInfo();
		List<Goods> findCalShow = goodsService.findIndexGoods();
		resultInfo.setFlag(true);
		resultInfo.setData(findCalShow);
		return resultInfo;
	}

	@RequestMapping("name")
	public ResultInfo name(int pageNum, int[] links, String name) {
		ResultInfo resultInfo = new ResultInfo();
		ArrayList<Link> list = new ArrayList<Link>();
		List<Goods> findAllGoods;
		for (int string : links) {
			list.add(new Link(string, null, 0, 0));
		}
		if ((links == null || links.length == 0) && (name.equals("") || name == null)) {
			findAllGoods = goodsService.findAllGoods(pageNum);
		} else if (name.equals("") || name == null) {
			findAllGoods = goodsService.findGoodsbyLink(list, pageNum);
		} else if (links == null || links.length == 0) {
			findAllGoods = goodsService.findGoodsbyName(name, pageNum);
		} else {
			findAllGoods = goodsService.findGoodsbyLinkAndName(name, list, pageNum);
		}
		resultInfo.setFlag(true);
		resultInfo.setData(findAllGoods);
		PageInfo<Goods> info = new PageInfo<Goods>(findAllGoods);
		resultInfo.setPage(info);
		return resultInfo;
	}

}

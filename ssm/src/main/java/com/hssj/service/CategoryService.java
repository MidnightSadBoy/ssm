package com.hssj.service;

import java.util.List;

import com.hssj.pojo.Category;
import com.hssj.pojo.Link;
import com.hssj.pojo.LinkHead;



public interface CategoryService {
	/**
	 * 查询所有分类
	 * @return
	 */
	public List<Category> findAll();

	/**
	 * 通过cid查询所有商品分类
	 * @param cid
	 * @return
	 */
	public List<Link> findLink(int cid);

	/**
	 * 各个分类的标题
	 * @param cid
	 * @return
	 */
	public List<LinkHead> findLinkHead(int cid);
}

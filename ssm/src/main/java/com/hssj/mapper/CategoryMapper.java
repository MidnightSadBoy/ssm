package com.hssj.mapper;

import java.util.List;

import com.hssj.pojo.Category;
import com.hssj.pojo.Link;
import com.hssj.pojo.LinkHead;

public interface CategoryMapper {
	/**
	 * 查询所有分类
	 * @return
	 */
	public List<Category> findAll();
	
	/**
	 * 通过cid查询商品分类
	 * @param cid
	 * @return
	 */
	public List<Link> findLink(int cid);

	/**
	 * 查询各个分类的标题
	 * @param cid
	 * @return
	 */
	public List<LinkHead> findLinkHead(int cid);
}

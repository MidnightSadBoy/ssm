package com.hssj.test;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.hssj.mapper.CategoryMapper;
import com.hssj.mapper.GoodsMapper;
import com.hssj.pojo.Category;
import com.hssj.pojo.Goods;
import com.hssj.pojo.Link;
import com.hssj.service.GoodsService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class Demo1 {

	@Autowired
	private CategoryMapper categoryMapper;

	@Autowired
	private GoodsMapper goodsMapper;

	@Autowired
	private GoodsService goodsService;

//	@Autowired
//	private CategoryMapper categoryMapper;

	@Test
	public void name() {
		List<Category> findAll = categoryMapper.findAll();
		System.out.println(findAll);
	}

	@Test
	public void findAll() {
		List<String> list = new ArrayList<String>();
		list.add("a");
		list.add("b");
		list.add("c");
		List<String> list1 = new ArrayList<String>();
		list1.add("q");
		list1.add("w");
		list1.add("e");
		list = list1;
		System.out.println(list);

	}

//	@Test
//	public void user() {
//		User user = new User();
//		user.setUsername("SadMachine1");
//		user.setPassword("asd123456");
//		user.setSex("m");
//		user.setEmail("2015337558@qq.com");
//		user.setTelephone("1234232455");
//		user.setCode(UniqueIdUtils.getUniqueid());
//		user.setBirthday("2000-06-12");
//		user.setStatus("N");
//		UserMapper mapper = openSession.getMapper(UserMapper.class);
//		User findByUsernameAndPassword = mapper.findByUsernameAndPassword(user);
//		System.out.println(findByUsernameAndPassword);
//	}

//	@Test
//	public void findd() {
//		List<Category> categories = categoryMapper.findAll();
//		for (Category category : categories) {
//			System.out.println(category);
//		}
//	}

	@Test
	public void testGoods() {
		Page<Object> startPage = PageHelper.startPage(10, 5);
		List<Goods> findByPage = goodsMapper.findByAll();
		System.out.println("-------------------------------------------------");
		System.out.println(startPage.toString());

	}

	@Test
	public void testGoods1() {
		Link list = new Link(2, "?????????", 1, 1);
		List<Goods> findByPage = goodsMapper.findByLink(list);
		System.out.println("-------------------------------------------------");
		for (Goods goods : findByPage) {
			System.out.println(goods);
		}
	}

	@Test
	public void testGoods2() {
		Link link = new Link(1, "?????????", 1, 1);
		List<Goods> goods = goodsMapper.findByLink(link);
//		list.add(new Link(11,"????????????",1,1));
//		list.add(new Link(13,"????????????",1,1));
		link.setLid(11);
		List<Goods> findByPage = goodsMapper.findByGoodsAndLink(goods, link);
		System.out.println("-------------------------------------------------");
		for (Goods good : findByPage) {
			System.out.println(good);
		}
	}

	@Test
	public void testGoods3() {
		ArrayList<Link> list = new ArrayList<Link>();
		list.add(new Link(2, "????????????", 1, 1));
		list.add(new Link(11, "????????????", 1, 1));
//		list.add(new Link(13, "????????????", 1, 1));
		List<Goods> findGoodsbyLink = goodsService.findGoodsbyLink(list, 1);
		System.out.println("-------------------------------------------------");
		for (Goods good : findGoodsbyLink) {
			System.out.println(good);
		}
		System.out.println("-------------------------------------------------");
		System.out.println(list.get(0).getLid());
	}

	@Test
	public void testGoods4() {
		List<Goods> findCalShow = goodsMapper.findByName("???");
		System.out.println("-------------------------------------------------");
		System.out.println("-------------------------------------------------");
		System.out.println(findCalShow);
	}
}

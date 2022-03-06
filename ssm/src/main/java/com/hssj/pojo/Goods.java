package com.hssj.pojo;

import java.util.List;

public class Goods {

	private Integer gid;
	private String image;// 图片路径
	private String name;
	private Double price;
	private List<Link> links;

	public Goods() {
		super();
	}

	public Goods(Integer gid, String image, String name, Double price, List<Link> links) {
		super();
		this.gid = gid;
		this.image = image;
		this.name = name;
		this.price = price;
		this.links = links;
	}

	public Integer getGid() {
		return gid;
	}

	public void setGid(Integer gid) {
		this.gid = gid;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public List<Link> getLinks() {
		return links;
	}

	public void setLinks(List<Link> links) {
		this.links = links;
	}

	@Override
	public String toString() {
		return "Goods [gid=" + gid + ", image=" + image + ", name=" + name + ", price=" + price + ", links=" + links
				+ "]";
	}

}

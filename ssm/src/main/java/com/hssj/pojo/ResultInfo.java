package com.hssj.pojo;

import java.io.Serializable;

/**
 * 封装返回前端的数据对象
 * 
 * @author 20153
 *
 */
public class ResultInfo implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private boolean flag;
	private Object data;
	private Object page;
	private String errorMsg;

	public ResultInfo() {
		super();
	}

	public ResultInfo(boolean flag) {
		super();
		this.flag = flag;
	}

	public ResultInfo(boolean flag, String errorMsg) {
		super();
		this.flag = flag;
		this.errorMsg = errorMsg;
	}

	public ResultInfo(boolean flag, Object data, String errorMsg) {
		super();
		this.flag = flag;
		this.data = data;
		this.errorMsg = errorMsg;
	}

	public ResultInfo(boolean flag, Object data, Object page, String errorMsg) {
		super();
		this.flag = flag;
		this.data = data;
		this.page = page;
		this.errorMsg = errorMsg;
	}
	
	public boolean isFlag() {
		return flag;
	}

	public void setFlag(boolean flag) {
		this.flag = flag;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	public Object getPage() {
		return page;
	}

	public void setPage(Object page) {
		this.page = page;
	}

	public String getErrorMsg() {
		return errorMsg;
	}

	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}

	@Override
	public String toString() {
		return "ResultInfo [flag=" + flag + ", data=" + data + ", page=" + page + ", errorMsg=" + errorMsg + "]";
	}

}

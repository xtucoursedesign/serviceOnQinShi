package org.bean;

import java.io.IOException;
import java.io.Serializable;
import java.util.List;
import java.util.Properties;

public class Page<T> implements Serializable{

	private static final long serialVersionUID = 1L;
	// һҳ��ʾ��������
	private static int pageSize;
	static {
		Properties pro = new Properties();
		try {
			pro.load(Page.class.getClassLoader().getResourceAsStream("page.properties"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			pageSize = Integer.parseInt(pro.getProperty("pageSize"));
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}
		if(pageSize == 0) {
			pageSize = 2;
		}
	}
	// ��ǰҳ���ݼ���
	private List<T> list;
	// ��ǰҳ��
	private int current;
	// ��ҳ��
	private int totalPage;
	// ������
	private int totalCount;
	
	public Page() {
		super();
	}

	public Page(List<T> list, int current, int totalPage, int totalCount) {
		super();
		this.list = list;
		this.current = current;
		this.totalPage = totalPage;
		this.totalCount = totalCount;
	}
	
	public int getPageSize() {
		return pageSize;
	}

	public List<T> getList() {
		return list;
	}

	public void setList(List<T> list) {
		this.list = list;
	}

	public int getCurrent() {
		if(current < 1)
			current = 1;
		if(current > getTotalPage()) {
			current = getTotalPage();
		}
		return current;
	}

	public void setCurrent(int current) {
		this.current = current;
	}

	public int getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}

	public int getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}

	@Override
	public String toString() {
		return "Page [list=" + list + ", current=" + current + ", totalPage=" + totalPage + ", totalCount=" + totalCount
				+ "]";
	}
}

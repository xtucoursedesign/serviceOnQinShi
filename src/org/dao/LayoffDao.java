package org.dao;

import java.util.List;

import org.bean.Layoff;

public interface LayoffDao {
	Layoff getLayoff(String lid);
	List<Layoff> getAllLayoffByProId(String proId);
	int getStatusByProId(String proId);
	int insertLayoff(Layoff l);
	int updateLayoff(Layoff l, String oldLid);
	int updateLayoffState(String lid, Integer complete);
	int removeLayoff(String lid);
}

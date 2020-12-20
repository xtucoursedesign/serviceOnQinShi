package org.service;

import java.util.List;

import org.bean.Layoff;

public interface LayoffService {
	Layoff getLayoff(String lid);
	int insertLayoff(Layoff l);
	int updateLayoff(Layoff l, String oldLid);
	List<Layoff> getAllLayoffByProId(String proId);
	boolean updateLayoffState(String lid, Integer complete);
	boolean removeLayoff(String lid);
}

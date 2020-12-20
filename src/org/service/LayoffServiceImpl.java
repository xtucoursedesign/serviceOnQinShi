package org.service;

import java.util.List;

import org.bean.Layoff;
import org.dao.LayoffDao;
import org.dao.impl.LayoffDaoImpl;

public class LayoffServiceImpl implements LayoffService {
	private LayoffDao ld = new LayoffDaoImpl();

	@Override
	public Layoff getLayoff(String lid) {
		return ld.getLayoff(lid);
	}

	@Override
	public int insertLayoff(Layoff l) {
		return ld.insertLayoff(l);
	}

	@Override
	public int updateLayoff(Layoff l, String oldLid) {
		return ld.updateLayoff(l, oldLid);
	}

	@Override
	public List<Layoff> getAllLayoffByProId(String proId) {
		return ld.getAllLayoffByProId(proId);
	}

	@Override
	public boolean updateLayoffState(String lid, Integer complete) {
		return ld.updateLayoffState(lid, complete) > 0;
	}

	@Override
	public boolean removeLayoff(String lid) {
		return ld.removeLayoff(lid) > 0;
	}
}

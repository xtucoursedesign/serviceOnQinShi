package org.service;

import org.bean.Main;
import org.bean.Page;
import org.dao.MainDao;
import org.dao.impl.MainDaoImpl;

public class MainServiceImpl implements MainService {
	
	private MainDao mainDao = new MainDaoImpl();

	@Override
	public Main getMain(String mid) {
		return mainDao.getMain(mid);
	}

	@Override
	public int insertMain(Main m) {
		return mainDao.insertMain(m);
	}

	@Override
	public boolean removeMain(String mid) {
		return mainDao.removeMain(mid) > 0 ? true : false;
	}

	@Override
	public int updateMain(Main m, String oldMid) {
		return mainDao.updateMain(m, oldMid);
	}

	@Override
	public Page<Main> getMainByPage(int page) {
		Page<Main> p = new Page<>();
		p.setCurrent(page);
		return mainDao.getMainByPage(p);
	}

	@Override
	public Page<Main> getMainBySearch(int page, String searchType, String searchKeyWord) {
		searchKeyWord = "%" + searchKeyWord + "%";
		Page<Main> p = new Page<>();
		p.setCurrent(page);
		return mainDao.getMainBySearch(p, searchType, searchKeyWord);
	}

}

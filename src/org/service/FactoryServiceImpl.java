package org.service;

import java.util.List;

import org.bean.Factory;
import org.dao.FactoryDao;
import org.dao.impl.FactoryDaoImpl;

public class FactoryServiceImpl implements FactoryService {
	private FactoryDao factoryDao = new FactoryDaoImpl();

	@Override
	public Factory getFactory(String bfid) {
		return factoryDao.getFactory(bfid);
	}

	@Override
	public int updateFactory(Factory f, String oldBfid) {
		return factoryDao.updateFactory(f, oldBfid);
	}

	@Override
	public List<Factory> getAllFactory() {
		return factoryDao.getAllFactory();
	}

	@Override
	public boolean removeFactory(String bfid) {
		int len = factoryDao.removeFactory(bfid);
		return len > 0;
	}

	@Override
	public int insertFactory(Factory f) {
		return factoryDao.insertFactory(f);
	}

}

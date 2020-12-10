package org.dao;

import java.util.List;

import org.bean.Factory;

public interface FactoryDao {
	Factory getFactory(String bfid);
	int updateFactory(Factory f, String oldBfid);
	List<Factory> getAllFactory();
	int removeFactory(String bfid);
	int insertFactory(Factory f);
}

package org.service;

import java.util.List;

import org.bean.Factory;

public interface FactoryService {
	Factory getFactory(String bfid);
	int updateFactory(Factory f, String oldBfid);
	List<Factory> getAllFactory();
	boolean removeFactory(String bfid);
	int insertFactory(Factory f);
}

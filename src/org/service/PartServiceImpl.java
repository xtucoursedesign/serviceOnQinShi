package org.service;

import java.util.List;

import org.bean.Page;
import org.bean.Part;
import org.dao.PartDao;
import org.dao.impl.PartDaoImpl;

public class PartServiceImpl implements PartService {
	
	private PartDao partDao = new PartDaoImpl();

	@Override
	public Part getPart(String pid) {
		return partDao.getPart(pid);
	}

	@Override
	public List<Part> getAllPart() {
		return partDao.getAllPart();
	}

	@Override
	public int insertPart(Part p) {
		return partDao.insertPart(p);
	}

	@Override
	public boolean removePart(String pid) {
		int len = partDao.removePart(pid);
		return len > 0;
	}

	@Override
	public int updatePart(Part p, String oldPid) {
		return partDao.updatePart(p, oldPid);
	}

	@Override
	public Page<Part> getPartByPage(int page) {
		Page<Part> p = new Page<>();
		p.setCurrent(page);
		return partDao.getPartByPage(p);
	}

	@Override
	public Page<Part> getPartBySearch(int page, String searchType, String searchKeyWord) {
		searchKeyWord = "%" + searchKeyWord + "%";
		Page<Part> p = new Page<>();
		p.setCurrent(page);
		return partDao.getPartBySearch(p, searchType, searchKeyWord);
	}

}

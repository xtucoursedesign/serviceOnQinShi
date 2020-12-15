package org.dao;

import java.util.List;

import org.bean.Page;
import org.bean.Part;

public interface PartDao {
	Part getPart(String pid);
	List<Part> getAllPart();
	Page<Part> getPartByPage(Page<Part> page);
	Page<Part> getPartBySearch(Page<Part> page, String searchType, String searchKeyWord);
	int insertPart(Part p);
	int removePart(String pid);
	int updatePart(Part p, String oldPid);
}

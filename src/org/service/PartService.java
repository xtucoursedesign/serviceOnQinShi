package org.service;

import java.util.List;

import org.bean.Page;
import org.bean.Part;

public interface PartService {
	Part getPart(String pid);
	List<Part> getAllPart();
	int insertPart(Part p);
	boolean removePart(String pid);
	int updatePart(Part p, String oldPid);
	Page<Part> getPartByPage(int page);
	Page<Part> getPartBySearch(int page, String searchType, String searchKeyWord);
}

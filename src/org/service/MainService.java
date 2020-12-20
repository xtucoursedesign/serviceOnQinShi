package org.service;

import java.util.List;

import org.bean.Main;
import org.bean.Page;

public interface MainService {
	List<Main> getAllMain();
	Main getMain(String mid);
	int insertMain(Main m);
	boolean removeMain(String mid);
	int updateMain(Main m, String oldMid);
	Page<Main> getMainByPage(int page);
	Page<Main> getMainBySearch(int page, String searchType, String searchKeyWord);
}

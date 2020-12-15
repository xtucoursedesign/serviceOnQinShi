package org.service;

import org.bean.Main;
import org.bean.Page;

public interface MainService {
	Main getMain(String mid);
	int insertMain(Main m);
	boolean removeMain(String mid);
	int updateMain(Main m, String oldMid);
	Page<Main> getMainByPage(int page);
	Page<Main> getMainBySearch(int page, String searchType, String searchKeyWord);
}

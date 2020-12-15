package org.dao;

import org.bean.Main;
import org.bean.Page;

public interface MainDao {
	Main getMain(String mid);
	int insertMain(Main m);
	int removeMain(String mid);
	int updateMain(Main m, String oldMid);
	Page<Main> getMainByPage(Page<Main> p);
	Page<Main> getMainBySearch(Page<Main> p, String searchType, String searchKeyWord);
}

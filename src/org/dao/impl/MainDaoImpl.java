package org.dao.impl;

import org.bean.Main;
import org.bean.Page;
import org.dao.BaseDao;
import org.dao.MainDao;

public class MainDaoImpl extends BaseDao<Main> implements MainDao{

	@Override
	public Main getMain(String mid) {
		// SELECT * FROM pro_manage.main_stru WHERE `mid`='00000000001'
		String sql = "SELECT * FROM pro_manage.main_stru WHERE `mid`=?";
		return this.getBean(sql, mid);
	}

	@Override
	public int insertMain(Main m) {
		Main main = getMain(m.getMid());
		if(main != null) {
			return -1;
		}
		/**
		 * INSERT INTO pro_manage.main_stru(`mid`,`name`,material,specifi,texture,weight,img,unit) 
		 * VALUES('00000000002','Á÷ÐÇ','Ìú','ÆÕÍ¨','Ìú','100','11','kg')
		 */
		String sql = "INSERT INTO pro_manage.main_stru(`mid`,`name`,material,specifi,texture,weight,img,unit) VALUES(?,?,?,?,?,?,?,?)";
		return this.update(sql, m.getMid(), m.getName(), m.getMaterial(), m.getSpecifi(), m.getTexture(), m.getWeight(), m.getImg(), m.getUnit());
	}

	@Override
	public int removeMain(String mid) {
		// DELETE FROM pro_manage.main_stru WHERE `mid`='00000000002'
		String sql = "DELETE FROM pro_manage.main_stru WHERE `mid`=?";
		return this.update(sql, mid);
	}

	@Override
	public int updateMain(Main m, String oldMid) {
		Main main = getMain(m.getMid());
		if(main != null && m.getMid() != oldMid) {
			return -1;
		}
		/**
		 * UPDATE pro_manage.main_stru SET `mid`='00000000003',`name`='li',material='1',
		 * specifi='2',texture='1',weight='1',img='1',unit='2' WHERE `mid`='00000000002'
		 */
		String sql = "UPDATE pro_manage.main_stru SET `mid`=?,`name`=?,material=?,specifi=?,texture=?,weight=?,img=?,unit=? WHERE `mid`=?";
		return this.update(sql, m.getMid(), m.getName(), m.getMaterial(), m.getSpecifi(), m.getTexture(), m.getWeight(), m.getImg(), m.getUnit(), oldMid);
	}

	@Override
	public Page<Main> getMainByPage(Page<Main> p) {
		String sql = "SELECT COUNT(1) FROM pro_manage.main_stru";
		int count = 0;
		try {
			count = Integer.parseInt(this.getSingleValue(sql) + "");
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}
		p.setTotalCount(count);
		int totalPage = count % p.getPageSize() == 0 ? count / p.getPageSize() : count / p.getPageSize() + 1;
		p.setTotalPage(totalPage);
		String sqll = "SELECT * FROM pro_manage.main_stru ORDER BY `mid` DESC LIMIT ?,?";
		int pNo = p.getCurrent();
		if(pNo < 1) {
			pNo = 1;
		}
		p.setList(this.getBeanList(sqll, (pNo - 1) * p.getPageSize(), p.getPageSize()));
		return p;
	}

	@Override
	public Page<Main> getMainBySearch(Page<Main> p, String searchType, String searchKeyWord) {
		String sql;
		String sqll;
		if("1".equals(searchType)) {
			sql = "SELECT COUNT(1) FROM pro_manage.main_stru WHERE `name` LIKE ?";
		}else {
			sql = "SELECT COUNT(1) FROM pro_manage.main_stru WHERE material LIKE ?";
		}
		
		int count = Integer.parseInt(this.getSingleValue(sql, searchKeyWord) + "");
		p.setTotalCount(count);
		int totalPage = count % p.getPageSize() == 0 ? count / p.getPageSize() : count / p.getPageSize() + 1;
		p.setTotalPage(totalPage);
		if("1".equals(searchType)) {
			sqll = "SELECT * FROM pro_manage.main_stru WHERE `name` LIKE ? LIMIT ?,?";
		}else {
			sqll = "SELECT * FROM pro_manage.main_stru WHERE material LIKE ? LIMIT ?,?";
		}
		int pNo = p.getCurrent();
		if(pNo < 1) {
			pNo = 1;
		}
		p.setList(this.getBeanList(sqll, searchKeyWord, (pNo - 1) * p.getPageSize(), p.getPageSize()));
		return p;
	}

}

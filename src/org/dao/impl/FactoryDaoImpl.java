package org.dao.impl;

import java.util.List;

import org.bean.Factory;
import org.dao.BaseDao;
import org.dao.FactoryDao;

public class FactoryDaoImpl extends BaseDao<Factory> implements FactoryDao {

	@Override
	public Factory getFactory(String bfid) {
		String sql = "SELECT * FROM pro_manage.branch_factory WHERE bfid=?";
		return this.getBean(sql, bfid);
	}

	@Override
	public int updateFactory(Factory f, String oldBfid) {
		Factory factory = getFactory(f.getBfid());
		if(factory != null && !f.getBfid().equals(oldBfid)) {
			return -1;
		}
		String sql = "UPDATE pro_manage.branch_factory SET `name`=?,bfid=? WHERE bfid=?";
		return this.update(sql, f.getName(), f.getBfid(), oldBfid);
	}

	@Override
	public List<Factory> getAllFactory() {
		String sql = "SELECT * FROM pro_manage.branch_factory";
		return this.getBeanList(sql);
	}

	@Override
	public int removeFactory(String bfid) {
		String sql = "DELETE FROM pro_manage.branch_factory WHERE bfid=?";
		return this.update(sql, bfid);
	}

	@Override
	public int insertFactory(Factory f) {
		Factory factory = getFactory(f.getBfid());
		if(factory != null) {
			return -1;
		}
		String sql = "INSERT INTO pro_manage.branch_factory(bfid,`name`) VALUES(?,?)";
		return this.update(sql, f.getBfid(), f.getName());
	}

}

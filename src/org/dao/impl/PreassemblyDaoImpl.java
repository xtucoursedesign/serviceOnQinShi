package org.dao.impl;

import java.util.List;

import org.bean.Preassembly;
import org.dao.BaseDao;
import org.dao.PreassemblyDao;

public class PreassemblyDaoImpl extends BaseDao<Preassembly> implements PreassemblyDao {

	@Override
	public int getStatusByProId(String proId) {
		String sql = "SELECT COUNT(1) FROM pro_manage.pre_assembly WHERE proid=?";
		int totalCount = Integer.parseInt(this.getSingleValue(sql, proId) + "");
		if(totalCount == 0) {
			return 0;
		}
		String sqll = "SELECT COUNT(1) FROM pro_manage.pre_assembly WHERE complete=1 AND proid=?";
		int completeCount = Integer.parseInt(this.getSingleValue(sqll, proId) + "");
		return Math.round(completeCount * 100.0f / totalCount);
	}

	@Override
	public Preassembly getPreassembly(String paid) {
		String sql = "SELECT * FROM pro_manage.pre_assembly WHERE paid=?";
		return this.getBean(sql, paid);
	}

	@Override
	public List<Preassembly> getAllPreassemblyByProId(String proid) {
		String sql = "SELECT * FROM pro_manage.pre_assembly WHERE proid=? ORDER BY num DESC";
		return this.getBeanList(sql, proid);
	}

	@Override
	public int insertPreassembly(Preassembly p) {
		Preassembly pre = getPreassembly(p.getPaid());
		if(pre != null) {
			return -1;
		}
		String sql = "INSERT INTO pro_manage.pre_assembly(paid,assdate,mainid,complete,proid,`require`) VALUES (?,?,?,?,?,?)";
		return this.update(sql, p.getPaid(), p.getAssdate(), p.getMainid(), p.getComplete(), p.getProid(), p.getRequire());
	}

	@Override
	public int updatePreassembly(Preassembly p, String oldPaid) {
		Preassembly pre = getPreassembly(p.getPaid());
		if(pre != null && !oldPaid.equals(p.getPaid())) {
			return -1;
		}
		String sql = "UPDATE pro_manage.pre_assembly SET paid=?,assdate=?,mainid=?,complete=?,`require`=?,proid=? WHERE paid=?";
		return this.update(sql, p.getPaid(), p.getAssdate(), p.getMainid(), p.getComplete(), p.getRequire(), p.getProid(), oldPaid);
	}

	@Override
	public int updatePreassemblyState(String paid, Integer complete) {
		String sql = "UPDATE pro_manage.pre_assembly SET complete=? WHERE paid=?";
		return this.update(sql, complete, paid);
	}

	@Override
	public int removePreassembly(String paid) {
		String sql = "DELETE FROM pro_manage.pre_assembly WHERE paid=?";
		return this.update(sql, paid);
	}

}

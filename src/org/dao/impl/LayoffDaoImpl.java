package org.dao.impl;

import java.util.List;

import org.bean.Layoff;
import org.dao.BaseDao;
import org.dao.LayoffDao;

public class LayoffDaoImpl extends BaseDao<Layoff> implements LayoffDao {

	@Override
	public int getStatusByProId(String proId) {
		String sql = "SELECT COUNT(1) FROM pro_manage.laying_off WHERE proid=?";
		int totalCount = Integer.parseInt(this.getSingleValue(sql, proId) + "");
		if(totalCount == 0) {
			return 0;
		}
		String sqll = "SELECT COUNT(1) FROM pro_manage.laying_off WHERE complete=1 AND proid=?";
		int completeCount = Integer.parseInt(this.getSingleValue(sqll, proId) + "");
		return Math.round(completeCount * 100.0f / totalCount);
	}

	@Override
	public int insertLayoff(Layoff l) {
		Layoff lay = getLayoff(l.getLid());
		if(lay != null) {
			return -1;
		}
		if("".equals(l.getNote())) {
			String sql = "INSERT INTO pro_manage.laying_off(lid,baitdate,partid,`count`,mainid,complete,proid,weight,unit) VALUES(?,?,?,?,?,?,?,?,?)";
			return this.update(sql, l.getLid(), l.getBaitdate(), l.getPartid(), l.getCount(), l.getMainid(), l.getComplete(), l.getProid(), l.getWeight(), l.getUnit());
		}else {
			String sql = "INSERT INTO pro_manage.laying_off(lid,baitdate,partid,`count`,mainid,complete,proid,weight,unit,note) VALUES(?,?,?,?,?,?,?,?,?,?)";
			return this.update(sql, l.getLid(), l.getBaitdate(), l.getPartid(), l.getCount(), l.getMainid(), l.getComplete(), l.getProid(), l.getWeight(), l.getUnit(), l.getNote());
		}
	}

	@Override
	public Layoff getLayoff(String lid) {
		String sql = "SELECT * FROM pro_manage.laying_off WHERE lid=?";
		return this.getBean(sql, lid);
	}

	@Override
	public int updateLayoff(Layoff l, String oldLid) {
		Layoff lay = getLayoff(l.getLid());
		if(lay != null && !oldLid.equals(l.getLid())) {
			return -1;
		}
		String sql = "UPDATE pro_manage.laying_off SET lid=?,baitdate=?,partid=?,`count`=?,mainid=?,complete=?,proid=?,weight=?,unit=?,note=? WHERE lid=?";
		return this.update(sql, l.getLid(), l.getBaitdate(), l.getPartid(), l.getCount(), l.getMainid(), l.getComplete(), l.getProid(), l.getWeight(), l.getUnit(), l.getNote(), oldLid);
	}

	@Override
	public List<Layoff> getAllLayoffByProId(String proId) {
		String sql = "SELECT * FROM pro_manage.laying_off WHERE proid=? ORDER BY num DESC";
		return this.getBeanList(sql, proId);
	}

	@Override
	public int updateLayoffState(String lid, Integer complete) {
		String sql = "UPDATE pro_manage.laying_off SET complete=? WHERE lid=?";
		return this.update(sql, complete, lid);
	}

	@Override
	public int removeLayoff(String lid) {
		String sql = "DELETE FROM pro_manage.laying_off WHERE lid=?";
		return this.update(sql, lid);
	}

}

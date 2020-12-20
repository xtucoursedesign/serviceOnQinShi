package org.dao.impl;

import java.util.List;

import org.bean.Assembly;
import org.dao.AssemblyDao;
import org.dao.BaseDao;

public class AssemblyDaoImpl extends BaseDao<Assembly> implements AssemblyDao{

	@Override
	public int getStatusByProId(String proId) {
		String sql = "SELECT COUNT(1) FROM pro_manage.assembly WHERE proid=?";
		int totalCount = Integer.parseInt(this.getSingleValue(sql, proId) + "");
		if(totalCount == 0) {
			return 0;
		}
		String sqll = "SELECT COUNT(1) FROM pro_manage.assembly WHERE complete=1 AND proid=?";
		int completeCount = Integer.parseInt(this.getSingleValue(sqll, proId) + "");
		return Math.round(completeCount * 100.0f / totalCount);
	}

	@Override
	public int insertAssembly(Assembly a) {
		Assembly asb = getAssembly(a.getAid());
		if(asb != null) {
			return -1;
		}
		if(a.getHole().equals(0)) {
			String sql = "INSERT INTO pro_manage.assembly(aid,secdate,partid,complete,proid,mainid,hole,`require`) VALUES(?,?,?,?,?,?,?,?)";
			return this.update(sql, a.getAid(), a.getSecdate(), a.getPartid(), a.getComplete(), a.getProid(), a.getMainid(), a.getHole(), a.getRequire());
		}else {
			if("".equals(a.getShotdate())) {
				String sql = "INSERT INTO pro_manage.assembly(aid,secdate,partid,complete,proid,mainid,hole,shotcomp,`require`) VALUES(?,?,?,?,?,?,?,?,?)";
				return this.update(sql, a.getAid(), a.getSecdate(), a.getPartid(), a.getComplete(), a.getProid(), a.getMainid(), a.getHole(), a.getShotcomp(), a.getRequire());
			}else {
				String sql = "INSERT INTO pro_manage.assembly(aid,secdate,partid,complete,proid,mainid,hole,shotdate,shotcomp,`require`) VALUES(?,?,?,?,?,?,?,?,?,?)";
				return this.update(sql, a.getAid(), a.getSecdate(), a.getPartid(), a.getComplete(), a.getProid(), a.getMainid(), a.getHole(), a.getShotdate(), a.getShotcomp(), a.getRequire());
			}
		}
	}

	@Override
	public Assembly getAssembly(String aid) {
		String sql = "SELECT * FROM pro_manage.assembly WHERE aid=?";
		return this.getBean(sql, aid);
	}

	@Override
	public int updateAssembly(Assembly a, String oldAid) {
		Assembly asb = getAssembly(a.getAid());
		if(asb != null && !oldAid.equals(a.getAid())) {
			return -1;
		}
		if(a.getHole().equals(0)) {
			String sql = "UPDATE pro_manage.assembly SET aid=?,secdate=?,partid=?,complete=?,proid=?,mainid=?,hole=?,`require`=?,shotdate=NULL,shotcomp=NULL WHERE aid=?";
			return this.update(sql, a.getAid(), a.getSecdate(), a.getPartid(), a.getComplete(), a.getProid(), a.getMainid(), a.getHole(), a.getRequire(), oldAid);
		}else {
			if("".equals(a.getShotdate())) {
				String sql = "UPDATE pro_manage.assembly SET aid=?,secdate=?,partid=?,complete=?,proid=?,mainid=?,hole=?,`require`=?,shotcomp=?,shotdate=NULL WHERE aid=?";
				return this.update(sql, a.getAid(), a.getSecdate(), a.getPartid(), a.getComplete(), a.getProid(), a.getMainid(), a.getHole(), a.getRequire(), a.getShotcomp(), oldAid);
			}else {
				String sql = "UPDATE pro_manage.assembly SET aid=?,secdate=?,partid=?,complete=?,proid=?,mainid=?,hole=?,`require`=?,shotdate=?,shotcomp=? WHERE aid=?";
				return this.update(sql, a.getAid(), a.getSecdate(), a.getPartid(), a.getComplete(), a.getProid(), a.getMainid(), a.getHole(), a.getRequire(), a.getShotdate(), a.getShotcomp(), oldAid);
			}
		}
	}

	@Override
	public List<Assembly> getAllAssemblyByProId(String proid) {
		String sql = "SELECT * FROM pro_manage.assembly WHERE proid=? ORDER BY num DESC";
		return this.getBeanList(sql, proid);
	}

	@Override
	public int updateAssemblyState(String aid, Integer complete) {
		String sql = "UPDATE pro_manage.assembly SET complete=? WHERE aid=?";
		return this.update(sql, complete, aid);
	}

	@Override
	public int updateHoleState(String aid, Integer shotcomp) {
		String sql = "UPDATE pro_manage.assembly SET shotcomp=? WHERE aid=?";
		return this.update(sql, shotcomp, aid);
	}

	@Override
	public int removeAssembly(String aid) {
		String sql = "DELETE FROM pro_manage.assembly WHERE aid=?";
		return this.update(sql, aid);
	}

}

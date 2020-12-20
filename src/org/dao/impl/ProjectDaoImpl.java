package org.dao.impl;

import java.util.List;

import org.bean.Project;
import org.dao.BaseDao;
import org.dao.ProjectDao;

public class ProjectDaoImpl extends BaseDao<Project> implements ProjectDao{

	@Override
	public Project getProject(String pid) {
		String sql = "SELECT * FROM pro_manage.project WHERE pid=?";
		return this.getBean(sql, pid);
	}

	@Override
	public List<Project> getAllProject() {
		String sql = "SELECT * FROM pro_manage.project ORDER BY num DESC";
		return this.getBeanList(sql);
	}

	@Override
	public int insertProject(Project p) {
		Project project = getProject(p.getPid());
		if(project != null) {
			return -1;
		}
		
		if(!"".equals(p.getActdate())) {
			String sql = "INSERT INTO pro_manage.project(pid,`name`,faid,investid,investname,approdate,spededate,actdate) VALUES(?,?,?,?,?,?,?,?)";
			return this.update(sql, p.getPid(), p.getName(), p.getFaid(), p.getInvestid(), p.getInvestname(), p.getApprodate(), p.getSpededate(), p.getActdate());
		}else {
			String sql = "INSERT INTO pro_manage.project(pid,`name`,faid,investid,investname,approdate,spededate) VALUES(?,?,?,?,?,?,?)";
			return this.update(sql, p.getPid(), p.getName(), p.getFaid(), p.getInvestid(), p.getInvestname(), p.getApprodate(), p.getSpededate());
		}
	}

	@Override
	public int updateProject(Project p, String oldPid) {
		Project project = getProject(p.getPid());
		if(project != null && !p.getPid().equals(oldPid)) {
			return -1;
		}
		if(!"".equals(p.getActdate())){
			String sql = "UPDATE pro_manage.project SET pid=?,`name`=?,faid=?,investid=?,investname=?,approdate=?,spededate=?,actdate=? WHERE pid=?";
			return this.update(sql, p.getPid(), p.getName(), p.getFaid(), p.getInvestid(), p.getInvestname(), p.getApprodate(), p.getSpededate(), p.getActdate(), oldPid);
		}else {
			String sql = "UPDATE pro_manage.project SET pid=?,`name`=?,faid=?,investid=?,investname=?,approdate=?,spededate=? WHERE pid=?";
			return this.update(sql, p.getPid(), p.getName(), p.getFaid(), p.getInvestid(), p.getInvestname(), p.getApprodate(), p.getSpededate(), oldPid);
		}
	}

	@Override
	public int removeProject(String pid) {
		String sql = "DELETE FROM pro_manage.project WHERE pid=?";
		return this.update(sql, pid);
	}

}

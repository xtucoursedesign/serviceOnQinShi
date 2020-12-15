package org.dao.impl;

import java.util.List;

import org.bean.Page;
import org.bean.Part;
import org.dao.BaseDao;
import org.dao.PartDao;

public class PartDaoImpl extends BaseDao<Part> implements PartDao {
	
	// SELECT * FROM pro_manage.part WHERE pid='00000000001'
	@Override
	public Part getPart(String pid) {
		String sql = "SELECT * FROM pro_manage.part WHERE pid=?";
		return this.getBean(sql, pid);
	}

	@Override
	public List<Part> getAllPart() {
		String sql = "SELECT * FROM pro_manage.part";
		return this.getBeanList(sql);
	}
	
	/**
	 * 
	 * INSERT INTO pro_manage.part(pid,`name`,material,specifi,texture,weight,img,unit)
	 * VALUES('00000000001','齿轮','铁','普通规格','生铁',55,'http://localhost:8080/xtuproject/upload/logo.png')
	 */
	
	@Override
	public int insertPart(Part p) {
		Part part = getPart(p.getPid());
		if(part != null) {
			return -1;
		}
		String sql = "INSERT INTO pro_manage.part(pid,`name`,material,specifi,texture,weight,img,unit) VALUES(?,?,?,?,?,?,?,?)";
		return this.update(sql, p.getPid(), p.getName(), p.getMaterial(), p.getSpecifi(), p.getTexture(), p.getWeight(), p.getImg(), p.getUnit());
	}

	@Override
	public int removePart(String pid) {
		String sql = "DELETE FROM pro_manage.part WHERE pid=?";
		return this.update(sql, pid);
	}
	
	/**
	 * UPDATE pro_manage.part SET pid='00000000001',`name`='中齿轮',material='铁',
	 * specifi='普通规格',texture='生铁',weight=55,img='http://localhost:8080/xtuproject/upload/logo.png' WHERE pid='00000000001'
	 */
	@Override
	public int updatePart(Part p, String oldPid) {
		Part part = getPart(p.getPid());
		if(part != null && !p.getPid().equals(oldPid)) {
			return -1;
		}
		String sql = "UPDATE pro_manage.part SET pid=?,`name`=?,material=?,specifi=?,texture=?,weight=?,img=?,unit=? WHERE pid=?";
		return this.update(sql, p.getPid(), p.getName(), p.getMaterial(), p.getSpecifi(), p.getTexture(), p.getWeight(), p.getImg(), p.getUnit(), oldPid);
	}

	@Override
	public Page<Part> getPartByPage(Page<Part> page) {
		String sql = "SELECT COUNT(1) FROM pro_manage.part";
		int count = Integer.parseInt(this.getSingleValue(sql) + "");
		page.setTotalCount(count);
		int totalPage = count % page.getPageSize() == 0 ? count / page.getPageSize() : count / page.getPageSize() + 1;
		page.setTotalPage(totalPage);
		String sqll = "SELECT * FROM pro_manage.part ORDER BY pid DESC LIMIT ?,?";
		int pNo = page.getCurrent();
		if(pNo < 1) {
			pNo = 1;
		}
		page.setList(this.getBeanList(sqll, (pNo - 1) * page.getPageSize(), page.getPageSize()));
		return page;
	}

	@Override
	public Page<Part> getPartBySearch(Page<Part> page, String searchType, String searchKeyWord) {
		String sql;
		String sqll;
		if("1".equals(searchType)) {
			sql = "SELECT COUNT(1) FROM pro_manage.part WHERE `name` LIKE ?";
		}else {
			sql = "SELECT COUNT(1) FROM pro_manage.part WHERE material LIKE ?";
		}
		
		int count = Integer.parseInt(this.getSingleValue(sql, searchKeyWord) + "");
		page.setTotalCount(count);
		int totalPage = count % page.getPageSize() == 0 ? count / page.getPageSize() : count / page.getPageSize() + 1;
		page.setTotalPage(totalPage);
		if("1".equals(searchType)) {
			sqll = "SELECT * FROM pro_manage.part WHERE `name` LIKE ? LIMIT ?,?";
		}else {
			sqll = "SELECT * FROM pro_manage.part WHERE material LIKE ? LIMIT ?,?";
		}
		int pNo = page.getCurrent();
		if(pNo < 1) {
			pNo = 1;
		}
		page.setList(this.getBeanList(sqll, searchKeyWord, (pNo - 1) * page.getPageSize(), page.getPageSize()));
		return page;
	}

}

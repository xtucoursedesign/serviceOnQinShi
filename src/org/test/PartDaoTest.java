package org.test;

import java.util.List;

import org.bean.Page;
import org.bean.Part;
import org.dao.PartDao;
import org.dao.impl.PartDaoImpl;
import org.junit.jupiter.api.Test;

class PartDaoTest {
	private PartDao partDao = new PartDaoImpl();
	
	@Test
	void testGetPart() {
		Part p = partDao.getPart("00000000001");
		System.out.println(p);
	}

	@Test
	void testGetAllPart() {
		List<Part> list = partDao.getAllPart();
		for(Part p : list) {
			System.out.println(p);
		}
	}

	@Test
	void testInsertPart() {
		// ('00000000004','小齿轮','铁','普通规格','生铁',50,'http://localhost:8080/xtuproject/upload/logo.png')
		Part p = new Part("00000000004", "小齿轮", "铁", "普通规格", "生铁", "50", "http://localhost:8080/xtuproject/upload/logo.png", "kg");
		partDao.insertPart(p);
	}

	@Test
	void testRemovePart() {
		partDao.removePart("00000000005");
	}

	@Test
	void testUpdatePart() {
		Part p = new Part("00000000005", "小齿轮", "铁", "普通规格", "生铁", "50", "http://localhost:8080/xtuproject/upload/logo.png", "kg");
		partDao.updatePart(p, "00000000004");
	}
	
	@Test
	void testGetPartByPage() {
		Page<Part> p = new Page<>();
		p.setCurrent(4);
		Page<Part> partByPage = partDao.getPartByPage(p);
		List<Part> list = partByPage.getList();
		System.out.println(list.size());
		System.out.println(list);
	}
	
	@Test
	void testGetPartBySearch() {
		Page<Part> p = new Page<>();
		p.setCurrent(1);
		String searchType = "1";
		String searchKeyWord = "%铜%";
		Page<Part> partByPage = partDao.getPartBySearch(p, searchType, searchKeyWord);
		List<Part> list = partByPage.getList();
		System.out.println(partByPage);
		System.out.println(partByPage.getTotalCount());
		System.out.println(list.size());
		System.out.println(list);
	}

}

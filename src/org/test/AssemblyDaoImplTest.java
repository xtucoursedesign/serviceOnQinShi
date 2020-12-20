package org.test;

import org.dao.AssemblyDao;
import org.dao.impl.AssemblyDaoImpl;
import org.junit.jupiter.api.Test;

class AssemblyDaoImplTest {
	private AssemblyDao assd = new AssemblyDaoImpl();
	
	@Test
	void testGetStatusByProId() {
		System.out.println(assd.getStatusByProId("00000000003"));
	}

}

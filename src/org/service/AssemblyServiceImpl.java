package org.service;

import java.util.List;

import org.bean.Assembly;
import org.dao.AssemblyDao;
import org.dao.impl.AssemblyDaoImpl;

public class AssemblyServiceImpl implements AssemblyService {
	private AssemblyDao ad = new AssemblyDaoImpl();

	@Override
	public Assembly getAssembly(String aid) {
		return ad.getAssembly(aid);
	}

	@Override
	public int insertAssembly(Assembly a) {
		return ad.insertAssembly(a);
	}

	@Override
	public int updateAssembly(Assembly a, String oldAid) {
		return ad.updateAssembly(a, oldAid);
	}

	@Override
	public List<Assembly> getAllAssemblyByProId(String proid) {
		return ad.getAllAssemblyByProId(proid);
	}

	@Override
	public boolean updateAssemblyState(String aid, Integer complete) {
		return ad.updateAssemblyState(aid, complete) > 0;
	}

	@Override
	public boolean updateHoleState(String aid, Integer shotcomp) {
		return ad.updateHoleState(aid, shotcomp) > 0;
	}

	@Override
	public boolean removeAssembly(String aid) {
		return ad.removeAssembly(aid) > 0;
	}

}

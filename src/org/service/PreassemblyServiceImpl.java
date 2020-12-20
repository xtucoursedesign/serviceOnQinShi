package org.service;

import java.util.List;

import org.bean.Preassembly;
import org.dao.PreassemblyDao;
import org.dao.impl.PreassemblyDaoImpl;

public class PreassemblyServiceImpl implements PreassemblyService {
	private PreassemblyDao pd = new PreassemblyDaoImpl();

	@Override
	public Preassembly getPreassembly(String paid) {
		return pd.getPreassembly(paid);
	}

	@Override
	public List<Preassembly> getAllPreassemblyByProId(String proid) {
		return pd.getAllPreassemblyByProId(proid);
	}

	@Override
	public int insertPreassembly(Preassembly p) {
		return pd.insertPreassembly(p);
	}

	@Override
	public int updatePreassembly(Preassembly p, String oldPaid) {
		return pd.updatePreassembly(p, oldPaid);
	}

	@Override
	public boolean updatePreassemblyState(String paid, Integer complete) {
		return pd.updatePreassemblyState(paid, complete) > 0;
	}

	@Override
	public boolean removePreassembly(String paid) {
		return pd.removePreassembly(paid) > 0;
	}

}

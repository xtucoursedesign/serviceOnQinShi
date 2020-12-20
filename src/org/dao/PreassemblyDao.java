package org.dao;

import java.util.List;

import org.bean.Preassembly;

public interface PreassemblyDao {
	Preassembly getPreassembly(String paid);
	// ×é×°
	int getStatusByProId(String proId);
	List<Preassembly> getAllPreassemblyByProId(String proid);
	int insertPreassembly(Preassembly p);
	int updatePreassembly(Preassembly p, String oldPaid);
	int updatePreassemblyState(String paid, Integer complete);
	int removePreassembly(String paid);
}

package org.service;

import java.util.List;

import org.bean.Preassembly;

public interface PreassemblyService {
	Preassembly getPreassembly(String paid);
	List<Preassembly> getAllPreassemblyByProId(String proid);
	int insertPreassembly(Preassembly p);
	int updatePreassembly(Preassembly p, String oldPaid);
	boolean updatePreassemblyState(String paid, Integer complete);
	boolean removePreassembly(String paid);
}

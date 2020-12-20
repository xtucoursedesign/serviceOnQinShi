package org.service;

import java.util.List;

import org.bean.Assembly;

public interface AssemblyService {
	Assembly getAssembly(String aid);
	int insertAssembly(Assembly a);
	int updateAssembly(Assembly a, String oldAid);
	List<Assembly> getAllAssemblyByProId(String proid);
	boolean updateAssemblyState(String aid, Integer complete);
	boolean updateHoleState(String aid, Integer shotcomp);
	boolean removeAssembly(String aid);
}

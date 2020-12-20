package org.dao;

import java.util.List;

import org.bean.Assembly;

public interface AssemblyDao {
	Assembly getAssembly(String aid);
	// ¶þ´Î×°Åä
	int getStatusByProId(String proId);
	int insertAssembly(Assembly a);
	int updateAssembly(Assembly a, String oldAid);
	List<Assembly> getAllAssemblyByProId(String proid);
	int updateAssemblyState(String aid, Integer complete);
	int updateHoleState(String aid, Integer shotcomp);
	int removeAssembly(String aid);
}

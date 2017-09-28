package com.zr.dao;



import net.sf.json.JSONArray;

public interface RoleDao {
	/**
	 * 得到所有角色的信息
	 * @return
	 */
 public JSONArray selectAllRole();
 
 /**
  * 通过角色名和功能id更新角色功能
  * @param roleName 角色名
  * @param funcsid 功能id字符串
  * @return
  */
 public void UpdataRoleFunc(String roleName,String funcsid);
}

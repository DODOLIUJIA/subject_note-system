package com.zr.service;

import java.util.List;

import com.zr.model.Role;

import net.sf.json.JSONArray;

public interface RoleService {
	/**
	 * 得到所有角色的信息
	 * @return
	 */
 public JSONArray selectAllRole();
 /**
  * 通过角色名得到该角色的功能
  * @param rname 角色名
  * @return
  */
 public JSONArray selectFuncsByRoleName(String Rname); 
 /**
  * 通过角色名和功能id更新角色功能
  * @param roleName 角色名
  * @param funcsid 功能id字符串
  * @return
  */
 public void UpdataRoleFunc(String roleName,String funcsid);
}

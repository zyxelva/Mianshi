package com.baobaotao.loginNlog.dao;

import org.springframework.stereotype.Repository;

import com.baobaotao.base.dao.BaseDao;
import com.baobaotao.loginNlog.entity.LoginLog;

@Repository
public class LoginLogDao extends BaseDao implements ILoginLogDao
{
	@Override
	public void insertLoginLog(LoginLog	 loginLog){
		this.add(loginLog);
	}
}

package com.baobaotao.loginNlog.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.baobaotao.exception.BusinessException;
import com.baobaotao.loginNlog.dao.ILoginLogDao;
import com.baobaotao.loginNlog.dao.IUserDao;
import com.baobaotao.loginNlog.entity.LoginLog;
import com.baobaotao.loginNlog.entity.User;

@Service("userService")
public class UserService implements IUserService
{

	private IUserDao userDao;


	private ILoginLogDao loginLogDao;


	public IUserDao getUserDao()
	{
		return userDao;
	}
	@Resource
	public void setUserDao(IUserDao userDao)
	{
		this.userDao = userDao;
	}

	public ILoginLogDao getLoginLogDao()
	{
		return loginLogDao;
	}
	@Resource
	public void setLoginLogDao(ILoginLogDao loginLogDao)
	{
		this.loginLogDao = loginLogDao;
	}

	@Override
	public boolean hasMatchUser(String userName, String password){
		int matchCount=userDao.getMatchCount(userName, password);
		return matchCount>0;
	}

	@Override
	public User findUserByUserName(String userName,String password){
		if(!hasMatchUser(userName, password)){
			throw new BusinessException();
		}
		User user=userDao.findUserByUserName(userName);
		return user;
	}

	@Override
	public void loginSuccess(User user){
		user.setCredits(5+user.getCredits());
		LoginLog loginLog=new LoginLog();
		loginLog.setUser_id(user.getUser_id());
		loginLog.setIp(user.getLast_ip());
		loginLog.setLogin_date(user.getLast_visit());

		userDao.updateLoginInfo(user);
		loginLogDao.insertLoginLog(loginLog);
	}
}

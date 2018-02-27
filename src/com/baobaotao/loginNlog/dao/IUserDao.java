package com.baobaotao.loginNlog.dao;

import com.baobaotao.loginNlog.entity.User;

public interface IUserDao
{
	public int getMatchCount(String userName,String password);
	public User findUserByUserName(final String userName);
	public void updateLoginInfo(User user);
}

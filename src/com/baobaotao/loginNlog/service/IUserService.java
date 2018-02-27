package com.baobaotao.loginNlog.service;

import com.baobaotao.loginNlog.entity.User;

public interface IUserService
{
	public boolean hasMatchUser(String userName, String password);
	public void loginSuccess(User user);
	public User findUserByUserName(String userName, String password);

}

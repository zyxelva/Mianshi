package com.baobaotao.loginNlog.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.baobaotao.base.dao.BaseDao;
import com.baobaotao.base.entity.Pager;
import com.baobaotao.loginNlog.entity.User;

@Repository("userDao")
public class UserDao extends BaseDao implements IUserDao
{
	@Override
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public int getMatchCount(String userName,String password){
		StringBuilder sqlStr=new StringBuilder(" FROM User "
																+  " WHERE user_name= :userName and password= :password " );
		Map sqlParams=new HashMap();
		sqlParams.put("userName", userName);
		sqlParams.put("password",password);
		Pager pager=new Pager();
		pager=this.findPagerByHql(pager, sqlStr.toString(), sqlParams);
		int cnt=(int) pager.getTotal();
		return cnt;
	}

	@Override
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public User findUserByUserName(final String userName){
		StringBuilder sqlStr=new StringBuilder("  FROM User WHERE user_name= :userName ");
		Map sqlParams=new HashMap();
		sqlParams.put("userName", userName);
		User user=new User();
		List<User> list=this.listByAlias(sqlStr.toString(), sqlParams);
		if(list.size()>0){
			user=list.get(0);
		}

		return user;
	}

	@Override
	public void updateLoginInfo(User user){
		String userId=user.getUser_id();
		User temp=this.get(User.class, userId);
		temp.setCredits(user.getCredits());
		temp.setLast_ip(user.getLast_ip());
		temp.setLast_visit(user.getLast_visit());
		user=temp;
		this.update(user);
	}
}

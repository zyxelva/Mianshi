package com.baobaotao.loginNlog.action;

import java.util.Date;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;

import com.baobaotao.common.utils.SystemContent;
import com.baobaotao.exception.BusinessException;
import com.baobaotao.loginNlog.entity.User;
import com.baobaotao.loginNlog.service.IUserService;
import com.baobaotao.web.model.RequestObject;
import com.baobaotao.web.model.ResponseObject;
@Controller("userLoginAction")
public class UserLoginAction
{
	private IUserService userService;

	public IUserService getUserService()
	{
		return userService;
	}
	@Resource
	public void setUserService(IUserService userService)
	{
		this.userService = userService;
	}

	public ResponseObject login(RequestObject request){
		ResponseObject response=new ResponseObject();
		String userName=request.get("userName").toString();
		String password =request.get("password").toString();
		try{
			if(null==userName||"".equals(userName)){
				throw new BusinessException("user does not exist!");
			}
			if(null==password||"".equals(password)){
				throw new BusinessException("password is Wrong! Please try again! ");
			}

			User user =userService.findUserByUserName(userName,password);
			if(null==user.getUser_id()||"".equals(user.getUser_id())){
				throw new BusinessException();
			}
			user.setLast_ip("127.0.0.1");
			user.setLast_visit(new Date());
			userService.loginSuccess(user);
			//SystemContent.setUserLocal(user);
			SystemContent.getSession().setAttribute("user", user);// 插入此人用户信息
			response.put("user", user);

		}catch(Exception ex){
			response.setResultCode(-1);
			if (ex instanceof BusinessException)
			{
				response.setResultMsgType("T000001");
				response.setResultMsg("HHHHHHHH");
			}
			else
			{
				response.setResultMsgType("T000001");
				response.setResultMsg("YYYYYYYYY");
			}
		}
		return response;

	}
}

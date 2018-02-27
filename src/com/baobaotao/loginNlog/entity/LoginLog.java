package com.baobaotao.loginNlog.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
@Entity
@Table(name = "t_login_log")
public class LoginLog implements Serializable
{
	/**
	 *
	 */
	private static final long serialVersionUID = 2822914042998091545L;
	/**
	 *
	 */
	@Id
	@GeneratedValue(generator = "system_uuid")
	@GenericGenerator(name = "system_uuid", strategy = "uuid")
	private String login_log_id;/**/
	private String user_id;/**/
	private String ip;/**/
	private Date login_date;/**/
	public String getLogin_log_id()
	{
		return login_log_id;
	}
	public void setLogin_log_id(String login_log_id)
	{
		this.login_log_id = login_log_id;
	}
	public String getUser_id()
	{
		return user_id;
	}
	public void setUser_id(String user_id)
	{
		this.user_id = user_id;
	}
	public String getIp()
	{
		return ip;
	}
	public void setIp(String ip)
	{
		this.ip = ip;
	}
	public Date getLogin_date()
	{
		return login_date;
	}
	public void setLogin_date(Date login_date)
	{
		this.login_date = login_date;
	}

}

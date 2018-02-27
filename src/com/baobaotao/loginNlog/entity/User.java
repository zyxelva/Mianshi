package com.baobaotao.loginNlog.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.baobaotao.base.entity.AbsBaseEntity;
@Entity
@Table(name = "t_user")
public class User extends AbsBaseEntity
{
	/**
	 *
	 */
	private static final long serialVersionUID = 7939713010651040417L;
	/**
	 *
	 */

	@Id
	@GeneratedValue(generator = "system_uuid")
	@GenericGenerator(name = "system_uuid", strategy = "uuid")
	private String user_id;/**/
	private String user_name;/**/
	private int credits;/**/
	private String password;/**/
	private Date last_visit;/**/
	private String last_ip;/**/


	public String getUser_id()
	{
		return user_id;
	}
	public void setUser_id(String user_id)
	{
		this.user_id = user_id;
	}
	public String getUser_name()
	{
		return user_name;
	}
	public void setUser_name(String user_name)
	{
		this.user_name = user_name;
	}
	public int getCredits()
	{
		return credits;
	}
	public void setCredits(int credits)
	{
		this.credits = credits;
	}
	public String getPassword()
	{
		return password;
	}
	public void setPassword(String password)
	{
		this.password = password;
	}
	public Date getLast_visit()
	{
		return last_visit;
	}
	public void setLast_visit(Date last_visit)
	{
		this.last_visit = last_visit;
	}
	public String getLast_ip()
	{
		return last_ip;
	}
	public void setLast_ip(String last_ip)
	{
		this.last_ip = last_ip;
	}

}

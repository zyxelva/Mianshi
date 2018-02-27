package com.baobaotao.common.utils;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.baobaotao.loginNlog.entity.User;

public class SystemContent {
	// 用户信息
	private static ThreadLocal<User> userLocal = new ThreadLocal<User>();
	private static ThreadLocal<HttpServletRequest> requestLocal = new ThreadLocal<HttpServletRequest>();
	private static ThreadLocal<HttpServletResponse> responseLocal = new ThreadLocal<HttpServletResponse>();
	private static ThreadLocal<Map<String, Session>> hibernateSessionLocal = new ThreadLocal<Map<String, Session>>();
	private static ThreadLocal<Map<String, Transaction>> hibernateTransactionLocal = new ThreadLocal<Map<String, Transaction>>();
	// 当前所选择国际化语言
	private static ThreadLocal<String> languageLocal = new ThreadLocal<String>() {
		@Override
		public String initialValue() {
			return PropertiesUtil.getProperty("DEFAULT_LANGUAGE");// "zh_CN";
		}
	};
	public static ThreadLocal<User> getUserLocal()
	{
		return userLocal;
	}

	public static void setUserLocal(ThreadLocal<User> userLocal)
	{
		SystemContent.userLocal = userLocal;
	}

	// 当前业务编号
	private static ThreadLocal<String> transactionId = new ThreadLocal<String>();
	// 当前操作编号
	private static ThreadLocal<String> operationId = new ThreadLocal<String>();

	public static String getTransactionId() {
		//TransactionValidate.Validate(transactionId.get());
		return transactionId.get();
	}

	public static void setTransactionId(String transactionId) {
		//TransactionValidate.Validate(transactionId);
		SystemContent.transactionId.set(transactionId);
	}

	public static String getOperationId() {
		return operationId.get();
	}

	public static void setOperationId(String operationId) {
		SystemContent.operationId.set(operationId);
	}

	// 用户信息
//	private static ThreadLocal<SysUser> userLocal = new ThreadLocal<SysUser>();
//	// 用户角色信息
//	private static ThreadLocal<List<SysRoleUser>> sysRoleLocal = new ThreadLocal<List<SysRoleUser>>();
//	// 用户权限信息
//	private static ThreadLocal<List<SysResourceAuthority>> sysResourceAuthorityLocal = new ThreadLocal<List<SysResourceAuthority>>();
//	// 菜单信息
	private static ThreadLocal<Map> menuLocal = new ThreadLocal<Map>();

	public static HttpServletRequest getRequest() {
		return requestLocal.get();
	}

	public static void setRequest(HttpServletRequest request) {
		requestLocal.set(request);
	}

	public static HttpServletResponse getResponse() {
		return responseLocal.get();
	}

	public static void setResponse(HttpServletResponse response) {
		responseLocal.set(response);
	}

	public static HttpSession getSession() {
		return requestLocal.get()
				.getSession();
	}

	public static String getLanguage() {
		return languageLocal.get();
	}

	public static void setLanguage(String lang) {
		languageLocal.set(lang);
	}


	public static Map getMenuLocal() {
		return menuLocal.get();
	}

	public static void setMenuLocal(Map menu) {
		menuLocal.set(menu);
	}


	public static Session getHibernateSession(String key) {
		if(key==null){
			return null;
		}
		if(hibernateSessionLocal.get()==null){
			return null;
		}
		if (hibernateSessionLocal.get().containsKey(key)) {
			return hibernateSessionLocal.get().get(key);
		} else {
			return null;
		}
	}

	public static Map<String, Session> getAllHibernateSession() {
		return hibernateSessionLocal.get();
	}

	public static void setHibernateSession(String key, Session session) {
		Map<String, Session> ss = hibernateSessionLocal.get();
		if(ss==null){
			ss=new HashMap<String, Session>();
		}
		ss.put(key, session);
		hibernateSessionLocal.set(ss);
	}

	public static void removeHibernateSession() {
		hibernateTransactionLocal.remove();
		hibernateSessionLocal.remove();
	}

	public static Transaction getHibernateTransaction(String key) {
		if(hibernateTransactionLocal.get()==null){
			return null;
		}
		if (hibernateTransactionLocal.get().containsKey(key)) {
			return hibernateTransactionLocal.get().get(key);
		} else {
			return null;
		}
	}
	public static Map<String,Transaction> getAllHibernateTransactionLocal() {
		return hibernateTransactionLocal.get();
	}

	public static void setHibernateTransactionLocal(String key,Transaction transaction) {
		Map<String,Transaction> tt=hibernateTransactionLocal.get();
		if(tt==null){
			tt=new HashMap<String,Transaction>();
		}
		tt.put(key, transaction);
		hibernateTransactionLocal.set(tt);
	}


}

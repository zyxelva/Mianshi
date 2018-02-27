package com.baobaotao.base.dao;

//com.grgbanking.framework.base.dao.BaseDao
import java.io.Serializable;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Repository;

import com.baobaotao.base.entity.Pager;
import com.baobaotao.base.multipledatasource.MultipleSessionFactory;
import com.baobaotao.base.multipledatasource.MultipleSessionFactoryAnnotation;
import com.baobaotao.common.utils.PropertiesUtil;
import com.baobaotao.common.utils.SystemContent;
import com.baobaotao.exception.UserException;

/**
 * @author Administrator
 *
 */
@Repository("baseDao")
public class BaseDao implements IBaseDao
{

	private SessionFactory sessionFactory;
	/**
	 * 创建一个Class的对象来获取泛型的class
	 */
	private Class<?> clz;

	public Class<?> getClz()
	{
		if (clz == null)
		{
			// 获取泛型的Class对象
			clz = (Class<?>) ((ParameterizedType) this.getClass().getGenericSuperclass()).getActualTypeArguments()[0];
		}
		return clz;
	}

	public SessionFactory getSessionFactory()
	{
		return sessionFactory;
	}

	@Resource
	public void setSessionFactory(SessionFactory sessionFactory)
	{
		this.sessionFactory = sessionFactory;
	}

	/**
	 *
	 * @param sessionFactoryBeanId
	 *            参数为空默认调用无参数getSession();
	 * @return
	 */
	protected Session getSession(String sessionFactoryBeanId)
	{
		/**/
		if (sessionFactoryBeanId == null || "".equals(sessionFactoryBeanId))
		{
			return getSession();
		}
		SystemContent.getAllHibernateSession();
		Session session = SystemContent.getHibernateSession(sessionFactoryBeanId);
		if (session == null)
		{
			session = MultipleSessionFactory.getSession(sessionFactoryBeanId);
			SystemContent.setHibernateSession(sessionFactoryBeanId, session);
		}

		Transaction tx = session.getTransaction();
		if (tx == null || tx.isActive() == false)
		{
			SystemContent.setHibernateTransactionLocal(sessionFactoryBeanId, session.beginTransaction());
		}
		return session;
	}

	protected Session getSession()
	{
		Session session = SystemContent.getHibernateSession(PropertiesUtil.getProperty("DEFAULT_SESSION_FACTORY_BEAN_ID"));
		if (session == null)
		{
			SystemContent.setHibernateSession(PropertiesUtil.getProperty("DEFAULT_SESSION_FACTORY_BEAN_ID"), sessionFactory.openSession());

			session = SystemContent.getHibernateSession(PropertiesUtil.getProperty("DEFAULT_SESSION_FACTORY_BEAN_ID"));
		}
		Transaction tx = session.getTransaction();
		if (tx == null || tx.isActive() == false)
		{
			SystemContent.setHibernateTransactionLocal(PropertiesUtil.getProperty("DEFAULT_SESSION_FACTORY_BEAN_ID"), session.beginTransaction());
		}
		return session;
	}

	@Override
	public Object add(Object obj)
	{
		getSession(getSessionFactoryBeanId(obj)).save(obj);
		return obj;
	}

	public Object queryObject(String hql, Object[] args)
	{
		return this.queryObject(hql, args, null);
	}

	public Object queryObject(String hql, Object arg)
	{
		return this.queryObject(hql, new Object[] { arg });
	}

	@Override
	public List<?> list(String hql)
	{
		return this.list(hql, String.class);
	}

	@Override
	public List<?> list(String hql, Class cls)
	{
		return this.list(hql, null, null, cls);
	}

	public List<?> list(String hql, Object arg)
	{
		return this.list(hql, new Object[] { arg });
	}

	public List<?> list(String hql, Object[] args)
	{
		return this.list(hql, args, Object.class);
	}

	@Override
	public List<?> list(String hql, Object arg, Class cls)
	{
		return this.list(hql, new Object[] { arg }, null, cls);
	}

	@Override
	public List<?> list(String hql, Object[] args, Class cls)
	{
		return this.list(hql, args, null, cls);
	}

	public List<?> list(String hql, Object[] args, Map<String, Object> alias)
	{
		Query query = getSession().createQuery(hql);
		setAliasParameter(query, alias);
		setParameter(query, args);
		return query.list();
	}

	public List<?> list(String hql, Object[] args, Map<String, Object> alias, Class cls)
	{
		Query query = getSession(getSessionFactoryBeanId(cls)).createQuery(hql);
		setAliasParameter(query, alias);
		setParameter(query, args);
		return query.list();
	}

	@SuppressWarnings("rawtypes")
	private void setAliasParameter(Query query, Map<String, Object> alias)
	{
		if (alias != null)
		{
			Set<String> keys = alias.keySet();
			for (String key : keys)
			{
				Object val = alias.get(key);
				if (val instanceof Collection)
				{
					// 查询条件是列表
					query.setParameterList(key, (Collection) val);
				}
				else
				{
					query.setParameter(key, val);
				}
			}
		}
	}

	private void setParameter(Query query, Object[] args)
	{
		if (args != null && args.length > 0)
		{
			int index = 0;
			for (Object arg : args)
			{
				query.setParameter(index++, arg);
			}
		}
	}

	public List<?> listByAlias(String hql, Map<String, Object> alias, Class cls)
	{
		return this.list(hql, null, alias, cls);
	}

	public Pager find(String hql, Object[] args, Class cls)
	{
		return this.find(hql, args, cls);
	}

	public Pager find(String hql, Object arg, Class cls)
	{
		return this.find(hql, new Object[] { arg }, cls);
	}

	public Pager find(String hql, Class cls)
	{
		return this.find(hql, cls);
	}

	// @SuppressWarnings("rawtypes")
	// private void setPagers(Query query, Pager pages) {
	// Integer pageSize = SystemContext.getPageSize();
	// Integer pageOffset = SystemContext.getPageOffset();
	// if (pageOffset == null || pageOffset < 0)
	// pageOffset = 0;
	// if (pageSize == null || pageSize < 0)
	// pageSize = 15;
	// pages.setOffset(pageOffset);
	// pages.setSize(pageSize);
	// query.setFirstResult(pageOffset).setMaxResults(pageSize);
	// }

	private String getCountHql(String hql, boolean isHql, Class cls)
	{
		String e = hql.substring(hql.indexOf("from"));
		String c = "select count(*) " + e;
		if (isHql)
		{
			c.replaceAll("fetch", "");
		}
		return c;
	}

	public Pager find(String hql, Object[] args, Map<String, Object> alias, Class cls)
	{
		// hql = initSort(hql);
		String cq = getCountHql(hql, true, cls);
		Query cquery = getSession(getSessionFactoryBeanId(cls)).createQuery(cq);
		Query query = getSession(getSessionFactoryBeanId(cls)).createQuery(hql);
		// 设置别名参数
		setAliasParameter(query, alias);
		setAliasParameter(cquery, alias);
		// 设置参数
		setParameter(query, args);
		setParameter(cquery, args);
		Pager pages = new Pager();
		// setPagers(query, pages);
		List<?> datas = query.list();
		pages.setDatas(datas);
		long total = (Long) cquery.uniqueResult();
		pages.setTotal(total);
		return pages;
	}

	public Pager findByAlias(String hql, Map<String, Object> alias, Class cls)
	{
		return this.find(hql, null, alias, cls);
	}

	public Object queryObject(String hql, Object[] args, Class cls)
	{
		return queryObject(hql, args, null, cls);
	}

	public Object queryObject(String hql, Object arg, Class cls)
	{
		return this.queryObject(hql, new Object[] { arg }, null, cls);
	}

	public Object queryObject(String hql, Class cls)
	{
		return queryObject(hql, null, null, cls);
	}

	public Object queryObject(String hql, Object[] args, Map<String, Object> alias, Class cls)
	{
		Query query = getSession(getSessionFactoryBeanId(cls)).createQuery(hql);
		setAliasParameter(query, alias);
		setParameter(query, args);
		return query.uniqueResult();
	}

	public void updateByHql(String hql, Object[] args)
	{
		Query query = getSession().createQuery(hql);
		setParameter(query, args);
		query.executeUpdate();
	}

	public void updateByHql(String hql, Object arg)
	{
		this.updateByHql(hql, new Object[] { arg });
	}

	public void updateByHql(String hql)
	{
		this.updateByHql(hql, null);
	}

	public List<?> listBySql(String sql)
	{
		// SQLQuery sq = getSession().createSQLQuery(sql);

		return listBySql(sql, null);
	}

	public List<?> listBySql(String sql, Object[] args)
	{
		SQLQuery sq = getSession().createSQLQuery(sql);
		if (args != null)
		{
			setParameter(sq, args);
		}
		// System.out.println("SystemContent可以传递到DAO,业务编号："+SystemContent.getTransactionId());
		return sq.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP).list();
	}

	public <N extends Object> List<N> listBySql(String sql, Object[] args, Class<?> clz, boolean hasEntity)
	{
		return this.listBySql(sql, args, null, clz, hasEntity);
	}

	public <N extends Object> List<N> listBySql(String sql, Object arg, Class<?> clz, boolean hasEntity)
	{
		return this.listBySql(sql, new Object[] { arg }, clz, hasEntity);
	}

	public <N extends Object> List<N> listBySql(String sql, Class<?> clz, boolean hasEntity)
	{
		return this.listBySql(sql, null, clz, hasEntity);
	}

	public <N extends Object> List<N> listBySql(String sql, Object[] args, Map<String, Object> alias, Class<?> clz, boolean hasEntity)
	{
		// sql = initSort(sql);
		SQLQuery sq = getSession().createSQLQuery(sql);
		setAliasParameter(sq, alias);
		setParameter(sq, args);
		if (hasEntity)
		{
			sq.addEntity(clz);
		}
		else
		{
			sq.setResultTransformer(Transformers.aliasToBean(clz));
		}
		return sq.list();
	}

	public <N extends Object> List<N> listByAliasSql(String sql, Map<String, Object> alias, Class<?> clz, boolean hasEntity)
	{
		return this.listBySql(sql, null, alias, clz, hasEntity);
	}

	// public Pager findBySql(String sql, Object[] args, Class<?> clz,
	// boolean hasEntity) {
	// return this.findBySql(sql, args, null, clz, hasEntity);
	// }
	//
	// public Pager findBySql(String sql, Object arg, Class<?> clz,
	// boolean hasEntity) {
	// return this.findBySql(sql, new Object[] { arg }, clz, hasEntity);
	// }
	//
	// public Pager findBySql(String sql, Class<?> clz, boolean hasEntity) {
	// return this.findBySql(sql, null, clz, hasEntity);
	// }

	// public Pager findBySql(String sql, Object[] args,
	// Map<String, Object> alias, Class<?> clz, boolean hasEntity) {
	// // sql = initSort(sql);
	// String cq = getCountHql(sql, false);
	// SQLQuery sq = getSession().createSQLQuery(sql);
	// SQLQuery cquery = getSession().createSQLQuery(cq);
	// setAliasParameter(sq, alias);
	// setAliasParameter(cquery, alias);
	// setParameter(sq, args);
	// setParameter(cquery, args);
	// Pager pages = new Pager();
	// // setPagers(sq, pages);
	// if (hasEntity) {
	// sq.addEntity(clz);
	// } else {
	// sq.setResultTransformer(Transformers.aliasToBean(clz));
	// }
	// List<?> datas = sq.list();
	// pages.setDatas(datas);
	// long total = ((BigInteger) cquery.uniqueResult()).longValue();
	// pages.setTotal(total);
	// return pages;
	// }

	// public Pager findBySql(String sql, Object[] args,
	// Map<String, Object> alias, Class<?> clz, boolean hasEntity) {
	// // sql = initSort(sql);
	// String cq = getCountHql(sql, false);
	// SQLQuery sq = getSession().createSQLQuery(sql);
	// SQLQuery cquery = getSession().createSQLQuery(cq);
	// setAliasParameter(sq, alias);
	// setAliasParameter(cquery, alias);
	// setParameter(sq, args);
	// setParameter(cquery, args);
	// Pager pages = new Pager();
	// // setPagers(sq, pages);
	// if (hasEntity) {
	// sq.addEntity(clz);
	// } else {
	// sq.setResultTransformer(Transformers.aliasToBean(clz));
	// }
	// List<?> datas = sq.list();
	// pages.setDatas(datas);
	// long total = ((BigInteger) cquery.uniqueResult()).longValue();
	// pages.setTotal(total);
	// return pages;
	// }

	// public Pager findByAliasSql(String sql, Map<String, Object> alias,
	// Class<?> clz, boolean hasEntity) {
	// return this.findBySql(sql, null, alias, clz, hasEntity);
	// }

	public Object queryObjectByAlias(String hql, Map<String, Object> alias, Class cls)
	{
		return this.queryObject(hql, null, alias, cls);
	}

	@Override
	public void delete(Object obj)
	{
		// TODO Auto-generated method stub
		getSession().delete(obj);
	}

	@Override
	public Object load(Object obj)
	{
		// TODO Auto-generated method stub
		return getSession().load(getClz(), (Serializable) obj);
	}

	@Override
	public void update(Object obj)
	{
		// TODO Auto-generated method stub
		getSession().update(obj);
	}

	@Override
	public <T> T get(Class cls, Serializable arg1)
	{
		// TODO Auto-generated method stub
		return (T) getSession(getSessionFactoryBeanId(cls)).get(cls, arg1);
	}

	@SuppressWarnings("unused")
	public String getSessionFactoryBeanId(Object obj)
	{
		if (obj == null)
		{
			return null;
		}

		Annotation a = null;
		if (obj instanceof Class)
		{
			a = ((Class) obj).getAnnotation(MultipleSessionFactoryAnnotation.class);
			// a=obj.getClass().getAnnotation((Class) obj);
		}
		else
		{
			a = obj.getClass().getAnnotation(MultipleSessionFactoryAnnotation.class);
		}

		if (a == null)
		{
			return PropertiesUtil.getProperty("DEFAULT_SESSION_FACTORY_BEAN_ID");
		}
		else
		{

			try
			{
				Method m = a.getClass().getDeclaredMethod("sessionFactoryBeanId", null);
				return m.invoke(a, null).toString();
			}
			catch (Exception e)
			{
				// TODO Auto-generated catch block
				throw new UserException(e);
			}
		}

	}

	@Override
	public List findByHqlSize(int size, String hql, Map map)
	{
		Query query = this.getSession().createQuery(hql);

		Iterator<?> it = map.keySet().iterator();
		while (it.hasNext())
		{
			String key = (String) it.next();

			if (map.get(key) instanceof List)
			{
				query.setParameterList(key, (List) map.get(key));
			}
			else
			{
				query.setParameter(key, map.get(key));
			}
		}

		List<?> list = query.setFirstResult(1).setMaxResults(size).list();

		return list;
	}

	@Override
	public Pager findPagerByHql(Pager pager, String hql, Map map)
	{
		String hqlCount = "";
		if (hql.toUpperCase().startsWith("SELECT "))
		{
			int i = hql.toUpperCase().indexOf(" FROM ");
			hqlCount = "SELECT COUNT(*) FROM " + hql.substring(i + 6);
		}
		else if (hql.toUpperCase().startsWith("FROM "))
		{
			hqlCount = "SELECT COUNT(*) " + hql;
		}
		else if (hql.toUpperCase().startsWith(" FROM "))
		{
			hqlCount = "SELECT COUNT(*) " + hql;
		}
		Query query = this.getSession().createQuery(hql);
		Query query2 = this.getSession().createQuery(hqlCount);

		Iterator<?> it = map.keySet().iterator();
		while (it.hasNext())
		{
			String key = (String) it.next();

			if (map.get(key) instanceof List)
			{
				query.setParameterList(key, (List) map.get(key));
				query2.setParameterList(key, (List) map.get(key));
			}
			else
			{
				query.setParameter(key, map.get(key));
				query2.setParameter(key, map.get(key));
			}
		}

		List<?> list = query.setFirstResult(pager.getOffset()).setMaxResults(pager.getSize()).list();

		pager.setDatas(list);
		pager.setTotal(((Long) query2.iterate().next()).intValue());
		return pager;
	}

	public List<?> listByAlias(String hql, Map<String, Object> alias)
	{
		return this.list(hql, null, alias);
	}

	@Override
	public Pager findPagerByHql(Pager pager, String hql, Map map, Class cls)
	{
		String hqlCount = "";
		if (hql.toUpperCase().startsWith("SELECT "))
		{
			int i = hql.toUpperCase().indexOf(" FROM ");
			hqlCount = "SELECT COUNT(*) FROM " + hql.substring(i + 6);
		}
		else if (hql.toUpperCase().startsWith("FROM "))
		{
			hqlCount = "SELECT COUNT(*) " + hql;
		}
		else if (hql.toUpperCase().startsWith(" FROM "))
		{
			hqlCount = "SELECT COUNT(*) " + hql;
		}
		Query query = getSession(getSessionFactoryBeanId(cls)).createQuery(hql);
		Query query2 = getSession(getSessionFactoryBeanId(cls)).createQuery(hqlCount);

		Iterator<?> it = map.keySet().iterator();
		while (it.hasNext())
		{
			String key = (String) it.next();

			if (map.get(key) instanceof List)
			{
				query.setParameterList(key, (List) map.get(key));
				query2.setParameterList(key, (List) map.get(key));
			}
			else
			{
				query.setParameter(key, map.get(key));
				query2.setParameter(key, map.get(key));
			}
		}

		List<?> list = query.setFirstResult(pager.getOffset()).setMaxResults(pager.getSize()).list();

		pager.setDatas(list);
		pager.setTotal(((Long) query2.iterate().next()).intValue());
		return pager;
	}

	@Override
	public Object add(Object obj, Class cls)
	{
		getSession(getSessionFactoryBeanId(cls)).save(obj);
		return obj;
	}

	@Override
	public void delete(Object obj, Class cls)
	{
		// TODO Auto-generated method stub
		getSession(getSessionFactoryBeanId(cls)).delete(obj);
	}

	@Override
	public void update(Object obj, Class cls)
	{
		// TODO Auto-generated method stub
		getSession(getSessionFactoryBeanId(cls)).update(obj);
	}

	@Override
	public String getSeq(String seqName)
	{
		Query query = getSession().createSQLQuery("select " + seqName + ".Nextval operationId from dual");
		List list = query.list();
		return list.get(0).toString();
	}

	@Override
	public List findByTableName(String tableName, String colNames)
	{
		Query query = getSession("oracleFactory").createQuery(" select new Map(" + colNames + ") from " + tableName);
		// Query query =
		// getSession("sessionFactory").createQuery(" select new Map("+colNames+") from "
		// + tableName);
		return query.list();
	}
}
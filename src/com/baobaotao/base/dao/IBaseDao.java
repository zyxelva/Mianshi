package com.baobaotao.base.dao;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import com.baobaotao.base.entity.Pager;

public interface IBaseDao {

	public Object add(Object obj);

	public Object add(Object obj, Class cls);

	public void delete(Object obj);

	public void delete(Object obj, Class cls);

	public void update(Object obj);

	public void update(Object obj, Class cls);

	public Object load(Object obj);

	public List<?> list(String hql);

	public List<?> list(String hql,Class cls);

	public List<?> list(String hql, Object arg, Class cls);

	public List<?> list(String hql, Object[] args, Class cls);

	public <T> T get(Class clz, Serializable arg1);

	public List findByHqlSize(int size , String hql, Map map);

	public Pager findPagerByHql(Pager pager, String hql, Map map);

	public Pager findPagerByHql(Pager pager, String hql, Map map, Class clz);

	public String getSeq(String seqName);

	public List findByTableName(String tableName,String colNames);
}
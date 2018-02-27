package com.baobaotao.common.json;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.baobaotao.base.entity.DynamicPropertyClass;
import com.baobaotao.base.entity.IBaseEntity;
import com.baobaotao.base.entity.Pager;
import com.baobaotao.exception.UserException;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

public class JsonUtil {
	/**
	 * 一个Map对象转化成JSON字符串 Map里面只支持，MAP,LIST,CLASS 三种对象
	 *
	 * @param map
	 * @return
	 */
	public static String dataToJsonString(Map<?, ?> map) {
		if (map == null)
		{
			return null;
		}
		StringBuffer sb = new StringBuffer();

		Iterator<?> it = map.keySet().iterator();
		while (it.hasNext()) {
			String key = (String) it.next();
			Object val = map.get(key);
			sb.append(",\"");
			sb.append(key);
			sb.append("\":");
			if("pager".equals(key)){
				sb.append(val);
			} else {
				sb.append(objectToJsonString(val));
			}
		}
		if (",".equals(sb.subSequence(0, 1))) {
			return "{" + sb.substring(1) + "}";
		} else {
			return "{" + sb.toString() + "}";
		}
	}

	/**
	 * 一个Pager对象转化成JSON字符串
	 *
	 * @param map
	 * @return
	 */
	public static String pagerToJsonString(Pager pager) {
		if (pager == null)
		{
			return null;
		}
		StringBuffer sb = new StringBuffer();
		try {
			Field[] field = pager.getClass().getDeclaredFields();

			for (int j = 0; j < field.length; j++) {
				String type = field[j].getGenericType().toString();
				String name = field[j].getName();
				sb.append(",\"");
				sb.append(name);
				sb.append("\":");
				if (type.equals("java.util.List<?>")) {
					name = name.substring(0, 1).toUpperCase()
							+ name.substring(1);
					Method m = pager.getClass().getMethod("get" + name);
					Object val = m.invoke(pager);
					JsonConfig jsonConfig = new JsonConfig();
					jsonConfig.registerJsonValueProcessor(Date.class,
							new JsonDateValueProcessor());
					sb.append(JSONArray.fromObject(val, jsonConfig));
				} else {
					name = name.substring(0, 1).toUpperCase()
							+ name.substring(1);
					Method m = pager.getClass().getMethod("get" + name);
					Object val = m.invoke(pager);
					sb.append(val);
				}
			}

		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		if (",".equals(sb.subSequence(0, 1))) {
			return "{" + sb.substring(1) + "}";
		} else {
			return "{" + sb.toString() + "}";
		}
	}

	private static String objectToJsonString(Object o) {
		if (o == null) {
			return "\"\"";
		}
		if (o instanceof IBaseEntity) {
			return classToJsonString(o);
		} else if (o instanceof Map) {
			return mapToJsonString((Map<?, ?>) o);
		} else if (o instanceof List) {
			return listToJsonString((List<?>) o);
		}
		return "\"" + o.toString() + "\"";
	}

	private static String listToJsonString(List<?> list) {
		if (list == null)
		{
			return null;
		}
		StringBuffer sb = new StringBuffer();

		for (Object li : list) {
			sb.append(",");
			if (li instanceof DynamicPropertyClass) {
				sb.append(li.toString());
			} else {
				sb.append(objectToJsonString(li));
			}
		}
		if (",".equals(sb.subSequence(0, 1))) {
			return "[" + sb.substring(1) + "]";
		} else {
			return "[" + sb.toString() + "]";
		}
	}

	private static String mapToJsonString(Map<?, ?> map) {
		if (map == null) {
			return null;
		}
		Iterator<?> it = map.keySet().iterator();
		StringBuffer sb = new StringBuffer();
		while (it.hasNext()) {
			String key = (String) it.next();
			Object val = map.get(key);
			sb.append(",\"" + key + "\":\"" + val + "\"");
		}
		if (",".equals(sb.subSequence(0, 1))) {
			return "{" + sb.substring(1) + "}";
		} else {
			return "{" + sb.toString() + "}";
		}
	}

	/**
	 *
	 * @param o
	 * @return
	 */
	private static String classToJsonString(Object o) {
		if (o == null) {
			return null;
		}
		String className = o.getClass().getName();
		Class<? extends Object> cls = o.getClass();
		StringBuffer sb = new StringBuffer();

		for (Field field : o.getClass().getDeclaredFields()) {

			PropertyDescriptor pd = null;
			Object val = null;
			String fieldName = field.getName();
			try {
				pd = new PropertyDescriptor(fieldName, cls);
				if (pd != null) {
					Method method = pd.getReadMethod();
					val = method.invoke(o, new Object[] {});
					// System.out.println(field.getType());;
					// System.out.println(fieldName+":"+val);
					sb.append(",\"" + fieldName + "\":\"" + val + "\"");
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		if (",".equals(sb.subSequence(0, 1))) {
			return "{\"className\":\"" + className + "\",\"data\":{"
					+ sb.substring(1) + "}}";
		} else {
			return "{\"className\":\"" + className + "\",\"data\":{"
					+ sb.toString() + "}}";
		}

	}

	/**
	 * 递归遍历一个JSONObjet对象
	 *
	 * @param obj
	 * @return
	 * @throws
	 * @throws Exception
	 */
	public static Object recursion(Object obj) throws UserException {
		if (obj instanceof JSONArray) {
			JSONArray jArray = (JSONArray) obj;
			List<Object> list = new ArrayList<Object>();
			for (Object o : jArray) {
				list.add(recursion(o));
			}
			return list;
			// JSONObject o = jArray.getJSONObject(0);
			// JSONObject.toBean(o.getJSONObject("data"),
			// Class.forName("java.util.Map"));
			// System.out.println(o.getJSONObject("data"));
			// System.out.println(JSONObject.toBean(o.getJSONObject("data"),
			// Class.forName("java.util.Map")));

		} else if (obj instanceof JSONObject) {

			if (((JSONObject) obj).has("className")
					&& ((JSONObject) obj).has("data")) {
				String className = ((JSONObject) obj).getString("className");
				JSONObject data = ((JSONObject) obj).getJSONObject("data");

				// JsonConfig cfg=new JsonConfig();
				// cfg.setCycleDetectionStrategy(CycleDetectionStrategy.LENIENT);
				// cfg.setExcludes(new String[]{"userId"});

				try {
					obj = JSONObject
							.toBean(data, Class.forName(className)/* ,cfg */);
				} catch (Exception ex) {
					throw new UserException(ex);
				}

			}
			return obj;
		} else {
			return obj;
		}
	}

	/**
	 * 将json格式的字符串解析成Map对象 <li>
	 * json格式：{"name":"admin","retries":"3fff","testname":"ddd","testretries":
	 * "fffffffff"}
	 */
	public static HashMap<String, String> getMapFromJsonString(Object object) {
		HashMap<String, String> data = new HashMap<String, String>();
		// 将json字符串转换成jsonObject
		JSONObject jsonObject = JSONObject.fromObject(object);
		Iterator it = jsonObject.keys();
		// 遍历jsonObject数据，添加到Map对象
		while (it.hasNext()) {
			String key = String.valueOf(it.next());
			//String value = (String) jsonObject.get(key);
			String value = jsonObject.get(key).toString();
			data.put(key, value);
		}
		return data;
	}

}
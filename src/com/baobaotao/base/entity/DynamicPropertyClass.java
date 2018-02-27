package com.baobaotao.base.entity;


import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;



public class DynamicPropertyClass {
	private Map<String, Object> data = new HashMap<String, Object>();

	public void put(String key, Object value) {
		this.data.put(key, value);
	}

	public Object get(String key) {
		return this.data.get(key);
	}

	public int size() {
		return this.data.size();
	}

	public static void main(String[] aregs) {
		DynamicPropertyClass dpc = new DynamicPropertyClass();

		dpc.put("name", "admin");
		dpc.put("age", 18);
		dpc.put("fs", 98.5);
		dpc.put("sr", new Date());

		System.out.println(dpc.toString());
	}
	@Override
	public String toString(){
		StringBuffer sb = new StringBuffer();
		for (Map.Entry<String, Object> entry : this.data.entrySet()) {

			sb.append(",\"");
			sb.append(entry.getKey());
			sb.append("\":");
			sb.append(objectToJsonString(entry.getValue()));

		}
		if (",".equals(sb.subSequence(0, 1))) {
			return "{" + sb.substring(1) + "}";
		} else {
			return "{" + sb.toString() + "}";
		}
	}
	private static String listToJsonString(List<?> list) {
		if (list == null)
		{
			return null;
		}
		StringBuffer sb = new StringBuffer();

		for (Object li : list) {
			sb.append(",");
			sb.append(objectToJsonString(li));
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

	private static String objectToJsonString(Object o) {
		if (o == null) {
			return "\"\"";
		}
		if (o instanceof List) {
			return listToJsonString((List<?>) o);
		} else if (o instanceof Map) {
			return mapToJsonString((Map<?, ?>) o);
		}else if(o instanceof DynamicPropertyClass){
			return o.toString();
		} else if (o instanceof java.util.Date) {
			return "\""
					+ (new java.text.SimpleDateFormat("yyyy-MM-dd hh:mm:ss"))
							.format(new Date()) + "\"";
		} else {
			return "\"" + o.toString() + "\"";
		}

	}

	public Set<String> keySet() {
		// TODO Auto-generated method stub
		return data.keySet();
	}

	public void remove(String key) {
		// TODO Auto-generated method stub
		data.remove(key);
	}
}

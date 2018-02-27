package com.baobaotao.web.model;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import com.baobaotao.common.json.JsonUtil;
import com.baobaotao.exception.UserException;

import net.sf.json.JSONObject;

public class RequestObject {
	private String serviceName;
	public String getServiceName() {
		return serviceName;
	}

	public String getMethod() {
		return method;
	}

	private String method;

	private Map<String, Object> data = new HashMap<String,Object>();;

	@SuppressWarnings("unchecked")
	public RequestObject(String jsonStr) throws UserException {
		JSONObject requestData;

		if(jsonStr==null||"".equals(jsonStr)){
			throw new UserException("请求数据为空！");
		}
		requestData = JSONObject.fromObject(jsonStr);

		Iterator<?> it = requestData.keys();
		while (it.hasNext()) {
			String key = (String) it.next();
			Object val = requestData.get(key);
			val=JsonUtil.recursion(val);
			//System.out.println("key:"+key+" value:"+val);
			data.put(key, val);
		}

		//data = (Map<String,Object>) JsonUtil.recursion(requestData);

		if (data.containsKey("serviceName")) {
			serviceName = data.get("serviceName").toString();
		}
		if (data.containsKey("method")) {
			method = data.get("method").toString();
		}

		if(serviceName==null||"".equals(serviceName)){
			throw new UserException("serviceName数据为空！");
		}

		if(method==null||"".equals(method)){
			throw new UserException("Method数据为空！");
		}
	}

	public Object get(String key) {
		if (containsKey(key)) {
			return data.get(key);
		} else {
			return null;
		}
	}

	public boolean containsKey(String key) {
		return data.containsKey(key);

	}

	public Set<String> keySet() {
		return data.keySet();
	}

	public Map<String, ?> getAll() {
		return this.data;
	}

}

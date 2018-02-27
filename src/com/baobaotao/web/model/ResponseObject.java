package com.baobaotao.web.model;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import com.baobaotao.common.json.JsonUtil;

public class ResponseObject {
	private int resultCode = 1;
	private String resultMsg = "";
	private String resultMsgType = "";

	private Map<String, Object> data = new HashMap<String, Object>();

	public int getResultCode() {
		return resultCode;
	}

	public void setResultCode(int resultCode) {
		this.resultCode = resultCode;
	}

	public String getResultMsg() {
		return resultMsg;
	}

	public void setResultMsg(String resultMsg) {
		this.resultMsg = resultMsg;
	}

	public String getResultMsgType() {
		return resultMsgType;
	}

	public void setResultMsgType(String resultMsgType) {
		this.resultMsgType = resultMsgType;
	}

	public void put(String key, Object val) {
		data.put(key, val);
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

	@Override
	public String toString() {
		data.put("resultCode", resultCode);
		data.put("resultMsg", resultMsg);
		data.put("resultMsgType", resultMsgType);
		//JSONObject.fromObject(data);
		//return JSONObject.fromObject(data).toString();
		return JsonUtil.dataToJsonString(data);
	}

}

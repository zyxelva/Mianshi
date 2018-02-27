package com.baobaotao.web.action;

import java.io.InputStream;

public interface IDwrService {
	public String request(String str);
	public String upload(InputStream is,String fileName);
}

package com.baobaotao.web.action;

import java.io.File;
import java.io.InputStream;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.UUID;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.directwebremoting.WebContext;
import org.directwebremoting.WebContextFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.baobaotao.common.utils.SystemContent;
import com.baobaotao.web.model.RequestObject;
import com.baobaotao.web.model.ResponseObject;

public class DwrService implements IDwrService
{
	private final static ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
	private static final Log logger = LogFactory.getLog("INFO");

	@Override
	public String request(String str)
	{
		Class<?> clsMake = null;
		Object ibs = null;
		RequestObject requestObj = null;
		ResponseObject ro = new ResponseObject();
		try
		{

			requestObj = new RequestObject(str);

			ibs = applicationContext.getBean(requestObj.getServiceName());
			clsMake = ibs.getClass();
			Object languageSession = WebContextFactory.get().getSession().getAttribute("language");
			if (languageSession != null)
			{
				SystemContent.setLanguage(languageSession.toString());
			}
			else
			{
				SystemContent.setLanguage("zh_CN");
			}
			SystemContent.setRequest(WebContextFactory.get().getHttpServletRequest());
			SystemContent.setResponse(WebContextFactory.get().getHttpServletResponse());

			logger.info("调用 serviceName : " + requestObj.getServiceName() + " ; methodName : " + requestObj.getMethod());
			System.out.println("serviceName:" + requestObj.getServiceName() + " ; methodName:" + requestObj.getMethod());
			Method m = clsMake.getMethod(requestObj.getMethod(), com.baobaotao.web.model.RequestObject.class);
			if (m != null)
			{
				ro = (ResponseObject) m.invoke(ibs, requestObj);
				System.out.println("最终返回：" + ro);
				return ro.toString();
			}

			ro.setResultCode(-1);
			ro.setResultMsg("没有找到对应的Method方法");
			return ro.toString();
		}
		catch (Exception e)
		{
			e.printStackTrace();
			ro.setResultCode(-1);
			ro.setResultMsg(e.getMessage());
		}
		// newInstance()创建此 Class 对象所表示的类的一个新实例。

		return ro.toString();
	}

	@Override
	public String upload(InputStream is, String fileName)
	{
		ResponseObject ro = new ResponseObject();
		try
		{
			// 获取相应的ServletAPI
			WebContext wc = WebContextFactory.get();
			// 对应需要在webapp目录下创建upload文件夹
			String realPath = wc.getSession().getServletContext().getRealPath("/upload");
			// String fn = FilenameUtils.getName(fileName);
			String extName = FilenameUtils.getExtension(fileName);
			String fn = UUID.randomUUID().toString().replace("-", "") + "." + extName;
			File f = new File(realPath + "/" + fn);
			// 最终储存文件位置/dwr-hello/src/main/webapp/upload/xxx.type
			// 以下方法可以直接将输入流转换为文件

			FileUtils.copyInputStreamToFile(is, f);
			HashMap<String, String> data = new HashMap<String, String>();
			data.put("fileName", fn);

			ro.put(fileName, fn);

		}
		catch (Exception ex)
		{
			ex.printStackTrace();
			ro.setResultCode(-1);
			ro.setResultMsg(ex.getMessage());
			// return ro.toString();
		}
		System.out.println("最终返回：" + ro);
		return ro.toString();
	}

}

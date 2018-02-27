package com.baobaotao.common.utils;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.apache.log4j.Logger;

@SuppressWarnings("null")
public class PropertiesUtil {
	private static Logger log = Logger.getLogger(PropertiesUtil.class);

	public static final String PROPERTIES_TYPE_SYSTEM = "SYSTEM";
	private static Map<String, Properties> propertiesResource = new HashMap<String, Properties>();

	static {
		Properties prop = null;
		try {
			prop=new Properties();
			prop.load(PropertiesUtil.class
					.getResourceAsStream("/sysconfig.properties"));

			propertiesResource.put(PROPERTIES_TYPE_SYSTEM, prop);
			//System.out.println(propertiesResource.get(PROPERTIES_TYPE_SYSTEM));
		} catch (Exception e) {
			log.error(e);
			e.printStackTrace();
		}
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println(getProperty("DEFAULT_LANGUAGE"));
		// Class.getClassLoader.getResourceAsStream("/")
		// System.out.println(PropertiesUtil.class.getResource("/sysconfig.properties"));
	}

	public static String getProperty(String key) {
		return propertiesResource.get(PROPERTIES_TYPE_SYSTEM).getProperty(key);
	}

	public static String getProperty(String propertiesType, String key) {
		return propertiesResource.get(propertiesType).getProperty(key);
	}

}

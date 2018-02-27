package com.baobaotao.base.entity;

import java.beans.PropertyDescriptor;
import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

@SuppressWarnings("serial")
public class AbsBaseEntity  implements Serializable ,IBaseEntity {
	@Override
	public String toJsonString() {
		String className = this.getClass().getName();
		Class<? extends AbsBaseEntity> cls = this.getClass();
		StringBuffer sb = new StringBuffer();

		for (Field field : this.getClass().getDeclaredFields()) {

			PropertyDescriptor pd = null;
			Object val = null;
			String fieldName = field.getName();
			try {
				pd = new PropertyDescriptor(fieldName, cls);
				if (pd != null) {
					Method method = pd.getReadMethod();
					val = method.invoke(this, new Object[] {});
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
			return "{\"className\":\"" + className + "\",\"data\":{" + sb.toString()
					+ "}}";
		}
	}

}

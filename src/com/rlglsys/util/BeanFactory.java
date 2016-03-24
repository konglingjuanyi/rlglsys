package com.rlglsys.util;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.lang.StringUtils;

public class BeanFactory {

	// 要调用的共通处理类对象
    private Object HObject;
    // 要调用的共通处理类对象的方法
    private String HMethod;
    // 不需要处理的字段属性
    private Map<String, String> noDoField;

	/**
	 * 重设Bean属性的值
	 * @param bean Bean对象
	 * @param HandlerCls 要调用的共通处理类
	 * @param HandlerMethod 要调用的共通处理类对象的方法
	 * @param noDoFields 不需要处理的字段属性
	 */
	public void reinstallFields(Object bean,
			                           Class<?> HandlerCls,
			                           String HandlerMethod,
			                           Map<String, String> noDoFields) {
        object = bean;
		// 获得Bean对象字段
        beanFields.clear();
        getDeclaredFieldsOfBean(bean.getClass());
        try {
			HObject = HandlerCls.newInstance();
	        HMethod = HandlerMethod;
	        noDoField = noDoFields;
	        // 处理参数Bean中声明的字段
			reinstallFieldValue();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
	        beanFields.clear();
		}
	}

	/**
	 * 重设Bean属性的值
	 * @param obj Bean对象
	 * @param HandlerCls 要调用的共通处理类
	 * @param HandlerMethod 要调用的共通处理类对象的方法
	 */
	public void reinstallFields(Object obj,
			                           Class<?> HandlerCls,
			                           String HandlerMethod){
		reinstallFields(obj, HandlerCls, HandlerMethod, null);
	}

	/**
	 * 要调用的共通处理类对象的方法例子
	 * @param obj 参数必须为Object可变类型
	 */
    public String testmethod(Object... obj) {
	    return obj[0].toString() + "test";
    }

	/**
	 * 重设字段的值
	 * @param fields 要重设字段
	 */
	private void reinstallFieldValue() {
		// 获得要调用的共通处理类对象的方法
		Method HandlerMethod;
		try {
			HandlerMethod = HObject.getClass().getMethod(HMethod, Object[].class);
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			return;
		}
		for (int i = 0; i < beanFields.size(); i++) {
			for(Field f : beanFields.get(i)) {
				if (noDoField != null && noDoField.containsKey(f.getName())) {
					continue;
				}
				if (f.getName().endsWith("VersionUID")) {
					continue;
				}
				try {
					PropertyDescriptor pd = new PropertyDescriptor(f.getName(), object.getClass());
					// 获得读方法
					Method rM = pd.getReadMethod();
					// 类型是String以外的字段不做处理
					if (String.class != rM.getReturnType()) {
						continue;
					}
					// 获得当前值
					Object curFieldValue = rM.invoke(object);
					// 获得写方法
					Method wM = pd.getWriteMethod();
					// 调用共同方法获得重设属性的值
					Object tmp = HandlerMethod.invoke(HObject, new Object[]{new Object[]{curFieldValue,f.getType().getName()}});
					// 重设属性的值
					wM.invoke(object, tmp);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
	
	private List<Field[]> beanFields = new ArrayList<Field[]>();

	/**
	 * 复制Bean对象的值
	 * @param beanFrom 元Bean对象
	 * @param beanTo 要复制的bean类
	 */
    public Object copyBeanValue(Object beanFrom, Class<?> beanTo) {
		try {
			object = beanTo.newInstance();
		} catch (Exception e) {
			return null;
		}
        beanFields.clear();
		// 获得元Bean对象字段
		getDeclaredFieldsOfBean(beanFrom.getClass());

		for (int i = 0; i < beanFields.size(); i++) {
			for(Field f : beanFields.get(i)) {
				if (f.getName().endsWith("VersionUID")) {
					continue;
				}
				try {
					PropertyDescriptor pdFrom = new PropertyDescriptor(f.getName(), beanFrom.getClass());
					PropertyDescriptor pdTo = new PropertyDescriptor(f.getName(), beanTo);
					// 获得读方法
					Method rM = pdFrom.getReadMethod();
					// 获得写方法
					Method wM = pdTo.getWriteMethod();
					wM.invoke(object, rM.invoke(beanFrom));
				} catch (Exception e) {
					continue;
				}
			}
		}
        beanFields.clear();
        return object;
    }

	/**
	 * 获得Bean对象字段
	 * @param bean bean类
	 */
    private void getDeclaredFieldsOfBean(Class<?> bean) {
    	Field[] field = bean.getDeclaredFields();
    	beanFields.add(field);
        Class<?> superclass = bean.getSuperclass();
        if (!"java.lang.Object".equals(superclass.getName())) {
        	getDeclaredFieldsOfBean(superclass);
        }
    }
    private Object object;
    private String pref = "";

	/**
	 * 从请求中获取Bean对象
	 * @param request 请求
	 * @param beanCls bean类
	 * @param prefix 请求中参数的前缀(如XXXX.id的时候)
	 */
    public Object getBeanFromRequest(HttpServletRequest request, Class<?> beanCls, String prefix) {
        try {
            object = beanCls.newInstance();
            if (prefix != null && !"".equals(prefix)) {
                pref = prefix + ".";
            }
            beanFields.clear();
    		// 获得Bean对象字段
    		getDeclaredFieldsOfBean(beanCls);
	        // 从请求中获取Bean字段的值
            setBeanValue(request, object, beanFields);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            beanFields.clear();
        }
        return object;
    }

	/**
	 * 从请求中获取Bean父类字段的值
	 * @param request 请求
	 * @param obj Bean对象(有可能是对象中的对象)
	 * @param fields 声明字段
	 */
    private void setBeanValue(HttpServletRequest request, Object obj, List<Field[]> beanField) {
    	for (int i = 0; i < beanField.size(); i++) {
    		for(Field field : beanField.get(i)) {
                try {
                    if ("serialVersionUID".equals(field.getName())) {
                        continue;
                    }
                    // 获得写方法
                    String set = "set" + StringUtils.capitalize(field.getName());
                    Method method = obj.getClass().getMethod(set, field.getType());
                    
                    // 通过set方法给给字段赋值
                    if (String.class == field.getType()){
                        String value = request.getParameter(pref + field.getName());
                        method.invoke(obj, value);
                    } else if (Integer.class == field.getType()){
                        if(request.getParameter(pref + field.getName()) != null &&
                                !"".equals(request.getParameter(pref + field.getName()))){
                            Integer value = Integer.valueOf(request.getParameter(field.getName()));
                            method.invoke(obj, value);
                        }
                    } else if ("int".equals(field.getType().getName())){
                        if(request.getParameter(pref + field.getName()) != null &&
                                !"".equals(request.getParameter(pref + field.getName()))){
                            int value = Integer.valueOf(request.getParameter(field.getName()));
                            method.invoke(obj, value);
                        }
                    } else if (field.getType().isAssignableFrom(Map.class)){
                    } else if (field.getType().isAssignableFrom(List.class)){
                    } else {//这里判断当实体中某个属性为对象类型时，再一次去反射
                    	//获取属性所对应的实体类
                        Class<?> subCls = Class.forName(field.getType().getName());
                        // new一个实体类
                        Object subObj = subCls.newInstance();
                        Field[] fs = subObj.getClass().getDeclaredFields();
                        List<Field[]> tmp = new ArrayList<Field[]>();
                        tmp.add(fs);
                        setBeanValue(request, subObj, tmp);
                        method.invoke(obj, subObj);
                    }
                } catch (Exception e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
    	}
    }
}

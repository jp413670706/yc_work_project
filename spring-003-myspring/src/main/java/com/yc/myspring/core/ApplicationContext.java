package com.yc.myspring.core;

import java.io.InputStream;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import com.yc.myspring.entity.BeanMapping;
import com.yc.myspring.entity.BeanProperty;

public class ApplicationContext {
	private static Map<String, BeanMapping> beanMappings;
	private static Map<String, Object> beans = new HashMap<String, Object>();
	
	public ApplicationContext() {
		parseBeanMapping();
	}
	// 框架配制文件
	static {
		// 1.创建解析对象
		beanMappings = new HashMap<String, BeanMapping>();
		SAXReader reader = new SAXReader();
		LogManager.getLogger().debug("xml解析器对象创建成功....");
		InputStream in = ApplicationContext.class.getClassLoader().getResourceAsStream("spring.xml");
		try {
			Document doc = reader.read(in);
			LogManager.getLogger().debug("xml解析器加载配制创建Document对象成功....");
			List<Element> eles = doc.getRootElement().elements("bean");
			for (Element ele : eles) {
				String id = ele.attribute("id").getValue();
				String className = ele.attribute("class").getValue();
				LogManager.getLogger().debug("读取到bean的属性id:" + id + ", class:" + className);
				List<Element> es = ele.elements("property");
				List<BeanProperty> bps = new ArrayList<BeanProperty>(); // 存放Bean属性
				for (Element e : es) {
					String name = e.attributeValue("name");
					String value = e.attributeValue("value");
					String ref = e.attributeValue("ref");
					LogManager.getLogger().debug("读取到property的属性name:" + name + ", value:" + value + ", ref:" + ref);
					bps.add(new BeanProperty(name, value, ref));
				}
				BeanMapping bm = new BeanMapping(id, className, bps);
				beanMappings.put(id, bm);
			}

		} catch (DocumentException e) {
			LogManager.getLogger().error("xml解析器加载配制创建Document对象失败!!!", e);
		}
	}

	public void parseBeanMapping() {
		for (Map.Entry<String, BeanMapping> bm : beanMappings.entrySet()) {
			createBean(bm.getValue());
		}
		beanMappings = null;
	}

	private Object createBean(BeanMapping beanMapping) {
		if (beans.containsKey(beanMapping.getId())) { // 判断bean是否已经创建此bean
			return beans.get(beanMapping.getId());
		}

		String name = null;
		Object obj = null;
		try {
			Class<?> clazz = Class.forName(beanMapping.getClassName());
			obj = clazz.newInstance();
			LogManager.getLogger().debug("通过全类名创建类 的 类的类对象成功...");

			List<BeanProperty> bps = beanMapping.getBeanPeoperties();
			if (bps.size() != 0) {
				for (BeanProperty bp : bps) {
					name = bp.getName();
					// 注值，通过setXxx方法
					Method getMethod = clazz.getMethod(getGetMethodName(name)); // 取得get方法的方法对象, 为了取到set方法参数数据的类型
					Method setMethod = clazz.getMethod(getSetMethodName(name), getMethod.getReturnType()); // 取得set方法的方法对象, 为了给对象的属性赋值

					String value = bp.getValue(); // 判断属性是否是基本类型 或String类型
					if (value != null) {
						setMethod.invoke(obj, value); // 通过反射调用方法
						continue;
					}

					String ref = bp.getRef();
					if (ref != null) { // 判断是否是引用类型
						Object refObject = beans.get(ref); // 取出bean集合对象中的对象
						if (refObject == null) {
							refObject = createBean(beanMappings.get(ref)); // bean集合对象中的没有此对象重新创建
						}
						setMethod.invoke(obj, refObject); // 通过反射调用方法
					}
				}
			}
		} catch (ClassNotFoundException e) {
			LogManager.getLogger().error("找不类:" + beanMapping.getClassName(), e);
		} catch (InstantiationException e) {
			LogManager.getLogger().error("类:" + beanMapping.getClassName() + "没有空的构造方法!!!", e);
		} catch (IllegalAccessException e) {
			LogManager.getLogger().error("类:" + beanMapping.getClassName() + "构造方法调用失败!!!", e);
		} catch (NoSuchMethodException e) {
			LogManager.getLogger().error("在类中没有找到" + name + "的get/set方法!!!", e);
		} catch (SecurityException e) {
			LogManager.getLogger().error("在类中" + name + "的get/set方法不能调用 !!!", e);
		} catch (Exception e) {
			LogManager.getLogger().error("在类注值失败!!!", e);
		}
		beans.put(beanMapping.getId(), obj);
		return obj;
	}

	// 拼接set方法名
	private String getSetMethodName(String name) {
		return "set" + Character.toUpperCase(name.charAt(0)) + name.substring(1);
	}

	// 拼接set方法名
	private String getGetMethodName(String name) {
		return "get" + Character.toUpperCase(name.charAt(0)) + name.substring(1);
	}

	public Object getBean(String bean) {
		return beans.get(bean);
	}
}
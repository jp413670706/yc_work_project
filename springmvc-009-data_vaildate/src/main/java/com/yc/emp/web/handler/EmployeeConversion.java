package com.yc.emp.web.handler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.yc.emp.dao.DepartmentDao;
import com.yc.emp.entity.Department;
import com.yc.emp.entity.Employee;
/**
 * 自定义类型转换器
 * @author JP
 */

@Component
public class EmployeeConversion implements Converter<String, Employee> {
	@Autowired
	private DepartmentDao departmentDao;
	@Override
	public Employee convert(String empInfo) {
		System.out.println("EmployeeConversion empInfo ==>" + empInfo);
		if (empInfo != null){
			String[] empStrs = empInfo.split(",");
			Department dept = departmentDao.getDepartment(Integer.valueOf(empStrs[3].trim()));
			Employee emp = new Employee(empStrs[0], empStrs[1], empStrs[2],dept);
			System.out.println("EmployeeConversion emp ==>" + emp);
			return emp;
		}
		return null;
	}

}

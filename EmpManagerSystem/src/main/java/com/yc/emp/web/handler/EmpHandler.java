package com.yc.emp.web.handler;

import java.io.PrintWriter;
import java.util.ArrayList;

import org.apache.logging.log4j.LogManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.yc.emp.dao.DepartmentDao;
import com.yc.emp.dao.EmployeeDao;
import com.yc.emp.entity.Employee;

@Controller
@RequestMapping("/emp")
@SessionAttributes("emps")
public class EmpHandler {
	@Autowired
	private EmployeeDao employeeDao;

	@Autowired
	private DepartmentDao departmentDao;

	@ModelAttribute
	public void getModel(ModelMap map) {
		map.put("emps", new ArrayList<Employee>());
	}

	@RequestMapping(value = "/emps", method = RequestMethod.GET)
	public String findAllEmps(ModelMap map) {
		LogManager.getLogger().debug("/emps请求成功到达处理方法中....");
		map.put("emps", employeeDao.getAll()); // 取到所有的员工信息
		return "list";
	}

	@RequestMapping(value = "/emp", method = RequestMethod.GET)
	public String input(ModelMap map) {
		LogManager.getLogger().debug("/input请求成功到达处理方法中....");
		map.put("depts", departmentDao.getDepartments()); // 取到所有的部门信息
		return "input";
	}

	@RequestMapping(value = "/emp", method = RequestMethod.POST)
	public String save(Employee emp, ModelMap map) {
		LogManager.getLogger().debug("/save请求成功到达处理方法中, 请求参数\n\temp ==>" + emp);
		employeeDao.save(emp);
		map.put("emps", employeeDao.getAll());
		return "redirect:/page/list.jsp";
	}

	@RequestMapping(value = "/emp/{id}", method = RequestMethod.DELETE)
	public void del(@PathVariable("id") Integer id, PrintWriter out) {
		LogManager.getLogger().debug("/del请求成功到达处理方法中, 请求参数\n\tid ==>" + id);
		employeeDao.delete(id);
		out.println(true);
		out.flush();
		out.close();
	}

	@RequestMapping(value = "/emp/{id}", method = RequestMethod.GET)
	public String edit(@PathVariable("id") Integer id, ModelMap map) {
		LogManager.getLogger().debug("/edit请求成功到达处理方法中, 请求参数\n\tid ==>" + id);
		map.put("emp", employeeDao.get(id)); // 取到所有的部门信息
		map.put("depts", departmentDao.getDepartments()); // 取到所有的部门信息
		return "edit";
	}

	@RequestMapping(value = "/emp", method = RequestMethod.PUT)
	public String update(Employee emp, ModelMap map) {
		LogManager.getLogger().debug("/update请求成功到达处理方法中, 请求参数\n\temp ==>" + emp);
		employeeDao.save(emp);
		map.put("emps", employeeDao.getAll());
		return "redirect:/page/list.jsp";

	}
}
package com.yc.emp.web.handler;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.apache.logging.log4j.LogManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.yc.emp.dao.DepartmentDao;
import com.yc.emp.dao.EmployeeDao;
import com.yc.emp.entity.Employee;

@Controller
@RequestMapping("/emp")
@SessionAttributes(value={"emps", "emp"})
public class EmpHandler {
	@Autowired
	private EmployeeDao employeeDao;

	@Autowired
	private DepartmentDao departmentDao;

	@ModelAttribute
	public void getModel(ModelMap map) {
		map.put("emps", new ArrayList<Employee>());
		map.put("emp", new Employee());
	}

	
	@RequestMapping(value = "/hello")
	public String hello() {
		System.out.println("EmpHandler ==> hello()");
		return "list";
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
	public String save(@ModelAttribute("emp")@Valid Employee emp, BindingResult bindingResult , ModelMap map) {
		LogManager.getLogger().debug("/save请求成功到达处理方法中, 请求参数\n\temp ==>" + emp);
		//employeeDao.save(emp);
		//System.out.println(bindingResult);
		if(bindingResult.getErrorCount() > 0){
			System.out.println("输入参数有异常....");
			List<ObjectError> errors = bindingResult.getAllErrors();
			for (ObjectError error : errors) {
				System.out.println(error.getCode() + " ==>" + error.getDefaultMessage() + " ==>" );
				//map.put(error.getObjectName(), error.getDefaultMessage());
			}
			return "forward:/page/input.jsp";
		}
		
		map.put("emps", employeeDao.getAll());
		return "redirect:/page/list.jsp";
	}
}
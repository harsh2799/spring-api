package com.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.bean.EmployeeBean;
import com.bean.ResponseBean;
import com.bean.SkillBean;
import com.dao.EmployeeDao;


@RestController
public class EmployeeController {

	@Autowired
	EmployeeDao empDao;
	
	@PostMapping("/addEmployee")
	public ResponseBean<EmployeeBean> addEmployee(@RequestBody EmployeeBean employeeBean){
		ResponseBean<EmployeeBean> rs = new ResponseBean<EmployeeBean>();
		
		int empId = empDao.addEmployee(employeeBean);
		
		employeeBean.setEmployee_id(empId);

		for (SkillBean sb : employeeBean.getSkills()) {
			empDao.addSkillForEmployee(empId, sb.getSkillId());
		}	
		
		rs.setData(employeeBean);
		rs.setMsg("Hit Received");
		rs.setStatus(201);
		return rs;
	}
	
	
	@PutMapping("/updateEmployee")
	public ResponseBean<EmployeeBean> updateEmployee(@RequestBody EmployeeBean employeeBean){
		
		ResponseBean<EmployeeBean> rs = new ResponseBean<>();
		empDao.updateEmployee(employeeBean);
		empDao.deleteSkillForEmployeeId(employeeBean.getEmployee_id());
		for(SkillBean sb : employeeBean.getSkills()) {
			empDao.addSkillForEmployee(employeeBean.getEmployee_id(), sb.getSkillId());
		}
		rs.setData(employeeBean);
		rs.setMsg("Employee Updated");
		rs.setStatus(200);
		return rs;
		
	}

	@GetMapping("/getAllEmployees")
	public ResponseBean<List<EmployeeBean>> getAllEmployees(){
		ResponseBean<List<EmployeeBean>> rs = new ResponseBean<List<EmployeeBean>>();
		List<EmployeeBean> employeeList = empDao.getAllEmployees();
		for(EmployeeBean employee: employeeList) {
			List<SkillBean> skills = empDao.getEmployeeSkills(employee.getEmployee_id());
			employee.setSkills((ArrayList<SkillBean>)skills);
		}
		rs.setData(employeeList);
		rs.setMsg("List Of Employees");
		rs.setStatus(200);
		return rs;
	}
	
	
	@DeleteMapping("/deleteEmployee/{employee_id}")
	public String deleteEmployee(@PathVariable("employee_id") int employee_id) {
		empDao.deleteEmployee(employee_id);
		empDao.deleteSkillForEmployeeId(employee_id);
		return "Employee Deleted";
	}
	

}

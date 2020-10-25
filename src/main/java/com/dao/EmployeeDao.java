package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.bean.EmployeeBean;
import com.bean.SkillBean;

@Repository
public class EmployeeDao {
	
	@Autowired
	JdbcTemplate stmt;
	
	public int addEmployee(EmployeeBean employeeBean) {
		KeyHolder keyHolder = new GeneratedKeyHolder();  //able to hold the keys (can be used to hold composite keys).
//		stmt.update("insert into employees (employee_name, employee_salary) values(?, ?)", employeeBean.getEmployee_name(), employeeBean.getEmployee_salary());
		String insertQ = "insert into employees (employee_name, employee_salary) values(?, ?)";
		stmt.update(new PreparedStatementCreator() {
			
			@Override
			public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
				PreparedStatement pstmt = con.prepareStatement(insertQ, Statement.RETURN_GENERATED_KEYS);
				pstmt.setString(1, employeeBean.getEmployee_name());
				pstmt.setInt(2, employeeBean.getEmployee_salary());
				return pstmt;
			}
		}, keyHolder);
		int employee_id = keyHolder.getKey().intValue();
//		int employee_id = (Integer)keyHolder.getKeys().get("employee_id");
		return employee_id;
	}

	public void addSkillForEmployee(int empId, int skillId) {
		stmt.update("insert into employee_skills(employee_id, skill_id) values(?, ?)", empId, skillId);
	}

	public void updateEmployee(EmployeeBean employeeBean) {
		stmt.update("update employees set employee_name = ?, employee_salary = ? where employee_id = ?",
				employeeBean.getEmployee_name(), employeeBean.getEmployee_salary(), employeeBean.getEmployee_id());
		
	}

	public void deleteSkillForEmployeeId(int employee_id) {
		stmt.update("delete from employee_skills where employee_id = ?", employee_id);
		
	}

	public List<EmployeeBean> getAllEmployees() {
		List<EmployeeBean> employees = stmt.query("select * from employees", BeanPropertyRowMapper.newInstance(EmployeeBean.class));
		
		return employees;
	}

	public List<SkillBean> getEmployeeSkills(int employee_id) {
		List<SkillBean> skills = stmt.query("select s.skill_id, s.skill_name from skills s, employee_skills es where es.skill_id = s.skill_id and es.employee_id = "+ employee_id,BeanPropertyRowMapper.newInstance(SkillBean.class));	
		return skills;
	}

	public void deleteEmployee(int employee_id) {
		stmt.update("delete from employees where employee_id = ?", employee_id);
		
	}
	
	
}

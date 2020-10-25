package com.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.bean.SkillBean;

@Repository
public class SkillDao {
	
	@Autowired 
	JdbcTemplate stmt;

	public void addSkill(SkillBean skill) {
		stmt.update("insert into skills (skill_name) values (?)", skill.getSkillName());
		
	}
	
	public SkillBean deleteSkill(int skill_id) {
		SkillBean skill = null;
		skill = getSkill(skill_id);
		if(skill != null)
		{
			stmt.update("delete from skills where skill_id = ?",  skill_id);
		}
		return skill;
	}

	public SkillBean getSkill(int skill_id) {
		// TODO Auto-generated method stub
		SkillBean skill = null;
		try {
			skill = stmt.queryForObject("select * from skills where skill_id = ?", new Object[] { skill_id }, BeanPropertyRowMapper.newInstance(SkillBean.class));
		}
		catch (Exception e)
		{
			System.out.println("Skill Not Found " + e.getMessage());
		}
		return skill;
	}

	public List<SkillBean> getSkills() {
		// TODO Auto-generated method stub
		List<SkillBean> skills = null;
		skills = stmt.query("select * from skills", new SkillsRowMapper());
		return skills;
	}
	
	class SkillsRowMapper implements RowMapper<SkillBean>{
		public SkillBean mapRow(ResultSet rs, int rowNum) throws SQLException {
			SkillBean skill = new SkillBean();
			skill.setSkillId(Integer.valueOf(rs.getString("skill_id")));
			skill.setSkillName(rs.getString("skill_name"));
			
			return skill;
			
		}
	}

	public SkillBean updateSkill(int skill_id, String skillName) {
		// TODO Auto-generated method stub
		SkillBean sk = null;
		stmt.update("update skills set skill_name = ? where skill_id = ?", skillName, skill_id);
		sk = getSkill(skill_id);
		return sk;
	}

	

	

}

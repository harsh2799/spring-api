package com.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bean.ResponseBean;
import com.bean.SkillBean;
import com.dao.SkillDao;
import java.util.List;

@RestController
public class SkillController {
	
	@Autowired
	SkillDao skillDao;
	
	@PostMapping("/addSkill")
	public ResponseBean<SkillBean> addSkill(SkillBean skill){
		skillDao.addSkill(skill);
		ResponseBean<SkillBean> rs = new ResponseBean<SkillBean>();
		rs.setData(skill);
		rs.setMsg("Skill Added");
		rs.setStatus(201);
		return rs;
	}
	
	@DeleteMapping("/skill/{skillId}")
	public ResponseBean<SkillBean> deleteSkill(@PathVariable("skillId") int skill_id){
		
		SkillBean skill = skillDao.deleteSkill(skill_id);
		ResponseBean<SkillBean> rs = new ResponseBean<SkillBean>();
		if(skill != null) {
			rs.setData(skill);
			rs.setMsg("Skill with ID: " + skill_id + " deleted.");
			rs.setStatus(200);
		}
		else
		{
			rs.setData(skill);
			rs.setMsg("Skill with ID: " + skill_id + " Not Found");
			rs.setStatus(200);
		}
		return rs;
	}
	
	@GetMapping("/skill/{skillId}")
	public ResponseBean<SkillBean> getSkill(@PathVariable ("skillId") int skill_id){
		SkillBean skill = skillDao.getSkill(skill_id);
		ResponseBean<SkillBean> rs = new ResponseBean<SkillBean>();
		if(skill != null) {
			rs.setData(skill);
			rs.setMsg("Skill with ID: " + skill_id + " deleted.");
			rs.setStatus(200);
		}
		else
		{
			rs.setData(skill);
			rs.setMsg("Skill with ID: " + skill_id + " Not Found");
			rs.setStatus(200);
		}
		return rs;
	}
	
	
	@GetMapping("/skills")
	public ResponseBean<List<SkillBean>> getSkills(){
		System.out.println("Here Here");
		ResponseBean<List<SkillBean>> rs = new ResponseBean<List<SkillBean>>();
		rs.setData(skillDao.getSkills());
		rs.setMsg("List Of Skills");
		rs.setStatus(200);
		return rs;
	}
	
	@PutMapping("/skill/{skillId}")
	public ResponseBean<SkillBean> updateSkill(@PathVariable("skillId") int skill_id, String skillName)
	{
		SkillBean skill = skillDao.updateSkill(skill_id, skillName);
		ResponseBean<SkillBean> rs = new ResponseBean<SkillBean>();
		if(skill != null) {
			rs.setData(skill);
			rs.setMsg("Skill with ID: " + skill_id + " Updated.");
			rs.setStatus(200);
		}
		else
		{
			rs.setData(skill);
			rs.setMsg("Skill with ID: " + skill_id + " Not Found");
			rs.setStatus(200);
		}
		return rs;
		
	}
	
	
	
	
}

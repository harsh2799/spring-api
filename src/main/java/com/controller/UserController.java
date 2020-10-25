package com.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bean.ResponseBean;
import com.bean.UserBean;
import com.dao.UserDao;

@RestController
public class UserController {
	
	@Autowired
	UserDao userDao;
	
	@PostMapping("/signup")
	public ResponseBean<UserBean> signup(UserBean user) {
		System.out.println(user.getEmail());
		userDao.insertUser(user);
			
//		return new ResponseEntity<UserBean>(user, null, HttpStatus.CREATED);
		ResponseBean<UserBean> responseBean = new ResponseBean<>();
		responseBean.setData(user);
		responseBean.setMsg("User Created");
		responseBean.setStatus(201);	
		return responseBean;
	}
	
	@GetMapping("/users")
	public ResponseBean<ArrayList<UserBean>> getUsers(){
		ResponseBean<ArrayList<UserBean>> rs = new ResponseBean<ArrayList<UserBean>>();
		rs.setData(userDao.listUsers());
		rs.setMsg("List Of users");
		rs.setStatus(200);
		
		return rs;
	}
	
	@GetMapping("/user/{userId}")
	public ResponseBean<UserBean> getUser(@PathVariable ("userId")int userId){
		ResponseBean<UserBean> rs = new ResponseBean<UserBean>();
		UserBean user = userDao.getUser(userId);
		rs.setData(user);
		if(user != null) {
			rs.setMsg("User  Found");
		}
		else {
			rs.setMsg("User Doesn't Exists...");
		}
		rs.setStatus(200);
		return rs;
	}
	
	@DeleteMapping("/user/{userId}")
	public ResponseBean<UserBean> deleteUser(@PathVariable ("userId")int userId){
		ResponseBean<UserBean> rs = new ResponseBean<UserBean>();
		boolean output = userDao.deleteUser(userId);
		rs.setData(null);
		if(output) {
			rs.setMsg("User with ID: "+ userId +" is Deleted");
		}
		else {
			rs.setMsg("User with ID: "+ userId +" doesn't exist");
		}
		rs.setStatus(200);
		return rs;
	}
	
	@PutMapping("/user")
	public ResponseBean<UserBean> updateUser(UserBean user){
		ResponseBean<UserBean> rs = new ResponseBean<UserBean>();
		userDao.updateUser(user);
		rs.setData(user);
		rs.setMsg("User Updated");
		rs.setStatus(201);
		return rs;
	}
}

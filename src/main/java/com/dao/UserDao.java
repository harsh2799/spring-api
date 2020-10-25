package com.dao;

import java.util.ArrayList;

import org.springframework.stereotype.Repository;

import com.bean.UserBean;

@Repository
public class UserDao {
	
	public static ArrayList<UserBean> users = new ArrayList<>();
	
	public void insertUser(UserBean user) {
		users.add(user);
	}
	
	public ArrayList<UserBean> listUsers(){
		return users;
	}

	public UserBean getUser(int userId) {
		for(UserBean user: users) {
			if(user.getUserId() == userId) {
				return user;
			}
		}
		return null;
	}

	public boolean deleteUser(int userId) {
		for(UserBean user: users) {
			if(user.getUserId() == userId) {
				users.remove(user);
				return true;
			}
		}
		return false;
	}

	public void updateUser(UserBean user) {
		for(UserBean use: users) {
			if(use.getEmail().equals(user.getEmail())) {
				use.setFirstname(user.getFirstname());
				use.setPassword(user.getPassword());
				use.setRoleId(user.getRoleId());
			}
		}

	}

}

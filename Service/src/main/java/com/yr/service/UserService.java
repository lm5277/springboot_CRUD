package com.yr.service;

import com.yr.dao.UserDao;
import com.yr.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
	@Autowired
	private UserDao testDao;
	
	
	/**
	 * 查询
	 * @return
	 */
	public List<User> getUsers()
	{
		return testDao.getUsers();
	}
	
	/**
	 * 添加
	 * @param user
	 */
	public void addUser(User user)
	{
		testDao.addUser(user);
	}
	
	
	/**
	 * 删除
	 * @param id
	 */
	public void deleteUser(User user)
	{
		testDao.deleteUser(user);
	}
	
	/**
	 * 修改
	 * @param user
	 */
	public void updateUser(User user)
	{
		testDao.updateUser(user);
	}
	
	/**
	 * 回响
	 * @param id
	 * @return
	 */
	public User getUserById(int id)
	{
		return testDao.getUserById(id);
	}
}

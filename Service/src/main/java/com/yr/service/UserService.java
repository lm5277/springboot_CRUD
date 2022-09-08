package com.yr.service;

import com.yr.dao.UserDao;
import com.yr.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class UserService {
	@Autowired
	private UserDao testDao;
	
	
	/**
	 * 查询
	 * @return
	 */
	@Transactional
	public List<User> cx()
	{
		return testDao.findAll();
	}
	
	/**
	 * 添加
	 * @param user
	 */
	@Transactional
	public void addUser(User user)
	{
		testDao.save(user);
	}
	
	
	/**
	 * 删除
	 * @param id
	 */
	@Transactional
			public void delete(Integer id)
	{
		System.out.println(id);
		testDao.deleteById(id);
	}
	
	/**
	 * 修改
	 * @param user
	 */
	@Transactional
	public void updateUser(User user)
	{
		testDao.saveAndFlush(user);
	}
	
	/**
	 * 回响
	 * @param id
	 * @return
	 */
	@Transactional
	public User getUserById(int id)
	{
		return testDao.getById(id);
	}
}

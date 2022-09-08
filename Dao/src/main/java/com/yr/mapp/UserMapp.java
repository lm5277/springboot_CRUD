package com.yr.mapp;

import com.yr.entity.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserMapp {
	
	/**
	 * 查询
	 * @return
	 */
	public List<User> getUserList();
	
	
	/**
	 * 添加
	 * @param user
	 */
	public void getinsert(User user);
	
	
	/**
	 * 删除
	 * @param id
	 */
	public void getdelete(User user);
	
	
	/**
	 * 修改
	 * @param user
	 */
	public void getupdate(User user);
	
	
	/**
	 * 回响id
	 */
	public User getByid (int id);

}

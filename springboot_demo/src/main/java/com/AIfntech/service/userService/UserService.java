package com.AIfntech.service.userService;

import java.sql.SQLException;
import java.util.List;

import org.apache.coyote.http11.filters.VoidInputFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.AIfntech.service.dao.UserDao;
import com.AIfntech.service.pojo.Result;
import com.AIfntech.service.pojo.TbCategoryAmt;
import com.AIfntech.service.pojo.User;

@Component
public class UserService {
	
	@Autowired
	private UserDao userDao;
	
	public void register(User user) throws SQLException {
		userDao.addUser(user);
	}
	public void dele(String username) throws SQLException {
		userDao.dele(username);
	}
	public User findByName(User user) throws SQLException {
		return userDao.findByName(user);
	}
	public List<User> findAll(int page,int limit) throws SQLException{
		return userDao.findAll(page,limit);
	}
	public void deleByName(List<String> usernameList) throws SQLException {
		userDao.deleByName(usernameList);
	}
	public void update(String status) throws SQLException {
		userDao.update("1");
	}
	public List<TbCategoryAmt> findCategoryAmt() {
		return userDao.findCategoryAmt();
	}
}

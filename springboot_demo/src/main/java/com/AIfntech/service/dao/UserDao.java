package com.AIfntech.service.dao;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.AIfntech.service.pojo.User;
import com.AIfntech.service.util.AIDBUtil;

@Component
public class UserDao {
	public void addUser(User user) throws SQLException {
		System.out.println(user);
		Connection con = AIDBUtil.getConnection();
		String sql = "insert into user" + " values(?,?,?)";
		if(con==null) {
			System.out.println("连接为null!");
		}
		PreparedStatement ptmt = con.prepareStatement(sql);
		ptmt.setString(1, user.getUsername());
		ptmt.setString(2, user.getPassword());
		ptmt.setString(3, user.getStatus());
		
		ptmt.execute();
	}
	public void dele(String username) throws SQLException {
		System.out.println("username的值为 ："+username);
		Connection con = AIDBUtil.getConnection();
		String sql = "delete from user where user_name=?";
		PreparedStatement ptmt = con.prepareStatement(sql);
		ptmt.setString(1, username);
		ptmt.execute();
	}
	//批量删除
	public void deleByName(List<String> usernameList) throws SQLException {
		Connection con = AIDBUtil.getConnection();
		String sql = "delete from user where user_name=?";
		PreparedStatement ptmt = con.prepareStatement(sql);
		for(int i=0;i<usernameList.size();i++) {
			ptmt.setString(1, usernameList.get(i));
			ptmt.addBatch();
			ptmt.execute();
		}
		ptmt.execute();
	}
	public User findByName(User user) throws SQLException {
		System.out.println(user);
		Connection con = AIDBUtil.getConnection();
		String sql = "select * from user where user_name=?";
		PreparedStatement ptmt = con.prepareStatement(sql);
		ptmt.setString(1, user.getUsername());
		ResultSet rs = ptmt.executeQuery();
		User result = null;
		while(rs.next()){
			 result = new User();
			 result.setUsername(rs.getString("user_name"));
			 result.setPassword(rs.getString("password"));
			 result.setStatus(rs.getString("status"));
		}
		return result;
	}
	
	public List<User> findAll() throws SQLException{
		Connection connection = AIDBUtil.getConnection();
		String sql = "select * from user";
		PreparedStatement ptmt = connection.prepareStatement(sql);
		ResultSet rs = ptmt.executeQuery();
		ArrayList<User> list = new ArrayList<User>();
		while(rs.next()) {
			list.add(new User(rs.getString("user_name"),rs.getString("password"),rs.getString("status")));
		}
		return list;
		
	}
	
	public void update(String status) throws SQLException {
		Connection connection = AIDBUtil.getConnection();
		String sql = "update user set status="+"?"+" where user_name="+"?";
		PreparedStatement ptmt = connection.prepareStatement(sql);
		for(int i=0;i<3;i++) {
			ptmt.setString(1, status);
			ptmt.setString(2, i+"");
			ptmt.addBatch();
			ptmt.execute();
			ptmt.clearBatch();
		}
		
	}
}

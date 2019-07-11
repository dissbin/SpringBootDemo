package com.AIfntech.service.dao;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.PrimitiveIterator.OfDouble;

import org.springframework.stereotype.Component;

import com.AIfntech.service.pojo.TbCategoryAmt;
import com.AIfntech.service.pojo.User;
import com.AIfntech.service.util.AIDBUtil;

@Component
public class UserDao {
	public void addUser(User user) throws SQLException {
		System.out.println(user);
		Connection con = null;
		String sql = null;
		try {
			con = AIDBUtil.getConnection();
			con.setAutoCommit(false);
			sql = "insert into user" + " values(?,?,?,?)";
			if(con==null) {
				System.out.println("连接为null!");
			}
			PreparedStatement ptmt = con.prepareStatement(sql);
			ptmt.setString(1, user.getUsername());
			ptmt.setString(2, user.getPassword());
			ptmt.setString(3, user.getStatus());
			ptmt.setString(4, user.getEmail());
			ptmt.execute();
			con.commit();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			try {
				con.rollback();
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}
	public void dele(String username) throws SQLException {
		Connection con = null;
		String sql = null;
		try {
			System.out.println("username的值为 ："+username);
			con = AIDBUtil.getConnection();
			con.setAutoCommit(false);
			sql = "delete from user where user_name=?";
			PreparedStatement ptmt = con.prepareStatement(sql);
			ptmt.setString(1, username);
			ptmt.execute();
			con.commit();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			try {
				con.rollback();
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}
	//批量删除
	public void deleByName(List<String> usernameList) throws SQLException {
		Connection con = null;
		String sql = null;
		try {
			con = AIDBUtil.getConnection();
			con.setAutoCommit(false);
			sql = "delete from user where user_name=?";
			PreparedStatement ptmt = con.prepareStatement(sql);
			for(int i=0;i<usernameList.size();i++) {
				ptmt.setString(1, usernameList.get(i));
				ptmt.addBatch();
				ptmt.execute();
			}
			ptmt.execute();
			con.commit();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			try {
				con.rollback();
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}
	public User findByName(User user) throws SQLException {
		Connection con = null;
		String sql = null;
		try {
			System.out.println(user);
			con = AIDBUtil.getConnection();
			con.setAutoCommit(false);
			sql = "select * from user where user_name=?";
			PreparedStatement ptmt = con.prepareStatement(sql);
			ptmt.setString(1, user.getUsername());
			ResultSet rs = ptmt.executeQuery();
			con.commit();
			User result = null;
			while(rs.next()){
				 result = new User();
				 result.setUsername(rs.getString("user_name"));
				 result.setPassword(rs.getString("password"));
				 result.setStatus(rs.getString("status"));
				 result.setEmail(rs.getString("email"));
			}
			return result;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			try {
				con.rollback();
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		return null;
	}
	
	public List<User> findAll(int page,int limit) throws SQLException{
		Connection connection = null;
		String sql = null;
		try {
			connection = AIDBUtil.getConnection();
			connection.setAutoCommit(false);
			sql = "select * from user limit " + page + "," + limit;
			PreparedStatement ptmt = connection.prepareStatement(sql);
			ResultSet rs = ptmt.executeQuery();
			connection.commit();
			ArrayList<User> list = new ArrayList<User>();
			while(rs.next()) {
				list.add(new User(rs.getString("user_name"),rs.getString("password"),rs.getString("status"),rs.getString("email")));
			}
			return list;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			try {
				connection.rollback();
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		return null;
		
	}
	
	public void update(String status) throws SQLException {
//		Connection connection = AIDBUtil.getConnection();
//		String sql = "update user set status="+"?"+" where user_name="+"?";
//		PreparedStatement ptmt = connection.prepareStatement(sql);
//		for(int i=0;i<3;i++) {
//			ptmt.setString(1, status);
//			ptmt.setString(2, i+"");
//			ptmt.addBatch();
//			ptmt.execute();
//			ptmt.clearBatch();
//		}
		Connection con = null;
		String sql = null;
		try {
			con = AIDBUtil.getConnection();
			//开启事务
			con.setAutoCommit(false);
			sql = "insert into user values(?,?,?,?)";
			PreparedStatement ptmt = con.prepareStatement(sql);
			for(int i=0;i<10000;i++) {
				ptmt.setString(1, "user"+i+"号");
				ptmt.setString(2, "123");
				ptmt.setString(3, "1");
				ptmt.setString(4, "user"+i+"@163.com");
				ptmt.addBatch();
				if (i%1000 == 0) {
					ptmt.executeBatch();
					ptmt.clearBatch();
				}	
				ptmt.executeBatch();
				//提交事务
				con.commit();
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			try {
				con.rollback();
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}
	
	public List<TbCategoryAmt> findCategoryAmt(){
		Connection connection = null;
		String sql = null;
		try {
			connection = AIDBUtil.getConnection();
			connection.setAutoCommit(false);
			sql = "select * from app ";
			PreparedStatement ptmt = connection.prepareStatement(sql);
			ResultSet rs = ptmt.executeQuery();
			connection.commit();
			ArrayList<TbCategoryAmt> list = new ArrayList<TbCategoryAmt>();
			while(rs.next()) {
				list.add(new TbCategoryAmt(rs.getInt("id"),rs.getString("category_name"),rs.getBigDecimal("goods_sell_amt")));
			}
			return list;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			try {
				connection.rollback();
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		return null;
	}
}

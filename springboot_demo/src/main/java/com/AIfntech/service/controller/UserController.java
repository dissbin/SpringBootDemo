package com.AIfntech.service.controller;

import java.sql.SQLException;
import java.util.List;

import org.apache.coyote.http11.filters.VoidInputFilter;
import org.hibernate.validator.cfg.context.ReturnValueConstraintMappingContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.AIfntech.service.pojo.Result;
import com.AIfntech.service.pojo.User;
import com.AIfntech.service.userService.UserService;

@Controller
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@RequestMapping("/hello")
	public String hello() {
		return "HelloWorld";
	}
	
	@Autowired
	private Environment env;

	@RequestMapping("/info")
	public String info(){
		return "HelloWorld~~"+env.getProperty("url");
	}
	
	@RequestMapping("/register")
	@ResponseBody
	public Result register(User user) {
		try {
			User resultUser = userService.findByName(user);
			if(resultUser!=null)
				return new Result(false, "用户名重复！");
			user.setStatus("1");
			userService.register(user);
			return new Result(true,"成功");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			return new Result(false, "失败");
		}
	}
	
	@RequestMapping("/prepareRegister")
	public String prepareRegister() {
		return "register";
	}
	@RequestMapping("/prepareLogin")
	public String prepareLogin() {
		return "login";
	}
	
	@RequestMapping("/dele")
	@ResponseBody
	public Result dele(String username) {
		
			try {
				userService.dele(username);
				return new Result(true,"成功");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return new Result(false, "失败");
			}
			
		
	}
	
	@RequestMapping("/login")
	@ResponseBody
	public Result login(User user) {
		
			try {
				User resultUser = userService.findByName(user);
				System.out.println(resultUser);
				if( resultUser!=null && resultUser.getPassword().equals(user.getPassword()))
					return new Result(true,"登录成功！");
				else {
					return new Result(false, "登录失败！");
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return new Result(false, "失败");
			}
			
		
	}
	@RequestMapping("/findAll")
	@ResponseBody
	public List<User> findAll(){
		try {
			return userService.findAll();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		
		}
		return null;
	
	}
	
	@RequestMapping("/deleByName")
	@ResponseBody
	public Result deleByName(List<String> usernameList) {
		try {
			userService.deleByName(usernameList);
			return new Result(true,"成功");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return new Result(false,"失败");
		}
	}
	@RequestMapping("/update")
	@ResponseBody
	public void update(String status) {
		try {
			userService.update(status);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

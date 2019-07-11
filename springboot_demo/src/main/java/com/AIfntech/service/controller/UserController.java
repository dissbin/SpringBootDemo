package com.AIfntech.service.controller;

import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.coyote.http11.filters.VoidInputFilter;
import org.hibernate.validator.cfg.context.ReturnValueConstraintMappingContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.AIfntech.service.pojo.Result;
import com.AIfntech.service.pojo.TbCategoryAmt;
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
	
	@RequestMapping("/prepareLogin")
	public String prepareLogin() {
		return "login";
	} 
	@RequestMapping("/prepareRegister")
	public String prepareRegister() {
		return "register";
	} 
	
	@RequestMapping("/login")
	@ResponseBody
	public Result login(User user,HttpServletRequest request) {
			try {
				User resultUser = userService.findByName(user);
				System.out.println("验证用户名:"+user);
				if( resultUser!=null && resultUser.getPassword().equals(user.getPassword())) {
					HttpSession session = request.getSession(true);
					System.out.println("通过Session取得用户名:"+request.getParameter("username"));
					session.setAttribute("username", request.getParameter("username")+"");
					return new Result(true, "登录成功！");
				}
				else {
					return new Result(false, "失败！"); 
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				
			}
			return new Result(false, "失败！"); 
			
		
	}
	@RequestMapping("/findAll")
	@ResponseBody
	public List<User> findAll(int page,int limit){
		try {
			return userService.findAll(page,limit);
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
	@RequestMapping("/showUsers")
	public String showUsers() {
		return "/html/users";
	}
	
	@RequestMapping("/findCategoryAmt")
	@ResponseBody
	public List<TbCategoryAmt> findCategoryAmt(){
		return userService.findCategoryAmt();
	}
}

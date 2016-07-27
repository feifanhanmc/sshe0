package sy.action;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.alibaba.fastjson.JSON;

import sy.model.Tuser;
import sy.service.UserServiceI;

		
@ParentPackage("basePackage")
@Namespace("/")
@Action("userAction")
public class UserAction
{
	private static final Logger logger = Logger.getLogger("UserAction.class");
	
	private UserServiceI userService;
	
	public UserServiceI getUserService()
	{
		return userService;
	}

	@Autowired
	public void setUserService(UserServiceI userService)
	{
		this.userService = userService;
	}

	public void test()
	{
		logger.info("进入userAction");
		//ApplicationContext ac = WebApplicationContextUtils.getWebApplicationContext(ServletActionContext.getServletContext());
		//UserServiceI userService = (UserServiceI) ac.getBean("userService");
		userService.test();
	}
	
	public void addUser()
	{	
		Tuser t = new Tuser();
		//本应是表单提交获得数据内容，目前直接给出@2016-7-19 13:20:23
		t.setId(UUID.randomUUID().toString());
		t.setName("FromUserAction1");
		t.setCreatedatetime(new Date());
		userService.save(t);
	}
	
	public void reg()
	{
		String name = ServletActionContext.getRequest().getParameter("name");
		String pwd = ServletActionContext.getRequest().getParameter("pwd");
		//String json = "";
		Map<String, Object> m = new HashMap<String, Object>();
		try
		{
			userService.add(name,pwd);
			//json = "{\"success\":true,\"msg\":\"注册成功\"}";
			m.put("success", true);
			m.put("msg", "注册成功");
		} catch (Exception e)
		{
			e.printStackTrace();
			//json = "{\"success\":true,\"msg\":\"注册失败\"}";
			m.put("success", true);
			m.put("msg", "注册失败");
		}
		
		try
		{
			ServletActionContext.getResponse().setCharacterEncoding("UTF-8");
			//ServletActionContext.getResponse().getWriter().write(json);
			ServletActionContext.getResponse().getWriter().write(JSON.toJSONString(m));
		} catch (IOException e)
		{
			e.printStackTrace();
		}
	}
}

package sy.action;

import org.apache.log4j.Logger;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.springframework.beans.factory.annotation.Autowired;

import sy.pageModel.DataGrid;
import sy.pageModel.Json;
import sy.pageModel.User;
import sy.service.UserServiceI;

import com.opensymphony.xwork2.ModelDriven;

		

@Namespace("/")
@Action("userAction")
public class UserAction extends BaseAction implements ModelDriven<User>
{
	User user = new User();
	
	@Override
	public User getModel()
	{
		return user;
	}
	
	private static final Logger logger = Logger.getLogger(UserAction.class);
	
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


	public void reg()
	{
		Json j = new Json();
		try
		{
			userService.save(user);
			j.setSuccess(true);
			j.setMsg("注册成功!");
		} catch (Exception e)
		{
			j.setMsg(e.getMessage());
		}
		
		super.writeJson(j);
	}

	public void login()
	{
		User u = userService.login(user);
		Json j = new Json();
		if(u != null)
		{
			j.setSuccess(true);
			j.setMsg("登陆成功！");
		}
		else
		{
			j.setMsg("登录失败，用户名或密码错误！");
		}
		
		super.writeJson(j);
	}

	public void datagrid()
	{
		super.writeJson(userService.datagrid());
		logger.info("here");
	}
}

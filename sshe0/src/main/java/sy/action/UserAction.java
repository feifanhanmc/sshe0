package sy.action;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.apache.log4j.Logger;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.springframework.beans.factory.annotation.Autowired;

import sy.model.Tuser;
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
		userService.test();
	}
	
	public void addUser()
	{	
		Tuser t = new Tuser();
		t.setId(UUID.randomUUID().toString());
		t.setName("FromUserAction1");
		t.setCreatedatetime(new Date());
		userService.save(t);
	}
	
	public void reg()
	{

		Map<String, Object> m = new HashMap<String, Object>();
		try
		{
			userService.add(user.getName(),user.getPwd());
			m.put("success", true);
			m.put("msg", "注册成功");
		} catch (Exception e)
		{
			e.printStackTrace();
			m.put("success", true);
			m.put("msg", e.getMessage());
		}
		
		super.writeJson(m);
	}


}

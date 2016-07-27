package sy.service.impl;

//import java.util.logging.Logger;

import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sy.dao.UserDaoI;
import sy.model.Tuser;
import sy.service.UserServiceI;

@Service("userService")
public class UserServiceImpl implements UserServiceI
{
	
	private static final Logger logger = Logger.getLogger(UserServiceImpl.class);
	
	private UserDaoI userDao;
	
	public UserDaoI getUserDao()
	{
		return userDao;
	}

	@Autowired
	public void setUserDao(UserDaoI userDao)
	{
		this.userDao = userDao;
	}



	public void test()
	{
		logger.info("SSS");
		
	}

	public Serializable save(Tuser t)
	{
		return userDao.save(t);
	}

	@Override
	public void add(String name, String pwd)
	{
		Tuser t = new Tuser();
		t.setId(UUID.randomUUID().toString());
		t.setName(name);
		t.setPwd(pwd);
		t.setCreatedatetime(new Date());
		userDao.save(t);
	}

}

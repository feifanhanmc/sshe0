package sy.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sy.dao.MenuDaoI;
import sy.dao.UserDaoI;
import sy.model.Tmenu;
import sy.model.Tuser;
import sy.service.RepairServiceI;
import sy.util.Encrypt;

@Service("repairService")
public class RepairServiceImpl implements RepairServiceI
{
	
	private static final Logger logger = Logger.getLogger(UserServiceImpl.class);
	
	private MenuDaoI menuDao;
	private UserDaoI userDao;
	
	public MenuDaoI getMenuDao()
	{
		return menuDao;
	}
	
	@Autowired
	public void setMenuDao(MenuDaoI menuDao)
	{
		this.menuDao = menuDao;
	}

	public UserDaoI getUserDao()
	{
		return userDao;
	}
	
	@Autowired
	public void setUserDao(UserDaoI userDao)
	{
		this.userDao = userDao;
	}

	
	
	@Override
	public void repair()
	{
		repairMenu();
		repairUser();
	}
	
	@Override
	public void repairMenu()
	{
		Tmenu root = new Tmenu();
		root.setId("0");
		root.setText("首页");
		root.setUrl("");
		menuDao.saveOrUpdate(root);
		
		Tmenu xtgl = new Tmenu();
		xtgl.setId("xtgl");
		xtgl.setTmenu(root);
		xtgl.setText("系统管理");
		xtgl.setUrl("");
		menuDao.saveOrUpdate(xtgl);

		Tmenu yhgl = new Tmenu();
		yhgl.setId("yhgl");
		yhgl.setTmenu(xtgl);
		yhgl.setText("用户管理");
		yhgl.setUrl("/admin/yhgl.jsp");
		menuDao.saveOrUpdate(yhgl);

		Tmenu jsgl = new Tmenu();
		jsgl.setId("jsgl");
		jsgl.setTmenu(xtgl);
		jsgl.setText("角色管理");
		menuDao.saveOrUpdate(jsgl);

		Tmenu qxgl = new Tmenu();
		qxgl.setId("qxgl");
		qxgl.setTmenu(xtgl);
		qxgl.setText("权限管理");
		menuDao.saveOrUpdate(qxgl);

		Tmenu cdgl = new Tmenu();
		cdgl.setId("cdgl");
		cdgl.setTmenu(xtgl);
		cdgl.setText("菜单管理");
		menuDao.saveOrUpdate(cdgl);

		Tmenu buggl = new Tmenu();
		buggl.setId("buggl");
		buggl.setTmenu(xtgl);
		buggl.setText("BUG管理");
		menuDao.saveOrUpdate(buggl);
	}
	
	public void repairUser()
	{
		Map<String, Object> m = new HashMap<String, Object>();
		m.put("name", "admin");
		m.put("id", "0");
		Tuser t = userDao.get("from Tuser t where t.name = :name and t.id != :id ",m);
		if( t != null)
		{
			t.setName(UUID.randomUUID().toString());
		}
		
		Tuser admin = new Tuser();
		admin.setId("0");
		admin.setName("admin");
		admin.setPwd(Encrypt.e("admin"));
		admin.setModifydatetime(new Date());
		userDao.saveOrUpdate(admin);
	}

}

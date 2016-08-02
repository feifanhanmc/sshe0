package sy.service.impl;

//import java.util.logging.Logger;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.apache.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sy.dao.UserDaoI;
import sy.model.Muser;
import sy.pageModel.DataGrid;
import sy.pageModel.User;
import sy.service.UserServiceI;
import sy.util.Encrypt;


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

	
	@Override
	public User save(User user)
	{
		Muser m = new Muser();
		BeanUtils.copyProperties(user, m, new String[]{"pwd"});
		m.setPwd(Encrypt.e(user.getPwd()));//加密处理
		m.setId(UUID.randomUUID().toString());
		m.setModifytime(new Date());
		userDao.save(m);
		BeanUtils.copyProperties(m, user);
		return user;
	}

	@Override
	public User login(User user)
	{
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("account",user.getAccount());
		params.put("pwd", Encrypt.e(user.getPwd()));
		Muser m = userDao.get("from Muser m where m.account = :account and m.pwd = :pwd ",params);
		if(m != null)
		{
			user.setRole(m.getRole());
			user.setName(m.getName());
			user.setId(m.getId());
			return user;
		}
			
		return null;
	}

	@Override
	public DataGrid datagrid(User user)
	{
		DataGrid dg = new DataGrid();
		String hql = "from Muser m";
		Map<String, Object> params = new HashMap<String, Object>();
		hql = addWhere(user, hql, params);		
		String totalHql = "select count(*) " + hql;
		hql = addOrder(user, hql);
		List<Muser> l = userDao.find(hql, params, user.getPage(), user.getRows());
		List<User> nl = new ArrayList<User>();
		changeModel(l, nl);
		dg.setTotal(userDao.count(totalHql, params));
		dg.setRows(nl);
		return dg;
	}

	private void changeModel(List<Muser> l, List<User> nl) 
	{
		if (l != null && l.size() > 0) 
		{
			for (Muser m : l) 
			{
				User u = new User();
				BeanUtils.copyProperties(m, u);
				nl.add(u);
			}
		}
	}
	
	private String addOrder(User user, String hql) 
	{
		if (user.getSort() != null) 
		{
			hql += " order by " + user.getSort() + " " + user.getOrder();
		}
		return hql;
	}

	private String addWhere(User user, String hql, Map<String, Object> params) 
	{
		if (user.getName() != null && !user.getName().trim().equals("")) 
		{
			hql += " where m.name like :name";
			params.put("name", "%%" + user.getName().trim() + "%%");
		}
		return hql;
	}

	@Override
	public void remove(String ids)
	{
		/*
		 * 先通过id查询出来，然后再删除
		 * 效率低
		 * 不如直接执行hql语句进行删除
		 * 
		 * 
		for(String id : ids.split(","))
		{
			Muser u = userDao.get(Muser.class, id);
			if(u != null)
			{
				userDao.delete(u);
			}
		}
		*/
		
		String [] nids = ids.split(",");
		String hql = "delete Muser muser where muser.id in (";
		for(int i = 0; i < nids.length; i++)
		{
			if( i > 0)
			{
				hql += ",";				
			}
			hql += "'" + nids[i] + "'";
		}
		hql += " ) ";
		userDao.executeHql(hql);
		
	}

	@Override
	public User edit(User user)
	{
		Muser m = userDao.get(Muser.class, user.getId());
		BeanUtils.copyProperties(user, m, new String[]{"id", "pwd"});
		m.setPwd(Encrypt.e(user.getPwd()));
		m.setModifytime(new Date());
		return user;
	}

	@Override
	public List exportExcel()
	{
		List<Muser> l = userDao.find("from Muser m");
		return l;
	}
	
}

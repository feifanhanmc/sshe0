package sy.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sy.dao.MenuDaoI;
import sy.model.Tmenu;
import sy.pageModel.Menu;
import sy.service.MenuServiceI;

@Service("menuService")
public class MenuServiceImpl implements MenuServiceI
{
	private static final Logger logger = Logger.getLogger(MenuServiceImpl.class);
	
	private MenuDaoI menuDao;

	public MenuDaoI getMenuDao()
	{
		return menuDao;
	}
	
	@Autowired
	public void setMenuDao(MenuDaoI menuDao)
	{
		this.menuDao = menuDao;
	}

	@Override
	public List<Menu> getTreeNode(String id, String uId, String uRole, String uName)
	{
		List<Menu> nl = new ArrayList<Menu>();
		String hql = null;
		Map<String, Object> params = new HashMap<String, Object>();
		if( id == null || id.equals("") )
		{
			hql = "from Tmenu t where t.tmenu is null";
		}
		else if( id.equals("0"))
		{
			String pids = null;
			if( uRole.equals("admin"))
				pids = "( 'xtgl', 'jwgl', 'cjgl')";
			else if( uRole.equals("teacher"))
				pids = "( 'jwgl', 'cjgl')";
			else
				pids = "( 'cjgl')";
			
			hql = "from Tmenu t where t.id in " + pids;
		}
		else
		{
			hql = "from Tmenu t where t.tmenu.id = :id ";
			params.put("id", id);
		}
		List<Tmenu> l = menuDao.find(hql, params);
		if( l != null && l.size() > 0)
		{
			for(Tmenu t : l)
			{
				Menu m = new Menu();
				BeanUtils.copyProperties(t, m);
				
				Map<String, Object> attributes = new HashMap<String, Object>();
				attributes.put("url", t.getUrl());
				m.setAttributes(attributes);
				
				Set<Tmenu> set = t.getTmenus();
				if(set != null && !set.isEmpty())
				{
					m.setState("closed");//节点以文件夹的形式体现
				}
				else
				{
					m.setState("open");//节点以文件的形式体现
				}
				nl.add(m);
			}
		}
		return nl;
	}

	@Override
	public List<Menu> getAllTreeNode()
	{
		List<Menu> nl = new ArrayList<Menu>();
		String hql = "from Tmenu t";
		List<Tmenu> l = menuDao.find(hql);
		if( l != null && l.size() > 0)
		{
			for(Tmenu t : l)
			{
				Menu m = new Menu();
				BeanUtils.copyProperties(t, m);
				
				Map<String, Object> attributes = new HashMap<String, Object>();
				attributes.put("url", t.getUrl());
				m.setAttributes(attributes);
				
				Tmenu tm = t.getTmenu();
				if( tm != null)
				{
					m.setPid(tm.getId());
				}
				nl.add(m);
			}
		}
		return nl;
	}
	
	
}

package sy.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sy.dao.MenuDaoI;
import sy.pageModel.Menu;
import sy.service.MenuServiceI;

@Service("menuService")
public class MenuServiceImpl implements MenuServiceI
{
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
	public void save(Menu menu)
	{
		// TODO Auto-generated method stub
		
	}
	
	
}

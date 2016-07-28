package sy.dao.impl;

import org.springframework.stereotype.Repository;

import sy.dao.MenuDaoI;
import sy.model.Tmenu;

@Repository("menuDao")
public class MenuDaoImpl extends BaseDaoImpl<Tmenu> implements MenuDaoI
{
	
}

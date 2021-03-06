package sy.action;

import org.apache.log4j.Logger;
import org.apache.struts2.convention.annotation.Action;
import org.springframework.beans.factory.annotation.Autowired;

import sy.pageModel.Menu;
import sy.service.MenuServiceI;

import com.opensymphony.xwork2.ModelDriven;

@Action("menuAction")
public class MenuAction extends BaseAction implements ModelDriven<Menu>
{
	
	private static final Logger logger = Logger.getLogger(MenuAction.class);
	
	private Menu menu = new Menu();
	
	private MenuServiceI menuService;
	

	@Override
	public Menu getModel()
	{
		return menu;
	}
	
	
	public MenuServiceI getMenuService()
	{
		return menuService;
	}
	
	@Autowired
	public void setMenuService(MenuServiceI menuService)
	{
		this.menuService = menuService;
	}
	
	
	public void getTreeNode()
	{
		String uId = super.finalId;
		String uRole = super.finalRole;
		String uName = super.finalName;
		
		logger.info("uRole : " + uRole);
		logger.info("uName : " + uName);
		
		super.writeJson(menuService.getTreeNode(menu.getId(), uId, uRole, uName));
	}
	
	public void getAllTreeNode()
	{
		super.writeJson(menuService.getAllTreeNode());
	}
}

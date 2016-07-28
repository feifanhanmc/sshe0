package sy.action;

import org.apache.struts2.convention.annotation.Action;

import sy.pageModel.Menu;

import com.opensymphony.xwork2.ModelDriven;

@Action("menuAction")
public class MenuAction extends BaseAction implements ModelDriven<Menu>
{
	private Menu menu = new Menu();

	@Override
	public Menu getModel()
	{
		return null;
	}
	
}

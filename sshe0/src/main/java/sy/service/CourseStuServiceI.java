package sy.service;

import java.util.List;

import sy.pageModel.CourseStu;
import sy.pageModel.DataGrid;

public interface CourseStuServiceI
{
	public DataGrid datagrid(CourseStu courseStu, String tId);
	
	public CourseStu edit(CourseStu courseStu);
	
	public List exportExcel();
	
	public String getCname(String cid);

	public String getSname(String sid);
	
	public void updateMcourseStuForm(String cid);
	
}

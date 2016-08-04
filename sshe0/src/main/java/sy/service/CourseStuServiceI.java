package sy.service;

import java.util.List;

import sy.pageModel.CourseStu;
import sy.pageModel.DataGrid;

public interface CourseStuServiceI
{
	public DataGrid datagrid(CourseStu courseStu, String tId);
	
	public CourseStu edit(CourseStu courseStu);
	
	public List exportExcel(String tid);
	
	public String getCname(String cid);

	public String getSname(String sid);
	
	public void updateMcourseStuForm(String cid);
	
	public int updateMcourseStuGrade(float oldGrade, float grade, String cid, String csid);
	
}

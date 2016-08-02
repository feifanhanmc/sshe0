package sy.service;

import java.util.List;

import sy.pageModel.Course;
import sy.pageModel.DataGrid;
import sy.pageModel.User;

public interface CourseServiceI
{
	public Course save(Course course);
	
	public DataGrid datagrid(Course course);
	
	public void remove(String ids);
	
	public Course edit(Course course);
	
	public List exportExcel();
}

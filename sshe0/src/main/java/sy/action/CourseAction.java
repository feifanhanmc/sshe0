package sy.action;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.springframework.beans.factory.annotation.Autowired;

import sy.model.Mcourse;
import sy.model.Muser;
import sy.pageModel.Course;
import sy.pageModel.Json;
import sy.pageModel.User;
import sy.service.CourseServiceI;
import sy.service.CourseStuServiceI;

import com.opensymphony.xwork2.ModelDriven;


@Namespace("/")
@Action("courseAction")
public class CourseAction extends BaseAction implements ModelDriven<Course>
{
	Course course = new Course();
	
	@Override
	public Course getModel()
	{
		return course;
	}
	
	public static final String FILE_SEPARATOR = System.getProperties().getProperty("file.separator");
	
	private static final Logger logger = Logger.getLogger(UserAction.class);
	
	private CourseServiceI courseService;
	
	private CourseStuServiceI courseStuService;


	public CourseServiceI getCourseService()
	{
		return courseService;
	}

	@Autowired
	public void setCourseService(CourseServiceI courseService)
	{
		this.courseService = courseService;
	}

	public CourseStuServiceI getCourseStuService()
	{
		return courseStuService;
	}

	@Autowired
	public void setCourseStuService(CourseStuServiceI courseStuService)
	{
		this.courseStuService = courseStuService;
	}
	
	
	
	public void add()
	{
		Json j = new Json();
		try
		{
			Course c = courseService.save(course);
			j.setSuccess(true);
			j.setMsg("添加成功!");
			j.setObj(c);
			
			//调用方法，完成McourseStu表的更新
			courseStuService.updateMcourseStuForm(c.getCid());
			
		} catch (Exception e)
		{
			j.setMsg(e.getMessage());
		}
		
		super.writeJson(j);
	}
	

	public void datagrid()
	{
		super.writeJson(courseService.datagrid(course));
	}
	
	public void remove()
	{
		courseService.remove(course.getCids());
		Json j = new Json();
		j.setSuccess(true);
		j.setMsg("删除成功！");
		
		super.writeJson(j);
	}
	
	public void edit()
	{
		Course c = courseService.edit(course);
		Json j = new Json();
		j.setSuccess(true);
		j.setMsg("修改成功！");
		j.setObj(c);
		
		super.writeJson(j);
	}
	
	public void exportExcel()
	{	
		HttpServletResponse response = ServletActionContext.getResponse();
	
		String docsPath = "D:";
		String fileName = "CourseInfo" + System.currentTimeMillis() + ".xlsx";	
		String filePath = docsPath + FILE_SEPARATOR + fileName;

		List<Mcourse> l = courseService.exportExcel();
		String[] str = {"课程名称","课程教师","平均分"};
		try 
		{
			OutputStream os = new FileOutputStream(filePath);
			XSSFWorkbook wb = new XSSFWorkbook();
			XSSFSheet sheet = wb.createSheet("test");
			
			XSSFRow r = sheet.createRow(0);
			for(int j = 0; j < str.length; j++)
				r.createCell(j).setCellValue(str[j]);
			
			for (int i = 0; i < l.size(); i++) 
			{	
				Mcourse m = (Mcourse) l.get(i);
				XSSFRow row = sheet.createRow(i+1);

				row.createCell(0).setCellValue(m.getCname());
				row.createCell(1).setCellValue(m.getTname());
				row.createCell(2).setCellValue(m.getAvg());

			}

			wb.write(os);
			os.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		super.download(filePath, response);
	}
}

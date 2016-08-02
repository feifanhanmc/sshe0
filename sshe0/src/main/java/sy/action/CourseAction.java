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
	
	
	public CourseServiceI getCourseService()
	{
		return courseService;
	}

	@Autowired
	public void setCourseService(CourseServiceI courseService)
	{
		this.courseService = courseService;
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
	
	public void exportExcel()
	{	
		HttpServletResponse response = ServletActionContext.getResponse();
		HttpServletRequest request = ServletActionContext.getRequest();
		
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
		
		download(filePath, response);
	}
	

	private void download(String path, HttpServletResponse response)
	{
		try 
		{
			File file = new File(path);
			String filename = file.getName();

			InputStream fis = new BufferedInputStream(new FileInputStream(path));
			byte[] buffer = new byte[fis.available()];
			fis.read(buffer);
			fis.close();
			response.reset();
			response.addHeader("Content-Disposition", "attachment;filename=" + new String(filename.getBytes()));
			response.addHeader("Content-Length", "" + file.length());
			OutputStream toClient = new BufferedOutputStream(response.getOutputStream());
			
			response.setContentType("application/vnd.ms-excel;charset=gb2312");
			toClient.write(buffer);
			toClient.flush();
			toClient.close();
		} 
		catch (IOException ex) 
		{
			ex.printStackTrace();
		}
	}
}

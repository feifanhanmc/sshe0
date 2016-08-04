package sy.action;

import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.springframework.beans.factory.annotation.Autowired;

import sy.model.McourseStu;
import sy.model.Muser;
import sy.pageModel.CourseStu;
import sy.pageModel.Json;
import sy.service.CourseStuServiceI;

import com.opensymphony.xwork2.ModelDriven;


@Namespace("/")
@Action("courseStuAction")
public class CourseStuAction extends BaseAction implements ModelDriven<CourseStu>
{
	CourseStu courseStu = new CourseStu();

	@Override
	public CourseStu getModel()
	{
		return courseStu;
	}
	
	public static final String FILE_SEPARATOR = System.getProperties().getProperty("file.separator");
	
	private static final Logger logger = Logger.getLogger(UserAction.class);
	
	private CourseStuServiceI courseStuService;

	public CourseStuServiceI getCourseStuService()
	{
		return courseStuService;
	}

	@Autowired
	public void setCourseStuService(CourseStuServiceI courseStuService)
	{
		this.courseStuService = courseStuService;
	}
	
	public void datagrid()
	{
		String tId = super.finalId;
		super.writeJson(courseStuService.datagrid(courseStu, tId));
	}
	
	public void edit()
	{
		CourseStu cs = courseStuService.edit(courseStu);
		Json j = new Json();
		j.setSuccess(true);
		j.setMsg("修改成功！");
		j.setObj(cs);
		
		//调用方法，完成McourseStu表的更新
//		courseStuService.updateMcourseStuForm(c.getCid());
		
		super.writeJson(j);
	}
	
	public void exportExcel()
	{	

		HttpServletResponse response = ServletActionContext.getResponse();
		
		String docsPath = "D:";
		String fileName = "CourseStuInfo" + System.currentTimeMillis() + ".xlsx";	
		String filePath = docsPath + FILE_SEPARATOR + fileName;

		List<McourseStu> l = courseStuService.exportExcel();
		String[] str = {"学生姓名","分数","排名","课程名"};
		try 
		{
			OutputStream os = new FileOutputStream(filePath);
			XSSFWorkbook wb = new XSSFWorkbook();
			XSSFSheet sheet = wb.createSheet("test");
			
			XSSFRow r = sheet.createRow(0);
			for(int j = 0; j < str.length; j++)
				r.createCell(j).setCellValue(str[j]);
			
			String cname = null;
			for (int i = 0; i < l.size(); i++) 
			{	
				McourseStu m = (McourseStu) l.get(i);
				XSSFRow row = sheet.createRow(i+1);
				
				if(i == 0)
					cname = courseStuService.getCname(m.getCid());
				
				row.createCell(0).setCellValue(courseStuService.getSname(m.getSid()));
				row.createCell(1).setCellValue(m.getGrade());
				row.createCell(2).setCellValue(m.getRank());
				row.createCell(3).setCellValue(cname);

			}

			wb.write(os);
			os.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		super.download(filePath, response);
	}
}

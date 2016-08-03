package sy.action;

import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
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

import sy.model.Muser;
import sy.pageModel.Json;
import sy.pageModel.User;
import sy.service.UserServiceI;

import com.alibaba.fastjson.JSONArray;
import com.opensymphony.xwork2.ModelDriven;

		

@Namespace("/")
@Action("userAction")
public class UserAction extends BaseAction implements ModelDriven<User>
{
	User user = new User();
	
	@Override
	public User getModel()
	{
		return user;
	}
	
	
	public static final String FILE_SEPARATOR = System.getProperties().getProperty("file.separator");
	
	private static final Logger logger = Logger.getLogger(UserAction.class);
	
	private UserServiceI userService;
	
	public UserServiceI getUserService()
	{
		return userService;
	}

	@Autowired
	public void setUserService(UserServiceI userService)
	{
		this.userService = userService;
	}
		
	
	public void reg()
	{
		Json j = new Json();
		try
		{
			userService.save(user);
			j.setSuccess(true);
			j.setMsg("注册成功!");
		} catch (Exception e)
		{
			j.setMsg(e.getMessage());
		}
		
		super.writeJson(j);
	}

	public void add()
	{
		Json j = new Json();
		try
		{
			User u = userService.save(user);
			j.setSuccess(true);
			j.setMsg("添加成功!");
			j.setObj(u);
		} catch (Exception e)
		{
			j.setMsg(e.getMessage());
		}
		
		super.writeJson(j);
	}
	
	public void login()
	{
		User u = userService.login(user);
		Json j = new Json();
		if(u != null)
		{
			j.setSuccess(true);
			j.setMsg("登陆成功！");
			super.setFinalId(u.getId());
			super.setFinalRole(u.getRole());
			super.setFinalName(u.getName());
		}
		else
		{
			j.setMsg("登录失败，用户名或密码错误！");
		}
		
		super.writeJson(j);
	}

	
	public void datagrid()
	{
		super.writeJson(userService.datagrid(user));
	}
	
	public void remove()
	{
		userService.remove(user.getIds());
		Json j = new Json();
		j.setSuccess(true);
		j.setMsg("删除成功！");
		
		super.writeJson(j);
	}
	
	public void edit()
	{
		User u = userService.edit(user);
		Json j = new Json();
		j.setSuccess(true);
		j.setMsg("修改成功！");
		j.setObj(u);
		
		super.writeJson(j);
	}
	
	public void exportExcel()
	{	

		HttpServletResponse response = ServletActionContext.getResponse();
		
		String docsPath = "D:";
		String fileName = "UserInfo" + System.currentTimeMillis() + ".xlsx";	
		String filePath = docsPath + FILE_SEPARATOR + fileName;

		List<Muser> l = userService.exportExcel();
		String[] str = {"ID","Account","Name","Role","Modify Time"};
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
				Muser m = (Muser) l.get(i);
				XSSFRow row = sheet.createRow(i+1);

				row.createCell(0).setCellValue(m.getId());
				row.createCell(1).setCellValue(m.getAccount());
				row.createCell(2).setCellValue(m.getName());
				row.createCell(3).setCellValue(m.getRole());
				row.createCell(4).setCellValue(m.getModifytime().toString());

			}

			wb.write(os);
			os.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		super.download(filePath, response);
	}
	
	public void getTname()
	{
		JSONArray j = userService.getTname();
		super.writeJson(j);
	}


}

package sy.action;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;

import com.alibaba.fastjson.JSON;

@ParentPackage("basePackage")
@Namespace("/")
public class BaseAction
{
	private static final Logger logger = Logger.getLogger(BaseAction.class);
	
	public static String finalRole;
	public static String finalName;
	public static String finalId;
	
	public String getFinalRole()
	{
		return finalRole;
	}
	public void setFinalRole(String finalRole)
	{
		this.finalRole = finalRole;
	}
	public String getFinalName()
	{
		return finalName;
	}
	public void setFinalName(String finalName)
	{
		this.finalName = finalName;
	}
	public String getFinalId()
	{
		return finalId;
	}
	public void setFinalId(String finalId)
	{
		this.finalId = finalId;
	}


	public void writeJson(Object object) {
		try {
			String json = JSON.toJSONStringWithDateFormat(object, "yyyy-MM-dd HH:mm:ss");
			ServletActionContext.getResponse().setContentType("text/html;charset=utf-8");
			ServletActionContext.getResponse().getWriter().write(json);
			ServletActionContext.getResponse().getWriter().flush();
			ServletActionContext.getResponse().getWriter().close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	protected void download(String path, HttpServletResponse response)
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

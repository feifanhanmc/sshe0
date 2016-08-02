package sy.action;

import java.io.IOException;

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
}

package sy.pageModel;

import java.io.Serializable;

public class Json implements Serializable
{
	private boolean success = false;
	
	private String msg = "";
	
	private Object obj = null;

	private String role;
	
	private String name;
	
	private String kcgl_id;
	
	private String kegl_text;
	
	
	
	public String getKcgl_id()
	{
		return kcgl_id;
	}

	public void setKcgl_id(String kcgl_id)
	{
		this.kcgl_id = kcgl_id;
	}

	public String getKegl_text()
	{
		return kegl_text;
	}

	public void setKegl_text(String kegl_text)
	{
		this.kegl_text = kegl_text;
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public String getRole()
	{
		return role;
	}

	public void setRole(String role)
	{
		this.role = role;
	}

	public boolean isSuccess()
	{
		return success;
	}

	public void setSuccess(boolean success)
	{
		this.success = success;
	}

	public String getMsg()
	{
		return msg;
	}

	public void setMsg(String msg)
	{
		this.msg = msg;
	}

	public Object getObj()
	{
		return obj;
	}

	public void setObj(Object obj)
	{
		this.obj = obj;
	}
	
}

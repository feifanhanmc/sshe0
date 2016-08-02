package sy.pageModel;

import java.util.Date;

public class User implements java.io.Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String id;
	private String account;
	private String name;
	private String pwd;
	private String role;
	private Date modifytime;
	
	private int page;
	private int rows;
	
	private String sort;
	private String order;
	
	private String ids;
		

	public String getRole()
	{
		return role;
	}
	public void setRole(String role)
	{
		this.role = role;
	}
	public String getAccount()
	{
		return account;
	}
	public void setAccount(String account)
	{
		this.account = account;
	}
	public String getIds()
	{
		return ids;
	}
	public void setIds(String ids)
	{
		this.ids = ids;
	}
	public String getSort()
	{
		return sort;
	}
	public void setSort(String sort)
	{
		this.sort = sort;
	}
	public String getOrder()
	{
		return order;
	}
	public void setOrder(String order)
	{
		this.order = order;
	}
	public int getPage()
	{
		return page;
	}
	public void setPage(int page)
	{
		this.page = page;
	}
	public int getRows()
	{
		return rows;
	}
	public void setRows(int rows)
	{
		this.rows = rows;
	}
	public String getId()
	{
		return id;
	}
	public void setId(String id)
	{
		this.id = id;
	}
	public String getName()
	{
		return name;
	}
	public void setName(String name)
	{
		this.name = name;
	}
	public String getPwd()
	{
		return pwd;
	}
	public void setPwd(String pwd)
	{
		this.pwd = pwd;
	}
	public Date getModifytime()
	{
		return modifytime;
	}
	public void setModifytime(Date modifytime)
	{
		this.modifytime = modifytime;
	}
	
}

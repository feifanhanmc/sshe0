package sy.pageModel;

import java.util.Date;

public class User implements java.io.Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String id;
	private String name;
	private String pwd;
	private Date createdatetime;
	private Date modifydatetime;
	
	private int page;
	private int rows;
	
	private String sort;
	private String order;
	
	
	
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
	public Date getCreatedatetime()
	{
		return createdatetime;
	}
	public void setCreatedatetime(Date createdatetime)
	{
		this.createdatetime = createdatetime;
	}
	public Date getModifydatetime()
	{
		return modifydatetime;
	}
	public void setModifydatetime(Date modifydatetime)
	{
		this.modifydatetime = modifydatetime;
	}
	
}

package sy.pageModel;

public class Course implements java.io.Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String cid;
	private String cname;
	private String tid;
	private float avg;
	
	
	private int page;
	private int rows;
	
	
	private String sort;
	private String order;
	
	
	private String tname;
	
	private String cids;
	
	
	

	public String getCids()
	{
		return cids;
	}
	public void setCids(String cids)
	{
		this.cids = cids;
	}
	public String getTname()
	{
		return tname;
	}
	public void setTname(String tname)
	{
		this.tname = tname;
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
	public String getCid()
	{
		return cid;
	}
	public void setCid(String cid)
	{
		this.cid = cid;
	}
	public String getCname()
	{
		return cname;
	}
	public void setCname(String cname)
	{
		this.cname = cname;
	}
	public String getTid()
	{
		return tid;
	}
	public void setTid(String tid)
	{
		this.tid = tid;
	}
	public float getAvg()
	{
		return avg;
	}
	public void setAvg(float avg)
	{
		this.avg = avg;
	}
	
	
}

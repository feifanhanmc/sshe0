package sy.model;

/**
 * Mcourse entity. @author MyEclipse Persistence Tools
 */

public class Mcourse implements java.io.Serializable
{

	// Fields

	private String cid;
	private String cname;
	private String tid;
	private String avg;

	// Constructors

	/** default constructor */
	public Mcourse()
	{
	}

	/** minimal constructor */
	public Mcourse(String cid)
	{
		this.cid = cid;
	}

	/** full constructor */
	public Mcourse(String cid, String cname, String tid, String avg)
	{
		this.cid = cid;
		this.cname = cname;
		this.tid = tid;
		this.avg = avg;
	}

	// Property accessors

	public String getCid()
	{
		return this.cid;
	}

	public void setCid(String cid)
	{
		this.cid = cid;
	}

	public String getCname()
	{
		return this.cname;
	}

	public void setCname(String cname)
	{
		this.cname = cname;
	}

	public String getTid()
	{
		return this.tid;
	}

	public void setTid(String tid)
	{
		this.tid = tid;
	}

	public String getAvg()
	{
		return this.avg;
	}

	public void setAvg(String avg)
	{
		this.avg = avg;
	}

}
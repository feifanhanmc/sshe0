package sy.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Mcourse entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "MCOURSE", schema = "SSHE")
public class Mcourse implements java.io.Serializable
{

	// Fields

	private String cid;
	private String cname;
	private String tid;
	private float average;
	private String tname;
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
	public Mcourse(String cid, String cname, String tid, float average)
	{
		this.cid = cid;
		this.cname = cname;
		this.tid = tid;
		this.average = average;
	}

	// Property accessors
	@Id
	@Column(name = "CID", unique = true, nullable = false, length = 36)
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

	public float getAverage()
	{
		return this.average;
	}

	public void setAverage(float average)
	{
		this.average = average;
	}

	public String getTname()
	{
		return tname;
	}

	public void setTname(String tname)
	{
		this.tname = tname;
	}

	
}
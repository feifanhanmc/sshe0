package sy.model;

import java.math.BigDecimal;

/**
 * McourseStu entity. @author MyEclipse Persistence Tools
 */

public class McourseStu implements java.io.Serializable
{

	// Fields

	private String cid;
	private String sid;
	private String grade;
	private BigDecimal rank;

	// Constructors

	/** default constructor */
	public McourseStu()
	{
	}

	/** minimal constructor */
	public McourseStu(String cid)
	{
		this.cid = cid;
	}

	/** full constructor */
	public McourseStu(String cid, String sid, String grade, BigDecimal rank)
	{
		this.cid = cid;
		this.sid = sid;
		this.grade = grade;
		this.rank = rank;
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

	public String getSid()
	{
		return this.sid;
	}

	public void setSid(String sid)
	{
		this.sid = sid;
	}

	public String getGrade()
	{
		return this.grade;
	}

	public void setGrade(String grade)
	{
		this.grade = grade;
	}

	public BigDecimal getRank()
	{
		return this.rank;
	}

	public void setRank(BigDecimal rank)
	{
		this.rank = rank;
	}

}
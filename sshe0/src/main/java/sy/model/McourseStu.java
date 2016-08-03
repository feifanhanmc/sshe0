package sy.model;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * McourseStu entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "MCOURSE_STU", schema = "SSHE")
public class McourseStu implements java.io.Serializable
{

	// Fields

	private String csid;
	private String cid;
	private String sid;
	private float grade;
	private int rank;

	// Constructors

	/** default constructor */
	public McourseStu()
	{
	}

	/** minimal constructor */
	public McourseStu(String csid)
	{
		this.csid = csid;
	}

	/** full constructor */
	public McourseStu(String csid, String cid, String sid, float grade,
			int rank)
	{
		this.csid = csid;
		this.cid = cid;
		this.sid = sid;
		this.grade = grade;
		this.rank = rank;
	}

	// Property accessors
	@Id
	@Column(name = "CSID", unique = true, nullable = false, length = 36)
	public String getCsid()
	{
		return this.csid;
	}

	public void setCsid(String csid)
	{
		this.csid = csid;
	}

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

	public float getGrade()
	{
		return this.grade;
	}

	public void setGrade(float grade)
	{
		this.grade = grade;
	}

	public int getRank()
	{
		return this.rank;
	}

	public void setRank(int rank)
	{
		this.rank = rank;
	}

}
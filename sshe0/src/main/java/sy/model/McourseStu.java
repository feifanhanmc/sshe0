package sy.model;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import sy.model.McourseStuId;

/**
 * McourseStu entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "MCOURSE_STU", schema = "SSHE")
public class McourseStu implements java.io.Serializable
{

	// Fields

	private McourseStuId id;
	private String grade;
	private BigDecimal rank;

	// Constructors

	/** default constructor */
	public McourseStu()
	{
	}

	/** minimal constructor */
	public McourseStu(McourseStuId id)
	{
		this.id = id;
	}

	/** full constructor */
	public McourseStu(McourseStuId id, String grade, BigDecimal rank)
	{
		this.id = id;
		this.grade = grade;
		this.rank = rank;
	}

	// Property accessors
	@Id
	@Column(name = "ID", unique = true, nullable = false, length = 36)
	public McourseStuId getId()
	{
		return this.id;
	}

	public void setId(McourseStuId id)
	{
		this.id = id;
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
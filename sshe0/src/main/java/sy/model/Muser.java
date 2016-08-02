package sy.model;

import java.sql.Timestamp;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Muser entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "MUSER", schema = "SSHE")
public class Muser implements java.io.Serializable
{

	// Fields

	private String id;
	private String account;
	private String pwd;
	private String name;
	private String role;
	private Date modifytime;

	// Constructors

	/** default constructor */
	public Muser()
	{
	}

	/** minimal constructor */
	public Muser(String id)
	{
		this.id = id;
	}

	/** full constructor */
	public Muser(String id, String account, String pwd, String name,
			String role, Timestamp modifytime)
	{
		this.id = id;
		this.account = account;
		this.pwd = pwd;
		this.name = name;
		this.role = role;
		this.modifytime = modifytime;
	}

	// Property accessors
	@Id
	@Column(name = "ID", unique = true, nullable = false, length = 36)
	public String getId()
	{
		return this.id;
	}

	public void setId(String id)
	{
		this.id = id;
	}

	public String getAccount()
	{
		return this.account;
	}

	public void setAccount(String account)
	{
		this.account = account;
	}

	public String getPwd()
	{
		return this.pwd;
	}

	public void setPwd(String pwd)
	{
		this.pwd = pwd;
	}

	public String getName()
	{
		return this.name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public String getRole()
	{
		return this.role;
	}

	public void setRole(String role)
	{
		this.role = role;
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
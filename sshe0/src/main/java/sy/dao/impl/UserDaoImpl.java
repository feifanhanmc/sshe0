package sy.dao.impl;

import java.io.Serializable;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import sy.dao.UserDaoI;
import sy.model.Tuser;

@Repository("userDao")
public class UserDaoImpl implements UserDaoI
{

	private SessionFactory sessionFactory;
	
	public SessionFactory getSessionFactory()
	{
		return sessionFactory;
	}

	@Autowired
	public void setSessionFactory(SessionFactory sessionFactory)
	{
		this.sessionFactory = sessionFactory;
	}

	@SuppressWarnings("deprecation")
	public Serializable save(Tuser t)
	{
		return this.sessionFactory.getCurrentSession().save(t);
		//return ((UserDaoI) this.sessionFactory.getSessionFactoryOptions()).save(t);
	}

}

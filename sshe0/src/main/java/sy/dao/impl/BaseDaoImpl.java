package sy.dao.impl;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import sy.dao.BaseDaoI;

@Repository("baseDao")
public class BaseDaoImpl<T> implements BaseDaoI<T>
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

	@Override
	public Serializable save(T o)
	{
		return this.sessionFactory.getCurrentSession().save(o);
	}

	@Override
	public T get(String hql)
	{
		Query q = this.sessionFactory.getCurrentSession().createQuery(hql);
		List<T> l = q.list();
		if(l != null && l.size() > 0)
			return l.get(0);
		return null;
	}

	@Override
	public T get(String hql, Object[] params)
	{
		Query q = this.sessionFactory.getCurrentSession().createQuery(hql);
		if(params != null && params.length > 0)
		{
			for(int i = 0 ; i < params.length ; i++)
			{
				q.setParameter(i, params[i]);
			}
		}
		List<T> l = q.list();
		if(l != null && l.size() > 0)
			return l.get(0);
		return null;
	}

}

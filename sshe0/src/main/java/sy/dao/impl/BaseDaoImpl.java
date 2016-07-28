package sy.dao.impl;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import sy.dao.BaseDaoI;
import sy.service.impl.UserServiceImpl;

@Repository("baseDao")
public class BaseDaoImpl<T> implements BaseDaoI<T>
{
	
	private static final Logger logger = Logger.getLogger(BaseDaoImpl.class);
	
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

	@Override
	public T get(String hql, Map<String, Object> params)
	{
		Query q = this.sessionFactory.getCurrentSession().createQuery(hql);
		if(params != null && !params.isEmpty())
		{
			for(String key : params.keySet())
			{
				q.setParameter(key, params.get(key));
			}
		}
		List<T> l = q.list();
		if(l != null && l.size() > 0)
			return l.get(0);
		return null;
	}

	@Override
	public void delete(T o)
	{
		this.sessionFactory.getCurrentSession().delete(o);		
	}

	@Override
	public void update(T o)
	{
		this.sessionFactory.getCurrentSession().update(o);
	}

	@Override
	public void saveOrUpdate(T o)
	{
		this.sessionFactory.getCurrentSession().saveOrUpdate(o);
	}

	@Override
	public List<T> find(String hql)
	{
		Query q = this.sessionFactory.getCurrentSession().createQuery(hql);
		return q.list();
	}

	@Override
	public List<T> find(String hql, Map<String, Object> params)
	{
		Query q = this.sessionFactory.getCurrentSession().createQuery(hql);
		if(params != null && !params.isEmpty())
		{
			for(String key : params.keySet())
			{
				q.setParameter(key, params.get(key));
			}
		}		
		return q.list();
	}

	@Override
	public List<T> find(String hql, int page, int rows)
	{
		Query q = this.sessionFactory.getCurrentSession().createQuery(hql);
		return q.setFirstResult((page - 1) * rows).setMaxResults(rows).list();
	}
	
	@Override
	public List<T> find(String hql, Map<String, Object> params, int page, int rows)
	{
		Query q = this.sessionFactory.getCurrentSession().createQuery(hql);
		if(params != null && !params.isEmpty())
		{
			for(String key : params.keySet())
			{
				q.setParameter(key, params.get(key));
			}
		}	
		return q.setFirstResult((page - 1) * rows).setMaxResults(rows).list();
	}

	@Override
	public Long count(String hql)
	{
		Query q = this.sessionFactory.getCurrentSession().createQuery(hql);
		return (Long) q.uniqueResult();
	}

	@Override
	public Long count(String hql, Map<String, Object> params)
	{
		Query q = this.sessionFactory.getCurrentSession().createQuery(hql);
		if(params != null && !params.isEmpty())
		{
			for(String key : params.keySet())
			{
				q.setParameter(key, params.get(key));
			}
		}			
		return (Long) q.uniqueResult();
	}


}

package sy.dao;

import java.io.Serializable;

public interface BaseDaoI<T>
{
	public Serializable save(T o);
	
	public T get(String hql);
}

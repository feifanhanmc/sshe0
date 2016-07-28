package sy.dao;

import java.io.Serializable;
import java.util.Map;

public interface BaseDaoI<T>
{
	public Serializable save(T o);
	
	public T get(String hql);
	public T get(String hql, Object[] params);
	public T get(String hql, Map<String ,Object> params);
}

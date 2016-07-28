package sy.dao;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public interface BaseDaoI<T>
{
	/*
	 * 增
	 */
	public Serializable save(T o);
	
	/*
	 * 删
	 */
	public void delete(T o);
	
	/*
	 * 改
	 */
	public void update(T o);
	
	/*
	 * 增或改
	 */
	public void saveOrUpdate(T o);
	
	/*
	 * 普通查询（返回一个对象）
	 */
	public T get(String hql);
	public T get(String hql, Object[] params);
	public T get(String hql, Map<String ,Object> params);
	
	/*
	 * 普通查询（返回多个对象）
	 */
	public List<T> find(String hql);
	public List<T> find(String hql, Map<String ,Object> params);
	
	/*
	 * 分页查询（返回多个对象）
	 */
	public List<T> find(String hql, int page, int rows);
	public List<T> find(String hql, Map<String ,Object> params, int page, int rows);
	
	/*
	 * 计算总记录数
	 */
	public Long count(String hql);
	public Long count(String hql, Map<String ,Object> params);
	
}

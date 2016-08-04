package sy.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.apache.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sy.dao.CourseDaoI;
import sy.dao.UserDaoI;
import sy.model.Mcourse;
import sy.pageModel.Course;
import sy.pageModel.DataGrid;
import sy.service.CourseServiceI;


@Service("courseService")
public class CourseServiceImpl implements CourseServiceI
{
	
	private static final Logger logger = Logger.getLogger(CourseServiceImpl.class);
	
	private CourseDaoI courseDao;
	
	private UserDaoI userDao;
	
	public CourseDaoI getCourseDao()
	{
		return courseDao;
	}

	@Autowired
	public void setCourseDao(CourseDaoI courseDao)
	{
		this.courseDao = courseDao;
	}
	
	public UserDaoI getUserDao()
	{
		return userDao;
	}

	@Autowired
	public void setUserDao(UserDaoI userDao)
	{
		this.userDao = userDao;
	}

	@Override
	public Course save(Course course)
	{
		Mcourse m = new Mcourse();
		BeanUtils.copyProperties(course, m);
		
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("name",course.getTname());
		String tid = (userDao.get("from Muser m where m.name = :name",params)).getId();
		m.setTid(tid);
		m.setCid(UUID.randomUUID().toString());
		m.setAverage(0);
		courseDao.save(m);
		BeanUtils.copyProperties(m, course);
		return course;
	}

	@Override
	public DataGrid datagrid(Course course)
	{
		DataGrid dg = new DataGrid();
		String hql = "from Mcourse m";
		Map<String, Object> params = new HashMap<String, Object>();
		hql = addWhere(course, hql, params);		
		String totalHql = "select count(*) " + hql;
		
		hql = addOrder(course, hql);
		
		List<Mcourse> l = courseDao.find(hql, params, course.getPage(), course.getRows());
		List<Course> nl = new ArrayList<Course>();
		changeModel(l, nl);
		dg.setTotal(courseDao.count(totalHql, params));
		logger.info("params from CourseService : " + params);
		dg.setRows(nl);
		return dg;
	}
	
	private void changeModel(List<Mcourse> l, List<Course> nl) 
	{
		if (l != null && l.size() > 0) 
		{
			for (Mcourse m : l) 
			{
				Course course = new Course();
				BeanUtils.copyProperties(m, course);
				nl.add(course);
			}
		}
	}
	
	private String addOrder(Course course, String hql) 
	{
		if (course.getSort() != null) 
		{
			hql += " order by " + course.getSort() + " " + course.getOrder();
		} 
		return hql;
	}

	private String addWhere(Course course, String hql, Map<String, Object> params) 
	{
		if (course.getCname() != null && !course.getCname().trim().equals("")) 
		{
			hql += " where m.cname like :cname";
			params.put("cname", "%%" + course.getCname().trim() + "%%");
		}
		return hql;
	}

	@Override
	public List exportExcel()
	{
		List<Mcourse> l = courseDao.find("from Mcourse m");
		return l;
	}

	@Override
	public void remove(String cids)
	{
		String [] ncids = cids.split(",");
		String hql = "delete Mcourse mcourse where mcourse.cid in (";
		for(int i = 0; i < ncids.length; i++)
		{
			if( i > 0)
			{
				hql += ",";				
			}
			hql += "'" + ncids[i] + "'";
		}
		hql += " ) ";
		courseDao.executeHql(hql);
		
	}
	
	@Override
	public Course edit(Course course)
	{
		Mcourse m = courseDao.get(Mcourse.class, course.getCid());
		BeanUtils.copyProperties(course, m, new String[]{"average"});
		return course;
	}
}

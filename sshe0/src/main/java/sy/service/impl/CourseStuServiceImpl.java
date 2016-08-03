package sy.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sy.dao.CourseDaoI;
import sy.dao.CourseStuDaoI;
import sy.dao.UserDaoI;
import sy.model.Mcourse;
import sy.model.McourseStu;
import sy.model.Muser;
import sy.pageModel.CourseStu;
import sy.pageModel.DataGrid;
import sy.service.CourseStuServiceI;


@Service("courseStuService")
public class CourseStuServiceImpl implements CourseStuServiceI
{

	private static final Logger logger = Logger.getLogger(UserServiceImpl.class);
	
	private CourseStuDaoI courseStuDao;
	
	private UserDaoI userDao;
	
	private CourseDaoI courseDao;
	
	
	
	public CourseStuDaoI getCourseStuDao()
	{
		return courseStuDao;
	}

	@Autowired
	public void setCourseStuDao(CourseStuDaoI courseStuDao)
	{
		this.courseStuDao = courseStuDao;
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
	
	public CourseDaoI getCourseDao()
	{
		return courseDao;
	}

	@Autowired
	public void setCourseDao(CourseDaoI courseDao)
	{
		this.courseDao = courseDao;
	}

	
	
	
	@Override
	public DataGrid datagrid(CourseStu courseStu, String tId)
	{
		/*
		 * 查询Muser表获取sname,sid
		 * 查询McourseStu表获取sid对应的grade,rank以及cid对应的cname
		 */
		DataGrid dg = new DataGrid();
		Map<String, Object> params = new HashMap<String, Object>();
		
//		从Muser中查询student
		String uhql = "from Muser um where um.role = 'student'";
//		总记录数,即学生总数
		String totalHql = "select count(*) " + uhql;
		dg.setTotal(userDao.count(totalHql, params));
//		执行student查询
//		List<Muser> ul = userDao.find(uhql, params, courseStu.getPage(), courseStu.getRows());
		List<Muser> ul = userDao.find(uhql);

		
//		查询此老师tId对应的cId和cName
		String thql =  "from Mcourse mc where mc.tid = '" + tId + "'";
		Mcourse mcourse = courseDao.get(thql);
		String cId = mcourse.getCid();
		String cName = mcourse.getCname();

		
//		课程查询
		String chql = "from McourseStu cm where cm.cid = '" + cId+ "'";
//		chql = addWhere(courseStu, chql, params);
//		chql = addOrder(courseStu, chql);
//		List<McourseStu> l = courseStuDao.find(chql, params, courseStu.getPage(), courseStu.getRows());
		List<McourseStu> l = courseStuDao.find(chql);
		
		
		
		List<CourseStu> nl = new ArrayList<CourseStu>();
	
		changeModel(l, nl, ul, cName);
		
		
		dg.setTotal(userDao.count(totalHql, params));
		dg.setRows(nl);
		return dg;
	}

	private void changeModel(List<McourseStu> l, List<CourseStu> nl, List<Muser> ul, String cName) 
	{	
		if (l != null && l.size() > 0) 
		{
			for(int i = 0; i < l.size(); i++)
			{
				McourseStu m = (McourseStu) l.get(i);
				CourseStu c = new CourseStu();

				c.setSid(m.getSid());
				c.setGrade(m.getGrade());
				c.setRank(m.getRank());
				c.setCname(cName);
				for(Muser ms : ul)
					if(ms.getId().equals(m.getSid()))
						c.setSname(ms.getName());

				nl.add(c);
			
			}
		}
	}
	
	private String addOrder(CourseStu courseStu, String hql) 
	{
		if (courseStu.getSort() != null) 
		{
			hql += " order by " + courseStu.getSort() + " " + courseStu.getOrder();
		}
		return hql;
	}

	private String addWhere(CourseStu courseStu, String hql, Map<String, Object> params) 
	{
		if (courseStu.getSname() != null && !courseStu.getSname().trim().equals("")) 
		{
			hql += " where m.name like :name";
			params.put("name", "%%" + courseStu.getSname().trim() + "%%");
		}
		return hql;
	}

}

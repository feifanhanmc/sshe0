package sy.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

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
		List<Muser> ul = userDao.find(uhql, params, courseStu.getPage(), courseStu.getRows());
//		List<Muser> ul = userDao.find(uhql);

		
//		查询此老师tId对应的cId和cName
		String thql =  "from Mcourse mc where mc.tid = '" + tId + "'";
		Mcourse mcourse = courseDao.get(thql);
		String cId = mcourse.getCid();
		String cName = mcourse.getCname();

		
//		课程查询
		String chql = "from McourseStu cm where cm.cid = '" + cId+ "'";
		chql = addWhere(courseStu, chql, params);
		chql = addOrder(courseStu, chql);
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

				c.setCsid(m.getCsid());
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
//			hql += " where m.name like :name";
			hql += " and m.name like :name";
			params.put("name", "%%" + courseStu.getSname().trim() + "%%");
		}
		return hql;
	}

	@Override
	public CourseStu edit(CourseStu courseStu)
	{
		McourseStu m = courseStuDao.get(McourseStu.class, courseStu.getCsid());
		float grade = courseStu.getGrade();
		
		//如果分数确实有修改，则自动更新成绩单
		int rankChange = 0;
		float oldGrade = courseStuDao.get("from McourseStu m where m.csid = '" + courseStu.getCsid() + "'").getGrade();
		if(oldGrade != grade)
			rankChange = updateMcourseStuGrade(oldGrade, grade, m.getCid(), m.getCsid());
		
		m.setGrade(grade);
		m.setRank(m.getRank() + rankChange);
		return courseStu;
	}

	@Override
	public List exportExcel(String tid)
	{
		String cid = courseDao.get("from Mcourse m where m.tid = '" + tid + "'").getCid();
		
		//这里要限制只能打印此教师的课程学生信息
		List<McourseStu> l = courseStuDao.find("from McourseStu m where m.cid = '" + cid + "'");
		return l;
	}

	@Override
	public String getCname(String cid)
	{
		return courseDao.get("from Mcourse cm where cm.cid = '" + cid+ "'").getCname().toString();
	}

	@Override
	public String getSname(String sid)
	{
		return userDao.get("from Muser m where m.id = '" + sid+ "'").getName().toString();
	}

	@Override
	public void updateMcourseStuForm(String cid)
	{
		List<Muser> muser = userDao.find("from Muser m where m.role = 'student'");
		for(Muser m : muser)
		{
			McourseStu mstu = new McourseStu();
			mstu.setCid(cid);
			mstu.setGrade(0);
			mstu.setRank(0);
			mstu.setSid(m.getId());
			mstu.setCsid(UUID.randomUUID().toString());
			
			courseStuDao.save(mstu);
		}		
	}

	@Override
	public int updateMcourseStuGrade(float oldGrade, float grade, String cid, String csid)
	{
		
		float average = 0;
		float total = 0;
		int sum = 0;
		int rankChange = 0;
		
		List<McourseStu> mstu = courseStuDao.find("from McourseStu m where m.cid = '" + cid + "'");
		for(McourseStu m : mstu)
		{
			total += m.getGrade();
			sum++;
			//更新Rank
			if(oldGrade < m.getGrade() && m.getGrade() < grade)
			{
				m.setRank(m.getRank() + 1);
				rankChange--;
			}
			else if(oldGrade > m.getGrade() && m.getGrade() > grade)
			{
				m.setRank(m.getRank() - 1);
				rankChange++;
			}
		}


		
		//更新Mcourse Average
		total = total - oldGrade + grade;
		sum = mstu.size();
		average = total/sum;
		courseDao.get("from Mcourse m where m.cid = '" + cid + "'").setAverage(average);
		
		return rankChange;
	}

	
	
}

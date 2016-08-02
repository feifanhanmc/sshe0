package sy.service.impl;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sy.dao.CourseStuDaoI;
import sy.service.CourseStuServiceI;


@Service("courseStuService")
public class CourseStuServiceImpl implements CourseStuServiceI
{

	private static final Logger logger = Logger.getLogger(UserServiceImpl.class);
	
	private CourseStuDaoI courseStuDao;

	public CourseStuDaoI getCourseStuDao()
	{
		return courseStuDao;
	}

	@Autowired
	public void setCourseStuDao(CourseStuDaoI courseStuDao)
	{
		this.courseStuDao = courseStuDao;
	}
	

}

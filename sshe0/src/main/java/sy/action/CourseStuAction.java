package sy.action;

import org.apache.log4j.Logger;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.springframework.beans.factory.annotation.Autowired;

import sy.pageModel.CourseStu;
import sy.service.CourseStuServiceI;

import com.opensymphony.xwork2.ModelDriven;


@Namespace("/")
@Action("courseStuAction")
public class CourseStuAction extends BaseAction implements ModelDriven<CourseStu>
{
	CourseStu courseStu = new CourseStu();

	@Override
	public CourseStu getModel()
	{
		return courseStu;
	}
	
	public static final String FILE_SEPARATOR = System.getProperties().getProperty("file.separator");
	
	private static final Logger logger = Logger.getLogger(UserAction.class);
	
	private CourseStuServiceI courseStuService;

	public CourseStuServiceI getCourseStuService()
	{
		return courseStuService;
	}

	@Autowired
	public void setCourseStuService(CourseStuServiceI courseStuService)
	{
		this.courseStuService = courseStuService;
	}
	
	public void datagrid()
	{
		String tId = super.finalId;
		super.writeJson(courseStuService.datagrid(courseStu, tId));
	}
	
		
}

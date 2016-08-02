package sy.dao.impl;

import org.springframework.stereotype.Repository;

import sy.dao.CourseDaoI;
import sy.model.Mcourse;


@Repository("courseDao")
public class CourseDaoImpl extends BaseDaoImpl<Mcourse> implements CourseDaoI
{

}

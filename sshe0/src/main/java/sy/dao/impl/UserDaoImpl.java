package sy.dao.impl;

import org.springframework.stereotype.Repository;

import sy.dao.UserDaoI;
import sy.model.Muser;


@Repository("userDao")
public class UserDaoImpl extends BaseDaoImpl<Muser> implements UserDaoI
{

}

package sy.service;

import java.io.Serializable;

import sy.model.Tuser;

public interface UserServiceI
{

	public void test();
	
	public Serializable save(Tuser t);
	
	public void add(String name,String pwd);
}

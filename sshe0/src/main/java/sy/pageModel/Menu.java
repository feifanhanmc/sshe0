package sy.pageModel;

import org.apache.log4j.Logger;

public class Menu
{
	
	private static final Logger logger = Logger.getLogger(Menu.class);

	private String state;
	private String pid;
	private String ptext;
	
	private String id;
    private String text;
    private String iconcls;
    private String url;
    
    
    
    public String getState()
	{
		return state;
	}

	public void setState(String state)
	{
		this.state = state;
	}

	public String getPid()
	{
		return pid;
	}

	public void setPid(String pid)
	{
		this.pid = pid;
	}

	public String getPtext()
	{
		return ptext;
	}

	public void setPtext(String ptext)
	{
		this.ptext = ptext;
	}


    
	public String getId()
	{
		return id;
	}
	
	public void setId(String id)
	{
		this.id = id;
	}
	
	public String getText()
	{
		return text;
	}
	
	public void setText(String text)
	{
		this.text = text;
	}
	
	public String getIconcls()
	{
		return iconcls;
	}
	
	public void setIconcls(String iconcls)
	{
		this.iconcls = iconcls;
	}
	
	public String getUrl()
	{
		return url;
	}
	
	public void setUrl(String url)
	{
		this.url = url;
	}
    
    
    
	
    
}

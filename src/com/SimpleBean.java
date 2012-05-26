package com;

public class SimpleBean {
	
	private String name="lucene";
	
	public SimpleBean()
	{}
	
	public SimpleBean(String name)
	{
		setname(name);
	}

	public void setname(String newname)
	{
		name=newname;
	}
	
	public String getname()
	{
		return(name);
	}
	
}

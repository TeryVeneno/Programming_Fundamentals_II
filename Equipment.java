
public class equipment1 {
	private boolean morning, afternoon, evening;
	private float salary;
	private String type;
	private Worker worker;
	
	public equipment1 (float s, String t, boolean m, boolean a, boolean e)
	{
		morning = m;
		afternoon = a;
		evening = e;
		type = t;
		salary = s;
		
	}
public boolean morning() 
	{
		return morning;
	}
public boolean afternoon ()
	{
	return afternoon;
	}
public boolean evening ()
	{
	
	return evening;
	}
public float salary ()
	{
	return salary;
	}
public String type ()
	{
	return type;
	
}
public void  setworker(Worker w)
{
  worker = w;	
}
public Worker getworker;
{
	worker= w;
}
}
package course.labs.Lab4;

//Class Made By: Ajelet && Milad

public class Equipment {
	private boolean morning, afternoon, evening;
	private float cost;
	private String type;
	private Worker worker;
	
	public Equipment (float c, String t, boolean m, boolean a, boolean e)
	{
		morning = m;
		afternoon = a;
		evening = e;
		type = t;
		cost = c;
		
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
	public float get_cost ()
		{
			return cost;
		}
	public String type ()
		{
		return type;
		}
	public void  setworker(Worker w)
	{
	  worker = w;
	}
	public Worker getworker ()
	{
		return worker;
	}
}
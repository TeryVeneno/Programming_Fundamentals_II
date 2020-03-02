package course.labs.multiple.Lab4;

//Class Made By: Ajelet && Milad

public class Equipment {
	private int[] can_work;
	private int cost;
	private String type;
	private Worker worker;
	private int workable_shifts;
	private int shifts_worked = 0;

	public Equipment (int c, String t, int m, int a, int e, int ws)
	{
		can_work = new int[]{m,a,e};
		type = t;
		cost = c;
		workable_shifts = ws;
	}
	public Equipment (int c, String t, int m, int a, int e)
		{
			this (c, t, m, a, e, 3);
		}
	public boolean get_can_work (int shift_time)
		{
			if (can_work[shift_time] == 1)
				return true;
			return false;
		}
	public int get_cost ()
		{
			return cost;
		}
	public String get_type ()
		{
		return type;
		}
	public int get_shifts_worked ()
		{
			return shifts_worked;
		}
	public int get_workable_shifts ()
		{
			return workable_shifts;
		}
	public void  setworker(Worker w)
	{
	  worker = w;
	}
	public Worker getworker ()
	{
		return worker;
	}
	public void reset_shifts_worked ()
		{
			shifts_worked = 0;
		}
	public void increase_shifts_worked ()
		{
			shifts_worked++;
		}
}
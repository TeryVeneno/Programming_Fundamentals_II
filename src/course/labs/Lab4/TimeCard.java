package course.labs.Lab4;

public class TimeCard {
	

	  private boolean isworking;
	   private ArrayList<Shift> shifts = new ArrayList<>();
	   
	   private float salary;
	   
	   public TimeCard(float salary)
	   	   {
		this.salary=salary;
	   	   }
	   
	   public float getSalary() 
	   {
		   return shifts.size()*salary;
	   }
	   
	   public boolean isworking()
	   {
		   return isworking;
	   }
	   
	   public void setWorking(boolean a)
	   {
		   isworking=a;
	   }
	   
	   public void addShift(String projectname, int time)
	   {
		   shifts.add(new Shift(projectname,time))
	   }
	  
	  
	  
   }
}

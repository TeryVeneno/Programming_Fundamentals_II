package course.labs.Lab4;
//class made by Ajelet && Mateilda 
public class Management 
{
	private ArrayList<Equipment> equipments = new ArrayList<>();
	
	public Equipment request_equipment(String type)
	{
		for (int i = 0; i < equipments.size(); i++) {
			if (equipments.get(i).get_type()==type) 
			{
				Equipment temp = equipments.get(i);
				equipments.remove(i);
				return temp;
			}
		}
		return null;
	}
	
	public void return_equipment(Equipment e) {
		equipments.add(e);
	}
	
}

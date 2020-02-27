package course.labs.Lab4;

import java.util.ArrayList;
import java.util.Arrays;

//class made by Ajelet && Mateilda
public class Management 
{
	private ArrayList<Equipment> equipments = new ArrayList<>();
	
	public Equipment request_equipment(String type, int shift_time)
	{
		for (int i = 0; i < equipments.size(); i++) {
			if (shift_time == 0)
				equipments.get(i).reset_shifts_worked();
			if (equipments.get(i).get_type()==type && equipments.get(i).get_can_work(shift_time))
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

	public void add_equipment (Equipment[] e) {
		equipments.addAll(Arrays.asList(e));
	}

	public ArrayList<Equipment> get_equipment_list () {
		return equipments;
	}
	
}

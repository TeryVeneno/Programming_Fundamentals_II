package course.labs.Lab4;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class ConstructionTeam {
    private Project project;
    private ArrayList<Equipment> equipments;
    private Foreman foreman;
    private Requirement cur_requirement;
    private int shift_time;
    private boolean started;

    public ConstructionTeam (Foreman f, Project p) {
        foreman = f;
        project = p;
        equipments = new ArrayList<>();
        shift_time = 0;
        cur_requirement = p.get_requirement();
        started = false;
    }

    public void return_equipment () {
        for (int i = 0; i < equipments.size(); i++)
            foreman.return_equipment(equipments.get(i));
        equipments.clear();
    }

    private void get_equipment () {
        if (cur_requirement.fulfilled()) {
            project.update_requirements();
            cur_requirement = project.get_requirement();
        } else {
            started = true;
            ArrayList<SubRequirement> subrequirements = cur_requirement.get_subrequirements();
            for (SubRequirement r : subrequirements) {
                int shifts = r.get_shifts();
                for (int i = 0; i < shifts; i++) {
                    if (i < r.get_amount_allowed()) {
                        Equipment temp = foreman.get_equipment(r.get_type(), shift_time);
                        if (temp != null)
                            equipments.add(temp);
                    }
                }
            }
        }
    }

    public ArrayList<Equipment> get_equipment_list () {
        return equipments;
    }

    public int get_cost () {
        get_equipment();
        int ret = 0;
        for (Equipment e : equipments) {
            ret += e.getworker().get_salary();
        }
        return (int)(ret*.10*equipments.size()+ret);
    }

    public boolean is_started () {
        return started;
    }

    public Project get_project () {
        return project;
    }

    public void update_project () {
        ArrayList<SubRequirement> subrequirements = cur_requirement.get_subrequirements();
        for (int i = 0; i < subrequirements.size(); i++)
            if (subrequirements.get(i).get_type() == "")
                subrequirements.get(i).fulfill_shifts(0);
        for (Equipment e : equipments)
            for (int i = 0; i < subrequirements.size(); i++) {
                if (subrequirements.get(i).get_type() == e.get_type()) {
                    int num_equipment = 0;
                    for (int j = 0; j < equipments.size(); j++)
                        if (equipments.get(i).get_type() == e.get_type())
                            num_equipment++;
                        subrequirements.get(i).fulfill_shifts(num_equipment);
                        e.getworker().add_shift(project.get_name(), shift_time);
                }
            }
        project.update_requirements();
        update_shift_time();
    }

    private void update_shift_time () {
        if (shift_time > 1)
            shift_time = 0;
        shift_time++;
    }
}

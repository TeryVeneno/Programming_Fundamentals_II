package course.labs.multiple.Lab4;

import java.util.ArrayList;

// Class Made By: Everyone

public class Foreman {
    private ArrayList<ConstructionTeam> teams = new ArrayList<>();
    private Management management;

    public Foreman (Management m) {
        management = m;
    }

    public void add_project (Project project) {
        teams.add(new ConstructionTeam(this, project));
    }

    public Equipment get_equipment (String type, int shift_time) {
       return management.request_equipment(type, shift_time);
    }

    public void return_equipment (Equipment e) {
        management.return_equipment(e);
    }

    public int get_cost_per_shift (int index) {
        int ret = teams.get(index).get_cost();
        teams.get(index).update_project();
        return ret;
    }

    public void start () {
        ArrayList<Equipment> all_equipments = management.get_equipment_list();
        int day = 1;
        boolean not_finished = true;

        while (not_finished) {
            int costs[] = new int[teams.size()];
            int total_costs[] = new int[teams.size()];
            int all_finished = 0;
            for (ConstructionTeam ct : teams)
                if (ct.get_project().fulfilled())
                    all_finished++;
            if (all_finished == teams.size())
                not_finished = false;

            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < teams.size(); j++) {
                    total_costs[j] += get_cost_per_shift(j);
                    System.out.println(teams.get(j).get_project().get_name());
                    System.out.print("Day " + day);
                    switch (i) {
                        case 0:
                            System.out.println(" (Morning)");
                            break;
                        case 1:
                            System.out.println(" (Afternoon)");
                            break;
                        case 2:
                            System.out.println(" (Evening)");
                            break;
                    }
                    ArrayList<Equipment> equipment_on_shift = teams.get(i).get_equipment_list();
                    for (Equipment e : equipment_on_shift)
                        System.out.println(e.get_type() + "(" + e.getworker().get_name() + ")");
                }
                for (ConstructionTeam ct : teams)
                    teams.get(i).return_equipment();
            }
            for (int i = 0; i < teams.size(); i++) {
                System.out.println(teams.get(i).get_project().get_name());
                System.out.println("Day "+day+" (Expenses): "+total_costs[i]);
            }
            day++;
        }
    }
}

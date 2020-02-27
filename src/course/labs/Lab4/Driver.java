package course.labs.Lab4;

import com.sun.org.apache.regexp.internal.RE;

import java.util.ArrayList;
import java.util.Random;

public class Driver {
    public static void main(String[] args) {
        Random rand = new Random();
        Equipment[] equipments = new Equipment[15];
        equipments[0] = new Equipment(850, "cement truck", 1, 1,1);
        equipments[1] = new Equipment(850, "cement truck", 0,0,1);
        equipments[2] = new Equipment(200, "carpenter", 1, 1, 1, 2);
        equipments[3] = new Equipment(200, "carpenter", 1, 1, 1, 2);
        equipments[4] = new Equipment(200, "carpenter", 1, 1, 1, 2);
        equipments[5] = new Equipment(200, "carpenter", 1, 1, 1, 2);
        equipments[6] = new Equipment(200, "carpenter", 1, 1, 1, 2);
        equipments[7] = new Equipment(150, "backhoe", 1, 1,1);
        equipments[8] = new Equipment(150, "backhoe", 1, 1,1);
        equipments[9] = new Equipment(850, "cement truck", 1, 1,1);
        equipments[10] = new Equipment(250, "electrician", 1, 1,1);
        equipments[11] = new Equipment(250, "electrician", 1, 1,0);
        equipments[12] = new Equipment(250, "electrician", 1, 1,0);
        equipments[13] = new Equipment(70, "plumber", 1, 1,1);
        equipments[14] = new Equipment(70, "plumber", 1, 1,1);

        Worker[] workers = new Worker[15];
        for (int i = 0; i < 15; i++) {
            workers[i] = new Worker(equipments[i], rand.nextFloat()+"", rand.nextInt()+"");
        }

        Requirement[] house_reqs = new Requirement[4];
        house_reqs[0] = new Requirement(new SubRequirement(2, "backhoe", 1));
        house_reqs[0].add_subrequirement(new SubRequirement(1, "carpenter"));
        house_reqs[0].add_subrequirement(new SubRequirement(1, "electrician"));
        house_reqs[0].add_subrequirement(new SubRequirement(1, "plumber"));

        house_reqs[1] = new Requirement(new SubRequirement(2, "cement truck"));
        house_reqs[1].add_subrequirement(new SubRequirement(1, "plumber"));
        house_reqs[1].add_subrequirement(new SubRequirement(1, "electrician"));

        house_reqs[2] = new Requirement(new SubRequirement(12, "carpenter"));
        house_reqs[2].add_subrequirement(new SubRequirement(3, "electrician"));
        house_reqs[2].add_subrequirement(new SubRequirement(1, "plumber"));

        house_reqs[3] = new Requirement(new SubRequirement(3, "carpenter", 5));
        house_reqs[3].add_subrequirement(new SubRequirement(3, "crane"));
        house_reqs[3].get_subrequirement(1).add_additional(house_reqs[3].get_subrequirement(0));
        house_reqs[3].get_subrequirement(0).add_additional(house_reqs[3].get_subrequirement(1));

        Project house = new Project("House", house_reqs);

        Requirement[] repair_reqs = new Requirement[4];
        repair_reqs[0] = new Requirement(new SubRequirement(6, "carpenter"));
        repair_reqs[0].add_subrequirement(new SubRequirement(1, "electrician"));

        repair_reqs[1] = new Requirement(new SubRequirement(1, "plumber"));
        repair_reqs[1].add_subrequirement(new SubRequirement(1, "electrician"));
        repair_reqs[1].get_subrequirement(1).add_additional(repair_reqs[1].get_subrequirement(0));
        repair_reqs[1].get_subrequirement(0).add_additional(repair_reqs[1].get_subrequirement(1));
        repair_reqs[1].add_subrequirement(new SubRequirement(1, "crane"));
        repair_reqs[1].add_subrequirement(new SubRequirement(1, "carpenter", 2));
        repair_reqs[1].get_subrequirement(2).add_additional(repair_reqs[1].get_subrequirement(3));
        repair_reqs[1].get_subrequirement(3).add_additional(repair_reqs[1].get_subrequirement(2));

        repair_reqs[2] = new Requirement(new SubRequirement(1, "plumber"));
        repair_reqs[2].add_subrequirement(new SubRequirement(1, "electrician"));
        repair_reqs[2].add_subrequirement(new SubRequirement(1, "carpenter"));

        repair_reqs[3] = new Requirement(new SubRequirement(2, "electrician"));
        repair_reqs[3].add_subrequirement(new SubRequirement(2, "carpenter"));

        Project repairs = new Project("Repairs", repair_reqs);

        Requirement[] event_center_reqs = new Requirement[6];
        event_center_reqs[0] = new Requirement(new SubRequirement(9, "backhoe"));

        event_center_reqs[1] = new Requirement(new SubRequirement(3, "electrician", 3));

        event_center_reqs[2] = new Requirement(new SubRequirement(4, "cement truck"));
        event_center_reqs[2].add_subrequirement(new SubRequirement(2, "plumber"));
        event_center_reqs[2].get_subrequirement(0).add_additional(event_center_reqs[2].get_subrequirement(1));
        event_center_reqs[2].get_subrequirement(1).add_additional(event_center_reqs[2].get_subrequirement(0));
        event_center_reqs[2].add_subrequirement(new SubRequirement(1, "electrician"));

        event_center_reqs[3] = new Requirement(new SubRequirement(2, "crane"));
        event_center_reqs[3].add_subrequirement(new SubRequirement(10, "plumber", 2));
        event_center_reqs[3].get_subrequirement(0).add_additional(event_center_reqs[3].get_subrequirement(1));
        event_center_reqs[3].get_subrequirement(1).add_additional(event_center_reqs[3].get_subrequirement(0));

        event_center_reqs[4] = new Requirement(new SubRequirement(4, "carpenter"));
        event_center_reqs[4].add_subrequirement(new SubRequirement(2, "crane"));
        event_center_reqs[4].add_subrequirement(new SubRequirement(3, "electrician"));
        event_center_reqs[4].add_subrequirement(new SubRequirement(1, "plumber"));

        event_center_reqs[5] = new Requirement(new SubRequirement(6, "carpenter"));
        event_center_reqs[5].add_subrequirement(new SubRequirement(1, "crane"));

        Project event_center = new Project("Event Center", event_center_reqs);

        Management management = new Management();
        management.add_equipment(equipments);
        Foreman foreman = new Foreman(management);
        foreman.add_project(house);
        foreman.add_project(repairs);
        foreman.add_project(event_center);

        foreman.start();
    }
}

package course.labs.Lab4;

// Class Made By: Simon

public class Worker {

    private TimeCard time_card;
    private Equipment equipment;
    private String type;
    private String name;
    private String address;


    public Worker (Equipment e, String name, String address) {
        // create time_card, salary
        time_card = new TimeCard(e.get_cost());
        equipment = e;
        type = e.get_type();
        this.name = name;
        this.address = address;
        e.setworker(this);
    }

    // To be finished
    public float get_salary () {
        if (equipment.get_type() == "electrician" && equipment.get_shifts_worked() == 2)
            return time_card.getSalary()+100;
        return time_card.getSalary();
    }

    public boolean is_working () {
        return time_card.isworking();
    }

    public void set_working (boolean w) {
        // set timecard working to true or false
    }

    public String get_name () {
        return name;
    }

    public String get_type () {
        return type;
    }

    public String get_address () {
        return address;
    }

    public Equipment get_equipment () {
        return equipment;
    }

    public void add_shift (String projectname, int time) {
        time_card.addShift(projectname, time);
    }
}

package course.labs.Lab4;

public class Worker {

    private TimeCard time_card;
    private Equipment equipment;
    private String type;
    private String name;
    private String address;


    public Worker (float salary, String type, String name, String address) {
        // create time_card, salary
        time_card = new TimeCard();
        this.type = type;
        this.name = name;
        this.address = address;
    }

    // To be finished
    public float get_salary (int shifts) {
        // time_card method returns salary based on number of shifts
        return 0;
    }

    public void set_equipment (Equipment e) {
        equipment = e;
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
}

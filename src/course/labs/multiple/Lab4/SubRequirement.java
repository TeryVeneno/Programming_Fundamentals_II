package course.labs.multiple.Lab4;

// Class Made By: Everyone

public class SubRequirement {
    private int num_shifts;
    private String type;
    private int amount_allowed;
    private int amount_required;
    private int num_equipment = 1;
    SubRequirement additional_requirement;

    public SubRequirement (int s, String t, int aa, int ar) {
        num_shifts = s;
        type = t;
        amount_allowed = aa;
        amount_required = ar;
    }

    public SubRequirement (int s, String t, int ar) {
        this(s, t, Integer.MAX_VALUE, ar);
    }

    public SubRequirement (int s, String t) {
        this(s, t, Integer.MAX_VALUE, 1);
    }

    public SubRequirement (SubRequirement s) {
        this (s.get_shifts(), s.get_type(), s.get_amount_allowed());
    }

    public void add_additional (SubRequirement r) {
        additional_requirement = r;
    }

    public int get_shifts () {
        return num_shifts;
    }

    public String get_type () {
        return type;
    }

    public int get_amount_allowed () {
        return amount_allowed;
    }

    public boolean fulfilled () {
        if (num_shifts == 0)
            return true;
        return false;
    }

    public boolean check_num_equipment () {
        return num_equipment < amount_required;
    }

    public void fulfill_shifts (int num_equipment) {
        if (num_equipment < amount_required || (additional_requirement != null && !additional_requirement.check_num_equipment())) {
            this.num_equipment = num_equipment;
            return;
        }
        this.num_equipment = num_equipment;
        num_shifts--;
    }
}

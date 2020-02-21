package course.labs.Lab4;

// Class Made By: Everyone

public class SubRequirement {
    private int num_shifts;
    private String type;

    public SubRequirement (int s, String t) {
        num_shifts = s;
        type = t;
    }

    public SubRequirement (SubRequirement s) {
        this (s.get_shifts(), s.get_type());
    }

    public int get_shifts () {
        return num_shifts;
    }

    public String get_type () {
        return type;
    }
}

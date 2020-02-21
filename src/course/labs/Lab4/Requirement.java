package course.labs.Lab4;

// Class Made By: Everyone

import java.util.ArrayList;
import java.util.Arrays;

@SuppressWarnings("CopyConstructorMissesField")
public class Requirement {

    private ArrayList<SubRequirement> subrequirements;
    private int internal_index = 0;

    public Requirement () {
        subrequirements = new ArrayList<>(1);
    }

    public Requirement (ArrayList<SubRequirement> subreqs) {
        subrequirements = new ArrayList<>(subreqs.size());
        for (SubRequirement subreq : subreqs) {
            subrequirements.add(new SubRequirement(subreq));
        }
    }

    public Requirement (Requirement r) {
        this(r.get_subrequirements());
    }

    public void add_subrequirement (SubRequirement sr) {
        subrequirements.add(sr);
    }

    public SubRequirement get_subrequirement () {
        if (internal_index < subrequirements.size())
            internal_index++;
        else
            return null;
        return subrequirements.get(internal_index-1);
    }

    public SubRequirement get_subrequirement (int index) {
        if (index >= subrequirements.size())
            index = subrequirements.size()-1;
        return subrequirements.get(index);
    }

    public ArrayList<SubRequirement> get_subrequirements () {
        ArrayList<SubRequirement> ret = new ArrayList<>(subrequirements.size());
        for (SubRequirement subrequirement : subrequirements) {
            ret.add(new SubRequirement(subrequirement));
        }
       return ret;
    }
}

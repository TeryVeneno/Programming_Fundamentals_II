package course.labs.Lab4;

// Class Made By: Everyone

import java.util.ArrayList;

public class Project {
    private ArrayList<Requirement> requirements;
    private int internal_index;

    public Project () {
        requirements = new ArrayList<>();
        internal_index = 0;
    }

    public void add_requirement (Requirement r) {
        requirements.add(r);
    }

    public Requirement get_requirement () {
        if (internal_index < requirements.size())
            internal_index++;
        else
            return null;
        return requirements.get(internal_index-1);
    }

    public Requirement get_requirement (int index) {
        if (index >= requirements.size())
            index = requirements.size()-1;
        return requirements.get(index);
    }

    public ArrayList<Requirement> get_requirements () {
        ArrayList<Requirement> ret = new ArrayList<>(requirements.size());
        for (Requirement requirement : requirements) {
            ret.add(new Requirement(requirement));
        }
        return ret;
    }
}

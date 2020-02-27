package course.labs.Lab4;

// Class Made By: Everyone

import java.util.ArrayList;
import java.util.Arrays;

public class Project {
    private ArrayList<Requirement> requirements;
    private int internal_index;
    String name;

    public Project (String name, Requirement[] r) {
        requirements = new ArrayList<>();
        internal_index = 0;
        this.name = name;
        requirements.addAll(Arrays.asList(r));
    }

    public String get_name () {
        return name;
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

    // Change calculations to show shifts
    public int length () {
        int ret = 0;
        for (Requirement r : requirements)
            ret += r.get_length();
        return ret;
    }

    public void update_requirements () {
        for (int i = 0; i < requirements.size(); i++)
            if (requirements.get(i).fulfilled()) {
                if (internal_index > i)
                    internal_index--;
                requirements.remove(i);
            }
    }

    public boolean fulfilled () {
        if (requirements.size() == 0)
            return true;
        return false;
    }
}

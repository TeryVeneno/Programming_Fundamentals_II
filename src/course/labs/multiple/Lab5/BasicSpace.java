package course.labs.multiple.Lab5;

import java.util.ArrayList;

/**
 * Some of the basics here, but you will probably need to edit or rework this class entirely
 */
class BasicSpace extends Space
{
    public BasicSpace(String display, int x, int y)
    {
        super(display, x, y);
    }
    public void land(Player p)
    {
        if (owner == null)
            p.addScore((int)(Math.random()*3)+1);
        else
            ;//??? how to handle this ???
        this.numberOfTimesLandedOn++;
    }
    public String getLine(int line)
    {
        switch(line) {
            case 3:
                if (rent >= 10)
                    return "|R: " + rent + "|";
                else
                    return "|R: " + rent + "  |";
            case 4:
                if (owner != null)
                    return "|O: "+owner.symbol+"  |";
                else
                    return "|O: N/A|";
            case 5:
                String ret = "| ";
                for (Player p : players_on_space)
                    ret += p.symbol;
                int n = 7-ret.length();
                for (int i = 0; i < n; i++) {
                    ret += " ";
                }
                return ret+"|";

        }
        return super.getLine(line);
    }
}

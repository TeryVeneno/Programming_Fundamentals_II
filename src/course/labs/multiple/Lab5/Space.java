package course.labs.multiple.Lab5;

import java.util.ArrayList;

/**
 * Basic class for each space/property in the game (I say space because some may not be properties, if you add things like chance, income tax, free parking)
 * This should be extended for each type of space on the board
 */
abstract class Space
{
    Player owner; 				//player that owns me or null
    int x, y; 					//my x and y for display purposes
    String display;				//6 character display
    int numberOfTimesLandedOn; 	//increases each time landed on
    int rent;					//set by the owning player
    protected ArrayList<Player> players_on_space = new ArrayList<>();
    public static final int number_of_display_lines = 7; //this will need to change if you change the size or shape of the spaces
    public Space(String display, int x, int y)
    {
        this.display = display;
        this.x = x;
        this.y = y;
    }

    /**
     * Different squares do different things when you land on them
     * @param p is the player ref. that landed on this space
     */
    public abstract void land(Player p);

    //optionally you may use/need an "unland" method to be called to do things
    //when your player leaves the square

    public void setOwner(Player newOwner)
    {
        owner = newOwner;
    }
    /**
     * sells the property (sets variables within Space)
     * @return sales profit from the property
     */
    public int sellProperty()
    {
        numberOfTimesLandedOn = 0;
        owner = null;//removes reference to the owner
        return 10*numberOfTimesLandedOn;
    }

    public ArrayList<Player> get_players_on_space () {
        return new ArrayList<>(players_on_space);
    }
    /**
     * gets the individual lines of a Space (students may edit this to account for other information to be displayed)
     * @param line to be displayed
     * @return line as a string (based on the variables within space)
     */
    public String getLine(int line)
    {
        switch(line)
        {
            case 0:
                return "~~~~~~~~";
            case 1:
                String ret = "|"+display;
                for (int i = 0; i < 6-display.length();i++)
                    ret+=" ";
                return ret+"|";
            case 2:
                return "~~~~~~~~";
            case 3:
                return "|      |";
            case 4:
                return "|      |";
            case 5:
                return "|      |";
            case 6:
                return "~~~~~~~~";

        }
        return "        ";
    }
}

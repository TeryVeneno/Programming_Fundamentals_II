package course.labs.multiple.Lab5;

/**
 * Base player class
 * players should be responsible for their actions (buy, sell, move...)
 * This should be extended for any additional types of players to play the game
 */
class Player
{
    //score/money/dollars/gold/tokens/moolah whatever you want to call this
    private int score;
    public final String name, symbol; //name has to fit in the spaces to know who owns the property

    public Player(String name, String symbol)
    {
        this.name = name;
        this.symbol = symbol;
        score = 50;
    }
    public void addScore(int amt)
    {
        score += amt;
    }
    public void buyProperty(Space s)
    {
        //score -= cost of the property
        s.setOwner(this);
    }
    public void sellProperty(Space s)
    {
        //score += cost of selling property
        score += s.sellProperty();
    }
    public void takeTurn()
    {
        //move()
        //do I own the property I landed on
        //change rent, or sell property
        //is the property unowned?
    }
    public void move()
    {
        //roll dice
        //navigate branches (this may require you to redraw the board and ask the player which path to follow)
        //decide if I want to stop should I land on any properties with moves<=3
    }
}

package course.labs.multiple.Lab5;

//You will be implementing a property buying/selling game with multiple players
//below is a description of features and some code to get you started:

//Display
//The display can be modified in any way you see fit to improve readability
    //Each Square should show some kind of name (they could follow a theme)
    //Squares should show their owner (can be blank if not owned by any player)
    //Squares should also show where each player is in the game
    //(if a player is on the square they should be shown somehow)
    //The board should be drawn at the start of each players turn, the
    //player's name and money/gold/currency stuffs should be shown as well

//Players
//Movement
//players move through the squares (adjacent squares and they can't go backwards)
//to move they roll 2, 6 sided dice (2-12 randomly)
//each move through a space that you do not own cost 2 moves
//each move through a space that you do own costs 1 move
//if you have 3 or less moves remaining and you land on your own space you may choose to stop (and pay your rent)
//when they reach a branch they can choose which direction to go (again can't go backwards)
//Other actions
//players can buy property
    //each property you buy cost 10 + 10 * amount of properties you own + 1 * number of times laned on
    //question to ask yourself is how does a player keep track of the properties they own?
//players can also sell property
    //property accrues value each time someone lands on the property
    //sell value = 10 * number of times landed on
    //selling a property resets the value (number of times landed on is returned to 0)
//when a player buys a property they can set the rent
    //when you land on your property you can choose to reset the rent as well
//rent can be set from 0-10 + 5 per adjacent squares owned (if I own 4 in a row I can set the price for 0 to 30)
    //remember if you land on your own properties you still must pay rent (that goes to the bank - there's no such thing as a free lunch)
    //so it could be useful to create safe zones (places that people want to go to raise property values)
    //if others land on your property they pay you the rent
//unowned properties give you 1-3 dollar/token/gold/whatever we call currency when you land on them

//Starting conditions
//players start with 50 coins/dollars/gold.... we need to agree on terminology here...
    //The board designates a starting area
    //players order is randomly chosen at the start of the game

//Objectives (how you get graded)
//Main Objective 75 pts:
    //finish the game (starting from the code below) and allow the players to travel around the board,
    //buy property, pay rent, set rent, when a player is bankrupt (has no money to pay rent) then they lose
    //and their property returns to the bank(unowned) (bank does finish paying the player the rest of the rent)
    //if there is only 1 player left than that player is the winner
//A turn limit can be implemented optionally (helps when playing with AIs)
    //in that case the player with the most money (not counting properties) is
    //the winner once the turn limit is reached
    //otherwise as stated before the last player remaining wins
//There must be at least 2 players in the game to start and the maximum is left up to you (but the board size might play a roll in that)

//Secondary Objective 25pts:
//10pts: add an basic AI player to play your game: all he does is make valid moves (he randomly buys property if he can, and chooses paths completely randomly)
//15pts: add a smarter AI player to play the game: this player should play the game more intelligently, choose paths that do not end badly for him, and decide rents that are appropriate
    //the smarter AI should also know when to sell properties


//Side Objectives 5-10pts:
//5pts: read the board in from a file instead of hard-coding it
//5pts: modify Space to store their next/adjacent spaces
    //this might help movement and ai related functions
//5-10pts: modify the drawing to somehow draw the board/spaces better
//5-10pts: add additional mechanics, such as but not limited to:
    //players battle in some way when they land on the same square
    //teleporting squares, trap squares (hold you for a turn?), extra move square
    //jail mechanic similar to Monopoly
    //trading (this would be really difficult but I'd like to see it, even if it only works with human to human players)
    //chance/community chest implementation

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class Lab5
{
    static Board board = new Board("C:\\Users\\RCHS\\IdeaProjects\\Programming_Fundamentals_II\\src\\course\\labs\\multiple\\Lab5\\spaces.txt",4, 4);
    static Player[] players = new Player[3];

    public static void main(String[] args)
    {
        players[0] = new Player("Human", "&");
        players[1] = new AIPlayer("AI1", "!");
        players[2] = new AIPlayer("AI2", "%");

        board.setup(players);
        run();
    }

    public static void run () {
        boolean running = true;
        int count = 0;
        while(running) {
            board.drawSpaces();
            System.out.println();
            count++;
            if (count > 2)
                count = 0;
            System.out.println(players[count].name+" has "+players[count].get_bits_amount()+" bits.");
            players[count].take_turn(board);
            ArrayList<Player> lost = new ArrayList<>();
            for (int i = 0; i < 3; i++)
                if (players[i].has_lost(board))
                    lost.add(players[i]);
            if (lost.size() > 2)
                for (int i = 0; i < 3; i++)
                    if (!players[i].has_lost(board))
                        System.out.println(players[i]+" has won!");

        }
    }

    //here you go, started you off right with this class
    static class AIPlayer extends Player
    {
        public AIPlayer(String name, String symbol) {
            super(name, symbol);
        }

        public void take_turn(Board b)
        {
            move(b);
            Random rand = new Random();
            int increase = 0;
            if (b.get_space(x+1, y) != null && b.get_space(x+1, y).owner == this)
                increase+=5;
            if (b.get_space(x-1, y) != null && b.get_space(x-1, y).owner == this)
                increase+=5;
            if (b.get_space(x, y+1) != null && b.get_space(x, y+1).owner == this)
                increase+=5;
            if (b.get_space(x, y-1) != null && b.get_space(x, y-1).owner == this)
                increase+=5;
            if (b.get_space(x,y).owner == this) {
                if (chance()) {
                    b.get_space(x,y).rent = rand.nextInt(10+increase);
                }
                if (chance())
                    sell_property(b.get_space(x,y));
            }
            if (b.get_space(x,y).owner == null) {
                add_bits(3);
                if (chance())
                    buy_property(b.get_space(x,y));
               b.get_space(x,y).rent = rand.nextInt(10+increase);
            }
        }

        public void move (Board b) {
            b.get_space(x,y).unland(this);
            Random rand = new Random();
            int d1 = rand.nextInt(4)+1;
            int d2 = rand.nextInt(4)+1;
            int moves = d1+d2;
            int ox = x, oy = y;
            while (moves > 0) {
                if (moves <= 3 && b.get_space(x,y).owner == this) {
                    if (chance())
                        moves = 0;
                    b.get_space(x,y).land(this);
                }
                ArrayList<String> directions = new ArrayList<>(2);
                Direction[] dir = new Direction[4];
                if (b.exists(x, y-1) && last_direction != Direction.DOWN)
                    directions.add(Direction.UP.toString());
                if (b.exists(x, y+1) && last_direction != Direction.UP)
                    directions.add(Direction.DOWN.toString());
                if (b.exists(x-1, y) && last_direction != Direction.RIGHT)
                    directions.add(Direction.LEFT.toString());
                if (b.exists(x+1, y) && last_direction != Direction.LEFT)
                    directions.add(Direction.RIGHT.toString());
                if (directions.size() == 1)
                    choose_dir(directions.get(0));
                else {
                    choose_dir(directions.get(rand.nextInt(directions.size())));
                }
                moves--;
            }
            b.get_space(x,y).land(this);
        }

        public boolean chance () {
            Random rand = new Random();
            return rand.nextBoolean();
        }
    }

    abstract static class Space
    {
        Player owner;
        int x, y;
        String display;
        int times_landed;
        int rent;
        protected ArrayList<Player> players_on_space = new ArrayList<>();
        public static final int number_of_display_lines = 7;

        public Space(String display, int x, int y)
        {
            this.display = display;
            this.x = x;
            this.y = y;

        }


        public abstract void land(Player p);

        public void unland (Player p) {
            players_on_space.remove(p);
        }

        public void set_owner(Player newOwner)
        {
            owner = newOwner;
        }


        public int sell_property()
        {
            times_landed = 0;
            owner = null;//removes reference to the owner
            return 10*times_landed;
        }

        public ArrayList<Player> get_players_on_space () {
            return new ArrayList<>(players_on_space);
        }


        public String get_line(int line)
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

    static class Board
    {
        //we'll assume aggregation is alright, but we still do not
        //want direct access to this array, we want the board to be responsible
        //for changing/editing spaces
        private ArrayList<ArrayList<Space>> spaces;

        public Board (int start_height, int start_width) {
            spaces = new ArrayList<>(start_height+1);
            for (int i = 0; i < start_height; i++)
                spaces.add(new ArrayList<>(Arrays.asList(new Space[start_width+1])));
        }

        public Board (String file_name, int sh, int sw) {
            this(sh,sw);
            File file = new File(file_name);
            Scanner scan = null;
            try {
                scan = new Scanner(file);
            } catch (FileNotFoundException e) {e.printStackTrace();}

            while(scan.hasNextLine()) {
                String[] pieces = scan.nextLine().split(",");
                addSpace(new BasicSpace(pieces[0], Integer.parseInt(pieces[2]), Integer.parseInt(pieces[1])));
            }
        }

        public void addSpace(Space new_space)
        {
            //update max variables (used in drawing output)
            if (new_space.y >= spaces.size())
                spaces.add(new ArrayList<>(Arrays.asList(new Space[spaces.get(0).size()])));
            if (new_space.x >= spaces.get(new_space.y).size())
                spaces.get(new_space.y).add(null);
            spaces.get(new_space.y).set(new_space.x, new_space);
        }
        //draws the board (there could be better ways to do this?)
        public void drawSpaces()
        {
            for (int row=0;row<spaces.size();row++)
            {
                for (int line = 0; line< Space.number_of_display_lines; line++) //if you make the spaces larger you may need to change this
                {
                    for (int col=0;col<spaces.get(row).size();col++)
                    {
                        Space current = spaces.get(row).get(col);
                        if (current != null)
                            System.out.print(current.get_line(line));
                        else
                            System.out.print("        "); //8 spaces to match the current width of each space
                    }
                    System.out.println();
                }
            }
        }

        public ArrayList<ArrayList<Space>> get_spaces () {
            return new ArrayList<>(spaces);
        }

        public Space get_space (int x, int y) {
            if (x < spaces.get(0).size() && y < spaces.size() && x >= 0 && y >= 0)
                return spaces.get(y).get(x);
            return null;
        }

        public void setup(Player[] players) {
            for (int i = 0; i < players.length; i++) {
                spaces.get(0).get(0).land(players[i]);
            }
        }

        public boolean exists(int x, int y) {
            if (x < spaces.get(0).size() && y < spaces.size() && x >= 0 && y >= 0)
                if (spaces.get(y).get(x) != null)
                    return true;
            return false;
        }
    }

    /**
     * Some of the basics here, but you will probably need to edit or rework this class entirely
     */
    static class BasicSpace extends Space
    {
        public BasicSpace(String display, int x, int y)
        {
            super(display, x, y);
        }
        public void land(Player p)
        {
            if (owner == null)
                p.add_bits((int)(Math.random()*3)+1);
            else
                p.pay_rent(owner, rent);//??? how to handle this ???
            players_on_space.add(p);
            this.times_landed++;
        }
        public String get_line(int line)
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
            return super.get_line(line);
        }
    }

    enum Direction {
        UP,DOWN,LEFT,RIGHT
    }

    public static class Player
    {
        private int bits;
        protected int properties_owned = 0;
        public final String name, symbol;
        private boolean lost = false;
        protected int x, y;
        protected Direction last_direction;

        public Player(String name, String symbol)
        {
            this.name = name;
            this.symbol = symbol;
            bits = 50;
        }
        public void add_bits(int amt)
        {
            bits += amt;
        }
        public void buy_property(Space s)
        {
            bits -= 10 + 10*properties_owned + s.times_landed;
            s.set_owner(this);
            properties_owned++;
        }
        public void sell_property(Space s)
        {
            bits += s.sell_property();
            properties_owned--;
        }
        public void take_turn(Board b)
        {
            move(b);
            int increase = 0;
            if (b.get_space(x+1, y) != null && b.get_space(x+1, y).owner == this)
                increase+=5;
            if (b.get_space(x-1, y) != null && b.get_space(x-1, y).owner == this)
                increase+=5;
            if (b.get_space(x, y+1) != null && b.get_space(x, y+1).owner == this)
                increase+=5;
            if (b.get_space(x, y-1) != null && b.get_space(x, y-1).owner == this)
                increase+=5;
            if (b.get_space(x,y).owner == this) {
                Scanner scan = new Scanner(System.in);
                System.out.print("Do you want to change the rent (yes/no): ");
                String c = scan.next();
                int val = -1;
                if (c == "yes") {
                    while (val > 10 + increase || val < 0) {
                        System.out.print("How much: ");
                        String i = scan.next();
                        val = Integer.parseInt(i);
                    }
                    b.get_space(x,y).rent = val;
                }
                System.out.print("Do you want to sell the property (yes/no): ");
                c = scan.next();
                if (c == "yes")
                    sell_property(b.get_space(x,y));
            }
            if (b.get_space(x,y).owner == null) {
                add_bits(3);
                Scanner scan = new Scanner(System.in);
                System.out.print("Do you want to buy the property (yes/no): ");
                String c = scan.next();
                System.out.println(c);
                if (c.equals("yes")) {
                    buy_property(b.get_space(x, y));
                    int val = -1;
                    while (val > 10 + increase || val < 0) {
                        System.out.print("What is the rent: ");
                        String i = scan.next();
                        val = Integer.parseInt(i);
                    }
                    b.get_space(x, y).rent = val;
                }
            }
        }
        public void pay_rent(Player p, int amt) {
            bits-=amt;
            if (bits <= 0 && p != this) {
                p.add_bits(bits+amt);
            } else if (bits > 0 && p != this)
                p.add_bits(amt);
        }
        public int get_bits_amount() {
            return bits;
        }
        public boolean has_lost(Board b) {
            if (bits <= 0) {
                b.get_space(x,y).unland(this);
                return true;
            }
            return false;
        }
        public void move(Board b)
        {
            b.get_space(x,y).unland(this);
            Random rand = new Random();
            int d1 = rand.nextInt(4)+1;
            int d2 = rand.nextInt(4)+1;
            int moves = d1+d2;
            int ox = x, oy = y;
            while (moves > 0) {
                if (moves <= 3 && b.get_space(x,y).owner == this) {
                    Scanner in = new Scanner(System.in);
                    System.out.print("Do you want to stop(yes/no): ");
                    String c = in.next();
                    if (c == "yes") {
                        moves = 0;
                        b.get_space(x,y).land(this);
                        break;
                    }
                }
                ArrayList<String> directions = new ArrayList<>(2);
                Direction[] dir = new Direction[4];
                if (b.exists(x, y-1) && last_direction != Direction.DOWN)
                    directions.add(Direction.UP.toString());
                if (b.exists(x, y+1) && last_direction != Direction.UP)
                    directions.add(Direction.DOWN.toString());
                if (b.exists(x-1, y) && last_direction != Direction.RIGHT)
                    directions.add(Direction.LEFT.toString());
                if (b.exists(x+1, y) && last_direction != Direction.LEFT)
                    directions.add(Direction.RIGHT.toString());
                if (directions.size() == 1)
                    choose_dir(directions.get(0));
                else {
                    Scanner in = new Scanner(System.in);
                    System.out.print("Where do you want to go (");
                    for (int i = 0; i < directions.size(); i++) {
                        if (i == directions.size()-1)
                            System.out.print(directions.get(i));
                        else
                            System.out.print(directions.get(i)+", ");
                    }
                    System.out.print("): ");
                    String choice = in.next();
                    choose_dir(choice);
                }
                moves--;
            }
            b.get_space(x,y).land(this);
        }

        public void choose_dir (String dir) {
            switch (dir) {
                case "UP":
                    y-=1;
                    last_direction = Direction.UP;
                    break;
                case "DOWN":
                    y+=1;
                    last_direction = Direction.DOWN;
                    break;
                case "LEFT":
                    x-=1;
                    last_direction = Direction.LEFT;
                    break;
                case "RIGHT":
                    x+=1;
                    last_direction = Direction.RIGHT;
                    break;
            }
        }
    }
}


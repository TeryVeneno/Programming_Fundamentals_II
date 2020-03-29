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

import java.util.ArrayList;

public class Lab5
{
    public static void main(String[] args)
    {
        Player bobBarker = new Player("Bob", "@");
        Player aiPat = new Player("Pat", "&");

        Board board = new Board(4, 4);
        for (int i = 0; i < 5; i++)
            board.addSpace(new BasicSpace("0R"+i+"C", i, 0));
        for (int i = 1; i < 4; i++)
            board.addSpace(new BasicSpace(i+"R0C", 0, i));
        for (int i = 1; i < 4; i++)
            board.addSpace(new BasicSpace(i+"R4C", 4, i));
        for (int i = 1; i < 5; i++)
            board.addSpace(new BasicSpace("2R"+i+"C", i, 2));
        for (int i = 0; i < 5; i++)
            board.addSpace(new BasicSpace("4R"+i+"C", i, 4));
        ArrayList<ArrayList<Space>> spaces = board.get_spaces();
        board.drawSpaces();
    }
}


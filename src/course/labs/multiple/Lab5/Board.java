package course.labs.multiple.Lab5;

import java.util.ArrayList;
import java.util.Arrays;

class Board
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
            for (int line=0;line<Space.number_of_display_lines;line++) //if you make the spaces larger you may need to change this
            {
                for (int col=0;col<spaces.get(row).size();col++)
                {
                    Space current = spaces.get(row).get(col);
                    if (current != null)
                        System.out.print(current.getLine(line));
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
}

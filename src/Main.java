import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.Stack;

import javax.swing.JOptionPane;


public class Main {

    final static int rowL = 5;//number of rows for chess board
    final static int colL = 5;//number of cols for chess board
    static Stack<Location> stack = new Stack<Location>(); //store moves in order (backtrack capability)

    //list of exhausted locations for each location.  Must use method convertLocToIndex to find a Locations proper index
    static ArrayList<ArrayList<Location>> exhausted = new ArrayList<ArrayList<Location>>(64);
    static int board[][] = new int[rowL][colL];//2d array used to store the order of moves
    static boolean visited[][] = new boolean[rowL][colL];//2d array used to store what locations have been used, NOT REQUIRED
    static Location startLoc;

    public static void main(String[] args) {

        System.out.println("START");
        initExhausted();
        ArrayList<Location> currentPossible;
        obtainStartLoc();
        System.out.println("Start Loc is " + startLoc);

        stack.push(startLoc);
//        visited[startLoc.getRow()][startLoc.getCol()] = true;
        board[startLoc.getRow()][startLoc.getCol()] = 1;
        while(stack.size() != rowL * colL && stack.size() != 0)
        {


        }


    }

    /*
     * Printed out the exhausted list for a given Location
     */
    public static void printExhausedList(Location loc)
    {
        System.out.println(exhausted.get(convertLocToIndex(loc)).toString());
    }

    /*
     * Prints out the possible move locations for a given Location
     */
    public static void printPossibleMoveLocations(Location loc)
    {

    }

    /*
     * prints out the board (numbers correspond to moves)
     */
    public static void printBoard()
    {

    }

    /*
     * prints out true/false for what spaces have been visited
     */
    public static void printVisited()
    {

    }

    /*
     * clear out the exhausted list for a given Location
     * This needs to be done everytime a Location is removed from the Stack
     */
    public static void clearExhausted(Location loc)
    {

    }

    /*
     * set up the exhausted list with empty exhausted lists.
     */
    public static void initExhausted()
    {
        exhausted = new ArrayList<ArrayList<Location>>(64);
    }

    /*
     * is this dest Location exhausted from the source Location
     */
    public static boolean inExhausted(Location source, Location dest)
    {

        return false;
    }

    /*
     * returns the next valid move for a given Location on a given ArrayList of possible moves
     */
    public static Location getNextMove(Location loc, ArrayList<Location> list)
    {
        return null;
    }

    /*
     * converts a (row,col) to an array index for use in the exhausted list
     */
    public static int convertLocToIndex(Location loc)
    {
        return (loc.getRow()*rowL) + loc.getCol();
    }

    /*
     * adds a dest Location in the exhausted list for the source Location
     */
    public static void addToExhausted(Location source, Location dest)
    {

    }

    /*
     * is this Location a valid one
     */
    public static boolean isValid(Location loc)
    {
        return loc.getRow()>=0 && loc.getRow()< board.length && loc.getCol()>=0 && loc.getCol()<board[0].length;
    }

    /*
     * returns another Location for the knight to move in.  If no more possible move
     * locations exist from Location loc, then return null
     */
    public static ArrayList<Location> getPossibleMoves(Location loc)
    {
        ArrayList<Location> possible = new ArrayList<>();
        int[] xToMove = { 2, 1, -1, -2, -2, -1, 1, 2 };
        int[] yToMove = { 1, 2, 2, 1, -1, -2, -2, -1 };
        for(int i =0; i<8; i++){
            Location p = new Location(loc.getRow()+xToMove[i],loc.getCol()+yToMove[i]);
            if(isValid(p)) {
                if(stack.search(p) == -1) possible.add(p);
            }
        }
        if(possible.size() >0){
            return possible;
        }
        return null;
    }


    /*
     * prompt for input and read in the start Location
     */
    public static void obtainStartLoc()
    {

    }

}
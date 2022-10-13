import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.Stack;
import java.util.*;

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

            currentPossible = getPossibleMoves(startLoc);
            if(getNextMove(startLoc,currentPossible)!=null){
                startLoc=getNextMove(stack.lastElement(),currentPossible);
                exhausted.get(convertLocToIndex(stack.lastElement())).add(getNextMove(stack.lastElement(),currentPossible));
                stack.push(startLoc);
                board[startLoc.getRow()][startLoc.getCol()] = stack.size();
            } else{
                board[startLoc.getRow()][startLoc.getCol()] =0;
                stack.pop();
                clearExhausted(startLoc);
                if (!stack.isEmpty()) startLoc = stack.lastElement();
            }
        }
        printBoard();
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
        ArrayList<Location> x = getPossibleMoves(loc);
        if(x == null) {
            printExhausedList(loc);
            return;
        }
        for(Location p : x){
            System.out.print(p.toString() + " ");
        }
        System.out.println();
    }

    /*
     * prints out the board (numbers correspond to moves)
     */
    public static void printBoard()
    {
        for(int[] x : board){
            for(int y : x){
                System.out.print(y + " ");
            }
            System.out.println();
        }
        System.out.println("---------------");
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
        exhausted.get(convertLocToIndex(loc)).clear();
    }

    /*
     * set up the exhausted list with empty exhausted lists.
     */
    public static void initExhausted()
    {
        for(int i = 0; i<rowL*colL; i++){
            exhausted.add(new ArrayList<Location>());
        }
    }

    /*
     * is this dest Location exhausted from the source Location
     */
    public static boolean inExhausted(Location source, Location dest)
    {
        for(Location x:exhausted.get(convertLocToIndex(source))){
            if(x.getRow() == dest.getRow() && x.getCol() == dest.getCol()) return true;
        }
        return false;
    }

    /*
     * returns the next valid move for a given Location on a given ArrayList of possible moves
     */
    public static Location getNextMove(Location loc, ArrayList<Location> list)
    {
        if(list != null){
            for(Location p :list){
                if(!inExhausted(loc,p)){
                    return p;
                }
            }
        }
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
        exhausted.get(convertLocToIndex(source)).add(dest);
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
                if(board[p.getRow()][p.getCol()] == 0) possible.add(p); // if location of board has 0.
            }
        }
        if(possible.size() >0){
            return possible;
        }
        return possible;
    }


    /*
     * prompt for input and read in the start Location
     */
    public static void obtainStartLoc()
    {
        Scanner input = new Scanner(System.in);
        System.out.print("Row: ");
        int row = input.nextInt();
        System.out.print("Col: ");
        int col = input.nextInt();
        startLoc = new Location(row,col);
    }

}

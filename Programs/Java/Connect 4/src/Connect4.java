import java.util.ArrayList;
import java.util.Scanner;

public class Connect4 {

    private char[][] board;
    //[numRows][numColumns]
    //row = y , column = x

    public Connect4 () {
        board = new char[6][7]; //[numRows][numColumns]

        for (int i=0; i<6; i++) {
            for (int j=0; j<7; j++) {
                board[i][j] = ' ';
            }
        }
    }

    public char[][] getBoard () {
        char[][] b = new char[6][7]; //[numRows][numColumns]

        for (int i=0; i<6; i++) {
            for (int j=0; j<7; j++) {
                b[i][j] = board[i][j];
            }
        }

        return b;
    }

    public int putPiece (int column, char color) {
        int row;
        for (row=board.length-1; row>=0; row--) {
            if (board[row][column] == ' ') {
                board[row][column] = color;
                return row;
            }
        }

        return row;
    }

    private boolean isWithin (int value, int min, int max) {
        return (value >= min && value <= max);
    }

    public char checkAlignment (int row, int column) {

        for (int i=0; i<4; i++) {

            //vertical check
            boolean isVertical = true;
            String vertical = "";

            for (int j=0; j<4; j++) {

                if (isWithin(row - i + j, 0, board.length-1)) {
                    vertical += "("+ (row - i + j) +","+ (column) +") ";

                    if (board[row - i + j][column] != board[row][column]) {
                        isVertical = false;
                    }
                } else {
                    isVertical = false;
                }

            }

            if (isVertical) {
                //System.out.println("Vertical: "+ vertical);
                return board[row][column];
            }

            //horizontal check
            boolean isHorizontal = true;
            String horizontal = "";

            for (int j=0; j<4; j++) {

                if (isWithin(column - i + j, 0, board[row].length-1)) {
                    horizontal += "("+ (row) +","+ (column - i + j) +") ";

                    if (board[row][column - i + j] != board[row][column]) {
                        isHorizontal = false;
                    }
                } else {
                    isHorizontal = false;
                }

            }

            if (isHorizontal) {
                //System.out.println("Horizontal: "+ horizontal);
                return board[row][column];
            }

            //diagonal check
            boolean isDiagonal = true;
            String diagonal = "";

            for (int j=0; j<4; j++) {

                if (isWithin(row - i + j, 0, board.length-1)) {
                    if (isWithin(column - i + j, 0, board[row].length-1)) {
                        diagonal += "("+ (row - i + j) +","+ (column - i + j) +") ";

                        if (board[row - i + j][column - i + j] != board[row][column]) {
                            isDiagonal = false;
                        }
                    } else {
                        isDiagonal = false;
                    }
                } else {
                    isDiagonal = false;
                }

            }

            if (isDiagonal) {
                //System.out.println("Diagonal: "+ diagonal);
                return board[row][column];
            }

            //antidiagonal check
            boolean isAntiDiagonal = true;
            String antiDiagonal = "";

            for (int j=0; j<4; j++) {

                if (isWithin(row - i + j, 0, board.length-1)) {
                    if (isWithin(column + i - j, 0, board[row].length-1)) {
                        antiDiagonal += "("+ (row - i + j) +","+ (column + i - j) +") ";

                        if (board[row - i + j][column + i - j] != board[row][column]) {
                            isAntiDiagonal = false;
                        }
                    } else {
                        isAntiDiagonal = false;
                    }
                } else {
                    isAntiDiagonal = false;
                }

            }

            if (isAntiDiagonal) {
                //System.out.println("AntiDiagonal: "+ antiDiagonal);
                return board[row][column];
            }
        }

        //if whole board is filled: tie
        if (getAvailableColumns().length == 0) {
            return 'T';
        }

        return ' ';
    }

    public void printScreen () {
        System.out.println("     0   1   2   3   4   5   6  ");
        System.out.println("   -----------------------------");

        char[] rowLet = new char[]{'0', '1', '2', '3', '4', '5'};
        for (int y=0; y<board.length; y++) {
            System.out.print(" "+ rowLet[y] +" | ");

            for (int x=0; x<board[y].length; x++) {
                System.out.print(getPiece(y,x) +" | ");
            }

            System.out.println("\n   -----------------------------");
        }
    }

    public String getPiece (int row, int column) {
        return (board[row][column] == ' ')? " " : String.valueOf(board[row][column]);
    }

    private int choose (double[] options) {
        Scanner s = new Scanner(System.in);

        String choice;
        boolean allowed = false;

        do {
            choice = s.nextLine();

            if (MyUtils.isNumeric(choice)) { //if input is a number
                if (options.length > 0) { //if there are predetermined options
                    for (int i=0; i<options.length; i++) { //check to see if choice matches any of them
                        if (Double.parseDouble(choice) == options[i]) {
                            allowed = true;
                        }
                    }
                } else { //if there are no predetermined options
                    allowed = true;
                }
            }

            if (!allowed) {
                System.out.println("Input not acceptable. Please re-enter."); //if choice not allowed, say so

                if (options.length > 0) { //if there are predetermined options, show them
                    String str = "Your options are: [";

                    for (int i=0; i<options.length-1; i++) {
                        str += (int)options[i] +", ";
                    }
                    str += (int)options[options.length-1] +"]";

                    System.out.println(str);
                }
            }

        } while (!allowed);

        return (int)Double.parseDouble(choice);
    }

    private double[] getAvailableColumns () {
        ArrayList<Integer> al = new ArrayList<>();

        for (int x=0; x<board[0].length; x++) { //check which columns are filled to the brim
            if (board[0][x] == ' ') {
                al.add(x);
            }
        }

        double[] availableColumns = new double[al.size()]; //convert ArrayList to Array
        for (int i=0; i<al.size(); i++) {
            availableColumns[i] = al.get(i);
        }

        return availableColumns;
    }

    public void play () {
        System.out.println("Player 1 choose a color (Red always goes first): \n\t1) Red (O)\n\t2) Yellow (X)");
        char p1 = (choose(new double[]{1,2}) == 1)? 'O' : 'X';
        char p2 = (p1 == 'O')? 'X' : 'O';

        System.out.println("Player 1 is " +((p1 == 'O')? "Red" : "Yellow") +" \'"+ p1 +"\'");
        System.out.println("Player 2 is " +((p2 == 'O')? "Red" : "Yellow") +" \'"+ p2 +"\'");
        System.out.println("Red always goes first.\n");

        int turn = (p1 == 'O')? 1 : 2;

        char winner = ' ';
        while (winner == ' ') {
            printScreen();

            System.out.println("Current player: \'"+ ((turn == 1)? p1 : p2) +"\'");

            System.out.print("Choose a column: ");
            int col = choose(getAvailableColumns());

            int row = putPiece(col, (turn == 1)? p1 : p2);

            winner = checkAlignment(row,col);

            turn++;
            if (turn > 2) {
                turn = 1;
            }
        }

        printScreen();

        if (winner == 'T') {
            System.out.println("!!! Winner is a tie !!!");
        } else {
            System.out.println("!!! Winner is Player \'"+ ((winner == p1)? p1 : p2) +"\' !!!");
        }
    }
}


package memoryboardgame;
import java.util.Random;
import java.util.Scanner;

public class MemoryBoardGame {
    static int nR, nC;
    static String[][] board, display;
    static boolean play = false, guessed = false;
    static int turns = 0;
    
    
    //creates a board to play on of specified size
    static String[][] buildBoard(int n){
        nR = nC = n;
        board = new String[nR][nC];
        display = new String[nR][nC];
        int cnt = 1;
        for (int r = 0; r < nR; r++) {
            if(r == nR/2)
                cnt = 1;
            for (int c = 0; c < nC; c++) {
                board[r][c] = ""+cnt;
                cnt++;
            }
        }
        for (int r = 0; r < nR; r++) {
            for (int c = 0; c < nC; c++) {
                display[r][c] = "* ";
            }
        }
        return board;
    }
    //randomizes the board
    static void shuffleBoard(){
        Random rand = new Random();
        for (int r = 0; r < nR; r++) {
            for (int c = 0; c < nC; c++) {
                int row = rand.nextInt(nR);
                int col = rand.nextInt(nC);
                String t = board[r][c];
                board[r][c] = board[row][col];
                board[row][col] = t;
                
            }
            
        }
    }
    //display the board on screen
    static void printBoard(){
        int cnt = 1;
        System.out.print("  ");
        while(cnt < nR+1){
            System.out.printf("%5d", cnt);
            cnt++;
        }
        cnt = 2;
        System.out.println("");
        System.out.print("   _________");
        while(cnt < nR){
            System.out.print("____________");
            cnt+=2;
        }
        System.out.println("");
        cnt = 1;
        for (int r = 0; r < nR; r++) {
            if(r < 9){
                System.out.print(cnt+" |");
            }
            else
                System.out.print(cnt+"|");
            cnt++;
            for (int i = 0; i < nC; i++) {
                System.out.printf("%5s",display[r][i]);
            }
            System.out.println("");
        }
        System.out.println("");
    }
    //main loop
    static void gameLoop(){
        Scanner in = new Scanner(System.in);
        System.out.println("Enter the size of your board (4, 6, 8, 10, 12): ");
        int size = in.nextInt();
        buildBoard(size);
        shuffleBoard();
        play = true;
        while(play){
            printBoard();
            
            System.out.println("Pick a row and column: ");
            System.out.println("Row: ");
            int a = in.nextInt() - 1;
            System.out.println("Column: ");
            int b = in.nextInt() - 1;
            System.out.println("Pick a row and column: ");
            System.out.println("Row: ");
            int c = in.nextInt() - 1;
            System.out.println("Column: ");
            int d = in.nextInt() - 1;
            
            while(a < 0 || b < 0 || c < 0 || d < 0 || a >= nR || b >= nR || c >= nR || d >= nR){
                System.out.println("One of these guesses are not a card! Guess again!");
                printBoard();
                System.out.println("Pick a row and column: ");
                System.out.println("Row: ");
                a = in.nextInt() - 1;
                System.out.println("Column: ");
                b = in.nextInt() - 1;
                System.out.println("Pick a row and column: ");
                System.out.println("Row: ");
                c = in.nextInt() - 1;
                System.out.println("Column: ");
                d = in.nextInt() - 1;
            }
            checkGuess(a,b,c,d);
            isGuessed();
        }
    }
    //finds if a spot has been guessed correctly or not and tells user
    static void checkGuess(int a, int b, int c, int d){
        if( (board[a][b].equals(display[a][b]) || board[c][d].equals(display[c][d])) ){
            System.out.println("You have already guessed one of these!");
        }
        else if(a == c && b == d){
            System.out.println("You cannot guess the same card twice!");
        }
        else if(board[a][b].equals(board[c][d])){
            display[a][b] = board[a][b];
            display[c][d] = board[c][d];
        }
        else{
            System.out.println("Incorrect!");
            System.out.println("The card at ("+(a+1)+" , "+(b+1)+") is "+board[a][b]);
            System.out.println("The card at ("+(c+1)+" , "+(d+1)+") is "+board[c][d]);
        }
    }
    //the game is finished
    static void isGuessed(){
        int asterisk = 0;
        for (int r = 0; r < nR; r++) {
            for (int c = 0; c < nC; c++) {
                if(display[r][c].equals("* "))
                    asterisk++;
            }
        }
        if(asterisk == 0)
            play = false;
    }
    
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        
        int choice = 1;
        while(!play && choice != 2){
            gameLoop();
            printBoard();
            System.out.println("Congratulations! You have beaten the game.");
            System.out.println("Would you like to play again?");
            System.out.println("1: Play again\n2: Quit");
            choice = in.nextInt();
        }
        
        System.out.println("Thanks for playing!\nGoodbye!");
        
    }
    
}

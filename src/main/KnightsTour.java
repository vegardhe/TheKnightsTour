package main;
import java.util.Scanner;

import static java.lang.Integer.parseInt;

public class KnightsTour
{
    private static int FREE = 1,  VISITED = 2;
    private static int n;
    private static int B[][];
    private static int squaresVisited = 0;
    private static int[][] moves;
    private static int[][] legalMoves = {{2,1},{1,2},{-1,2},{-2,1},{-2,-1},{-1,-2},{1,-2},{2,-1}};

    public static boolean findRoute(int i, int j)
    {
        //Adding move to list and marking square as visited.
        moves[squaresVisited][0] = i;
        moves[squaresVisited][1] = j;
        B[i][j] = VISITED;
        squaresVisited++;

        if (squaresVisited==(n * n))
            return true;

        // Looping all legal moves
        for (int m=0; m<8; m++){
            if (inBounds(n,i+legalMoves[m][0],j+legalMoves[m][1]) && B[i+legalMoves[m][0]][j+legalMoves[m][1]] == FREE)
                if (findRoute(i+legalMoves[m][0],j+legalMoves[m][1]))
                    return true;
        }

        //No legal moves in this position.
        squaresVisited--;
        B[i][j] = FREE;
        return false;
    }

    //Checks if the given move is inside the bounds of the board.
    public static boolean inBounds(int n, int x, int y){
        if(x>n-1 || x<0 || y>n-1 || y<0)
            return false;
        return true;
    }

    public static void main(String args[]) {
        //Getting user inputs and validating.
        Scanner in = new Scanner(System.in);
        System.out.print("n?: ");
        n = parseInt(in.nextLine());
        System.out.print("Starting x (1-" + n + "): ");
        int x = parseInt(in.nextLine()) - 1;
        System.out.print("Starting y (1-" + n + "): ");
        int y = parseInt(in.nextLine()) - 1;

        if(x > n-1 || x < 0 || y > n-1 || y<0){
            System.out.println("Invalid input!");
            return;
        }

        //Initializing board and moves.
        B = new int[n][n];
        moves = new int[n*n][2];

        //Generating board.
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                B[i][j]=FREE;

        //Finding tours and printing results.
        long timer = System.currentTimeMillis();
        boolean foundRoute = findRoute(x, y);
        long completed = System.currentTimeMillis()-timer;

        if(foundRoute){
            for(int i = 0; i < moves.length-1; i++){
                System.out.println(i + 1 + ":("+(moves[i][0] + 1) + ", " + (moves[i][1]+1)
                        + ")," + "(" + (moves[i+1][0] + 1) + ", " + (moves[i + 1][1] + 1) + ")");
            }
        }else{
            System.out.println("Unable to find a knight's tour for this board/starting position.");
        }
        System.out.println("Completed in: " + completed + "ms");
    }
}
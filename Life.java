/************ 

Date: Sept 21
Written by Negib Sherif
Assignment 1

To compile run: Javac Life.java
To simulate run: java Life n m x

Where - n is the number of iterations that the program runs for
        m is the size of the square grid
        x is the pattern type to initialize the grid with (P = Penta-decathlon Oscillator, S = Simkin glider gun, R = Random) 

************/

import java.awt.Color;

public class Life {
    private int[][] currentGenCells;         // cell[i][j] = 1 if alive, 0 o/w
    private boolean[][] nextGenCells;         // cell[i][j] = 1 if alive, 0 o/w
    private Picture pic; // Grid to be drawn
    private int magnification = 10;
    private Color colors[] = {Color.WHITE, Color.BLACK};
    private int pentaInitialConfig[][] = {
        {0,0,1,0,0,0,0,1,0,0},
        {1,1,0,1,1,1,1,0,1,1},
        {0,0,1,0,0,0,0,1,0,0},
    };
    private int simkinInitialConfig[][] = { 
        {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
        {0,1,1,0,0,0,0,1,1,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0},
        {0,1,1,0,0,0,0,1,1,0,0,0,0,0,0,0,0,0,0,1,1,1,0,0,0,0,0,0,0,0,0,0,0,0,0},
        {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0},
        {0,0,0,0,0,1,1,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
        {0,0,0,0,0,1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
        {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
        {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
        {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
        {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,1,0,0,0,0,0},
        {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,1,0,0,0,0,0},
        {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
        {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,1,0,0,0,0,0,1,1,0},
        {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,1,0,0,0,0,0,1,1,0},
        {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0}
    };

    public Life (int x, int y) {
        currentGenCells = new int[x][y];
        pic = new Picture(x * magnification, y * magnification);
    }

    public void showBoard()
    {
        pic.show();
    }

    // draws a cell
    private void drawCell(int i, int j, Color color)
    {        
        for (int offsetX = 0; offsetX < magnification; offsetX++)
        {
            for (int offsetY = 0; offsetY < magnification; offsetY++)
            {
                pic.set((i*magnification)+offsetX,
                        (j*magnification)+offsetY, color);
            }
        }
    }

    // // adds initial config current gen cells array
    // private void addInitialConfig (int i, int j) {
    //     currentGenCells[i][j] = pentaInitialConfig[k][l];        
    // }

    // draw each cell in initial board
    private void initializeCells(int gridSize, String pattern)
    {     
        int iIterator = 0;
        int jIterator = 0;
        // Penta-decathlon Oscillator pattern
        if (pattern.equals("P")) {            
            for (int i = 0; i < gridSize; i++)
            {
                for (int j = 0; j < gridSize; j++)
                {
                    if (i >= (gridSize/2)-1 && i<=(gridSize/2)+1) 
                    {
                        if (j >= (gridSize/2) - (pentaInitialConfig[0].length/2) && j<(gridSize/2) + pentaInitialConfig[0].length/2) {
                            currentGenCells[j][i] = pentaInitialConfig[iIterator][jIterator];
                            System.out.println(i);
                            jIterator++;
                        }  
                    }
                }
                jIterator = 0;
                if (i >= (gridSize/2)-1 && i<=(gridSize/2)+1) {
                    iIterator++;
                }

            }
        }

        // Random pattern
        if (pattern.equals("R")) {
            for (int i = 0; i < gridSize; i++)
            {
                for (int j = 0; j < gridSize; j++)
                {
                    currentGenCells[i][j] = (int)Math.round( Math.random()); // 0 or 1
                }
            }
        }

        // Simkin glider gun pattern
        if (pattern.equals("S")) {
            for (int i = 0; i < gridSize; i++)
            {
                for (int j = 0; j < gridSize; j++)
                {
                    if (i >= (gridSize/2)-7 && i<=(gridSize/2)+7) 
                    {
                        if (j >= (gridSize/2) - (simkinInitialConfig[0].length/2) && j<(gridSize/2) + simkinInitialConfig[0].length/2) {
                            currentGenCells[j][i] = simkinInitialConfig[iIterator][jIterator];
                            System.out.println(i);
                            jIterator++;
                        }  
                    }
                }
                jIterator = 0;
                if (i >= (gridSize/2)-7 && i<=(gridSize/2)+7) {
                    iIterator++;
                }

            }
        }
        showBoard();
    }

    // draw each cell in initial board
    private void drawInitialBoard()
    {     
        for (int i = 0; i < currentGenCells.length; i++)
        {
            for (int j = 0; j < currentGenCells.length; j++)
            {
                // 0 == white
                if (currentGenCells[i][j] == 0) {
                    drawCell(i, j, Color.WHITE);
                }
                // 1 == black
                else {
                    drawCell(i, j, Color.BLACK);
                }
            }
        }
        
        showBoard();
    }

    /* 
    Params: Takes in the x and y coordinates of the cell
    Brings cell to life by filling it with a black color
    */
    private void reviveCell(int x, int y) {
        pic.set(x*magnification, y*magnification, Color.WHITE);
    } 

    /* 
    Params: Takes in the x and y coordinates of the cell
    Brings cell to life by filling it with a black color
    */
    private void killCell(int x, int y) {
        pic.set(x, y, Color.WHITE);
    }

    public static void main (String args[]) {
        int numOfIterations = Integer.parseInt(args[0]);
        int gridSize =  Integer.parseInt(args[1]);
        String pattern = args[2];

        Life life = new Life(gridSize, gridSize);

        life.initializeCells(gridSize, pattern);

        life.drawInitialBoard();

        //life.killCell(1,1);

        // switch (pattern) {
        //     case (R):  // Random

        //         break;
        //     case (S):  // Simkin glider gun
            
        //         break;
        //     case (P):  // Penta-decathlon Oscillator

        //         break;
        // }
    }
}
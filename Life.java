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
    private int[][] currentGenCells;       // cell[i][j] = 1 if alive, 0 if dead
    private Picture pic;                   // Grid to be drawn
    private int magnification = 10;        // Magnification of cells on the board
    private int pentaInitialConfig[][] = { // Initial configuration for Penta-decathlon Oscillator pattern
        {0,0,1,0,0,0,0,1,0,0},
        {1,1,0,1,1,1,1,0,1,1},
        {0,0,1,0,0,0,0,1,0,0},
    };

    private int simkinInitialConfig[][] = { // Initial configuration for Simkin glider gun pattern
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

    /* 
    Constructor
    */
    public Life (int x) {
        currentGenCells = new int[x][x];
        pic = new Picture(x * magnification, x * magnification);
    }
    
    /* 
    Shows the board
    */
    public void showBoard()
    {
        pic.show();
    }

    /* 
    Param: The index of the cell (i, j), and the color for the cell to be colored.
    Fills a cell found at index (i,j) with the color found in the parameter
    */
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

    /* 
    Params: Takes in the grid size and pattern given by the user.
    Initializes the board with the pattern centered in the middle of the grid. 
    */
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
                    // Aligning the initial configuration in the middle of the given grid
                    if (i >= (gridSize/2)-1 && i<=(gridSize/2)+1) // Getting the vertical center of the grid
                    {
                        if (j >= (gridSize/2) - (pentaInitialConfig[0].length/2) && j<(gridSize/2) + pentaInitialConfig[0].length/2) // Getting the horizontal edges from the middle where I can copy the initial pattern configuration
                        {
                            currentGenCells[j][i] = pentaInitialConfig[iIterator][jIterator]; // Copy initial pattern configuration
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
                    // Aligning the initial configuration in the middle of the given grid
                    if (i >= (gridSize/2)-7 && i<=(gridSize/2)+7) // Getting the vertical center of the grid
                    {
                        if (j >= (gridSize/2) - (simkinInitialConfig[0].length/2) && j<(gridSize/2) + simkinInitialConfig[0].length/2) // Getting the horizontal edges from the middle where I can copy the initial pattern configuration
                        {
                            currentGenCells[j][i] = simkinInitialConfig[iIterator][jIterator]; // Copy initial pattern configuration
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

    /* 
    Draws the board and shows it
    */
    private void draw()
    {     
        for (int i = 0; i < currentGenCells.length; i++)
        {
            for (int j = 0; j < currentGenCells.length; j++)
            {
                if (currentGenCells[i][j] == 0) // 0 == white
                {
                    drawCell(i, j, Color.WHITE);
                }
                else                            // 1 == black
                {
                    drawCell(i, j, Color.BLACK);
                }
            }
        }
        showBoard();
    }
    
    /* 
    Params: Takes in the grid size given by user
    Updates the board according to the rules of game of life
    */
    public void update(int gridSize) {
        int[][] nextGenCells = new int[gridSize][gridSize];

        for (int x = 0; x < gridSize; x++) {
            for (int y = 0; y < gridSize; y++) {
            int neighbors = 0;
            int currentCell = currentGenCells[x][y];
            for (int i = -1; i <= 1; i++) {
                for (int j = -1; j <= 1; j++) 
                {
                    // uses most up-to-date values, avoid out-of-bounds
                    neighbors += currentGenCells[(x+i + gridSize) % gridSize][(y+j + gridSize) % gridSize];
                }
            }
            neighbors -= currentCell; // Subtract current cell

            // Game of life rules 
            if ((currentCell == 1) && (neighbors <  2)) // Any live cell with fewer than two live neighbouts, dies
                nextGenCells[x][y] = 0;
            else if ((currentCell == 1) && (neighbors == 2 | neighbors == 3 )) // Any live cell with two or three live neighbours, lives
                nextGenCells[x][y] = 1;
            else if ((currentCell == 1) && (neighbors > 3)) // Any live cell with more than three live neighbours, dies
                nextGenCells[x][y] = 0;
            else if ((currentCell == 0) && (neighbors == 3)) // Any dead cell with exactly three live neighbours, becomes alive 
                nextGenCells[x][y] = 1;
            else 
                nextGenCells[x][y] = currentCell;
            }
        }

        currentGenCells = nextGenCells;      
    }

    public static void main (String args[]) {
        int numOfIterations = Integer.parseInt(args[0]);
        int gridSize =  Integer.parseInt(args[1]);
        String pattern = args[2];

        Life life = new Life(gridSize);

        life.initializeCells(gridSize, pattern);

        life.draw();

        for (int i = 0; i < numOfIterations; i++) {
            try { Thread.sleep(100); } // Delay so we can see what's happening!
            catch (Exception ex) { /* ignore */ }
            
            life.update(gridSize);
            life.draw();
        }
    }
}
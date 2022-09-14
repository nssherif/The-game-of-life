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

    private Color white = new Color (255, 255, 255);
    private Color black = new Color (255, 255, 255);
    private int cells[][]; // Cells of the grid
    private Picture pic; // Grid to be drawn
    private int magnification = 10;

    public Life (int x, int y) {
        cells = new int[x][y];
        pic = new Picture(x * magnification, y * magnification);
    }

    public void show()
    {
        pic.show();
    }

    // fill a cell with a random colour
    private void drawCell(int i, int j)
    {
        float r = (float) Math.random();
        float g = (float) Math.random();
        float b = (float) Math.random();
        Color col = new Color(r,g,b);
        
        for (int offsetX = 0; offsetX < magnification; offsetX++)
        {
            for (int offsetY = 0; offsetY < magnification; offsetY++)
            {
                // set() colours an individual pixel
                pic.set((i*magnification)+offsetX,
                        (j*magnification)+offsetY, col);
            }
        }
    }

    /* 
    Params: Takes in the x and y coordinates of the cell
    Brings cell to life by filling it with a black color
    */
    private void reviveCell(int x, int y) {
        pic.set(x*magnification, y*magnification, black);
    } 

    /* 
    Params: Takes in the x and y coordinates of the cell
    Brings cell to life by filling it with a black color
    */
    private void killCell(int x, int y) {
        pic.set(x, y, white);
    }

    public static void main (String args[]) {
        int numOfIterations = Integer.parseInt(args[0]);
        int gridSize =  Integer.parseInt(args[1]);
        String pattern = args[2];

        Life life = new Life(gridSize, gridSize);

        //life.killCell(1,1);
        // fill each cell with a random colour
        for (int i = 0; i < gridSize; i++)
        {
            for (int j = 0; j < gridSize; j++)
            {
                life.drawCell(i,j);
            }
        }
        life.show();

        // switch (pattern) {
        //     case (R):  // Random

        //         break;
        //     case (S):  // Simkin glider gun
            
        //         break;
        //     case (P):  // Penta-decathlon Oscillator

        //         break;
        // }

        System.out.println(pattern);
    }
}
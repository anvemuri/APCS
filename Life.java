/*Life is a computer simulation of the life and death events of a population of bacterial organisms. This program will 
determine the life, death, and survival of bacteria from one generation to the next, assuming the starting grid of bacteria 
is considered generation zero. The rules for the creation of the next generation are as follows:
-A 'neighbor' of a cell is any living cell touching that cell (a living cell that surrounds that cell).
-Any living cell with one or zero neighbors will 'die' of loneliness (deaths).
-Any living cell with four or more living neighbors will 'die' from overcrowding (deaths).
-Any living cell with two or three neighbors will continue living into the next generation (no change).
-Any dead cell with exactly three neighbors will 'come to life' in the next generation (births).
-All births and deaths occur simultaneously.*/

import java.io.*;
import java.util.*;
public class Life{
    char[][] grid;
    public static void main(String[] args){
        Life life = new Life("life100.txt");
        life.printgrid();
        life.runLife(5);
        life.printgrid();
        System.out.println("Number of living cells in row 9 --> " + life.rowCount(9));
        System.out.println("Number of living cells in col 9 --> " + life.colCount(9));
        System.out.println("Number of living cells in total --> " + life.totalCount());
    }
    
    // Constructor that initializes a game of Life from the given data file
    public Life(String fileName){
        Scanner in = null;
        try{
            in = new Scanner(new File(fileName));
        }
        catch(IOException i){
            System.out.println("Error: " + i.getMessage());
        }
        if (in.hasNextInt()){
            grid = new char[in.nextInt()][in.nextInt()];
        }
        else{
            System.out.println("File not valid");
        }
        for (int i = 0; i < grid.length; i++){
            for (int j = 0; j < grid[0].length; j++){
                grid[i][j] = ' ';
            }
        }
        while (in.hasNextInt()){
            grid[in.nextInt()][in.nextInt()] = '*';
        }
    }

    // Method that returns the total number of living cells on the grid
    public int totalCount(){
        int count = 0;
        for (int r = 0; r < grid.length; r++){
            for (int c = 0; c < grid[r].length; c++){
                if (grid[r][c] == '*')
                    count++;
            }
        }
        return count;
    }
    public void runLife(int numGenerations){
        for (int i = 0; i < numGenerations; i++)
            nextGeneration();
    }
    public int rowCount(int row){
        int count = 0;
        for (int c = 0; c < grid[row].length; c++){
            if (grid[row][c] == '*')
                count++;
        }
        return count;
    }
    public int colCount(int col){
        int count = 0;
        for (int r = 0; r < grid.length; r++){
            if (grid[r][col] == '*')
                count++;
        }
        return count;
    }
    public int numCells(){
        int count = 0;
        for (int r = 0; r < grid.length; r++){
            for (int c = 0; c < grid[r].length; c++){
                if (grid[r][c] == '*')
                    count++;
            }
        }
        return count;
    }
    public void printgrid(){
        System.out.println("    01234567890123456789");
        for (int i = 0; i < grid.length; i++){
            System.out.printf("%-4d", i);
            for (int j = 0; j < grid[0].length; j++){
                System.out.print(grid[i][j]);
            }
            System.out.println();
        }
        System.out.println();
    }
    public void nextGeneration(){
        char[][] temp = new char[grid.length][grid[0].length]; // temporary grid so that everything is simultaneous
        createCopy(grid, temp); // copies grid to temp
        for (int row = 0; row < grid.length; row++){
            for (int col = 0; col < grid[row].length; col++){ // for every cell
                int count = neighborsAlive(row, col); // count is number of neighbors alive
                if (grid[row][col] == '*') // if cell is alive
               {
                    if (count != 2 && count != 3) // if not 2 or 3 alive neighbors, cell is dead
                        temp[row][col] = ' ';
                    // else, cell is alive
                }
                else // if cell is dead
               {
                    if (count == 3) // if 3 alive neighbors, cell is alive
                        temp[row][col] = '*';
                    // else, cell is dead
                }
            } 
        }
        createCopy(temp, grid); // copies back to grid
    }
    public void createCopy(char[][] orig, char[][] copy){
        for(int i = 0; i < orig.length; i++)
            for(int j = 0; j < orig[i].length; j++)
                copy[i][j] = orig[i][j];
    }
    public int neighborsAlive(int row, int col){
        int count = 0, tempRow = row, tempCol = col;
        for (int i = 0; i < 9; i++){
            if (i < 3)
                tempRow = row - 1;
            else if (i >= 3 && i <= 5)
                tempRow = row;
            else{
                tempRow = row + 1;
            }
            if (i % 3 == 0)
                tempCol = col - 1;
            else if ((i-1) % 3 == 0)
                tempCol = col;
            else{
                tempCol = col + 1;
            }
            if (tempRow >= 0 && tempCol >= 0 && tempRow < grid.length && tempCol < grid[0].length && i!= 4 && grid[tempRow][tempCol] == '*')
                count++;

        }
        return count;

    }
}

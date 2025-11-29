/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package board;

import java.io.IOException;
import utils.CSVReader;

/**
 *
 * @author Nour
 */
public class SudokuBoard {
    private int[][]board;
    
    public SudokuBoard()
    {board=new int[9][9];
    board=null;}
    
    public void setBoard(int[][]board)
    {for(int i=0;i<9;i++)
        for(int j=0;j<9;j++)
            if(board[i][j]<0 || board[i][j]>9)
                throw new IllegalArgumentException("Invalid number entered.");
    this.board=board;
    
    }
    
    public int[][] getBoard()
    {return this.board;}
    
    public void importBoardFromFile(String filename)
    {  int[][]board=new int[9][9];
        try{
       board=CSVReader.readCSV(filename);
       setBoard(board);
    }
    catch(IOException e)
    {System.out.println("Error in reading file.");}
    catch(IllegalArgumentException e)
    {System.out.println("Try a different file or edit the file.");}
    
    }
    
}

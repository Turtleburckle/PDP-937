package com.company;

import java.util.Random;

public class Matrix {
    private int[][] matrix;
    private int numberOfRows;
    private int numberOfColumns;

    public Matrix (int nrOfRows, int nrOfColumns)
    {
        this.numberOfRows = nrOfRows;
        this.numberOfColumns = nrOfColumns;
        this.matrix = new int[this.numberOfRows][this.numberOfColumns];
        this.createMatrix();
    }

    private void createMatrix()
    {
        for (int i=0; i<this.numberOfRows; i++)
        {
            for(int j=0; j <this.numberOfColumns;j++)
            {
                this.matrix[i][j] = this.getRandomNumber();
            }
        }
    }

    public int getNumberOfRows() {return this.numberOfRows;}

    public int getNumberOfColumns () {return this.numberOfColumns;}

    public int[][] getMatrix(){return this.matrix;}

    private int getRandomNumber()
    {
        Random random = new Random();
        int randomNumber = random.nextInt(50);
        return randomNumber;
    }

    @Override
    public String toString()
    {
        String result = "";
        for (int indexI = 0; indexI < this.getNumberOfRows(); indexI++ )
        {
            result += " |";
            for (int indexJ = 0; indexJ < this.getNumberOfColumns(); indexJ++)
            {
                result += " " + this.matrix[indexI][indexJ];
                if (indexJ+1 == this.getNumberOfColumns()){result += "| \n";}
            }
        }
        return result;
    }



}

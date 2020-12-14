package com.company;

public class MatrixMultiplication extends Thread {
    private Matrix matrix1;
    private Matrix matrix2;
    private int[][] finalMatrix;
    private int currentRow;
    private int currentColumn;
    private int currentStep;
    private int allStepsTotal;
    private boolean readyOrNot;
    private int currentTask;
    private int numberOfTasks;
    private int numberOfMultiplications;
    private int type;

    public MatrixMultiplication(Matrix matrix1, Matrix matrix2, int numberOfTasks, int type)
    {
        this.matrix1=matrix1;
        this.matrix2 = matrix2;
        this.getMatrixReady();
        this.currentStep = 1;
        this.allStepsTotal = this.matrix1.getNumberOfRows()*this.matrix2.getNumberOfColumns();
        this.currentRow = 0;
        this.currentColumn = 0;
        this.numberOfTasks = numberOfTasks;
        this.currentTask = 1;
        this.setNumberOfMultiplicationsPerTask();
        this.type = type;
    }

    @Override
    public void run()
    {
        //System.out.println("asaaa siii?");
        if(this.type==0){this.oneStep();}
        else if(this.type == 1){this.rowByRow();}
        else if(this.type == 2){this.columnByColumn();}
        else if(this.type == 3){this.numberByNumber();}
        //System.out.println(this.getFinalMatrix());


    }

    @Override
    public void start()
    {
        //System.out.println("asaaa siii?");
        if(this.type==0){this.oneStep();}
        else if(this.type == 1){this.rowByRow();}
        else if(this.type == 2){this.columnByColumn();}
        else if(this.type == 3){this.numberByNumber();}
        System.out.println(this.getFinalMatrix());
    }

    private void getMatrixReady()
    {   /*  A - m*n matrix      B - p*q matrix
            if m = p --> if n != q --> C - matrix of m/q * p
                     --> if n = q  --> C - matrix of m*n / p*q
            if n = q --> if m != p --> C - can not be done
                     --> if m = p  --> C - matrix of m*n / p*q
            if m = q --> if n != p --> C - can not be done
                     --> if n == p --> C - matrix of m*n / p*q
            if n = p --> if m != q --> C - can not be done
                     --> if m == q --> C - matrix of m*n / p*q
     */
        if (this.matrix1.getNumberOfRows() == this.matrix2.getNumberOfRows())
        {
            if (this.matrix1.getNumberOfColumns() != this.matrix2.getNumberOfColumns()){this.finalMatrix = new int[this.matrix1.getNumberOfRows()][this.matrix2.getNumberOfColumns()];}
            else {this.finalMatrix = new int[this.matrix1.getNumberOfRows()][this.matrix1.getNumberOfColumns()];}
            this.allStepsTotal = this.matrix1.getNumberOfRows()*this.matrix2.getNumberOfColumns();
            this.readyOrNot = true;
        }
        else {
            System.out.println("This multiplication can not be done!");
            this.readyOrNot = false;
        }
    }

    public void rowByRow()
    {
        if (this.currentTask == this.numberOfTasks)
        {
            this.numberOfMultiplications += (this.matrix1.getNumberOfRows()*this.matrix2.getNumberOfColumns())%this.numberOfTasks;
        }
        for(int currentMultiplication = 0; currentMultiplication < this.numberOfMultiplications; currentMultiplication++)
        {
            this.finalMatrix[this.currentRow][this.currentColumn] = this.getSumOfMultiplication(this.currentRow,this.currentColumn);
            if (this.currentColumn == this.matrix2.getNumberOfColumns()-1)
            {
                this.currentColumn=0;
                this.currentRow++;
            }
            else
            {
                this.currentColumn++;
            }
        }
        this.currentTask++;
        //System.out.println(this.getFinalMatrix());
    }

    public void columnByColumn()
    {
        if (this.currentTask == this.numberOfTasks)
        {
            this.numberOfMultiplications += (this.matrix1.getNumberOfRows()*this.matrix2.getNumberOfColumns())%this.numberOfTasks;
        }
        for (int currentMultiplication = 0; currentMultiplication < this.numberOfMultiplications; currentMultiplication++)
        {
            this.finalMatrix[this.currentRow][this.currentColumn] = this.getSumOfMultiplication(this.currentRow,this.currentColumn);
            if(this.currentRow == this.matrix1.getNumberOfRows()-1)
            {
                this.currentColumn++;
                this.currentRow=0;
            }
            else
            {
                this.currentRow++;
            }
        }
        this.currentTask++;
    }

    public void numberByNumber()
    {
        while (this.currentRow != this.matrix1.getNumberOfRows())
        {
            this.finalMatrix[this.currentRow][this.currentColumn] = this.getSumOfMultiplication(this.currentRow,this.currentColumn);
            if(this.currentColumn + this.numberOfTasks > (this.matrix2.getNumberOfColumns()-1))
            {
                this.currentRow++;
                this.currentColumn = this.currentColumn + this.numberOfTasks - this.matrix2.getNumberOfColumns() ;
            }
            else
            {
                this.currentColumn += this.numberOfTasks;
            }
        }
        this.currentTask++;
        this.currentColumn = this.currentTask - 1;
        this.currentRow = 0;
    }

    private void setNumberOfMultiplicationsPerTask()
    {
        this.numberOfMultiplications = (this.matrix1.getNumberOfRows()*this.matrix2.getNumberOfColumns())/this.numberOfTasks;
    }

    public int getSumOfMultiplication(int row, int column)
    {
        int sum = 0;
        for(int index=0; index < this.matrix1.getNumberOfRows(); index++)
        {
            sum += this.matrix1.getMatrix()[row][index] * this.matrix2.getMatrix()[index][column];
        }
        return sum;
    }

    public void oneStep()
    {
        /*                                                      x1                                    x2
        * | a00 a01 a02 |   | b00 b01 |     | ( a00 * b00 + a01 * b10 + a02 * b20)  ( a00 * b01 + a01 * b11 + a02 * b21 ) |
        *                                                       x3                                    x4
        * | a10 a11 a12 | * | b10 b11 | =   | ( a10 * b00 + a11 * b10 + a12 * b20)  ( a10 * b01 + a11 * b11 + a12 * b21 ) |
        *                                                       x5                                    x6
        * | a20 a21 a22 |   | b20 b21 |     | ( a20 * b00 + a21 * b10 + a22 * b20)  ( a20 * b01 + a21 * b11 + a22 * b21 ) |
        *
        *   x1 --> A [row0][all columns] & B [all rows][column0]
        *   x2 --> A [row0][all columns] & B [all rows][column1]
        *   x3 --> A [row1][all columns] & B [all rows][column0]
        *   x4 --> A [row1][all columns] & B [all rows][column1]
        *   x5 --> A [row2][all columns] & B [all rows][column0]
        *   x6 --> A [row2][all columns] & B [all rows][column1]
        * */
        int x = 0;
        for (int index = 0; index < this.matrix1.getNumberOfRows(); index++)
        {
            x += this.matrix1.getMatrix()[this.currentRow][index] * this.matrix2.getMatrix()[index][this.currentColumn];
        }
        this.finalMatrix[this.currentRow][this.currentColumn] = x;
        if (this.currentColumn+1 < this.matrix2.getNumberOfColumns()) {this.currentColumn++;}
        else {
            this.currentColumn=0;
            this.currentRow ++;
        }
        this.currentStep++;
    }

    public void allSteps()
    {
        while (this.currentStep <= this.allStepsTotal)
        {
            this.oneStep();
        }
    }

    public int getCurrentStep(){return this.currentStep;}
    public boolean getReadyOrNot() {return this.readyOrNot;}
    public String getFinalMatrix ()
    {
        String result = "";
        for (int indexI = 0; indexI < this.matrix1.getNumberOfRows(); indexI++ )
        {
            result += " |";
            for (int indexJ = 0; indexJ < this.matrix2.getNumberOfColumns(); indexJ++)
            {
                result += " " + this.finalMatrix[indexI][indexJ];
                if (indexJ+1 == this.matrix2.getNumberOfColumns()){result += "| \n";}
            }
        }
        return result;
    }


}

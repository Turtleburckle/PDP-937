package com.company;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Controller {

    private ShadyStuff generator;
    private Matrix matrix1;
    private Matrix matrix2;
    private int numberOfTasks;
    private int typeOfTask;


    public Controller(ShadyStuff generator, int typeOfTask)
    {
        this.generator = generator;
        this.typeOfTask = typeOfTask;
        this.fourByFour();
    }

    private void fourByFour()
    {
        this.matrix1 = generator.getMatrix(4,4);
        this.matrix2 = generator.getMatrix(4,4);
        if (this.typeOfTask == 0){this.numberOfTasks = this.matrix1.getNumberOfRows()*this.matrix2.getNumberOfColumns();}
        else{this.numberOfTasks = 4;}
    }

    private void nineByNine()
    {
        this.matrix1 = generator.getMatrix(9,9);
        this.matrix2 = generator.getMatrix(9,9);
        if (this.typeOfTask == 0){this.numberOfTasks = this.matrix1.getNumberOfRows()*this.matrix2.getNumberOfColumns();}
        else{this.numberOfTasks = 4;}
    }

    private void tenByTen()
    {
        this.matrix1 = this.generator.getMatrix(10,10);
        this.matrix2 = this.generator.getMatrix(10,10);
        if (this.typeOfTask == 0){this.numberOfTasks = this.matrix1.getNumberOfRows()*this.matrix2.getNumberOfColumns();}
        else{this.numberOfTasks = 5;}
    }

    private void oneHundredByOneHundred()
    {
        this.matrix1 = this.generator.getMatrix(100,100);
        this.matrix2 = this.generator.getMatrix(100,100);
        if (this.typeOfTask == 0){this.numberOfTasks = this.matrix1.getNumberOfRows()*this.matrix2.getNumberOfColumns();}
        else{this.numberOfTasks = 20;}
    }

    private void oneThousandByOneThousand()
    {
        this.matrix1 = this.generator.getMatrix(1000,1000);
        this.matrix2 = this.generator.getMatrix(1000,1000);
        if (this.typeOfTask == 0){this.numberOfTasks = this.matrix1.getNumberOfRows()*this.matrix2.getNumberOfColumns();}
        else{this.numberOfTasks = 10;}
    }

    private void tenThousandByTenThousand()
    {
        this.matrix1 = this.generator.getMatrix(10000,10000);
        this.matrix2 = this.generator.getMatrix(10000,10000);
        if (this.typeOfTask == 0){this.numberOfTasks = this.matrix1.getNumberOfRows()*this.matrix2.getNumberOfColumns();}
        else{this.numberOfTasks = 10;}
    }


    public void threadForEachTask()
    {
        MatrixMultiplication multiplication = new MatrixMultiplication(this.matrix1,this.matrix2,this.numberOfTasks,this.typeOfTask);
        ArrayList<MatrixMultiplication> threads = new ArrayList<>();
        float start = System.nanoTime() / 1000000;
        for (int index = 0; index < this.numberOfTasks; index++)
        {
            threads.add(multiplication);
        }

        for (Thread thread : threads)
        {
            thread.start();
        }

        for(Thread thread: threads)
        {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        float end = System.nanoTime() / 1000000;
        System.out.println("\n End work multiplication: " + (end - start) / 1000 + " seconds");
        //System.out.println(this.matrix1.toString());
        //System.out.println(this.matrix2.toString());
        //System.out.println(multiplication.getFinalMatrix());

    }

    public void threadPoolForTasks()
    {
        MatrixMultiplication multiplication = new MatrixMultiplication(this.matrix1,this.matrix2,this.numberOfTasks,this.typeOfTask);
        ArrayList<MatrixMultiplication> threads = new ArrayList<>();
        float start = System.nanoTime() / 1000000;
        for (int index = 0; index < this.numberOfTasks; index++)
        {
            threads.add(multiplication);
        }
        ExecutorService pool = Executors.newFixedThreadPool(this.numberOfTasks);
        for (Thread thread : threads)
        {
            pool.execute(thread);
        }
        pool.shutdown();
        float end = System.nanoTime() / 1000000;
        System.out.println("\n End work multiplication: " + (end - start) / 1000 + " seconds");
        //System.out.println(multiplication.getFinalMatrix());
        //System.out.println(this.matrix1.toString());
        //System.out.println(this.matrix2.toString());
    }


}

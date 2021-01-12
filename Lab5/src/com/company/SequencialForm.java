package com.company;

import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class SequencialForm {
    private Polynomial polynomial1;
    private Polynomial polynomial2;

    public SequencialForm (Polynomial polynomial1, Polynomial polynomial2)
    {
        this.polynomial1 = polynomial1;
        this.polynomial2 = polynomial2;
    }

    public void basicMultiplication()
    {
        MultiplyPolynomials multiplication = new MultiplyPolynomials(this.polynomial1,this.polynomial2,0);
        multiplication.setCurrentTask(0);
        multiplication.setTotalTasks(this.polynomial1.getLength());
        float start = System.nanoTime() / 1000000;
        for (int index = 0; index < this.polynomial1.getLength()*this.polynomial2.getLength(); index++)
        {
            multiplication.basicMultiplication();
        }
        float end = System.nanoTime() / 1000000;
        System.out.println("\n End work multiplication: " + (end - start) / 1000 + " seconds");
        System.out.println(multiplication.toString());
        System.out.println("Result : " + multiplication.getResult());
    }

    public void karatsubaMultiplication()
    {
        MultiplyPolynomials multiplication = new MultiplyPolynomials(this.polynomial1,this.polynomial2,1);
        multiplication.setCurrentTask(0);
        multiplication.setTotalTasks(5);
        ArrayList<MultiplyPolynomials> threads = new ArrayList<>();
        float start = System.nanoTime() / 1000000;
        for (int index = 0; index <= 5; index++)
        {
            threads.add(multiplication);
        }
        ExecutorService pool = Executors.newFixedThreadPool(5);
        for (Thread thread : threads)
        {
            pool.execute(thread);
            try {
                pool.awaitTermination(1,TimeUnit.MILLISECONDS);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        pool.shutdown();
        float end = System.nanoTime() / 1000000;
        multiplication.generateResult();
        System.out.println("\n End work multiplication: " + (end - start) / 1000 + " seconds");
        System.out.println(multiplication.toString());
        System.out.println("Result : " + multiplication.getResult());
    }

}

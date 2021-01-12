package com.company;

import java.util.ArrayList;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ParallelizedForm {

    private Polynomial polynomial1;
    private Polynomial polynomial2;

    public ParallelizedForm(Polynomial polynomial1, Polynomial polynomial2)
    {
        this.polynomial1 = polynomial1;
        this.polynomial2 = polynomial2;
    }

    public void basicMultiplication()
    {
        MultiplyPolynomials multiplication = new MultiplyPolynomials(this.polynomial1,this.polynomial2,0);
        ThreadPoolExecutor threadPoolExecutor = (ThreadPoolExecutor) Executors.newFixedThreadPool(this.polynomial1.getLength()*this.polynomial2.getLength());
        float start = System.nanoTime() / 1000000;
        for(int index =0; index<this.polynomial1.getLength()*this.polynomial2.getLength();index++)
        {
            //System.out.println("Step: " + index);
            threadPoolExecutor.execute(multiplication);
            try {
                threadPoolExecutor.awaitTermination(1, TimeUnit.MILLISECONDS);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        threadPoolExecutor.shutdown();
        try {
            threadPoolExecutor.awaitTermination(50, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        float end = System.nanoTime() / 1000000;
        System.out.println("\n End work multiplication: " + (end - start) / 1000 + " seconds");
        System.out.println(multiplication.toString());
        System.out.println("Result : " + multiplication.getResult());
    }

    public void karatsubaMultiplication()
    {
        MultiplyPolynomials multiplication = new MultiplyPolynomials(this.polynomial1,this.polynomial2,1);
        ThreadPoolExecutor threadPoolExecutor = (ThreadPoolExecutor) Executors.newFixedThreadPool(this.polynomial1.getLength()*this.polynomial2.getLength());
        float start = System.nanoTime() / 1000000;
        for(int index =0; index<5;index++)
        {
            //System.out.println("Step: " + index);
            threadPoolExecutor.execute(multiplication);
                try {
                    threadPoolExecutor.awaitTermination(1, TimeUnit.MILLISECONDS);
                } catch (InterruptedException e) {
                    e.printStackTrace();
            }
        }
        threadPoolExecutor.shutdown();
        try {
            threadPoolExecutor.awaitTermination(50, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        float end = System.nanoTime() / 1000000;
        System.out.println("\n End work multiplication: " + (end - start) / 1000 + " seconds");
        System.out.println(multiplication.toString());
        System.out.println("Result : " + multiplication.getResult());
    }
}

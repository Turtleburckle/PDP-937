package com.company.controller;

import com.company.repository.Repository;

import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import mpi.MPI;

public class Controller {

    private Repository repository;

    public Controller(Repository repository){
        this.repository = repository;
    }

    public void sequentialAlgorithmSequentialCalls(){
        //**//
        float start = System.nanoTime() / 1000000;
        for(int index = 0; index < this.repository.getNumberOfTotalSteps(); index++){
            if(index == 0){this.repository.setFirstStep();} else{ this.repository.colorCurrentNode();}
        }
        float end = System.nanoTime() / 1000000;
        System.out.println("\n End work coloring: " + (end - start) / 1000 + " seconds");
        System.out.println(this.repository.toString());
    }

    public void sequentialAlgorithmThreads(){
        /****/
        ArrayList<Repository> threads = new ArrayList<Repository>();
        float start = System.nanoTime() / 1000000;
        for(int index=0; index<this.repository.getNumberOfTotalSteps(); index++){threads.add(repository);}
        ExecutorService pool = Executors.newFixedThreadPool(this.repository.getNumberOfTotalSteps());
        for (Thread thread : threads)
        {
            pool.execute(thread);
            try {
                pool.awaitTermination(1, TimeUnit.MILLISECONDS);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        pool.shutdown();
        float end = System.nanoTime() / 1000000;
        System.out.println("\n End work coloring: " + (end - start) / 1000 + " seconds");
        System.out.println(this.repository.toString());
    }

    public void worker(int me){
        int n;
    }

    public void sequentialAlgorithmMPI(String[] args){
        /****/
        //float start = System.nanoTime() / 1000000;
        MPI.Init(null);
        int nrOfProcesses = this.repository.getNumberOfTotalSteps();
        int me = MPI.COMM_WORLD.Rank();
        int size = MPI.COMM_WORLD.Size();
        System.out.println("Hello world from <"+me+"> of <"+size+">");
        MPI.Finalize();
    }


}

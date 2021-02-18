package com.company;

import com.company.controller.Controller;
import com.company.model.Graph;
import com.company.repository.Repository;

import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
        ShadyStuff generator = new ShadyStuff();
        Graph graph = generator.getGraph();
        graph.setColors(generator.getColors());
        Repository repository = new Repository(graph);
        Controller controller = new Controller(repository);
        System.out.println(graph.toString());
        //controller.sequentialAlgorithmSequentialCalls();
        controller.sequentialAlgorithmThreads();
        //controller.sequentialAlgorithmMPI(args);
    }
}

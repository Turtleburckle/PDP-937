package com.company;

import com.company.model.Color;
import com.company.model.Graph;
import com.company.model.Node;
import com.company.model.Vertex;

import java.util.ArrayList;
import java.util.Random;

public class ShadyStuff {

    private final ArrayList<Color> colors;
    private final Graph graph;

    public ShadyStuff (){
        this.colors = new ArrayList<Color>();
        this.graph = new Graph();
        this.addColors();
        this.createGraph();
        //this.generateRandomGraph(10,22);
    }

    public void generateRandomGraph(int numberOfNodes, int numberOfVertices){
        /****/
        for(int index=1; index <= numberOfNodes; index++){
            Node node = new Node(index);
            this.graph.addNode(node);
        }
        int index=0;
        while(index<numberOfVertices){
            int nodeIndex1 = this.generateRandomNumber(numberOfNodes);
            int nodeIndex2 = nodeIndex1;
            while(nodeIndex1 == nodeIndex2){nodeIndex2 = this.generateRandomNumber(numberOfNodes);}
           if(!this.graph.existsVertexBetweenTwoNodes(this.graph.getNodeByIndex(nodeIndex1),this.graph.getNodeByIndex(nodeIndex2))){
               Vertex vertex = new Vertex(this.graph.getNodeByIndex(nodeIndex1),this.graph.getNodeByIndex(nodeIndex2));
               this.graph.addVertex(vertex);
               index++;
            }
        }

    }

    private int generateRandomNumber(int n){
        Random random = new Random();
        int randomNumber = random.nextInt(n);
        if (randomNumber <= 0) {return this.generateRandomNumber(n);}
        return randomNumber;
    }

    public ArrayList<Color> getColors(){return this.colors;}
    public Graph getGraph(){return this.graph;}

    public void createGraph(){
        Node n1 = new Node(1);
        Node n2 = new Node(2);
        Node n3 = new Node(3);
        Node n4 = new Node(4);
        Node n5 = new Node(5);
        Node n6 = new Node(6);
        Node n7 = new Node(7);
        Node n8 = new Node(8);
        Node n9 = new Node(9);
        Node n10 = new Node(10);
        Vertex v1 = new Vertex(n1,n2);
        Vertex v2 = new Vertex(n2,n5);
        Vertex v3 = new Vertex(n5,n8);
        Vertex v4 = new Vertex(n8,n10);
        Vertex v5 = new Vertex(n10,n9);
        Vertex v6 = new Vertex(n9,n6);
        Vertex v7 = new Vertex(n6,n3);
        Vertex v8 = new Vertex(n1,n3);
        Vertex v9 = new Vertex(n3,n4);
        Vertex v10 = new Vertex(n1,n7);
        Vertex v11 = new Vertex(n1,n6);
        Vertex v12 = new Vertex(n3,n7);
        Vertex v13 = new Vertex(n4,n6);
        Vertex v14 = new Vertex(n6,n7);
        Vertex v15 = new Vertex(n4,n7);
        Vertex v16 = new Vertex(n9,n7);
        Vertex v17 = new Vertex(n5,n7);
        Vertex v18 = new Vertex(n4,n8);
        Vertex v19 = new Vertex(n4,n5);
        Vertex v20 = new Vertex(n5,n10);
        Vertex v21 = new Vertex(n1,n4);
        this.graph.addVertex(v1);
        this.graph.addVertex(v2);
        this.graph.addVertex(v3);
        this.graph.addVertex(v4);
        this.graph.addVertex(v5);
        this.graph.addVertex(v6);
        this.graph.addVertex(v7);
        this.graph.addVertex(v8);
        this.graph.addVertex(v8);
        this.graph.addVertex(v9);
        this.graph.addVertex(v10);
        this.graph.addVertex(v11);
        this.graph.addVertex(v12);
        this.graph.addVertex(v13);
        this.graph.addVertex(v14);
        this.graph.addVertex(v15);
        this.graph.addVertex(v16);
        this.graph.addVertex(v17);
        this.graph.addVertex(v18);
        this.graph.addVertex(v19);
        this.graph.addVertex(v20);
        this.graph.addVertex(v21);
    }

    public void addColors(){
        Color red = new Color("Red","Re",1);
        Color pink = new Color("Pink","Pi",2);
        Color blue = new Color("Blue","Bl",3);
        Color purple = new Color("Purple","Pu",4);
        Color green = new Color("Green", "Gr",5);
        Color yellow = new Color("Yellow", "Ye",6);
        Color orange = new Color("Orange", "Or",7);
        Color brown = new Color("Brown", "Br",8);
        Color black = new Color("Black", "Bk",9);
        Color white = new Color("White", "Wh",10);
        this.colors.add(red);
        this.colors.add(pink);
        this.colors.add(blue);
        this.colors.add(purple);
        this.colors.add(green);
        this.colors.add(yellow);
        this.colors.add(orange);
        this.colors.add(brown);
        this.colors.add(black);
        this.colors.add(white);
    }

}

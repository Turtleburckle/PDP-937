package com.company.repository;

import com.company.model.Color;
import com.company.model.Graph;
import com.company.model.Node;

import java.util.ArrayList;

public class Repository extends Thread{

    private final Graph graph;
    private Node currentNode;
    private int currentStep;
    private ArrayList<Node> previousNodes;

    public Repository(Graph graph){
        this.previousNodes = new ArrayList<Node>();
        this.graph = graph;
        this. currentNode = this.graph.getFirstNode();
        this.currentStep = 1;
    }

    public int getNumberOfTotalSteps(){return this.graph.getNumberOfNodes();}

    public boolean colorCurrentNode(){
        ArrayList<Color> usedColors = this.graph.getUsedColors();
        for(Node node : this.previousNodes){
            if(this.graph.existsVertexBetweenTwoNodes(node,this.currentNode)){
                if(usedColors.contains(node.getColor())){
                    usedColors.remove(node.getColor());
                }
            }
        }
        if(usedColors.size()!=0){
            this.currentNode.setColor(usedColors.get(0));
        }else {this.currentNode.setColor(this.graph.getNextUnusedColor());}
        this.previousNodes.add(this.currentNode);
        boolean done = this.nextNode();
        this.currentStep++;
        if(!done){return false;}
        return true;
    }

    public void setFirstStep(){
        this.currentNode.setColor(graph.getFirstColor());
        this.addToPreviousNodes(this.currentNode);
        this.nextNode();
        this.currentStep++;
    }

    private void addToPreviousNodes(Node node){
        this.previousNodes.add(node);
    }

    public Node getCurrentNode(){return this.currentNode;}

    public boolean nextNode(){
        /** -- Sets the current node with the one that is next.
         * Input : nothing;
         * Output : true -> if the graph has a next node;
         *          false -> if the graph has all the nodes colored;
         * **/
        if(this.hasNext(this.currentNode)){
            this.currentNode = this.graph.getNodeByIndex(this.currentNode.getIndex()+1);
            return true;
        }
        return false;
    }

    public boolean hasNext(Node node){
        /** -- Checks if the transmitted node has a node after it.
         * Input : node -> Node;
         * Output : false -> if the node is the last;
         *          true -> if the graph contains nodes after it;
         * **/
        if(node.getIndex()+1 > this.graph.getNumberOfNodes()){
            return false;
        }
        return true;
    }

    public int getCurrentStep(){return this.currentStep;}

    public String getStepString(){return "Step" + this.currentStep;}

    @Override
    public void run() { if(this.currentStep == 1){this.setFirstStep();}else {this.colorCurrentNode();}}

    @Override
    public void start() { if(this.currentStep == 1){this.setFirstStep();}else {this.colorCurrentNode();}}

    @Override
    public String toString(){return this.graph.toString();}


}

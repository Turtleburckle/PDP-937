package com.company.model;

import java.util.ArrayList;

public class Graph {
    private ArrayList<Vertex> vertices;
    private ArrayList<Node> nodes;
    private ArrayList<Color> colors;

    public Graph(){
        this.vertices = new ArrayList<Vertex>();
        this.nodes = new ArrayList<Node>();
        this.colors = new ArrayList<Color>();
    }

    public Color getNextUnusedColor(){
        for(Color color : this.colors){
            if(!color.isUsed()){
                return color;
            }
        }
        return null;
    }


    public Color getFirstColor(){
        /** -- Finds the first color.
         * Input : nothing;
         * Output : the first color added -> Color;
         * **/
        for(Color color : this.colors){
            if (color.getIndex() == 1){
                return color;
            }
        }
        return null;
    }

    public ArrayList<Color> getUsedColors(){
        ArrayList<Color> result = new ArrayList<Color>();
        for(Color color : this.colors){
            if(color.isUsed()){
                result.add(color);
            }
        }
        return result;
    }

    public Color getColorByIndex(int index){
        /** -- Finds the color of the transmitted index.
         * Input : index -> integer;
         * Output : color found -> Color;
         * **/
        for(Color color : this.colors){
            if(color.getIndex() == index){
                return color;
            }
        }
        return null;
    }

    public Node getFirstNode(){
        /** -- Finds the first node.
         * Input : nothing
         * Output : the first node from the graph -> Node
         * **/
        return this.nodes.get(0);
    }

    public Node getNodeByIndex(int index){
        /** -- Finds the node of the transmitted index.
         * Input : index -> integer
         * Output : node -> Node
         * **/
        for(Node node : this.nodes){
            if(node.getIndex() == index){return node;}
        }
        return null;
    }

    public void addNode(Node node){
        /** -- Adds a node to the list of nodes.
         * Input: node -> Node
         * Output: nothing
         * **/
        this.nodes.add(node);
    }

    public int getNumberOfNodes(){
        /** -- Generates the number of nodes existing in the Graph.
         * Input: nothing
         * Output: number of nodes (size of node's list) -> integer
         * **/
        return this.nodes.size();
    }

    public void addVertex(Vertex vertex){
        /** -- Adds a vertex to the graph.
         * Input : vertex -> Vertex
         * Output : nothing
         * **/
        this.vertices.add(vertex);
        if(this.nodes.size()==0){
            this.addNode(vertex.getNode1());
            this.addNode(vertex.getNode2());
        }else{
            if(!this.nodes.contains(vertex.getNode1())){this.addNode(vertex.getNode1());}
            if(!this.nodes.contains(vertex.getNode2())){this.addNode(vertex.getNode2());}
        }
    }

    public ArrayList<Node> getUncoloredNodes(){
        /** -- Generates a list of all the uncolored nodes from the graph.
         * Input : nothing
         * Output : result -> List of Nodes
         * **/
        ArrayList<Node> result = new ArrayList<Node>();
        for(Node node : this.nodes){
            if(!node.coloredNode()){result.add(node);}
        }
        return result;
    }

    /** -- Generates a list with all the vertices that contain the given node.
     * Input: node -> Node
     * Output: result -> List of Vertices
     **/
    public ArrayList<Vertex> getVerticesWithNode(Node node){
        ArrayList<Vertex> result = new ArrayList<Vertex>();
        for(Vertex vertex : this.vertices){
            if(vertex.getNode1().equals(node) || vertex.getNode2().equals(node)){
                result.add(vertex);
            }
        }
        return result;
    }

    public boolean existsVertexBetweenTwoNodes(Node node1, Node node2){
        for(Vertex vertex : this.vertices){
            if(vertex.getNode1().equals(node1) && vertex.getNode2().equals(node2)){return true;}
            if(vertex.getNode2().equals(node1) && vertex.getNode1().equals(node2)){return true;}
        }
        return false;
    }

    public void setColors(ArrayList<Color> colors) {
        this.colors = colors;
    }

    @Override
    public String toString(){
        String result = "    ";
        for(int index=0;index<this.nodes.size();index++){
            if(this.nodes.get(index).getIndex()<10){result += " ... " + (this.nodes.get(index).getIndex()) ;}
            else {result += " ..." + (this.nodes.get(index).getIndex()) ;}
        }
        result += "...  \n";
        for(int index1=0; index1<this.nodes.size();index1++){
            if(this.nodes.get(index1).getIndex()<10){
                result += " " + this.nodes.get(index1).getIndex() +" |";
            }
            else{result += this.nodes.get(index1).getIndex() +" |";}

            for(int index2=0; index2<this.nodes.size();index2++){
                if(this.existsVertexBetweenTwoNodes(this.nodes.get(index1),this.nodes.get(index2))){
                    result += " --- 1";
                }
                else {result += " --- 0";}
            }
            if(this.nodes.get(index1).coloredNode()){
                result += "--- |" + this.nodes.get(index1).getColor().toString()+ " - " + this.nodes.get(index1).getIndex() + "\n";
            }else {result += "--- | uncolored" + " - " + this.nodes.get(index1).getIndex() + "\n"; }
        }
        return result;
    }
}

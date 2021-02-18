package com.company.model;

public class Node {
    private Color color;
    private int index;

    public Node(Color color, int index){
        this.color = color;
        this.index = index;
    }

    public Node(int index){
        this.index = index;
    }

    public boolean coloredNode(){
        if(color == null){
            return false;
        }
        return color.isUsed();
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
        color.setUsed();
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }
}

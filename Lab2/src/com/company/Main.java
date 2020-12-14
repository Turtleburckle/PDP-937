package com.company;

public class Main {

    public static void main(String[] args) {
        ShadyStuff hardCodedStuff = new ShadyStuff();
        View ui = new View(hardCodedStuff.getVectors());
        ui.run();
    }
}

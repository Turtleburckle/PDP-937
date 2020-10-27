package com.company;

import com.company.view.View;

public class Main {

    public static void main(String[] args) {
        ShadyStuff populate = new ShadyStuff();
        View ui = new View(populate);
        ui.run();

    }

}

package com.company;

import java.util.ArrayList;
import java.util.Random;

public class ShadyStuff {

    public Polynomial polynomial1;
    public Polynomial polynomial2;

    public ShadyStuff ()
    {
        this.callGenerator();
    }

    public Polynomial getFirstPolynomial(){return this.polynomial1;}
    public Polynomial getSecondPolynomial(){return this.polynomial2;}

    private void callGenerator()
    {
        this.polynomial1 = this.generatePolynomial("x",this.generateRandomNumber(0));
        this.polynomial2 = this.generatePolynomial("x",this.generateRandomNumber(0));
    }

    private int generateRandomNumber(int scope)
    {   // scope - 0 if it's for length and 1 if it's for polynomial
        Random rand = new Random();
        if (scope == 0){return 5;}
        else {return rand.nextInt(1000);}
    }

    private Polynomial generatePolynomial(String variable, int length)
    {
        ArrayList<Term> list = new ArrayList<Term>();
        for (int index = 0; index <= length; index++ )
        {
            list.add(new Term(this.generateRandomNumber(1),variable,index));
        }
        return new Polynomial(list);
    }
}

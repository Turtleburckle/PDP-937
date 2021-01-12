package com.company;

import java.util.ArrayList;

public class Polynomial {
    /* Inner representation         x - the variable
        5 | 7 | 8 | 3 | 4 | 6 | 7 | 2 - the coefficients
        0   1   2   3   4   5   6   7 - the exponents
     */
    private ArrayList<Term> polynomial;
    private int length;
    
    public Polynomial(ArrayList<Term> polynomial)
    {
        this.polynomial = polynomial;
        this.length = this.polynomial.size();
    }

    public int getTermFromExponent(int index)
    {
        return this.polynomial.get(index).getCoefficient();
    }

    public Term getTermFromIndex(int index)
    {
        return this.polynomial.get(index);
    }

    public int getLength() {
        return length;
    }

    public ArrayList<Term> getPolynomial() {
        return polynomial;
    }

    @Override
    public String toString()
    {
        String polynomialString = "";
        for(int index=0; index<this.length;index++)
        {
            polynomialString += this.polynomial.get(index).getCoefficient();
            if (this.polynomial.get(index).getExponent() == 1){polynomialString += this.polynomial.get(index).getVariable();}
            else if (this.polynomial.get(index).getExponent() > 1) {polynomialString += this.polynomial.get(index).getVariable() +"^"+ this.polynomial.get(index).getExponent();}
            if (this.polynomial.get(index).getCoefficient() >= 0 && index < this.polynomial.size()-1) {polynomialString += " + ";}
        }
        return polynomialString;
    }
}

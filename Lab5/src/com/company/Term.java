package com.company;

public class Term {
    private int coefficient;
    private String variable1;
    private String variable2;
    private int exponent1;
    private int exponent2;

    public Term (int coefficient, String variable, int exponent)
    {
        this.coefficient = coefficient;
        this.variable1 = variable;
        this.exponent1 = exponent;
    }

    public Term(int coefficient, String variable1, String variable2, int exponent1, int exponent2)
    {
        this.coefficient = coefficient;
        this.variable1 = variable1;
        this.variable2 = variable2;
        this.exponent1 = exponent1;
        this.exponent2 = exponent2;
    }

    public void setExponent(int newExponent)
    {
        this.exponent1 = newExponent;
    }
    public void setCoefficient(int newExponent)
    {
        this.coefficient = newExponent;
    }


    public int getCoefficient() {
        return coefficient;
    }

    public String getVariable() {
        return variable1;
    }

    public int getExponent() {
        return exponent1;
    }

    public String getVariable2() {
        return variable2;
    }

    public int getExponent2() {
        return exponent2;
    }
}

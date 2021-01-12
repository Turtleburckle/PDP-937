package com.company;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class MultiplyPolynomials extends Thread{
    private Polynomial polynomial1;
    private Polynomial polynomial2;
    private ArrayList<Term> finalPolynomial;
    private int multiplicationMethod; //0 - O(n^2) | 1 - Karatsuba
    private int currentTask;
    private int indexPolynomial1;
    private int indexPolynomial2;
    private int totalTasks;
    private ArrayList<Term> p1;
    private ArrayList<Term> p2;
    private ArrayList<Term> q1;
    private ArrayList<Term> q2;
    private ArrayList<Term> p1q1;
    private ArrayList<Term> p1q2;
    private ArrayList<Term> p2q1;
    private ArrayList<Term> p2q2;
    private ArrayList<Term> Task3;


    public MultiplyPolynomials (Polynomial polynomial1,Polynomial polynomial2, int multiplicationMethod)
    {
        this.polynomial1 = polynomial1;
        this.polynomial2 = polynomial2;
        this.multiplicationMethod = multiplicationMethod;
        this.finalPolynomial =new ArrayList<Term>();
        this.currentTask = 0;
        this.totalTasks = this.polynomial1.getLength()*this.polynomial2.getLength();
        this.indexPolynomial1 = 0;
        this.indexPolynomial2 = 0;
        this.prepareKaratsuba();
    }

    public void prepareKaratsuba()
    {
        this.p1 = new ArrayList<Term>();
        this.p2 = new ArrayList<Term>();
        this.q1 = new ArrayList<Term>();
        this.q2 = new ArrayList<Term>();

        for (int index = 0; index < this.polynomial1.getLength(); index++)
        {
            if (index < this.polynomial1.getLength()/2)
            {
                this.p2.add(this.polynomial1.getPolynomial().get(index));
                this.q2.add(this.polynomial2.getPolynomial().get(index));
            }
            else
                {
                    Term newTerm1 = new Term(this.polynomial1.getTermFromIndex(index).getCoefficient(),this.polynomial1.getTermFromIndex(index).getVariable(),(this.polynomial1.getTermFromIndex(index).getExponent()-(this.polynomial1.getLength()/2)));
                    Term newTerm2 = new Term(this.polynomial2.getTermFromIndex(index).getCoefficient(),this.polynomial2.getTermFromIndex(index).getVariable(),(this.polynomial2.getTermFromIndex(index).getExponent()-(this.polynomial2.getLength()/2)));
                    this.p1.add(newTerm1);
                    this.q1.add(newTerm2);
                }
        }
        //System.out.println("" + this.p1.get(0).getCoefficient() +" "+ this.p1.get(1).getCoefficient() +" "+ this.p1.get(2).getCoefficient());

    }

    public void startTasks(){
        if (this.multiplicationMethod == 0){this.basicMultiplication();}
        else {this.karatsubaMultiplication();}
    }

    public void basicMultiplication(){
        int coefficient = this.polynomial1.getTermFromIndex(this.indexPolynomial1).getCoefficient() * this.polynomial2.getTermFromIndex(this.indexPolynomial2).getCoefficient();
        String variable = "x";
        int exponent = this.polynomial1.getTermFromIndex(this.indexPolynomial1).getExponent() + this.polynomial2.getTermFromIndex(this.indexPolynomial2).getExponent();
        Term newTermToAdd = new Term(coefficient,variable,exponent);
        this.finalPolynomial.add(newTermToAdd);
        this.indexPolynomial2++;
        if(this.indexPolynomial2 == this.polynomial2.getLength()){this.indexPolynomial1++;this.indexPolynomial2=0;}
        this.currentTask++;
    }

    public String getResult()
    {
        this.finalPolynomial = this.addSameExponents(this.finalPolynomial);
        String stringResult = "";
        //System.out.println(this.finalPolynomial.size());
        for (int index=0; index < this.finalPolynomial.size(); index++)
        {
            stringResult += this.finalPolynomial.get(index).getCoefficient();
            if (this.finalPolynomial.get(index).getExponent() == 1){stringResult+=this.finalPolynomial.get(index).getVariable();}
            else if (this.finalPolynomial.get(index).getExponent() > 1) {stringResult += this.finalPolynomial.get(index).getVariable()+"^"+this.finalPolynomial.get(index).getExponent();}

            if (this.finalPolynomial.get(index).getExponent2() == 1){stringResult+=this.finalPolynomial.get(index).getVariable2();}
            else if (this.finalPolynomial.get(index).getExponent2() > 1) {stringResult += this.finalPolynomial.get(index).getVariable2()+"^"+this.finalPolynomial.get(index).getExponent2();}

            if (index < this.finalPolynomial.size()-1){stringResult += " + ";}
        }
        return stringResult;
    }

    public void karatsubaMultiplication()
    {

        if(this.currentTask == 0){this.p1q1 = this.multiplyTwo(this.p1,this.q1); }
        else if (this.currentTask == 1){this.p2q2 = this.multiplyTwo(this.p2,this.q2);}
        else if (this.currentTask == 2) { this.p1q2 = this.multiplyTwo(this.p1,this.q2); }
        else if (this.currentTask == 3) {this.p2q1 = this.multiplyTwo(this.p2,this.q1); }
        else if (this.currentTask == 4){this.Task3 = this.addTwo(this.p1q2,this.p2q1);}
        else if (this.currentTask == 5)
        {

            Term x2n = new Term(1,"x",this.polynomial1.getLength());
            this.p1q1 = this.multiplyPolynomialTerm(this.p1q1, x2n);
            Term xn = new Term(1,"x",this.polynomial1.getLength()/2);
            this.Task3 = this.multiplyPolynomialTerm(this.Task3, xn);
        }
        currentTask++;

    }

    public void generateResult()
    {
        ArrayList<Term> result = this.addTwo(this.p1q1,this.Task3);
        result = this.addTwo(result,this.p2q2);
        this.finalPolynomial = result;
    }

    private ArrayList<Term> addSameExponents(ArrayList<Term> listToOrder)
    {
        ArrayList<Term> result = new ArrayList<Term>();
        for(int index1=0; index1 < listToOrder.size(); index1++)
        {
            int index2 = index1+1;
            Term currentTerm = listToOrder.get(index1);
            while (index2 < listToOrder.size())
            {
                if(listToOrder.get(index1).getExponent() == listToOrder.get(index2).getExponent())
                {
                    currentTerm.setCoefficient(currentTerm.getCoefficient() + listToOrder.get(index2).getCoefficient());
                    listToOrder.remove(index2);
                }
                index2++;
            }
            result.add(currentTerm);
        }
        return result;
    }

    private ArrayList<Term> addTwo(ArrayList<Term> first, ArrayList<Term> second)
    {
        ArrayList<Term> result = new ArrayList<Term>();
        for(int index=0; index<second.size();index++)
        {
            first.add(second.get(index));
        }
        result = this.addSameExponents(first);
        return result;
    }

    private ArrayList<Term> multiplyTwo(ArrayList<Term> first, ArrayList<Term> second)
    {
        ArrayList<Term> result = new ArrayList<Term>();
        for(int index1 =0; index1 < first.size(); index1++)
        {
            for(int index2=0; index2 < second.size(); index2++)
            {
                int coefficient = first.get(index1).getCoefficient() * second.get(index2).getCoefficient();
                String variable = "x";
                int exponent = first.get(index1).getExponent() + second.get(index2).getExponent();
                Term termToAdd = new Term(coefficient,variable,exponent);
                result.add(termToAdd);
            }
        }
        return result;
    }

    private ArrayList<Term> multiplyPolynomialTerm(ArrayList<Term> polynomial, Term term)
    {
        ArrayList<Term> result = new ArrayList<Term>();
        for(int index=0; index<polynomial.size();index++)
        {
            int coefficient = polynomial.get(index).getCoefficient() * term.getCoefficient();
            String variable = "x";
            int exponent = polynomial.get(index).getExponent() + term.getExponent();
            Term resultTerm = new Term(coefficient,variable,exponent);
            result.add(resultTerm);
        }
        return result;
    }

    @Override
    public void run()
    {
        if (this.multiplicationMethod == 0){this.basicMultiplication();}
        else {this.karatsubaMultiplication();}
    }

    @Override
    public void start()
    {
        if (this.multiplicationMethod == 0){this.basicMultiplication();}
        else {this.karatsubaMultiplication();}
    }
    @Override
    public String toString()
    {
        return "First Polynomial : " + this.polynomial1.toString() + "\nSecond Polynomial : " + this.polynomial2.toString();
    }

    public void setTotalTasks(int totalTasks) {
        this.totalTasks = totalTasks;
    }

    public void setCurrentTask(int currentTask) {
        this.currentTask = currentTask;
    }
}

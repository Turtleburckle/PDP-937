package com.company;

import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
	// write your code here
        ShadyStuff generator = new ShadyStuff();
        Polynomial polynomial1 = generator.getFirstPolynomial();
        Polynomial polynomial2 = generator.getSecondPolynomial();
        SequencialForm sequencialForm = new SequencialForm(polynomial1,polynomial2);
        //sequencialForm.basicMultiplication();
        //sequencialForm.karatsubaMultiplication();
        ParallelizedForm parallelizedForm = new ParallelizedForm(polynomial1,polynomial2);
        //parallelizedForm.basicMultiplication();
        //parallelizedForm.karatsubaMultiplication();
    }
}

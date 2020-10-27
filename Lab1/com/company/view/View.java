package com.company.view;

import com.company.ShadyStuff;
import com.company.model.Bill;
import com.company.model.Inventory;
import com.company.model.Product;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class View {

    private int numberOfThreads;
    private Inventory inventory;
    private ArrayList<Product> products;
    private ArrayList<Bill> bills;

    public View(ShadyStuff populate)
    {
        this.inventory = populate.getInventory();
        this.products = populate.getProducts();
        this.bills = new ArrayList<>();
    }

    public void run()
    {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter the number of threads: ");
        this.numberOfThreads = scanner.nextInt();
        this.createThreads();
    }

    private void createThreads(){
        System.out.println(this.inventory.toString());
        float startTime =  System.nanoTime() / 1000000;
        for(int index = 0; index <=this.numberOfThreads; index++)
        {
            Bill bill = new Bill(this.inventory,"Bill number " + index);
            int productNumber = this.getRandomNumber(10);
            for (int index2 = 0; index2 < productNumber; index2++)
            {
                int quantity = this.getRandomNumber(10);
                int barcode = this.getRandomNumber(this.products.size());
                bill.add(products.get(productNumber),quantity);
            }
            this.bills.add(bill);
        }

        ArrayList<Thread> threads = new ArrayList<>();
        for(Bill bill : this.bills)
        {
            threads.add(new Thread(bill));
        }

        for(Thread thread : threads)
        {
            thread.start();
        }

        for(Thread thread : threads)
        {
            try
            {
                thread.join();
            }
            catch (InterruptedException e)
            {
                System.out.println(e);
            }
        }

        this.checkStock();
        float endTime = System.nanoTime() / 1000000;
        System.out.println("\n Time : " + (endTime - startTime) / 1000 + " seconds");
        System.out.println(this.inventory.toString());
    }

    private void checkStock()
    {
        System.err.println("Inventory checking ... ");
        boolean allGood = true;
        for (Bill bill : bills)
        {
            if (bill.getTotalPrice() == -100)
            {
                allGood = false;
            }
        }
        if (allGood)
        {
            System.err.println("Verification Successful!");
        }
        else
        {
            System.err.println("Stock verification failed!");
        }

    }

    private int getRandomNumber(int range)
    {
        int randomNumber = new Random().nextInt(range);
        if(randomNumber < 0) {return randomNumber * -1;}
        else if (randomNumber == 0) {return this.getRandomNumber(range);}
        return randomNumber;
    }




}

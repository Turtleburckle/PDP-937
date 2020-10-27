package com.company.model;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Product {
    private int barCode;
    private String name;
    private double price;
    private Lock _mutex = new ReentrantLock();

    public Product (int barCode, String name, double price)
    {
        this.barCode = barCode;
        this.name = name;
        this.price = price;
    }

    public int getBarCode() { return this.barCode; }
    public String getName() { return this.name;}
    public double getPrice() { return price;}
    public void lockProduct(){ _mutex.lock();}
    public void unlockProduct() { _mutex.unlock();}
}

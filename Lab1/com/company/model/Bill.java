package com.company.model;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Bill extends Inventory implements Runnable {
    private String name;
    private double totalPrice = 0.0f;
    private Boolean inventoryChanged;
    private Inventory inventory;
    private Lock _mutex = new ReentrantLock();

    public Bill(Inventory inventory, String name) {
        this.inventory = inventory;
        this.name = name;
    }

    @Override
    public void run() {
        for (Product product : this.getProducts()){
            //_mutex.lock();
            try {
                inventory.remove(product, this.getQuantity(product));
                System.out.println(this.name + ": took " + product.getName() + " -> " + String.valueOf(this.getQuantity(product)));
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
            //_mutex.unlock();
        }
    }

    @Override
    public void add(Product product, int quantity) {
        super.add(product, quantity);
        inventoryChanged = true;
    }

    @Override
    public void remove(Product product, int quantity) {
        super.remove(product, quantity);
        inventoryChanged = true;
    }

    public String getName() {
        return this.name;
    }

    public double getTotalPrice() {
        if (inventoryChanged == null) return -100;
        if (inventoryChanged) {
            this.totalPrice = 0;
            for (Product product : this.getProducts()){
                this.totalPrice += this.getQuantity(product) * product.getPrice();
            }
            this.inventoryChanged = false;
        }

        return this.totalPrice;
    }
}

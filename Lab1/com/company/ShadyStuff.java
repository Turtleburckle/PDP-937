package com.company;

import com.company.model.Inventory;
import com.company.model.Product;

import java.util.ArrayList;
import java.util.Random;

public class ShadyStuff {

    private ArrayList<Product> products;
    private Inventory inventory;

    public ShadyStuff ()
    {
        this.products = new ArrayList<>();
        this.inventory = new Inventory();
        this.addProducts();
    }

    public Inventory getInventory(){return this.inventory;}
    public ArrayList<Product> getProducts(){return this.products;}

    private void addProducts()
    {
        Product product1 = new Product(1,"Milk",3.20);
        this.products.add(product1);
        this.inventory.add(product1,755);
        Product product2 = new Product(2,"Bread",1.40);
        this.products.add(product2);
        this.inventory.add(product2,400);
        Product product3 = new Product(3,"Honey",10.45);
        this.products.add(product3);
        this.inventory.add(product3,256);
        Product product4 = new Product(4,"Cereal",8.77);
        this.products.add(product4);
        this.inventory.add(product1,456);
        Product product5 = new Product(5,"Coffee",14.22);
        this.products.add(product5);
        this.inventory.add(product5,135);
        Product product6 = new Product(6,"Chocolate",4.70);
        this.products.add(product6);
        this.inventory.add(product6,457);
        Product product7 = new Product(7,"Cheese",5.12);
        this.products.add(product7);
        this.inventory.add(product7,324);
        Product product8 = new Product(8,"Water",2.14);
        this.products.add(product8);
        this.inventory.add(product8,2000);
        Product product9 = new Product(9,"Soda",3.78);
        this.products.add(product9);
        this.inventory.add(product9,154);
        Product product10 = new Product(10,"Pasta",8.44);
        this.products.add(product10);
        this.inventory.add(product10,64);
        Product product11 = new Product(11,"Tomatoes",2.96);
        this.products.add(product11);
        this.inventory.add(product11,67);
        Product product12 = new Product(12,"Potatoes",2.50);
        this.products.add(product12);
        this.inventory.add(product12,32);
        Product product13 = new Product(13,"Sugar",4.99);
        this.products.add(product13);
        this.inventory.add(product13,789);
        Product product14 = new Product(14,"Salt",3.22);
        this.products.add(product14);
        this.inventory.add(product14,475);
        Product product15 = new Product(15,"Pepper",2.75);
        this.products.add(product15);
        this.inventory.add(product15,224);
        Product product16 = new Product(16,"Flour",3.77);
        this.products.add(product16);
        this.inventory.add(product16,78);
        Product product17 = new Product(17,"Oil",3.89);
        this.products.add(product17);
        this.inventory.add(product17,65);
    }


}

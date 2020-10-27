package com.company.model;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Set;

public class Inventory {
    private Hashtable <Product,Integer> products;

    public Inventory(){this.products = new Hashtable<>();}

    public void add(Product product, int quantity)
    {
        if (this.products.contains(product))
        {
            this.products.replace(product,this.products.get(product) + quantity);
        }
        else
        {
            this.products.put(product,quantity);
        }
    }

    public void remove(Product product, int quantity)
    {
        if (this.products.containsKey(product))
        {
            if (this.getQuantity(product) < quantity)
            {
                throw new RuntimeException("Insufficient quantity! This amount can't be purchased!");
            }
            product.lockProduct();
            this.products.replace(product, this.products.get(product) - quantity);
            if (this.getQuantity(product) == 0){
                this.products.remove(product);
            }
            product.unlockProduct();
        }
        else{
            throw new RuntimeException("This item is not available anymore!");
        }
    }


    public ArrayList<Product> getProducts(){
        ArrayList<Product> products = new ArrayList<>(this.products.keySet());
        return products;
    }

    public int getQuantity(Product product) {
        return this.products.getOrDefault(product, 0);
    }

    @Override
    public String toString() {
        StringBuilder myString = new StringBuilder();
        for (Product product : this.getProducts()){
            myString.append("{").append(product.getName()).append(", ").append(this.getQuantity(product)).append("}\n");
        }

        return myString.toString();
    }
}

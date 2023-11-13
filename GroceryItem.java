package edu.sdccd.cisc191;

public class GroceryItem {
    private String name;
    private double price;

    public GroceryItem(String name, double price) {
        this.name = name;
        this.price = price;
    }

    @Override
    public String toString() {
        return "GroceryItem{" +
                "name='" + name + '\'' +
                ", price=" + price +
                '}';
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }
}

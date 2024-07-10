package ru.clevertec.check;

public class Product {
    private int id;
    private String name;
    private double price;
    private boolean isWholesale;

    public Product(int id, String name, double price, boolean isWholesale) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.isWholesale = isWholesale;
    }

    public int getId() { return id; }
    public String getName() { return name; }
    public double getPrice() { return price; }
    public boolean isWholesale() { return isWholesale; }
}

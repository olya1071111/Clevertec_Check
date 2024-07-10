package ru.clevertec.check;

public class CheckPosition {

    private Product product;
    private int quantity;

    private double percentageDiscount;
    private double total;
    private double amountDiscount;

    public CheckPosition(Product product, int quantity, double percentageDiscount) {
        this.product = product;
        this.quantity = quantity;
        this.percentageDiscount = percentageDiscount;
        this.total = product.getPrice() * quantity;
        this.amountDiscount = total * (percentageDiscount / 100);
    }

    public Product getProduct() {
        return product;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getTotal() {
        return total;
    }

    public double getAmountDiscount() {
        return amountDiscount;
    }

}

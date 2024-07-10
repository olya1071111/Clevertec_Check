package ru.clevertec.check;

public class DiscountCard {
    private int number;
    private int discount;

    public DiscountCard(int number, int discount) {
        this.number = number;
        this.discount = discount;
    }

    public int getNumber() {
        return number;
    }

    public int getDiscount() {
        return discount;
    }
}

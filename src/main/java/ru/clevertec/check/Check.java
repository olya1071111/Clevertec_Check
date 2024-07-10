package ru.clevertec.check;

import java.util.List;

public class Check {
    private List<CheckPosition> positions;
    private double totalPrice;
    private double totalDiscount;
    private double totalWithDiscount;
    private int discountCardNumber;
    private int discountPercentage;

    public Check(List<CheckPosition> positions, double totalPrice, double totalDiscount, double totalWithDiscount,
                 int discountCardNumber, int discountPercentage) {
        this.positions = positions;
        this.totalPrice = totalPrice;
        this.totalDiscount = totalDiscount;
        this.totalWithDiscount = totalWithDiscount;
        this.discountCardNumber = discountCardNumber;
        this.discountPercentage = discountPercentage;
    }

    public List<CheckPosition> getPositions() {
        return positions;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public double getTotalDiscount() {
        return totalDiscount;
    }

    public double getTotalWithDiscount() {
        return totalWithDiscount;
    }

    public int getDiscountCardNumber() {
        return discountCardNumber;
    }

    public int getDiscountPercentage() {
        return discountPercentage;
    }
}

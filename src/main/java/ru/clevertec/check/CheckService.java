package ru.clevertec.check;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class CheckService {
    private List<Product> products;
    private List<DiscountCard> discountCards;

    public CheckService(List<Product> products, List<DiscountCard> discountCards) {
        this.products = products;
        this.discountCards = discountCards;
    }

    public Check createCheck(Map<Integer, Integer> productQuantities, int discountCardNumber, double balance)
            throws Exception {

        List<CheckPosition> items = new ArrayList<>();

        final double[] totalAmount = {0.0};
        final double[] totalDiscount = {0.0};

        int discount = discountCards.stream()
                .filter(card -> card.getNumber() == discountCardNumber)
                .findFirst()
                .map(DiscountCard::getDiscount)
                .orElse(2); // discount 2%

        for (Map.Entry<Integer, Integer> entry : productQuantities.entrySet()) {
            int productId = entry.getKey();
            int quantity = entry.getValue();

            Product product = products.stream()
                    .filter(p -> p.getId() == productId)
                    .findFirst()
                    .orElseThrow(() -> new InvalidInputException("BAD REQUEST: Product with ID " + productId + " not found"));

            if (quantity <= 0) {
                throw new InvalidInputException("BAD REQUEST: The quantity for product with ID " + productId + " is incorrect!");
            }

            double itemDiscount = 0;
            if (product.isWholesale() && quantity >= 5) {
                itemDiscount = 10; // discount 10%
            } else {
                itemDiscount = discount;
            }

            CheckPosition checkPosition = new CheckPosition(product, quantity, itemDiscount);
            items.add(checkPosition);
            totalAmount[0] += checkPosition.getTotal();
            totalDiscount[0] += checkPosition.getAmountDiscount();
        }

        double totalWithDiscount = totalAmount[0] - totalDiscount[0];

        if (balance < totalWithDiscount) {
            throw new InsufficientFundsException("NOT ENOUGH MONEY!");
        }

        return new Check(items, totalAmount[0], totalDiscount[0], totalWithDiscount, discountCardNumber, discount);
    }
}

package ru.clevertec.check;

import java.math.BigDecimal;
import java.util.*;

public class CheckRunner {
    public static void main(String[] args) {

        try {

            if (args.length < 3) {
                throw new InvalidInputException("incorrect data has been entered");
            }

            Integer discountCardNumber = null;
            BigDecimal balanceWithoutRound = null;
            Double balance = 0.0;
            Map<Integer, Integer> productQuantities = new LinkedHashMap<>();

            for (String arg : args) {

                if (arg.startsWith("discountCard")) {
                    try {
                        String cardNumber = arg.split("=")[1];
                        if (!cardNumber.matches("\\d{4}"))
                            throw new InvalidInputException("Invalid discount card format");
                        discountCardNumber = Integer.parseInt(cardNumber);
                    } catch (ArrayIndexOutOfBoundsException e) {
                        throw new InvalidInputException("Invalid discount card format");
                    }

                } else if (arg.startsWith("balanceDebitCard")) {
                    try {
                        balanceWithoutRound = new BigDecimal(arg.split("=")[1]);
                        balance = balanceWithoutRound.doubleValue();

                    } catch (ArrayIndexOutOfBoundsException | NumberFormatException e) {
                        throw new InvalidInputException("Invalid balance format");
                    }

                } else {
                    try {
                        String[] parts = arg.split("-");
                        if (parts.length != 2) throw new InvalidInputException("Invalid product format");
                        int productId = Integer.parseInt(parts[0]);
                        int quantity = Integer.parseInt(parts[1]);
                        if (quantity <= 0) throw new InvalidInputException("Invalid product quantity");
                        productQuantities.put(productId, quantity);
                    } catch (ArrayIndexOutOfBoundsException | NumberFormatException e) {
                        throw new InvalidInputException("Invalid product format");
                    }
                }
            }

            if (balanceWithoutRound == null) {
                throw new InvalidInputException("Balance not specified");
            }

            if (productQuantities.isEmpty()) {
                throw new InvalidInputException("No products specified");
            }


            List<Product> products = FileReaderCsv.readProductsCSV("src/main/resources/products.csv");
            List<DiscountCard> discountCards = FileReaderCsv.readDiscountCardsCSV("src/main/resources/discountCards.csv");

            CheckService checkService = new CheckService(products, discountCards);

            Check check = checkService.createCheck(productQuantities, discountCardNumber, balance);

            CheckPrinter.writeCheck(check, "src/main/resources/result.csv");

            CheckPrinter.printCheck(check);

        } catch (InvalidInputException e) {
            System.err.println("BAD REQUEST: " + e.getMessage());
        } catch (InsufficientFundsException e) {
            System.err.println(e.getMessage());
        } catch (Exception e) {
            System.err.println("INTERNAL SERVER ERROR: " + e.getMessage());
        }
    }

}

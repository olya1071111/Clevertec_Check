package ru.clevertec.check;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CheckPrinter {
    public static void writeCheck(Check check, String filePath) throws IOException {

        try (PrintWriter writer = new PrintWriter(new FileWriter(filePath))) {
            String date = new SimpleDateFormat("dd.MM.yyyy").format(new Date());
            String time = new SimpleDateFormat("HH:mm:ss").format(new Date());

            writer.println("Date; Time");
            writer.println(date + ";" + time + "\n");

            writer.println("QTY; DESCRIPTION; PRICE; DISCOUNT; TOTAL");
            for (CheckPosition position : check.getPositions()) {
                writer.printf("%d; %s; %.2f$; %.2f$; %.2f$\n",
                        position.getQuantity(),
                        position.getProduct().getName(),
                        position.getProduct().getPrice(),
                        position.getAmountDiscount(),
                        position.getTotal());
            }

            writer.println("\n" + "DISCOUNT CARD; DISCOUNT PERCENTAGE");
            writer.printf("%d; %d%%\n", check.getDiscountCardNumber(), check.getDiscountPercentage());

            writer.println("\n" + "TOTAL PRICE; TOTAL DISCOUNT; TOTAL WITH DISCOUNT");
            writer.printf("%.2f$; %.2f$; %.2f$\n",
                    check.getTotalPrice(),
                    check.getTotalDiscount(),
                    check.getTotalWithDiscount());
        }
    }

    public static void printCheck(Check check) {
        String date = new SimpleDateFormat("dd.MM.yyyy").format(new Date());
        String time = new SimpleDateFormat("HH:mm:ss").format(new Date());

        System.out.println("Date; Time");
        System.out.println(date + ";" + time + "\n");

        System.out.println("QTY; DESCRIPTION; PRICE; DISCOUNT; TOTAL");
        for (CheckPosition position : check.getPositions()) {
            System.out.printf("%d; %s; %.2f$; %.2f$; %.2f$\n",
                    position.getQuantity(),
                    position.getProduct().getName(),
                    position.getProduct().getPrice(),
                    position.getAmountDiscount(),
                    position.getTotal());
        }

        System.out.println("\n" + "DISCOUNT CARD; DISCOUNT PERCENTAGE");
        System.out.printf("%d; %d%%\n", check.getDiscountCardNumber(), check.getDiscountPercentage());

        System.out.println("\n" + "TOTAL PRICE; TOTAL DISCOUNT; TOTAL WITH DISCOUNT");
        System.out.printf("%.2f$; %.2f$; %.2f$\n",
                check.getTotalPrice(),
                check.getTotalDiscount(),
                check.getTotalWithDiscount());
    }
}

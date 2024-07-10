package ru.clevertec.check;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FileReaderCsv {

    public static List<Product> readProductsCSV(String filePath) throws IOException {

        List<Product> products = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            br.readLine();
            while ((line = br.readLine()) != null) {
                String[] values = line.split(";");
                if (values.length < 5) {
                    throw new IOException("Error reading the file produts.csv: " + line);
                }

                int id = Integer.parseInt(values[0].trim());
                String name = values[1].trim();
                double price = Double.parseDouble(values[2].trim());
                boolean isWholesale = values[4].trim().equals("true");
                products.add(new Product(id, name, price, isWholesale));
            }
        }
        return products;
    }

    public static List<DiscountCard> readDiscountCardsCSV(String filePath) throws IOException {
        List<DiscountCard> discountCards = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            br.readLine();
            while ((line = br.readLine()) != null) {
                String[] values = line.split(";");
                if (values.length < 3) {
                    throw new IOException("Error reading the file DiscountCards.csv: " + line);
                }
                int number = Integer.parseInt(values[1].trim());
                int discount = Integer.parseInt(values[2].trim());
                discountCards.add(new DiscountCard(number, discount));
            }
        }
        return discountCards;
    }
}



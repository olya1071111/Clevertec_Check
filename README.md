Application check generator

Requirements:
- Java 21
- CSV files: products.csv and discountCards.csv in ./src/main/resources/

Run:
- the application with the following command format
  java -cp src ./src/main/java/ru/clevertec/check/CheckRunner.java [id products]-[quantities] discountCard=[card_number] balanceDebitCard=[balance_number]

where:
- id_product is product id from the file products.csv
- quantities is the quantity of the product
- card_number is 4 digit discount card number
- balance_number is the balance on the debit card

The application generate a file result.csv which stores the information in the receipt.

For example:  java -cp src ./src/main/java/ru/clevertec/check/CheckRunner.java 3-1 2-5 5-1 discountCard=1111 balanceDebitCard=100

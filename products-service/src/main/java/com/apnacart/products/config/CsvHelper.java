package com.apnacart.products.config;

import com.apnacart.products.entities.Product;
import org.apache.commons.csv.*;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CsvHelper {

    public static String TYPE = "text/csv";
    static String[] HEADERs = { "Id", "Title", "Description", "Published" };

    public static boolean hasCSVFormat(MultipartFile file) {

        if (!TYPE.equals(file.getContentType())) {
            return false;
        }
        return true;
    }

    public static List<Product> csvToProducts(InputStream is) {

        try (BufferedReader fileReader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
             CSVParser csvParser = new CSVParser(fileReader,
                     CSVFormat.DEFAULT.withFirstRecordAsHeader().withIgnoreHeaderCase().withTrim());) {

            List<Product> products = new ArrayList<>();

            Iterable<CSVRecord> csvRecords = csvParser.getRecords();

            for (CSVRecord csvRecord : csvRecords) {
                System.out.println(csvRecord);
                Product product = new Product(
                        csvRecord.get(0),
                        csvRecord.get(1),
                        Integer.parseInt(csvRecord.get(2)),
                        Integer.parseInt(csvRecord.get(3)),
                        csvRecord.get(4),
                        csvRecord.get(5)
                );

                products.add(product);
            }

            return products;
        } catch (IOException | RuntimeException e) {
            throw new RuntimeException("fail to parse CSV file: " + e.getMessage());
        }
    }

    public static ByteArrayInputStream tutorialsToCSV(List<Product> products) {
        final CSVFormat format = CSVFormat.DEFAULT.withQuoteMode(QuoteMode.MINIMAL);

        try (ByteArrayOutputStream out = new ByteArrayOutputStream();
             CSVPrinter csvPrinter = new CSVPrinter(new PrintWriter(out), format);) {
            for (Product product : products) {
                List<String> data = Arrays.asList(
                        String.valueOf(product.getId()),
                        product.getLabel(),
                        product.getDescription()
                );

                csvPrinter.printRecord(data);
            }

            csvPrinter.flush();
            return new ByteArrayInputStream(out.toByteArray());
        } catch (IOException e) {
            throw new RuntimeException("fail to import data to CSV file: " + e.getMessage());
        }
    }
}

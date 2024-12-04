
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.CSVRecord;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Noise {
    // Method to read CSV using Apache Commons CSV
    public static List<double[]> readCSV(String filePath) throws IOException {
        List<double[]> data = new ArrayList<>();
        try (Reader reader = new FileReader(filePath);
             CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT)) {
            for (CSVRecord record : csvParser) {
                try {
                    double x = Double.parseDouble(record.get(0));
                    double y = Double.parseDouble(record.get(1));
                    data.add(new double[]{x, y});
                } catch (NumberFormatException e) {
                    System.err.println("Skipping invalid record: " + record);
                }
            }
        }
        return data;
    }

    // Method to write CSV using Apache Commons CSV
    public static void writeCSV(String filePath, List<double[]> data) throws IOException {
        try (Writer writer = new FileWriter(filePath);
             CSVPrinter csvPrinter = new CSVPrinter(writer, CSVFormat.DEFAULT)) {
            for (double[] row : data) {
                csvPrinter.printRecord(row[0], row[1]);
            }
        }
    }

    // Method to add random salt to the data
    public static void saltData(List<double[]> data, double min, double max) {
        Random random = new Random();
        for (double[] row : data) {
            double salt = min + (max - min) * random.nextDouble();
            row[1] += salt;
        }
    }

    // Main method to handle multiple input and output files
    public static void main(String[] args) {
        String[] inputFiles = {"graph1.csv", "graph2.csv", "graph3.csv"};
        String[] outputFiles = {"SaltedGraph1.csv", "SaltedGraph2.csv", "SaltedGraph3.csv"};

        double saltMin = -10.0;
        double saltMax = 10.0;

        for (int i = 0; i < inputFiles.length; i++) {
            try {
                System.out.println("Processing file: " + inputFiles[i]);

                // Read, salt, and write data for each file
                List<double[]> data = readCSV(inputFiles[i]);
                saltData(data, saltMin, saltMax);
                writeCSV(outputFiles[i], data);

                System.out.println("Salted data saved to: " + outputFiles[i]);
            } catch (IOException e) {
                System.err.println("Error processing file: " + inputFiles[i]);
                e.printStackTrace();
            }
        }
    }
}

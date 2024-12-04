import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;

import java.io.FileWriter;
import java.io.IOException;

public class Grapher {
    public static void generateCSV(String filePath, double slope, double intercept, double start, double end, double step) {
        try (FileWriter writer = new FileWriter(filePath);
             CSVPrinter csvPrinter = new CSVPrinter(writer, CSVFormat.DEFAULT.withHeader("x", "y"))) {

            for (double x = start; x <= end; x += step) {
                double y = slope * x + intercept;
                csvPrinter.printRecord(x, y);
            }

            System.out.println("Data was written to " + filePath);
        } catch (IOException e) {
            System.out.println("Error occurred while attempting to write file: " + filePath);
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        // Define parameters for the three datasets
        double start = -10.0;
        double end = 10.0;
        double step = 0.1;

        // First graph parameters
        generateCSV("graph1.csv", 2.0, 3.0, start, end, step);

        // Second graph parameters
        generateCSV("graph2.csv", 5.0, 10.0, start, end, step);

        // Third graph parameters
        generateCSV("graph3.csv", 10.0, 1.0, start, end, step);
    }
}



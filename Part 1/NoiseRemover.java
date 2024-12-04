import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.CSVRecord;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class NoiseRemover {
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
                    System.err.println("Skipping invalid row: " + record);
                }
            }
        }
        return data;
    }

    public static List<double[]> smoothData(List<double[]> data, int window) {
        List<double[]> smoothedData = new ArrayList<>();
        for (int i = 0; i < data.size(); i++) {
            double x = data.get(i)[0];
            double smoothY = calculateAverage(data, i, window);
            smoothedData.add(new double[]{x, smoothY});
        }
        return smoothedData;
    }

    public static double calculateAverage(List<double[]> data, int index, int window) {
        int count = 0;
        double sum = 0;

        for (int j = Math.max(0, index - window); j <= Math.min(data.size() - 1, index + window); j++) {
            sum += data.get(j)[1];
            count++;
        }
        return sum / count;
    }

    public static void writeCSV(String filePath, List<double[]> data) throws IOException {
        try (Writer writer = new FileWriter(filePath);
             CSVPrinter csvPrinter = new CSVPrinter(writer, CSVFormat.DEFAULT)) {
            for (double[] row : data) {
                csvPrinter.printRecord(row[0], row[1]);
            }
        }
    }

    public static void main(String[] args) {
        String[] inputs = {"SaltedGraph1.csv", "SaltedGraph2.csv", "SaltedGraph3.csv"};
        String[] outputs = {"SmoothedGraph1.csv", "SmoothedGraph2.csv", "SmoothedGraph3.csv"};

        int valueWindow = 3;

        for (int i = 0; i < inputs.length; i++) {
            try {
                System.out.println("Processing file: " + inputs[i]);

                // Read, smooth, and write data
                List<double[]> data = readCSV(inputs[i]);
                List<double[]> smoothedData = smoothData(data, valueWindow);
                writeCSV(outputs[i], smoothedData);

                System.out.println("Smoothed data saved to: " + outputs[i]);
            } catch (IOException e) {
                System.err.println("Error processing file: " + inputs[i]);
                e.printStackTrace();
            }
        }
    }
}



import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Salter {
    //Reads the CSV files
    public static List<double[]> readCSV(String filePath) throws IOException{
        List<double[]> data= new ArrayList<>();
        BufferedReader reader = new BufferedReader(new FileReader(filePath));
        String line;

        while ((line = reader.readLine()) != null){
            String[] values =line.split(",");
            try {
                double x = Double.parseDouble(values[0]);
                double y= Double.parseDouble(values[1]);
                data.add(new double[]{x,y});
            } catch (NumberFormatException e){

            }
        }
        reader.close();
        return data;
    }
    // Writes the CSV output files
    public static void writeCSV(String filePath, List<double[]> data)
    throws IOException {
        BufferedWriter writer= new BufferedWriter(new FileWriter(filePath));
        for (double [] row: data){
            writer.write(row[0]+ ","+ row[1]);
            writer.newLine();
        }
        writer.close();

    }
    // Salts the datat read in from CSV file
    public static void saltData(List<double []> data, double min, double max){
        Random random = new Random();
        for (double [] row:data){
            double salt= min + (max-min)* random.nextDouble();
            row[1]+=salt;
        }
    }
    public static void main(String[] args) {
        String[] Input={"graph1.csv", "graph2.csv", "graph3.csv"} ;
        String[] Output= {"SaltedGraph1.csv", "SaltedGraph2.csv", "SaltedGraph3.csv"};

        double SaltMin= -10.0;
        double SaltMax=10.0;
        for (int i=0; i<Input.length; i++) {
            try {
                List<double[]> data = readCSV(Input[i]);
                saltData(data, SaltMin, SaltMax);
                writeCSV(Output[i], data);
                System.out.println("Salted Data Saved to " + Output[i]);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}

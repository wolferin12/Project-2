import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Smooth {
    // Reads in the Salted CSV files
    public static List<double []> readCSVs(String filePath)throws IOException{
        List<double[]>data= new ArrayList<>();
        BufferedReader reader= new BufferedReader(new FileReader(filePath));
        String line;
         while ((line= reader.readLine())!=null){
             String[] value= line.split(", ");
             try {
                 double x= Double.parseDouble(value[0]);
                 double y= Double.parseDouble(value[1]);
                 data.add(new double[]{x, y});
             }catch (NumberFormatException e){

             }
         }
         reader.close();
        return data;
    }
    // Smooths the data
    public static List<double[]> smoothData(List<double []> data, int window){
        List<double []> smoothedData= new ArrayList<>();
        for (int i=0; i< data.size(); i++){
            double x= data.get(i)[0];
            double smoothY = calculateAverage(data, i, window);
            smoothedData.add(new double[]{x, smoothY});
        }
        return smoothedData;
    }
    //Calculates the average of y within the given window
    public static double calculateAverage(List<double[]>data, int index, int window ){
        int count=0;
        double sum=0;

        for (int j= Math.max(0, index-window); j <=Math.min(data.size()-1, index+window); j++){
            sum+=data.get(j)[1];
            count++;
        }
        return sum/count;
    }
    //Writes the output CSV file
    public static void writeCSV(String filePath, List<double[]> data) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            writer.write("x,y\n"); // Write header
            for (double[] row : data) {
                writer.write(row[0] + "," + row[1]);
                writer.newLine();
            }
        }
    }
    public static void main(String[] args) {
        String[] inputs={"SaltedGraph1.csv", "SaltedGraph2.csv", "SaltedGraph3.csv"};
        String[] outputs= {"SmoothedGraph1.csv", "SmoothedGraph2.csv", "SmoothedGraph3.csv"};

        int valueWindow=3;
      for(int i= 0; i<inputs.length; i++) {
          try {
              List<double[]> data = readCSVs(inputs[i]);
              List<double[]> smoothedData = smoothData(data, valueWindow);
              writeCSV(outputs[i], smoothedData);
              System.out.println("Smoothed data saved to " + outputs[i]);
          } catch (IOException e) {
              e.printStackTrace();
          }
      }
    }
}

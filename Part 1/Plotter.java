import java.io.FileWriter;
import java.io.IOException;
public class Plotter {
    public static void main(String[] args) {
        // Slope and y intercept
        double m= 2.0;
        double b= 3.0;
        // Start and end for the x values and the incrementation
        double start=-10.0;
        double end= 10.0;
        double increase= 0.1;
       //Prints out graph 1 in the csv
        String graph1= "graph1.csv";
        try (FileWriter writer = new FileWriter(graph1)){
            writer.write("x,y\n");

            for (double x =start; x<=end; x+=increase){
                double y= m*x+b;
                writer.write(x + ","+y+"\n");
            }
            System.out.println("Data was written to "+ graph1);
        } catch (IOException e) {
            System.out.println("Error Occurred While Attempting to Write File");
            e.printStackTrace();
        }
        // Slope and y intercept for graph 2
        double m2=5.0;
        double b2=10;
        //Prints out graph 2
        String graph2="graph2.csv";
        try (FileWriter writer = new FileWriter(graph2)){
            writer.write("x,y\n");

            for (double x =start; x<=end; x+=increase){
                double y= m2*x+b2;
                writer.write(x + ","+y+"\n");
            }
            System.out.println("Data was written to "+ graph2);
        } catch (IOException e) {
            System.out.println("Error Occurred While Attempting to Write File");
            e.printStackTrace();
        }
        // slope and y intercept for graph 3
        double m3= 10.0;
        double b3=1.0;
        //prints out graph 3
        String graph3="graph3.csv";
        try (FileWriter writer = new FileWriter(graph3)){
            writer.write("x,y\n");

            for (double x =start; x<=end; x+=increase){
                double y= m3*x+b3;
                writer.write(x + ","+y+"\n");
            }
            System.out.println("Data was written to "+ graph3);
        } catch (IOException e) {
            System.out.println("Error Occurred While Attempting to Write File");
            e.printStackTrace();
        }
    }
}

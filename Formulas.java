public class Formulas {
    static int Factorial(int b){
        int factorial=1;
        int e=1;
        while (e <= b){
            factorial *=e;
            e++;
        }
        return factorial;
    }

    public static void main(String[] args) {
        int n= 7;
        int y= 3;
        int combination;
        int permutation;
        permutation= Factorial(n)/Factorial(n-y);
        combination= Factorial(n)/(Factorial(y)*Factorial(n-y));
        double p= .30;
        double q= 1-p;
        double biDistribution= combination*Math.pow(p,y)*Math.pow(q,n-y);
        double geoDistribution= Math.pow(q, y-1)*p;
        System.out.println("Binomial Distribution: "+String.format("%.3f",biDistribution));
        System.out.println("Geometric Probability Distribution: "+String.format("%.3f",geoDistribution));

    }
}

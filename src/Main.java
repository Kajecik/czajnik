import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.lang.Math;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.io.PrintWriter;

public class Main {

    private static double round(double value, int places) {
        if (places < 0) throw new IllegalArgumentException();

        BigDecimal bd = new BigDecimal(Double.toString(value));
        bd = bd.setScale(places, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }

    public static void main(String[] args) throws FileNotFoundException {

        PrintWriter zapis = new PrintWriter("pkty.txt");
        zapis.println("x, y, z, wartosc");

        double x[][] = new double[4][4];
        double y[][] = new double[4][4];
        double z[][] = new double[4][4];

        int i;
        int j;
        int k;

        double t;
        double s;
        String xyz;

        double zmienna_x;
        double zmienna_y;
        double zmienna_z;

        File plik = new File("dane.txt");
        Scanner in = new Scanner(plik);

        //petla wczytujaca kolejno 32 platy
        for(i=0;i<33;i++) {
            //zapisywanie (z pliku) punktow x,y,z pojedynczego plata do macierzy, a potem obliczenia wykonywane dla danego platu
            for(j=0;j<3;j++){
                for(k=0;k<3;k++) {
                    xyz = in.next();
                    x[j][k] = Double.parseDouble(xyz);

                    xyz = in.next();
                    y[j][k] = Double.parseDouble(xyz);

                    xyz = in.next();
                    z[j][k] = Double.parseDouble(xyz);
                }
            }

            //t od 0 do 1
            //s od 0 do 1
            for(t=0;t<=1;t=t+0.1) {
                for(s=0;s<=1;s=s+0.1) {
                    //obliczanie x,y,z z wzoru
                    zmienna_x = ((1-s)*(1-s)*(1-s))*(x[0][0]*(1-t)*(1-t)*(1-t)+3*x[0][1]*t*(1-t)*(1-t)+3*x[0][2]*t*t*(1-t)+x[0][3]*t*t*t)+
                            (3*(1-s)*(1-s)*s)*(x[1][0]*(1-t)*(1-t)*(1-t)+3*x[1][1]*t*(1-t)*(1-t)+3*x[1][2]*t*t*(1-t)+x[1][3]*t*t*t)+
                            (3*(1-s)*s*s)*(x[2][0]*(1-t)*(1-t)*(1-t)+3*x[2][1]*t*(1-t)*(1-t)+3*x[2][2]*t*t*(1-t)+x[2][3]*t*t*t)+
                            (s*s*s)*(x[3][0]*(1-t)*(1-t)*(1-t)+3*x[3][1]*t*(1-t)*(1-t)+3*x[3][2]*t*t*(1-t)+x[3][3]*t*t*t);

                    zmienna_y = ((1-s)*(1-s)*(1-s))*(y[0][0]*(1-t)*(1-t)*(1-t)+3*y[0][1]*t*(1-t)*(1-t)+3*y[0][2]*t*t*(1-t)+y[0][3]*t*t*t)+
                            (3*(1-s)*(1-s)*s)*(y[1][0]*(1-t)*(1-t)*(1-t)+3*y[1][1]*t*(1-t)*(1-t)+3*y[1][2]*t*t*(1-t)+y[1][3]*t*t*t)+
                            (3*(1-s)*s*s)*(y[2][0]*(1-t)*(1-t)*(1-t)+3*y[2][1]*t*(1-t)*(1-t)+3*y[2][2]*t*t*(1-t)+y[2][3]*t*t*t)+
                            (s*s*s)*(y[3][0]*(1-t)*(1-t)*(1-t)+3*y[3][1]*t*(1-t)*(1-t)+3*y[3][2]*t*t*(1-t)+y[3][3]*t*t*t);

                    zmienna_z = ((1-s)*(1-s)*(1-s))*(z[0][0]*(1-t)*(1-t)*(1-t)+3*z[0][1]*t*(1-t)*(1-t)+3*z[0][2]*t*t*(1-t)+z[0][3]*t*t*t)+
                            (3*(1-s)*(1-s)*s)*(z[1][0]*(1-t)*(1-t)*(1-t)+3*z[1][1]*t*(1-t)*(1-t)+3*z[1][2]*t*t*(1-t)+z[1][3]*t*t*t)+
                            (3*(1-s)*s*s)*(z[2][0]*(1-t)*(1-t)*(1-t)+3*z[2][1]*t*(1-t)*(1-t)+3*z[2][2]*t*t*(1-t)+z[2][3]*t*t*t)+
                            (s*s*s)*(z[3][0]*(1-t)*(1-t)*(1-t)+3*z[3][1]*t*(1-t)*(1-t)+3*z[3][2]*t*t*(1-t)+z[3][3]*t*t*t);
                    //zapisywanie (do pliku) policzonych x,y,z i ich sumy dla roznych wartosci t i s
                    zapis.println(round(zmienna_x,2) + ", " + round(zmienna_y,2) + ", " + round(zmienna_z,2) + ", " + round(zmienna_x+zmienna_z+zmienna_y,2));
                }
            }
        }
        zapis.close();
    }
}

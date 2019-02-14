import java.util.Arrays;

public class MySort
{
    public static void main(String [] args )
    {
        int N = Integer.parseInt(args[0]);
        double[] data = new double[N];
        for (int i=0; i<N; i++)
            data[i] = Math.random();
        double timePrev = System.nanoTime();
        Arrays.sort(data);
        double timeCollection= (System.nanoTime()-timePrev)/1000000000.0;
        System.out.println(timeCollection);

    }
}
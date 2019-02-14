import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MySortAL
{
    public static void main(String [] args )
    {
        int N = Integer.parseInt(args[0]);
        List<Double> data = new ArrayList<Double>(N);
        for (int i=0; i<N; i++)
            data.add(Math.random());
        double timePrev = System.nanoTime();
        Collections.sort(data);
        double timeCollection= (System.nanoTime()-timePrev)/1000000000.0;
        System.out.println(timeCollection);

    }
}
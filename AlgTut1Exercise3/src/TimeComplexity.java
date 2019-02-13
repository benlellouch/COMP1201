import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class TimeComplexity {
    public static void main(String[] args) {
        int N = Integer.parseInt(args[0]);
        double data[] = new double[N];
        List<Double> dataList = new ArrayList<Double>(N);
        for(int i = 0; i<N; i++){
            data[i]= Math.random();
            dataList.add(Math.random());
        }
        double timePrev = System.nanoTime();
        Collections.sort(dataList);
        double timeCollection= (System.nanoTime()-timePrev)/1000000000.0;
        System.out.println(timeCollection);
        timePrev= System.nanoTime();
        Arrays.sort(data);
        double timeArray = (System.nanoTime()-timePrev)/1000000000.0;
        System.out.println(timeArray);

        if(timeCollection < timeArray){
            System.out.println("The ArrayList is a faster data structure than the regular array.");
        } else{
            System.out.println("The array is a faster data structure than the ArrayList.");
        }

    }
}

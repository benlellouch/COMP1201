import java.util.Random;

public class Main {
    public static void main(String[] args) {
        CircularArrayQueue circularArrayQueue = new CircularArrayQueue(10);
        Random rn = new Random();
        for (int i = 0; i< 10; i++){
            circularArrayQueue.enqueue(18);
        }

        int dequeue = circularArrayQueue.dequeue();
        circularArrayQueue.dequeue();

//        for (int i =0; i<12;i++){
//           int test = circularArrayQueue.circularArrayQueue[i];
//            System.out.println(test);
//        }

        System.out.println("Size of Array "+circularArrayQueue.circularArrayQueue.length);
        System.out.println("Capacity Left "+ circularArrayQueue.getCapacityLeft());
        System.out.println("Number of Items "+circularArrayQueue.noItems());
        System.out.println("Dequeued " + dequeue);
    }
}

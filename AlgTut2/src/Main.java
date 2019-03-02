import java.util.Random;

public class Main {
    public static void main(String[] args) {
        CircularArrayQueue circularArrayQueue = new CircularArrayQueue();
        Random rn = new Random();
        int num= 10;
        for (int i = 0; i< num; i++){
            circularArrayQueue.enqueue(18);
        }

        int dequeue = circularArrayQueue.dequeue();
        circularArrayQueue.dequeue();

        circularArrayQueue.enqueue(79);
        circularArrayQueue.enqueue(48);
        circularArrayQueue.enqueue(89);
        circularArrayQueue.enqueue(78);

//        for (int i =0; i<num;i++){
//           int test = circularArrayQueue.circularArrayQueue[i];
//            System.out.println((i+1) + " " + test);
//        }

        System.out.println("Size of Array "+circularArrayQueue.circularArrayQueue.length);
        System.out.println("Capacity Left "+ circularArrayQueue.getCapacityLeft());
        System.out.println("Number of Items "+circularArrayQueue.noItems());
        System.out.println("Dequeued " + dequeue);
    }
}

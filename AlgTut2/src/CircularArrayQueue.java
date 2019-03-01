import java.util.NoSuchElementException;

public class CircularArrayQueue implements MyQueue {
    Integer[]circularArrayQueue;
    private int rear = 0;
    private int front =0;
    public CircularArrayQueue (int i){
        this.circularArrayQueue = new Integer[i];
    }
    @Override
    public void enqueue(int in) {

            if (rear == (circularArrayQueue.length) && front == 0) {
                Integer[] clone = circularArrayQueue.clone();
                circularArrayQueue = new Integer[clone.length * 2];
                for (int i = 0; i < clone.length; i++) {
                    circularArrayQueue[i] = clone[i];
                }
                circularArrayQueue[rear] = in;
                rear++;

            } else {
                circularArrayQueue[rear] = in;
                rear++;
            }

    }

    @Override
    public int dequeue() throws NoSuchElementException {
        int queued = circularArrayQueue[front];
        circularArrayQueue[front] = null;
        front++;

        return queued;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public int noItems() {
        return 0;
    }

    public int getCapacityLeft(){
        return circularArrayQueue.length - rear + rear;
    }
}

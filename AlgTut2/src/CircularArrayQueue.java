import java.util.NoSuchElementException;

public class CircularArrayQueue implements MyQueue {
    Integer[]circularArrayQueue;
    private int rear = -1;
    private int front =-1;
    public CircularArrayQueue (int i){
        this.circularArrayQueue = new Integer[i];
    }
    @Override
    public void enqueue(int in) {
            //condition for the array being full
            if ((rear+1)%circularArrayQueue.length == front) {
                Integer[] clone = circularArrayQueue.clone();
                circularArrayQueue = new Integer[clone.length * 2];
                for (int i = 0; i < clone.length; i++) {
                    circularArrayQueue[i] = clone[i];
                }
                rear=(rear +1)%circularArrayQueue.length;
                circularArrayQueue[rear] = in;


            } else if (rear == -1){
                front = 0; rear = 0;
                circularArrayQueue[rear] = in;

            } else {
                rear=(rear +1)%circularArrayQueue.length;
                circularArrayQueue[rear]= in;
            }

    }

    @Override
    public int dequeue() throws NoSuchElementException {

        if(rear == -1){
            throw new  NoSuchElementException("The Queue is Empty");
        } else if (front == rear){
            int queued = circularArrayQueue[front];
            circularArrayQueue[front] = null;
            front =-1;
            rear =-1;
            return queued;
        } else {
            int queued = circularArrayQueue[front];
            front=(front+1)%circularArrayQueue.length;
            return queued;
        }

    }

    @Override
    public boolean isEmpty() {
        if (front == -1){
            return true;
        }else{return false;}
    }

    @Override
    public int noItems() {
        return rear - front;
    }

    public int getCapacityLeft(){
        return circularArrayQueue.length - (rear - front);
    }
}

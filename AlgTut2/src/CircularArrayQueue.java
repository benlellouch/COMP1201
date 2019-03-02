import java.util.NoSuchElementException;



public class CircularArrayQueue implements MyQueue {
    Integer[]circularArrayQueue;
    private int tail = -1;
    private int head =-1;
    public CircularArrayQueue (){
        this.circularArrayQueue = new Integer[10];
    }
    @Override
    public void enqueue(int in) {
            //condition for the array being full
            if ((tail +1)%circularArrayQueue.length == head) {
                Integer[] clone = circularArrayQueue.clone();
                circularArrayQueue = new Integer[clone.length * 2];
                for (int i = 0; i < clone.length; i++) {
                    circularArrayQueue[i] = clone[i];
                }
                tail =(tail +1)%circularArrayQueue.length;
                circularArrayQueue[tail] = in;



            } else if (head == -1){
                head = 0; tail = 0;
                circularArrayQueue[tail] = in;

            } else {
                tail =(tail +1)%circularArrayQueue.length;
                circularArrayQueue[tail]= in;

            }

    }

    @Override
    public int dequeue() throws NoSuchElementException {

//        if(tail == -1){
//            throw new  NoSuchElementException("The Queue is Empty");
//        } else if (head == tail){
//            int queued = circularArrayQueue[head];
//            circularArrayQueue[head] = null;
//            head =-1;
//            tail =-1;
//            return queued;
//        } else {
//            int queued = circularArrayQueue[head];
//            head =(head +1)%circularArrayQueue.length;
//            return queued;
//        }

        if (tail == head){
            throw new NoSuchElementException("The Queue is Empty");
        } else {
            int queued = circularArrayQueue[head];
            head=(head+1)%circularArrayQueue.length;
            return queued;
        }

    }

    @Override
    public boolean isEmpty() {
        if (head == -1){
            return true;
        }else{return false;}
    }

    @Override
    public int noItems() {
        return  head - (tail +1)  ;
    }

    public int getCapacityLeft(){
        return circularArrayQueue.length - ((tail +1) - head);
    }
}

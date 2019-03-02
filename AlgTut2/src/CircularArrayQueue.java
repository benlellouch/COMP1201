import java.util.NoSuchElementException;



public class CircularArrayQueue implements MyQueue {
    private Integer[]circularArrayQueue;




    private int head =0;
    private int tail =0;
    private int numberOfItems = 0;
    public CircularArrayQueue (){
        this.circularArrayQueue = new Integer[10];
    }
    @Override
    public void enqueue(int in) {

        if(numberOfItems >= circularArrayQueue.length){
            Integer[] clone = new Integer[circularArrayQueue.length * 2];
            for (int i = 0; i < numberOfItems; i++) {
                clone[i]= circularArrayQueue[head];
                head++;
                if(head == circularArrayQueue.length){
                    head = 0;
                }
            }
            circularArrayQueue = clone;
            head=0;
        }
        tail = (head+numberOfItems)%circularArrayQueue.length;
        circularArrayQueue[tail] = in;
        numberOfItems++;




    }

    @Override
    public int dequeue() throws NoSuchElementException {

        if(numberOfItems>0){
            int temp = circularArrayQueue[head];
            circularArrayQueue[head]=null;
            head++;
            if (head==circularArrayQueue.length){
                head =0;
            }
            numberOfItems --;
            return temp;
        } else{
            throw new NoSuchElementException("The Queue is empty");
        }


    }

    @Override
    public boolean isEmpty() {
        if (numberOfItems == 0){
            return true;
        }else{return false;}
    }

    @Override
    public int noItems() {
        return  numberOfItems;
    }

    public int getCapacityLeft(){
        return circularArrayQueue.length - numberOfItems;
    }

    public Integer[] getCircularArrayQueue() {
        return circularArrayQueue;
    }

}

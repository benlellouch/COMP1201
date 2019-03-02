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
            //circularArrayQueue = new Integer[clone.length];
            circularArrayQueue = clone;
            head=0;
        }
        tail = (head+numberOfItems)%circularArrayQueue.length;
        circularArrayQueue[tail] = in;
        numberOfItems++;

//        if ((tail +1)%circularArrayQueue.length == head) {
//
//            tail =(tail +1)%circularArrayQueue.length;
//            circularArrayQueue[tail] = in;
//
//
//
//        } else if (head == -1){
//            head = 0; tail = 0;
//            circularArrayQueue[tail] = in;
//
//        } else {
//            tail =(tail +1)%circularArrayQueue.length;
//            circularArrayQueue[tail]= in;
//
//        }



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

//        if (tail == head){
//            throw new NoSuchElementException("The Queue is Empty");
//        } else {
//            int queued = circularArrayQueue[head];
//            head=(head+1)%circularArrayQueue.length;
//            return queued;
//        }

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

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
    public void enqueue(int input) {

        if(numberOfItems >= circularArrayQueue.length){ // checks if the array is full
            Integer[] clone = new Integer[circularArrayQueue.length * 2]; // creates a new array that is double the size of the circularArrayQueue
            //we create a loop from i to numberOfItems so that all the elements present in the circularArrayQueue gets copied
            for (int i = 0; i < numberOfItems; i++) {
                clone[i]= circularArrayQueue[head]; // we start at head and not at i so that the oldest elements get copied first
                head = (head+1)%circularArrayQueue.length; // we use modulo so that the head "wraps back around" when it reaches the end of the array
            }
            circularArrayQueue = clone; //assigns the new array to the circularArrayQueue
            head=0; //sets the head to 0
        }
        tail = (head+numberOfItems)%circularArrayQueue.length; // we use modulo so that when the tail reaches the end of the array it goes back to 0
        circularArrayQueue[tail] = input; // assigns the input to the current position of the tail
        numberOfItems++;// increments the nymber of items since an element has been added




    }

    @Override
    public int dequeue() throws NoSuchElementException {

        if(numberOfItems>0){ // checks if the array contains elements
            int temp = circularArrayQueue[head]; //temporarily stores the values that is at the head
            circularArrayQueue[head]=null; // sets the value that is at the head to null
            head = (head +1)%circularArrayQueue.length; // increments the head (we use modulo so that the head reaches the end of the array it goes back to 0 )
            numberOfItems --; // de-increments the number of items as a element has been removed from the array
            return temp; //returns the temporarily stored value
        } else{
            throw new NoSuchElementException("The Queue is empty"); // if the array is empty then throw an exception.
        }


    }

    @Override
    public boolean isEmpty() {
        if (numberOfItems == 0){ // checks if there are any items in the array
            return true; // returns true if there aren't any
        }else{return false;} //returns false if there are
    }

    @Override
    public int noItems() {
        return  numberOfItems;
    }

    public int getCapacityLeft(){
        return circularArrayQueue.length - numberOfItems;
    }


}

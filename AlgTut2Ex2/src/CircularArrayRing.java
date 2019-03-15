import java.util.AbstractCollection;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class CircularArrayRing<E> extends AbstractCollection<E> implements Ring<E> {

    Object[] array;
    private int numberOfItems;

    /*As it is impossible to create generic arrays in java I decided to use weak typing.
     * I created an array of type object and then have a getter  for that
      * array because I have to specify the type of object every time I want to
      * access the value of a an element of that array.*/

    public CircularArrayRing(int size){
        array = new Object[size];
    } // creates a new array that is of the size specified as the argument
    public CircularArrayRing(){array = new Object[10];} // default constructor creates a new array of size 10

    E access(int i) { // return the value of the array that is stored in the i_th position
        @SuppressWarnings("unchecked")
        final E e = (E) array[i];
        return e;
    }

    public boolean add(E e){
            // we are using modulo when determining the index as we want the index to return to 0 when numberOfItems reaches the length of the array
            array[numberOfItems%array.length]= e;
            numberOfItems ++; //increments the number of items since we're adding an element
            return numberOfItems>array.length;
    }



    @Override
    public E get(int index) throws IndexOutOfBoundsException {
        //checks if the index passed as the argument can be returned
        if (index > (numberOfItems - 1) || index > array.length || index<0){
            throw new IndexOutOfBoundsException("Can't do that bro");
        } else {
            /* since we want index 0 to return the previous elements,
            we add -1 to the formula so that when index = 0 the access
            method returns numberOfItems - 1. */
            return access((numberOfItems-1-index)%array.length);

        }
    }


    @Override
    public Iterator<E> iterator()  {
        Iterator<E> iterator = new Iterator<>() {
            int count = 0;
            @Override

            public boolean hasNext() {
                /*this method checks if the array has another
            that can be iterated on. We check if count is
            greater or equal that the array length because
            when count reaches the array length it means that
            we have iterated through the whole array.
            */
                if(count >= array.length){
                    return false;
                }else{
                    return true;
                }
            }

            @Override
            public E next() {
                /*we are basically doing the same thing than with get(int index)
                    but we increment count so that every time we call next() it returns
                    the next previous element.
                    * */
                if (count < array.length) {

                    @SuppressWarnings("unchecked") final E e = (E) array[(numberOfItems - 1 - count) % array.length];
                    count++;
                    return e;
                } else {
                    throw new NoSuchElementException("Trying to call next() on a non existing element");
                }
            }
        };

        return iterator;
    }

    @Override
    public int size() { //returns the size of the array
        if(numberOfItems>array.length){
            return array.length;
        }else{
            return numberOfItems;
        }
    }

}

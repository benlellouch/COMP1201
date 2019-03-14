import java.util.AbstractCollection;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class CircularArrayRing<E> extends AbstractCollection<E> implements Ring<E> {

    Object[] array;
    private int numberOfItems;

    public CircularArrayRing(int size){
        array = new Object[size];
    }
    public CircularArrayRing(){array = new Object[10];}

    E access(int i) {
        @SuppressWarnings("unchecked")
        final E e = (E) array[i];
        return e;
    }

    public void mutate(int i, E e) {
        array[i] = e;
    }

    public Object[] getArray() {
        return array;
    }

    public boolean add(E e){
            mutate(numberOfItems%array.length,e);
            numberOfItems ++;
            return false;
    }



    @Override
    public E get(int index) throws IndexOutOfBoundsException {
        if (index > (numberOfItems - 1) || index > array.length || index<0){
            throw new IndexOutOfBoundsException("Can't do that bro");
        } else {
            return access((numberOfItems-1-index)%array.length);

        }
    }


    @Override
    public Iterator<E> iterator()  {
        Iterator<E> iterator = new Iterator<>() {
            int count = 0;
            @Override
            public boolean hasNext() {
                if(count >= array.length){
                    return false;
                }else{
                    return true;
                }
            }

            @Override
            public E next() {
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
    public int size() {
        if(numberOfItems>array.length){
            return array.length;
        }else{
            return numberOfItems;
        }
    }

    public static void main(String[] args) {

        CircularArrayRing<Integer> circularArrayRing = new CircularArrayRing<>(10);
        for (int i=0; i <15; i++){
            circularArrayRing.add(i);
        }
        for (int i = 0; i<circularArrayRing.getArray().length;i++) {
            System.out.println(circularArrayRing.get(i));
        }
    }
}

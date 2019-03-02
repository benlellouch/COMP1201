public class  ArrayQueue
{
    /* ==========================================
      Node "inner class"
      ========================================== */
    public class Node
    {
        double value;
        Node   next;

        public Node( double x )
        {
            value = x;
            next  = null;
        }

        public String toString()
        {
            return "" + value;
        }
    }

    public double[] buf;          // Circular buffer
    public int      read, write;  // read and write pointers

    // Constructor
    public ArrayQueue(int size)
    {
        buf = new double[size];    // Create array for circular buffer

        read = 0;                  // Initialized read & write pointers
        write = 0;
    }

    /* ====================================================
       enqueue(x ):
      ==================================================== */
    public void enqueue( double x )   throws Exception
    {
        if ( read == ( write + 1 ) % (buf.length) )  // Full...
        {
            throw new Exception("Queue is full");
        }

        buf[write] = x;                 // Store x in buf at write pointer
        write = (write+1)%(buf.length); // Advance the write pointer
    }

    /* ====================================================
       dequeue():
      ==================================================== */
    public double dequeue( ) throws Exception
    {
        double r;   // Variable used to save the return value

        if ( read == write )
        {
            throw new Exception("Queue is empty");
        }

        r = buf[read];                 // Save return value
        read = (read+1)%(buf.length);  // Advance the read pointer

        return r;
    }

    public static void main(String[] args) {
        ArrayQueue arrayQueue = new ArrayQueue(10);
        for (int i = 0; i<10; i++){
            try {
                arrayQueue.enqueue(1.0);
            }catch (Exception e){}
        }

        System.out.println(arrayQueue.write - arrayQueue.read);
    }


}
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * The LockingCircularBuffer Class starts with the circular buffer from the example provided and implements the locking with lock and condition rather
 * than the synchronized keyword.
 * @author Ben Weinberg
 */
public class LockingCircularBuffer implements Buffer {
    private final int[] buffer = {-1, -1, -1}; // shared buffer
    private int occupiedCells = 0; // count number of buffers used

    // Lock to control synchronization with this buffer
    private final Lock accessLock = new ReentrantLock();

    // conditions to control reading and writing
    private final Condition canWrite = accessLock.newCondition();
    private final Condition canRead = accessLock.newCondition();
    private int writeIndex = 0; // index of next element to write to
    private int readIndex = 0; // index of next element to read

    // place int value into buffer

    /**
     * Put something into the buffer using locks and conditions
     * @param value value you want to place into the buffer
     * @throws InterruptedException
     */
    public void blockingPut(int value) throws InterruptedException {
        accessLock.lock(); // lock this object

        // output thread information and buffer information, then wait
        try {
            // while buffer is not empty, place thread in waiting state
            while (occupiedCells == buffer.length) {
                System.out.println("Producer tries to write.");
                displayState("Buffer full. Producer waits.");
                canWrite.await();// wait until buffer is empty
            }

            buffer[writeIndex] = value; // set new buffer value

            // indicate producer cannot store another value
            // until consumer retrieves current buffer value
            occupiedCells++;
            writeIndex = (writeIndex + 1) % buffer.length;

            displayState("Producer writes " + value);

            // signal any threads waiting to read from buffer
            canRead.signalAll();
        } finally {
            accessLock.unlock(); // unlock this object
        }
    }

    // return value from buffer

    /**
     * Get something from the buffer using locks and conditions
     * @return value gotten from the buffer
     * @throws InterruptedException
     */
    public int blockingGet() throws InterruptedException {
        int readValue = 0; // initialize value read from buffer
        accessLock.lock(); // lock this object

        // output thread information and buffer information, then wait
        try {
            // if there is no data to read, place thread in waiting state
            while (occupiedCells == 0) {
                System.out.println("Consumer tries to read.");
                displayState("Buffer empty. Consumer waits.");
                canRead.await(); // wait until buffer has anything in it
            }

            readValue = buffer[readIndex]; // retrieve value from buffer

            // indicate that producer can store another value
            // because consumer just retrieved buffer value
            readIndex = (readIndex + 1) % buffer.length;
            occupiedCells--;

            displayState("Consumer reads " + readValue);

            // signal any threads waiting for buffer to be empty
            canWrite.signalAll();
        } finally {
            accessLock.unlock(); // unlock this object
        }

        return readValue;
    }

    // display current operation and buffer state

    /**
     * helper function to help display the status of the buffer
     * @param operation what operation was preformed to write to console
     */
    public void displayState(String operation) {
        try{
            accessLock.lock();
            // output operation and number of occupied buffer cells
            System.out.printf("%s%s%d)%n%s", operation,
                    " (buffer cells occupied: ", occupiedCells, "buffer cells:  ");

            for (int value : buffer)
                System.out.printf(" %2d  ", value); // output values in buffer

            System.out.printf("%n               ");

            for (int i = 0; i < buffer.length; i++)
                System.out.print("---- ");

            System.out.printf("%n               ");

            for (int i = 0; i < buffer.length; i++){
                if (i == writeIndex && i == readIndex)  System.out.print(" WR");    // both write and read index
                else if (i == writeIndex)               System.out.print(" W   ");  // just write index
                else if (i == readIndex)                System.out.print("  R  ");  // just read index
                else                                    System.out.print("     ");  // neither index
            }
            System.out.printf("%n%n");
        }
        finally {
            accessLock.unlock();
        }
    }

} // end class LockingCircularBuffer
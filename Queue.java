import java.util.Scanner;

public class Queue{
/*
            * front is the index of the first item on the queue
            * back  is the index of the last item on the queue
            * Sometimes, front > back (that's the "wrap-around" part).
            * For example, let's say the queue starts with a capacity of 4.
            *                -------
            * q.enqueue(1): |1| | | |
            *                -------
            *                -------
            * q.enqueue(2): |1|2| | |
            *                -------
            *                -------
            * q.enqueue(3): |1|2|3| |
            *                -------
            *                -------
            * q.dequeue():  | |2|3| |
            *                -------
            * q.enqueue(4): | |2|3|4|
            *                -------
            *                -------
            * q.dequeue():  | | |3|4|
            *                -------
            *                -------
            * q.enqueue(5): |5| |3|4| <-- notice that 3 is the front of the queue, 5 is the back
 */
    private int capacity = 4;
    int size = 0;

    // initialize empty array queue
    private int[] items = new int[capacity];

    // front is the index of the first item on the queue
    private int front = 0;

    // return the size of the queue
    public int size() {
        return size;
    }

    public void enqueue(int item) throws FullQueueException {

        if (size == capacity) throw new FullQueueException();

        System.out.println("Enqueueing " + item);

        items[(front + size) % items.length] = item;
        size++;
    }

    public int dequeue() throws EmptyQueueException {

        if (size == 0) throw new EmptyQueueException();

        else{
            int dequeueItem = items[front] ;
            front = (front + 1) % items.length;
            size--;
            return dequeueItem;
        }
    }

    public int peek() throws EmptyQueueException {
        if (size == 0) throw new EmptyQueueException();
        return items[front];
    }

    public String toString() {

        if (size == 0)
            return "[ ]" + " || front: " + front + " || size: " + size + " || capacity: " + capacity;

        String answer = "[ ";

        int position = front;

        for (int i=0; i<size; i++) {
            answer += items[position++] + " ";
            if (position == capacity) position = 0;
        }
        return answer + "] || front: " + front + " || size: " + size + " ||capacity: " + capacity;
    }

    private static class EmptyQueueException extends Exception{
        private String message;
        public EmptyQueueException(){
            this.message = "This Queue is empty";
        }

        @Override
        public String getMessage(){
            return message;
        }
    }

    private static class FullQueueException extends Exception{
        private String message;
        public FullQueueException(){
            this.message = "This queue is already full";
        }

        @Override
        public String getMessage(){
            return message;
        }
    }

    public static void main(String[ ] args) throws EmptyQueueException, FullQueueException {

        Queue q = new Queue();
        Scanner console = new Scanner(System.in);
        int choice;
        int number;
        System.out.println(q);

        while (true) {
            System.out.println("Enter 1 to enqueue, 2 to dequeue, 3 to stop, 4 to peek at the front of the queue");

            choice = console.nextInt();

            switch (choice) {

                case 3: System.exit(0);

                case 1: System.out.println("Enter the number to enqueue");
                    number = console.nextInt();
                    q.enqueue(number);
                    System.out.println(q);
                    break;

                case 2: System.out.println(q.dequeue() + " is dequeued");
                    System.out.println(q);
                    break;

                case 4:
                    System.out.println("number the front of the queue is " + q.peek() );
                    break;

                default: System.out.println("Invalid input");
            }
        }
    }
}

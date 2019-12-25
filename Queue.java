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

    private int[] items = new int[capacity];

    // front is the index of the first item on the queue
    private int front = 0;

    // back is the index of the last item on the queue.  -1 indicates empty queue
    private int back = -1;

    public int size() {
        return size;
    }

    public void enqueue(int item) {
        if (size == capacity)
            expandCapacity();
        System.out.println("Enqueueing " + item);
        size++;
        back = (back + 1)%capacity; // wraparound
        items[back] = item;
    }

    public int dequeue() throws EmptyQueueException {
        if (size == 0) throw new EmptyQueueException();
        int answer = items[front++];
        size--;
        if (size == 0) {
            front = 0;
            back = -1;
        }
        if (front == capacity) front = 0;
        return answer;
    }

    public int peek() throws EmptyQueueException {
        if (size == 0) throw new EmptyQueueException();
        return items[front];
    }

    private void expandCapacity() {
        System.out.println("Calling expandCapacity. The array will now be doubled in size. The old size is " + size);
        int oldCapacity = capacity;
        capacity *= 2;
        int[] newItems = new int[capacity];
        for (int i=0; i<size; i++) {
            newItems[i] = items[front++];
            if (front == oldCapacity) front = 0;
        }
        items = newItems;
        front = 0;
        back = size - 1;
    }

    public String toString() {
        if (size == 0)
            return "[ ]" + " front: " + front + " || back: " + back + " || size: " + size + " || capacity: " + capacity;
        String answer = "[ ";
        int position = front;
        for (int i=0; i<size; i++) {
            answer += items[position++] + " ";
            if (position == capacity) position = 0;
        }
        return answer + "] front: " + front + " || back: " + back + " || size: " + size + " ||capacity: " + capacity;
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

    public static void main(String[ ] args) throws EmptyQueueException {

        Queue q = new Queue();
        Scanner console = new Scanner(System.in);
        int choice;
        int number;
        System.out.println(q);

        while (true) {
            System.out.println("Enter 1 to enqueue, 2 to dequeue, 3 to stop");

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

                default: System.out.println("Invalid input");
            }
        }
    }
}

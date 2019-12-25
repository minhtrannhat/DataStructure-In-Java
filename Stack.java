import java.util.*;

public class Stack
{
    // to keep the index of the last element pushed into the stack
    // newly initialized stack has head = -1

    private int head = -1;
    private int[] array;


    // initialize a stack
    public Stack(int n)
    {
        array = new int[n];
    }

    // check if the array is full
    private boolean isFull()
    {
        return (head == array.length - 1);
    }

    // check if the array is empty
    private boolean isEmpty()
    {
        return (head == -1);
    }

    // function to push a new element into the stack
    private void push(int num) throws FullStackException
    {
        // check whether the stack is full or not
        if (isFull())
            throw new FullStackException();

        else
        {
            head++;
            array[head] = num;
        }
    }

    // function to take out an element from the stack and return the last element
    private int pop() throws EmptyStackException
    {
        if (isEmpty()) {
            throw new EmptyStackException();
        }

        else {
            int temp = array[head];
            head--;
            return temp;
        }

    }

    // function to return the element at the top of the stack
    private int top() throws EmptyStackException
    {
        if (isEmpty()) {
            throw new EmptyStackException();
        }

        else {
            return array[head];
        }
    }
    // function to return the size of the stack
    private int sizeOf()
    {
        return head + 1;
    }

    private static class FullStackException extends Exception
    {
        private String message;
        public FullStackException()
        {
            this.message = "this stack is full";
        }

        @Override
        public String getMessage(){

            return message;
        }
    }

    public static void main(String[] args) {
        Stack newStack = new Stack(1);

        try {
            newStack.push(5);
            newStack.push(3);
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
        System.out.println("The size of the stack as of now is " + newStack.sizeOf());
    }
}
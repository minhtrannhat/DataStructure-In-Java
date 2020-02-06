import java.util.ArrayList;

public class StackWithArrayList {
    private ArrayList<Object> list = new ArrayList<>();

    public boolean isEmpty(){
        return list.isEmpty();
    }

    public int getSize(){
        return list.size();
    }

    public Object peek(){
        return list.get(getSize() - 1);
    }

    public Object pop(){
        Object o = list.get(getSize() - 1);
        list.remove(getSize() - 1_);

        return o;
    }

    @Override
    public String toString(){
        return "stack: " + list.toString();
    }

}

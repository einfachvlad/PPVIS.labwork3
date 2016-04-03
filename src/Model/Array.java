package Model;

import java.util.*;

public class Array implements Iterable<Integer> {
    private List<Integer> array;
    private long timeOfSort;

    public Array() {
        array = new ArrayList<>();
    }

    public void add(int date) {
        array.add(date);
    }

    public int size() {
       return array.size();
    }

    public List sort() {
        long timeout= System.currentTimeMillis();
        array.sort(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o1.compareTo(o2);
            }
        });
        for(long i=0;i<10000000;i++);
        timeout = System.currentTimeMillis() - timeout;
        timeOfSort=timeout;
        return array;
    }

    public List getArray() {
        return array;
    }

    public Iterator<Integer> iterator() {
        return array.iterator();
    }

    public long getTimeOfSort(){
        return timeOfSort;
    }

}

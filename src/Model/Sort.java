package Model;

import java.util.*;

public class Sort {

    static final int L = 2;


    public List startedSerialSort(List<Integer> array){

        if (array.size() < Sort.L+1) {
                internalSort(array);
        } else {
            externalSort(array);
        }

        return  array;
    }

    private void internalSort(List<Integer> array) {
        array.sort(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o1.compareTo(o2);
            }
        });
    }

   private void externalSort(List<Integer> array) {
        List<List<Integer>> serials = new ArrayList<>();


            serialsCreating(array,serials);
            mergeDevide(serials);
            array.clear();
            array.addAll(serials.get(0));
        System.out.println();
    }

    private List serialsCreating(List<Integer> array,List<List<Integer>> serials) {

        List<Integer> serial = new ArrayList<>();
        for (int element : array) {
            if (serial.size() != Sort.L) {
                serial.add( element);
            } else {
                internalSort(serial);
                serials.add(serial);
                serial = new ArrayList<>();
                serial.add( element);
            }
        }
        internalSort(serial);
        serials.add(serial);
        return serials;
    }

    private void mergeDevide(List<List<Integer>> series) {
        List<List<Integer>> arrays1 = new ArrayList<>();
        List<List<Integer>> arrays2 = new ArrayList<>();

        while (series.size() != 1) {
            boolean swap = true;
            for (List<Integer> seria : series) {
                if (swap) {
                    arrays1.add(seria);
                    swap = !swap;
                } else {
                    arrays2.add(seria);
                    swap = !swap;
                }
            }

            series.clear();

            Iterator iterator = arrays2.iterator();
            for (List<Integer> array1 : arrays1) {
                List<Integer> array = new ArrayList<>();
                array.addAll(array1);
                if (iterator.hasNext())
                    array.addAll((List) iterator.next());
                internalSort(array);
                series.add(array);

            }
            arrays1.clear();
            arrays2.clear();
        }
    }


}

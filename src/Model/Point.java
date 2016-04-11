package Model;

import javax.management.ObjectName;
import java.io.*;
import java.util.*;

public class Point {
    Random random = new Random();

    static final int L = 2;
    private int numberOfArrays;
    private int countOfElements;
    private long averageSortTime;
    int X;
    int Y;
    List<List<Integer>> arraysX = new ArrayList<>();
    List<List<Integer>> series = new ArrayList<>();
    List<Long> arraysY = new ArrayList<>();

    public void setMeasures(int numberOfArrays) {
        this.numberOfArrays = numberOfArrays;
    }

    public void initArray(int countOfElements) {
        this.countOfElements = countOfElements;

        for (int index = 0; index < numberOfArrays; index++) {
            List array = new ArrayList<>();
            for (int data = 0; data < countOfElements; data++)
                array.add(random.nextInt(2147483647));

            arraysX.add(array);
        }
    }

    public void sort() {
        if (countOfElements < Point.L) {
            for (List array : arraysX) {
                Date date=new Date();
                long millis = date.getTime();
                internalSort(array);
               // for (int i = -2147483647; i < 2147483647; i++) ;
                date=new Date();
                long timeSpent = date.getTime() -  millis;
                arraysY.add(timeSpent);
                averageSortTime();
            }
        } else {
            externalSort(arraysX);
            averageSortTime();
        }
        System.out.println(averageSortTime);

    }

    public long getAverageSortTime() {
        return averageSortTime;
    }

    private void internalSort(List array) {
        array.sort(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o1.compareTo(o2);
            }
        });
    }

    private void externalSort(List<List<Integer>> arrays) {
        for (List array : arrays) {
            Date date=new Date();
            long millis = date.getTime();
            seriesCreating(array);
            mergeDevide(series);
            array.clear();
            array.addAll(series.get(0));
            //for (int i = -2147483647; i < 2147483647; i++) ;
            date=new Date();
            long timeSpent = date.getTime() -  millis;
            arraysY.add(timeSpent);
        }
        System.out.println();
    }

    private List seriesCreating(List array) {
        int countOfSeries = array.size() / Point.L;
        series = new ArrayList<>();

        List<Integer> seria = new ArrayList<>();
        for (Object element : array) {
            if (seria.size() != Point.L) {
                seria.add((int) element);
            } else {
                internalSort(seria);
                series.add(seria);
                seria = new ArrayList<>();
                seria.add((int) element);
            }
        }
        internalSort(seria);
        series.add(seria);
        return series;
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

    private void show(List array) {
        for (Object element : array) {
            System.out.println((int) element);
        }
        System.out.println("\n");
    }

    private long averageSortTime() {
        long time = 0;
        for (long element : arraysY) {
            time += element;
        }
        averageSortTime = time / arraysY.size();
        return averageSortTime;
    }
}
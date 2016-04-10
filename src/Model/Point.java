package Model;

import javax.management.ObjectName;
import java.io.*;
import java.util.*;

public class Point {
    Random random = new Random();

    static final int L = 50;
    private int numberOfArrays;
    private int maxCountElements;
    private int countOfElements;
    String inFile = "input.txt";
    String outFile1 = "out1.txt";
    String outFile2 = "out2.txt";
    int X;
    int Y;
    List<List<Integer>> arraysX = new ArrayList<>();
    List<List<Integer>> series = new ArrayList<>();
    List<List<Integer>> arraysY = new ArrayList<>();

    public void setMeasures(int numberOfArrays, int maxCountElements) {
        this.numberOfArrays = numberOfArrays;
        this.maxCountElements = maxCountElements;
    }

    public void initArray(int countOfElements) {
        this.countOfElements = countOfElements;

        for (int index = 0; index < numberOfArrays; index++) {
            List array = new ArrayList<>();
            for (int data = 0; data < countOfElements; data++)
                array.add(random.nextInt(1000));

            arraysX.add(array);
        }
    }

    public void sort() {
        if (countOfElements < Point.L) {
            for (List array : arraysX) {
                internalSort(array);
                show(array);
            }
        } else {
            externalSort(arraysX);
        }
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
            seriesCreating(array);
            writeFile(series);
            readFile();

        }

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

    private void writeFile(List<List<Integer>> series) {
        File file = new File(inFile);
        try {
            //проверяем, что если файл не существует то создаем его
            if (!file.exists()) {
                file.createNewFile();
            }

            //PrintWriter обеспечит возможности записи в файл
            PrintWriter in = new PrintWriter(file.getAbsoluteFile());

            try {
                for (List seria : series) {
                    for (Object element : seria) {
                        in.print(element);
                        in.print(" ");
                    }
                    in.print("\n");
                }
            } finally {
                //После чего мы должны закрыть файл
                //Иначе файл не запишется
                in.close();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private String readFile() {

        File inputFile = new File(inFile);
        File outputFile1 = new File(outFile1);
        File outputFile2 = new File(outFile2);


        //Этот спец. объект для построения строки
        StringBuilder sb = new StringBuilder();

        try {
            PrintWriter out1 = new PrintWriter(outputFile1.getAbsoluteFile());
            PrintWriter out2 = new PrintWriter(outputFile2.getAbsoluteFile());


            try {
                //Объект для чтения файла в буфер
                BufferedReader in = new BufferedReader(new FileReader(inputFile.getAbsoluteFile()));
                try {
                    //В цикле построчно считываем файл
                    String s;
                    boolean swap = true;
                    while ((s = in.readLine()) != null) {
                        sb.append(s);
                        //sb.append("\n");
                        if (swap) {
                            out1.println(sb);
                            swap = !swap;
                            sb.delete(0, sb.length());
                        } else {
                            out2.println(sb);
                            swap = !swap;
                            sb.delete(0, sb.length());
                        }


                    }
                } finally {
                    //Также не забываем закрыть файл
                    in.close();
                    out1.close();
                    out2.close();
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } catch (FileNotFoundException e) {
        }
        //Возвращаем полученный текст с файла
        return sb.toString();
    }


    private void show(List array) {
        for (Object element : array) {
            System.out.println((int) element);
        }
        System.out.println("\n");
    }

}
package Controller;

import Model.ArraysGenerator;
import Model.AverageTime;
import Model.Point;
import Model.Sort;
import View.*;
import View.Window;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class BuildThread implements Runnable {
    private int maxCountElements;
    private int currentCountElements = 2;
    private int numberOfArrays;
    JTable table = new JTable();
    Graphic grap;
    Window window;

    public BuildThread(JTable table, Graphic grap, Window window) {
        this.window = window;
        this.maxCountElements = window.numberOfElements;
        this.numberOfArrays = window.numberOfArrays;
        this.table = table;
        this.grap = grap;
    }

    public void run() {
        System.out.println("Привет из побочного потока!");

        Sort sort = new Sort();
        ArraysGenerator generator = new ArraysGenerator();
        GraphTableModel model = new GraphTableModel();
        while (currentCountElements < maxCountElements + 1) {
            AverageTime averageTime = new AverageTime();
            while (averageTime.size()!=numberOfArrays) {
                List<Integer> unsortedArray = generator.generate(currentCountElements);
                long timeMillis = System.currentTimeMillis();
                sort.startedSerialSort(unsortedArray);
                //(for(int i=0;i<1000000;i++);
                long timeOfSort = System.currentTimeMillis() - timeMillis;
                averageTime.add((int)timeOfSort);
            }
            Point point=new Point(currentCountElements,averageTime.getAverageTime());

            model.addPoint(point);
            table.setModel(model.getModel());
            // grap.setCoordinats(currentCountElements,point.getAverageSortTime());
            currentCountElements += 50;
           /* if(currentCountElements>250)
            {

                //JPanel panel = new JPanel(new BorderLayout());
               // panel.add(grap);
                JScrollPane scrollPane = new JScrollPane(window.mainwindow);
               //window.mainwindow.remove(grap);
               // JScrollPane scrollPane=new JScrollPane(grap);
                scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
                scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
                //scrollPane.add(window.mainwindow);
                //window.mainwindow.add(panel, BorderLayout.CENTER);
            }*/

            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                return;
            }
        }
        if(currentCountElements>maxCountElements)
        {
            currentCountElements=maxCountElements;
            AverageTime averageTime = new AverageTime();
            while (averageTime.size()!=numberOfArrays) {
                List<Integer> unsortedArray = generator.generate(currentCountElements);
                long timeMillis = System.currentTimeMillis();
                sort.startedSerialSort(unsortedArray);
                //(for(int i=0;i<1000000;i++);
                long timeOfSort = System.currentTimeMillis() - timeMillis;
                averageTime.add((int)timeOfSort);
            }
            Point point=new Point(currentCountElements,averageTime.getAverageTime());

            model.addPoint(point);
            table.setModel(model.getModel());
            // grap.setCoordinats(currentCountElements,point.getAverageSortTime());
        }

    }
}

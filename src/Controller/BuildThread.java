package Controller;

import Model.Point;
import View.GraphTableModel;
import View.Graphic;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class BuildThread implements Runnable {
    private int maxCountElements;
    private int currentCountElements=2;
    private int numberOfArrays;
    JTable table = new JTable();
    Graphic grap;
    public BuildThread(JTable table, Graphic grap,int maxCountElements,int numberOfArrays){
        this.maxCountElements=maxCountElements;
        this.numberOfArrays=numberOfArrays;
        this.table=table;
        this.grap=grap;
    }
    public void run() {
        System.out.println("Привет из побочного потока!");

        List<Integer>X=new ArrayList<>();
        List<Long>Y=new ArrayList<>();
        while (currentCountElements != maxCountElements+1) {
            Point point = new Point();
            point.setMeasures(numberOfArrays);
            point.initArray(currentCountElements);
            point.sort();

            X.add(currentCountElements);
            Y.add(point.getAverageSortTime());
            GraphTableModel model = new GraphTableModel(X, Y);
            table.setModel(model.getModel());
           // grap.setCoordinats(currentCountElements-4998,point.getAverageSortTime());
            currentCountElements++;

            /*try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                return;
            }*/
        }

    }
}

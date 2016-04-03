package Controller;


import Model.Array;
import View.GraphTableModel;
import View.Graphic;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class BuildFunction implements ActionListener {

    private int numberOfArrays;
    String inputNumber;
    List<Array> arrays = new ArrayList<>();
    Random random = new Random();
    JTable table = new JTable();
    Graphic grap;

    public BuildFunction(JTable table,Graphic grap) {
        this.table = table;
        this.grap=grap;
    }

    public void actionPerformed(ActionEvent event) {
        try {
            inputNumber = JOptionPane.showInputDialog(null, "Введите кол-во массивов");

            if (inputNumber == null) throw new ExceptionInInitializerError();
            numberOfArrays = Integer.valueOf(inputNumber);

        } catch (ExceptionInInitializerError e) {
        } catch (Exception e) {
            JOptionPane.showMessageDialog
                    (null, "Введите число", "Ошибка", JOptionPane.ERROR_MESSAGE);
        }

        arraysInit();
        sort();
        buildtable();

    }

    private void arraysInit() {
        for (int index = 0; index < numberOfArrays; index++) {
            Array array = new Array();
            for (int data = 0; data < index + 1; data++)
                array.add(random.nextInt(1000));

            arrays.add(array);
        }
        System.out.println(arrays.size());
    }

    private void sort() {
        for (Array array : arrays) {
            System.out.println("\n");
            array.sort();
            for (int date : array) {
                System.out.println(date);
            }
            System.out.println(array.getTimeOfSort());
        }

    }

    private void buildtable() {

        List<Integer> X = new ArrayList<>();
        List<Long> Y = new ArrayList<>();
        for (Array array : arrays) {
            X.add(array.size());
            Y.add(array.getTimeOfSort());
        }
        GraphTableModel model = new GraphTableModel(X, Y);
        table.setModel(model.getModel());

        grap.setMaxMeasures(X,Y);

    }
}

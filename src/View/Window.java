package View;

import Controller.Build;
import Controller.BuildFunction;
import Controller.OpenInputAction;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class Window {
    public JFrame mainwindow;
    JTable table;
    Graphic grap;
    GraphTableModel tableModel;

    public Window() {
        mainwindow = new JFrame("Лабораторная работа №3");
        mainwindow.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        grap = new Graphic();
        mainwindow.add(table(), BorderLayout.WEST);
        mainwindow.add(grap,BorderLayout.CENTER);
        mainwindow.add(buttons(), BorderLayout.SOUTH);
        mainwindow.setSize(690,550);
        mainwindow.setVisible(true);
    }

    private JComponent table() {
        table = new JTable();

        tableModel = new GraphTableModel();
        table.setModel(tableModel.getModel());
        JScrollPane scrollPane = new JScrollPane(table);

        scrollPane.setMinimumSize(new Dimension(150, 400));
        scrollPane.setPreferredSize(new Dimension(150, 400));
        scrollPane.setMaximumSize(new Dimension(150, 400));

        return scrollPane;
    }


    private Box buttons() {
        Box buttons = Box.createHorizontalBox();

        JButton inputFunction = new JButton("Задание функции");
        inputFunction.addActionListener(new OpenInputAction());
        JLabel scale = new JLabel("Заглушка");
        JButton startGraphich = new JButton("Построить график");
        startGraphich.addActionListener(new Build());

        buttons.add(Box.createHorizontalStrut(6));
        buttons.add(inputFunction);
        buttons.add(Box.createHorizontalStrut(200));
        buttons.add(scale);
        buttons.add(Box.createHorizontalStrut(6));
        buttons.add(startGraphich);
        return buttons;

    }

}

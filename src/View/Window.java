package View;

import Controller.*;
import Model.Scale;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Window {
    public int numberOfArrays = 0;
    public int numberOfElements = 0;
    public JFrame mainwindow;
    JLabel scaleLabel = new JLabel();
    Scale scale = new Scale();
    JTable table;
    GraphicPanel grap;
    GraphTableModel tableModel;

    public Window() {
        mainwindow = new JFrame("Лабораторная работа №3");
        mainwindow.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        grap = new GraphicPanel("массивы","мс");

        JScrollPane scrollPane = new JScrollPane(grap);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

        mainwindow.add(table(), BorderLayout.WEST);
        mainwindow.add(scrollPane, BorderLayout.CENTER);
        mainwindow.add(buttons(), BorderLayout.SOUTH);

        Zoom zoom = new Zoom(scale, scaleLabel, grap);
        Dragging dragging=new Dragging(scrollPane);
        grap.addMouseListener(zoom);
        grap.addMouseWheelListener(zoom);
        grap.addMouseListener(dragging);
        grap.addMouseMotionListener(dragging);

        mainwindow.setSize(970, 565);
        mainwindow.setVisible(true);

    }

    private JComponent table() {
        table = new JTable();

        tableModel = new GraphTableModel("Кол-во элементов массива","Время сортировки");
        table.setModel(tableModel.getModel());
        JScrollPane scrollPane = new JScrollPane(table);

        /*scrollPane.setMinimumSize(new Dimension(250, 400));
        scrollPane.setPreferredSize(new Dimension(250, 400));
        scrollPane.setMaximumSize(new Dimension(250, 400));*/

        return scrollPane;
    }


    private Box buttons() {
        Box buttons = Box.createHorizontalBox();

        JButton inputFunction = new JButton("Задание функции");
        inputFunction.addActionListener(new OpenInputAction(this));
        scaleLabel.setText(scale.getCurrentScale());

        Build build = new Build(table, grap, this);
        JButton startGraphich = new JButton("Построить график");
        startGraphich.addActionListener(build);

        JButton stopGraphich = new JButton("Стоп");
        stopGraphich.addActionListener(new StopBuild(build));

        buttons.add(Box.createHorizontalStrut(6));
        buttons.add(inputFunction);
        buttons.add(Box.createHorizontalStrut(200));
        buttons.add(scaleLabel);
        buttons.add(Box.createHorizontalStrut(6));
        buttons.add(startGraphich);
        buttons.add(Box.createHorizontalStrut(6));
        buttons.add(stopGraphich);
        return buttons;

    }

}

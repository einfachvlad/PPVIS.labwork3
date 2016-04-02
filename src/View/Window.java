package View;


import javax.swing.*;

public class Window {
    public JFrame mainwindow;

    public Window(){
        mainwindow=new JFrame("Лабораторная работа №3");
        mainwindow.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        mainwindow.setSize(400,300);
        mainwindow.setVisible(true);
    }
}

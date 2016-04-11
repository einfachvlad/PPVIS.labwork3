package Controller;

import View.Graphic;
import View.Window;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Build implements ActionListener {

    static BuildThread build;
    Window window;
    JTable table = new JTable();
    Graphic grap = new Graphic();

    public Build(JTable table, Graphic grap, Window window) {
        this.window = window;
        this.table = table;
        this.grap = grap;
    }

    public void actionPerformed(ActionEvent event) {
        build = new BuildThread(table, grap,window.numberOfElements,window.numberOfArrays);
        Thread thread = new Thread(build);
        thread.start();

        System.out.println("Главный поток завершён...");

    }
}

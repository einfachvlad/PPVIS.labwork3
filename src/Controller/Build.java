package Controller;

import View.Graphic;
import View.GraphicPanel;
import View.Window;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Build implements ActionListener {

    static BuildThread build;
    Thread thread;

    boolean running;
    Window window;
    JTable table = new JTable();
    GraphicPanel grap;

    public Build(JTable table, GraphicPanel grap, Window window) {
        this.window = window;
        this.table = table;
        this.grap = grap;
        running = false;
    }

    public void actionPerformed(ActionEvent event) {
        if (window.numberOfArrays != 0 && window.numberOfElements != 0) {
            if (!running) {
                build = new BuildThread(table, grap,window);
                thread = new Thread(build);
                thread.start();
                running = true;
                System.out.println("Поток запущен");
            }
            System.out.println("Главный поток завершён...");
        } else
            JOptionPane.showMessageDialog(null, "Вы не задали значения для графика", "Ошибка", JOptionPane.ERROR_MESSAGE);


    }

    public void stopThread() {
        if (thread != null) {
            if (thread.isAlive()) {
                    thread.interrupt();
                    running = false;
            }
            else
                JOptionPane.showMessageDialog(null, "Построение графика уже остановлено", "Ошибка", JOptionPane.ERROR_MESSAGE);

        } else
            JOptionPane.showMessageDialog(null, "Нечего останавливать", "Ошибка", JOptionPane.ERROR_MESSAGE);

    }
}

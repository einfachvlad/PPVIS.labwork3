package Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Build implements ActionListener {

    static BuildThread build;

    public void actionPerformed(ActionEvent event) {
        build = new BuildThread();
        Thread thread = new Thread(build);
        thread.start();

        System.out.println("Главный поток завершён...");

    }
}

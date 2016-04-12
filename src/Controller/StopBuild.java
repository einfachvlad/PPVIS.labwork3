package Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StopBuild implements ActionListener{

    Build build;
    public StopBuild(Build build){
        this.build=build;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        build.stopThread();
    }
}

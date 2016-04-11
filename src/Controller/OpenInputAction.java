package Controller;

import View.Input;
import View.Window;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class OpenInputAction implements ActionListener {
    Input dialog = null;
    Window window;
    public OpenInputAction(Window window){
        this.window=window;
    }
    public void actionPerformed(ActionEvent event) {
        if (dialog == null) {
            dialog = new Input(window);
        }
        dialog.dialog.setVisible(true);
    }
}

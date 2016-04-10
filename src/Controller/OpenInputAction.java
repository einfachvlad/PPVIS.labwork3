package Controller;

import View.Input;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class OpenInputAction implements ActionListener{
    Input dialog=null;

    public void actionPerformed(ActionEvent event) {
        if (dialog == null)
        {
            dialog = new Input(null);
        }
        dialog.dialog.setVisible(true);
    }
}

package Controller;

import View.Input;
import View.Window;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class OkInput implements ActionListener {
    Input dialog;
    Window window;
    public OkInput(Input dialog, Window window) {
        this.dialog = dialog;
        this.window = window;
    }

    public void actionPerformed(ActionEvent event) {
        try {

            window.numberOfArrays = Integer.valueOf(dialog.arraysInput.getText());
            window.numberOfElements = Integer.valueOf(dialog.elementsInput.getText());
            dialog.dialog.setVisible(false);
            dialog.arraysInput.setText("");
            dialog.elementsInput.setText("");

        } catch (Exception e) {
            JOptionPane.showMessageDialog
                    (null, "Введите число", "Ошибка", JOptionPane.ERROR_MESSAGE);
        }
    }
}

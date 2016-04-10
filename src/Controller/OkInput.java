package Controller;

import View.Input;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class OkInput implements ActionListener {
    Input dialog;
    int numberOfArrays;
    int numberOfElements;

    public OkInput(Input dialog) {
        this.dialog = dialog;
    }

    public void actionPerformed(ActionEvent event) {
        try {

            numberOfArrays = Integer.valueOf(dialog.arraysInput.getText());
            numberOfElements=Integer.valueOf(dialog.elementsInput.getText());
            dialog.dialog.setVisible(false);
            dialog.arraysInput.setText("");
            dialog.elementsInput.setText("");

        }catch (Exception e) {
            JOptionPane.showMessageDialog
                    (null, "Введите число", "Ошибка", JOptionPane.ERROR_MESSAGE);
        }
    }
}

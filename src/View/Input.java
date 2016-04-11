package View;

import Controller.OkInput;

import javax.swing.*;
import java.awt.*;

public class Input {
    public JDialog dialog;
    Window window;
    public JTextField arraysInput;
    public JTextField elementsInput;
    public Input(Window owner) {
        this.window=owner;
        dialog = new JDialog(owner.mainwindow, "Ввод", true);
        dialog.setContentPane(components());
        dialog.pack();
    }

    private JPanel components() {
        JPanel mainpanel = new JPanel();
        JPanel fields = new JPanel();
        fields.setLayout(new GridLayout(2, 2, 6, 12));

        JLabel numOfArrays = new JLabel("Кол-во массивов");
        JLabel numOfElements = new JLabel("Макс.кол-во элементов в массиве");

        arraysInput = new JTextField(10);
        elementsInput = new JTextField(10);

        JButton ok = new JButton("OK");
        ok.addActionListener(new OkInput(this,window));

        fields.add(numOfArrays);
        fields.add(arraysInput);
        fields.add(numOfElements);
        fields.add(elementsInput);

        mainpanel.add(fields);
        mainpanel.add(ok, BorderLayout.SOUTH);
        mainpanel.setPreferredSize(new Dimension(360, 90));

        return mainpanel;
    }

}

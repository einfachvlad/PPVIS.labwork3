package View;

import Controller.OkInput;

import javax.swing.*;
import java.awt.*;

public class Input {
    public JDialog dialog;
    public JTextField arraysInput;
    public JTextField elementsInput;

    public Input(JFrame owner) {
        dialog = new JDialog(owner, "Ввод", true);
        dialog.setContentPane(components());
        dialog.pack();
    }

    private JPanel components() {
        JPanel mainpanel = new JPanel();
        JPanel fields = new JPanel();
        fields.setLayout(new GridLayout(2, 2, 6, 12));

        JLabel numberOfArrays = new JLabel("Кол-во массивов");
        JLabel numberOfElements = new JLabel("Макс.кол-во элементов в массиве");

        arraysInput = new JTextField(10);
        elementsInput = new JTextField(10);

        JButton ok = new JButton("OK");
        ok.addActionListener(new OkInput(this));

        fields.add(numberOfArrays);
        fields.add(arraysInput);
        fields.add(numberOfElements);
        fields.add(elementsInput);

        mainpanel.add(fields);
        mainpanel.add(ok, BorderLayout.SOUTH);
        mainpanel.setPreferredSize(new Dimension(300, 90));

        return mainpanel;
    }

}

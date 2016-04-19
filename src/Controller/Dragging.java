package Controller;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Dragging extends MouseAdapter {

    int prevX, prevY;
    JScrollPane scrollPane;

    public Dragging(JScrollPane scrollPane) {
        this.scrollPane = scrollPane;
    }

    @Override
    public void mousePressed(MouseEvent e) {
        prevX = e.getX();
        prevY = e.getY();

    }

    @Override
    public void mouseDragged(MouseEvent e) {
        super.mouseDragged(e);
        int dX = prevX - e.getX();
        int dY = prevY - e.getY();

        JScrollBar verticalScrollBar = scrollPane.getVerticalScrollBar();
        JScrollBar horizontalScrollBar = scrollPane.getHorizontalScrollBar();

        verticalScrollBar.setValue(verticalScrollBar.getValue() + dY);
        horizontalScrollBar.setValue(horizontalScrollBar.getValue() + dX);

        prevX = e.getX();
        prevY = e.getY();
    }

}

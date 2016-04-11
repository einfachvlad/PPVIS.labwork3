package Controller;

import Model.Scale;

import javax.swing.*;
import java.awt.event.*;

public class Zoom extends MouseAdapter implements MouseWheelListener {
    boolean mouseInGrap = false;
    Scale scale = new Scale();
    JLabel scaleLabel;

    public Zoom(Scale scale, JLabel scaleLabel) {
        this.scale = scale;
        this.scaleLabel = scaleLabel;
    }

    @Override
    public void mouseWheelMoved(MouseWheelEvent e) {
        if (e.isControlDown()) {
            System.out.println("Ctrl нажат");
            if (mouseInGrap) {
                if (e.getWheelRotation() > 0) {
                    // Positive zoom (zoom out)
                    if (scale.getProcent() != 0) {
                        scale.zoomOut();
                        System.out.println(scale.getCurrentScale());
                        scaleLabel.setText(scale.getCurrentScale());
                    }
                } else {
                    scale.zoomIn();
                    System.out.println(scale.getCurrentScale());
                    scaleLabel.setText(scale.getCurrentScale());
                }
            }
        }
        // System.out.println("wheelInGrap" + wheelInGrap);
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        mouseInGrap = true;
        System.out.println("mouseInGrap" + mouseInGrap);
    }

    @Override
    public void mouseExited(MouseEvent e) {
        mouseInGrap = false;
        System.out.println("mouseInGrap" + mouseInGrap);
    }

}

package Controller;

import Model.Scale;
import View.Graphic;

import javax.swing.*;
import java.awt.event.*;

public class Zoom extends MouseAdapter implements MouseWheelListener {
    boolean mouseInGrap = false;
    Scale scale = new Scale();
    JLabel scaleLabel;
    Graphic grap;

    public Zoom(Scale scale, JLabel scaleLabel, Graphic grap) {
        this.scale = scale;
        this.scaleLabel = scaleLabel;
        this.grap=grap;
    }

    @Override
    public void mouseWheelMoved(MouseWheelEvent e) {
        if (e.isControlDown()) {
            if (mouseInGrap) {
                if (e.getWheelRotation() > 0) {
                    // Positive zoom (zoom out)
                    if (scale.getProcent() != 0) {
                        scale.zoomOut();
                        scaleLabel.setText(scale.getCurrentScale());
                        grap.zoomOut();
                    }
                } else {
                    scale.zoomIn();
                    scaleLabel.setText(scale.getCurrentScale());
                    grap.zoomIn();
                }
            }
        }
        // System.out.println("wheelInGrap" + wheelInGrap);
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        mouseInGrap = true;
    }

    @Override
    public void mouseExited(MouseEvent e) {
        mouseInGrap = false;
    }

}

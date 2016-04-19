package Controller;

import Model.Scale;
import View.Graphic;
import View.GraphicPanel;

import javax.swing.*;
import java.awt.event.*;

public class Zoom extends MouseAdapter implements MouseWheelListener {
    boolean mouseInGrap = false;
    Scale scale = new Scale();
    JLabel scaleLabel;
    GraphicPanel grap;
    int zoomSize;
    JScrollPane scrollPane;

    public Zoom(Scale scale, JLabel scaleLabel, GraphicPanel grap) {
        this.scale = scale;
        this.scaleLabel = scaleLabel;
        this.grap = grap;
        zoomSize = grap.getHeight()/ 4;
    }

    @Override
    public void mouseWheelMoved(MouseWheelEvent e) {
        if (mouseInGrap) {
            if (e.isControlDown()) {
                if (e.getWheelRotation() > 0) {
                    // Positive zoom (zoom out)
                    if (scale.getProcent() != 0) {
                        scale.zoomOut();
                        scaleLabel.setText(scale.getCurrentScale());
                        grap.zoomOut(grap.getWidth() - zoomSize, grap.getHeight() - zoomSize);
                    }
                } else {
                    scale.zoomIn();
                    scaleLabel.setText(scale.getCurrentScale());
                    if (scale.getProcent() > 100)
                        grap.zoomIn(grap.getWidth() + zoomSize, grap.getHeight() + zoomSize);
                    else
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

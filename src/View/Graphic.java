package View;

import javax.swing.*;
import java.awt.*;


public class Graphic extends JPanel {
    private int pointWidth, nx, startY, oyk, oyx, startX, oxk, oxy, yLength, xLength, sw, xln, l2, margin, point;
    private float xng, halfX, halfY, hx, yg, xk;

    Graphic() {
        startY = 50; // начальный отступ по y
        startX = 50; //начальный отступ по х
        yLength = 400; // длина оси у
        xLength = 400; // длина оси х
        halfX = halfY = (float) 0.5;
        margin = 10;
        point = 10;
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(Color.WHITE);
        g2d.fillRect(margin, margin, 500, 500);

        g2d.setColor(Color.BLACK);
        //осьY
        g.drawLine((int) (xLength * halfX + startX + margin), startY - margin,
                (int) (xLength * halfX + startX + margin), startY + yLength);
        //стрелки
        g.drawLine((int) (xLength * halfX + startX + margin), startY - margin,//стрелки
                (int) (xLength * halfX + startX + margin) - 5, startY - margin + 5);

        g.drawLine((int) (xLength * halfX + startX + margin), startY - margin,
                (int) (xLength * halfX + startX + margin) + 5, startY - margin + 5);

        // Надпись
        g.drawString("Y", (int) (xLength * halfX + startX + margin) - 10, startY - margin + 10);
        //Деления
        int ly = yLength;
        int pointCount = (int) yLength / point;
        for (int i = 0; i < pointCount + 1; i++) {
            g.drawLine((int) (xLength * halfX - 2 + startX + margin), ly - point + startY + margin,
                    (int) (xLength * halfX + 2 + startX + margin), ly - point + startY + margin);
            ly = ly - point;
        }

        g.drawString("0", (int) (xLength * halfX + startX + margin) - 10, (int) (yLength * halfY + startY) + margin + 10);

        // Ось Х
        g.drawLine(startX + margin, (int) (yLength * halfY + startY) + margin, xLength + startX + margin, (int) (yLength * halfY + startY) + margin);
        //стрелки
        g.drawLine(xLength + startX + margin, (int) (yLength * halfY + startY) + margin, xLength + startX + margin - 5,
                (int) (yLength * halfY + startY) + margin - 5);
        g.drawLine(xLength + startX + margin, (int) (yLength * halfY + startY) + margin, xLength + startX + margin - 5,
                (int) (yLength * halfY + startY) + margin + 5);
        // Надпись
        g.drawString("Х", xLength + startX + margin - 10, (int) (yLength * halfY + startY) + margin - 10);

        //Деления
        int lx = xLength;
        pointCount = (int) xLength / point;
        for (int i = 1; i < pointCount + 1; i++) {
            g.drawLine( (lx-point+ startX + margin), (int)(yLength *halfY+2 + startY + margin),
                    (lx-point+ startX + margin),(int)(yLength *halfY-2 + startY + margin));
            lx = lx - point;
        }

        super.repaint();
    }
}
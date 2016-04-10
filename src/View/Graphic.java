package View;

import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.util.List;


public class Graphic extends JPanel {
    private int pointWidth, nx, startY, oyk, oyx, startX, oxk, oxy, yLength, xLength, sw, xln, l2, margin, point, pointX, maxY, maxX, betweenPointsX, pointY, betweenPointsY;
    private double xng, halfX, halfY, hx, yg, xk;
    private boolean XYAvailible = false;
    private List<Integer> X;
    private List<Long> Y;

    public Graphic() {
        startY = 50; // начальный отступ по y
        startX = 50; //начальный отступ по х
        yLength = 400; // длина оси у
        xLength = 400; // длина оси х
        halfX = halfY = (double) 0.5;
        margin = 10;
        point = 10;

        if (maxX != 0.0 && maxY != 0.0)
            XYAvailible = true;
    }

    public void setMaxMeasures(List<Integer> X, List<Long> Y) {
        this.X = X;
        this.Y = Y;
        maxX = X.get(X.size() - 1);

        for (Long y : Y) {
            if (y > maxY)
                maxY = y.intValue();
        }

        betweenPointsX = maxX / X.size();
        betweenPointsY = maxY / Y.size();
        pointX = (int) ((xLength * halfX) / maxX);
        pointY = (int) (yLength * halfY) / maxY;
        if (maxX != 0 && maxY != 0)
            XYAvailible = true;

    }

    public void paintGraphich(Graphics2D g2d) {
        int[] xArray = new int[X.size()];
        int[] yArray = new int[Y.size()];
        for (int date : X) {
            xArray[X.indexOf(date)] = (int) (xLength * halfX) + margin + startX + (date * pointX);
            yArray[X.indexOf(date)] = (int) ((yLength * halfX) + margin + startY-
                    (Y.get(X.indexOf(date)) * pointY));
        }

        g2d.drawPolyline(xArray, yArray, X.size());

    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(Color.WHITE);
        g2d.fillRect(margin, margin, 500, 500);

        g2d.setColor(Color.BLACK);
        //осьY
        g2d.drawLine((int) (xLength * halfX + startX + margin), startY - margin,
                (int) (xLength * halfX + startX + margin), startY + yLength);
        //стрелки
        g2d.drawLine((int) (xLength * halfX + startX + margin), startY - margin,//стрелки
                (int) (xLength * halfX + startX + margin) - 5, startY - margin + 5);

        g2d.drawLine((int) (xLength * halfX + startX + margin), startY - margin,
                (int) (xLength * halfX + startX + margin) + 5, startY - margin + 5);

        // Надпись
        g2d.drawString("Y", (int) (xLength * halfX + startX + margin) - 10, startY - margin + 10);
        //Деления
        int ly = yLength;
        int pointCount = (int) yLength / point;
        for (int i = 0; i < pointCount + 1; i++) {
            g2d.drawLine((int) (xLength * halfX - 2 + startX + margin), ly - point + startY + margin,
                    (int) (xLength * halfX + 2 + startX + margin), ly - point + startY + margin);

            ly = ly - point;
        }

        if (XYAvailible) {
            int my = maxY;
            int lY = 0;
            pointCount = maxY/2;
            for (int i = 0; i < pointCount; i++) {
                g.drawString(Integer.toString((int) my), (int) (xLength * halfX + startX + margin) - 20, (int) (lY + pointY + startY - margin));
                my = my - betweenPointsY;
                lY = lY + pointX;
            }
            my = 0 - betweenPointsY;
            lY = (int) (yLength * halfY) -(my*betweenPointsY)+margin;
            for (int i = 0; i < pointCount; i++) {
                g.drawString(Integer.toString((int) my), (int) (xLength * halfX + startX + margin) - 20, (int) (lY + pointY + startY + margin));
                my = my - betweenPointsY;
                lY = lY + pointX;
            }
        }


        g2d.drawString("0", (int) (xLength * halfX + startX + margin) - 10, (int) (yLength * halfY + startY) + margin + 10);

        // Ось Х
        g2d.drawLine(startX + margin, (int) (yLength * halfY + startY) + margin, xLength + startX + margin, (int) (yLength * halfY + startY) + margin);
        //стрелки
        g2d.drawLine(xLength + startX + margin, (int) (yLength * halfY + startY) + margin, xLength + startX + margin - 5,
                (int) (yLength * halfY + startY) + margin - 5);
        g2d.drawLine(xLength + startX + margin, (int) (yLength * halfY + startY) + margin, xLength + startX + margin - 5,
                (int) (yLength * halfY + startY) + margin + 5);
        // Надпись
        g2d.drawString("Х", xLength + startX + margin - 10, (int) (yLength * halfY + startY) + margin - 10);

        //Деления
        int lx = xLength;
        pointCount = (int) xLength / point;
        for (int i = 1; i < pointCount + 1; i++) {
            g.drawLine((lx - point + startX + margin), (int) (yLength * halfY + 2 + startY + margin),
                    (lx - point + startX + margin), (int) (yLength * halfY - 2 + startY + margin));
            lx = lx - point;
        }
        if (XYAvailible) {
            int mx = maxX;
            lx = xLength;
            pointCount = mx;
            for (int i = 0; i < pointCount; i++) {
                g.drawString(Integer.toString((int) mx), (lx + startX + margin) - point, (int) (yLength * halfY + 20 + startY + margin));
                mx = mx - betweenPointsX;
                lx = lx - pointX;
            }
            lx = (int) (xLength * halfX) + margin;
            mx = 0 - betweenPointsX;
            for (int i = 0; i < pointCount; i++) {
                g.drawString(Integer.toString((int) mx), (lx + startX + margin) - pointX, (int) (yLength * halfY + 20 + startY + margin));
                mx = mx - betweenPointsX;
                lx = lx - pointX;
            }
        }
        if (XYAvailible)
            paintGraphich(g2d);

        super.repaint();
    }
}
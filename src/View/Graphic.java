package View;

import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.util.List;


public class Graphic extends JPanel {
    private int pointWidth, nx, startY, oyk, oyx, startX, oxk, oxy, yLength, xLength, sw, xln, l2, margin, point, pointX, maxY, maxX, betweenPointsX, pointY, betweenPointsY;
    private double halfX, halfY;
    private int hX, hY;
    private boolean XYAvailible = false;
    private List<Integer> xList;
    private List<Integer> yList;
    private int X;
    private int Y;
    int zoomLengthX;
    int zoomLengthY;
    int zoomStartX;
    int zoomStartY;
    int pointer;


    public Graphic() {
        startY = 50; // начальный отступ по y
        startX = 50; //начальный отступ по х
        yLength = 400; // длина оси у
        xLength = 400; // длина оси х
        halfX = halfY = (double) 0.5;
        hX = hY = 200;
        margin = 10;
        point = 10;
        xList = new ArrayList<>();
        yList = new ArrayList<>();

        zoomLengthX = xLength * 25 / 100;
        zoomLengthY = yLength * 25 / 100;

        zoomStartX = startX * 25 / 100;
        zoomStartY = startY * 25 / 100;

        pointer=pointer*25/100;

        if (maxX != 0.0 && maxY != 0.0)
            XYAvailible = true;
    }

    public void paintPoint(Graphics2D g2d) {
        g2d.drawLine(margin + startX + hX + X, margin + startY + hY - Y, margin + startX + hX + X, margin + startY + hY - Y);
    }

    public void setCoordinats(int X, long Y) {
        this.X = X;
        this.Y = (int) Y;
        xList.add(margin + startX + hX + this.X);
        yList.add(margin + startY + hY - this.Y);

        XYAvailible = true;

    }


    public void paintGraphich(Graphics2D g2d) {
        try {
            Iterator xIterator = xList.iterator();
            Iterator yIterator = yList.iterator();

            int nextX = (int) xIterator.next();
            int nextY = (int) yIterator.next();

            int currentX;
            int currentY;

            while (xIterator.hasNext() && yIterator.hasNext()) {

                currentX = nextX;
                currentY = nextY;
                nextX = (int) xIterator.next();
                nextY = (int) yIterator.next();
                g2d.drawLine(currentX, currentY, nextX, nextY);

            }
        } catch (ConcurrentModificationException e) {}

    }

    public void zoomIn() {

        xLength = xLength + zoomLengthX;
        yLength = yLength + zoomLengthY;

        startX -= zoomStartX;
        startY -= zoomStartY;

    }

    public void zoomOut() {

        xLength = xLength - zoomLengthX;
        yLength = yLength - zoomLengthY;

        startX += zoomStartX;
        startY += zoomStartY;
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
                (int) (xLength * halfX + startX + margin) - pointer, startY - margin + pointer);

        g2d.drawLine((int) (xLength * halfX + startX + margin), startY - margin,
                (int) (xLength * halfX + startX + margin) + pointer, startY - margin + pointer);

        // Надпись
        g2d.drawString("Y", (int) (xLength * halfX + startX + margin) - 10, startY - margin + 10);
        //Деления
       /* int ly = yLength;
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
        }*/


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
       /* int lx = xLength;
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
        }*/
        if (XYAvailible)
            paintGraphich(g2d);

        super.repaint();
    }
}
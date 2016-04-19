package View;

import javax.swing.*;
import java.awt.*;
import java.awt.List;
import java.awt.image.BufferedImage;
import java.util.*;

public class GraphicPanel extends JPanel {
    BufferedImage img;


    private int pointWidth, nx, startY, oyk, oyx, startX, oxk, oxy, yLength, xLength, sw, xln, l2, pointX, maxY, maxX, betweenPointsX, pointY, betweenPointsY;
    private int halfX, halfY;
    private int hX, hY;
    private boolean XYAvailible = false;
    private java.util.List<Integer> xList;
    private java.util.List<Integer> yList;
    private int X;
    private int Y;
    int zoomLengthX;
    int zoomLengthY;
    int zoomStartX;
    int zoomStartY;
    int pointer;
    Dimension defaultSize;
    Graphics2D d2;
    public String xName;
    public String yName;

    public GraphicPanel(String x, String y) {
        xName = x;
        yName = y;

        this.setSize(500, 500);
        this.setPreferredSize(new Dimension(500, 500));
        xLength = 400;
        yLength = 400;
        halfX = 200;
        halfY = 200;
        startY = 60; // начальный отступ по y
        startX = 60; //начальный отступ по х
        pointer = 5;

        zoomLengthX = xLength * 25 / 100;
        zoomLengthY = yLength * 25 / 100;
        zoomStartX = startX * 25 / 100;
        zoomStartY = startY * 25 / 100;

        defaultSize = new Dimension(this.getWidth(), this.getHeight());

        xList = new ArrayList<>();
        yList = new ArrayList<>();
    }

    public void paintOY(Graphics2D d2) {
        //осьY
        d2.drawLine(halfX + startX, startY, halfX + startX, startY + yLength);
        //стрелки
        d2.drawLine(halfX + startX, startY, halfX + startX - pointer, startY + pointer);

        d2.drawLine(halfX + startX, startY, halfX + startX + pointer, startY + pointer);

        // Надпись
        d2.drawString(yName, halfX + startX - 15, startY + 10);
        //Деления
       /* int ly = yLength;
        int pointCount =  yLength / point;
        for (int i = 0; i < pointCount + 1; i++) {
            d2.drawLine( (xLength * halfX - 2 + startX ), ly - point + startY ,
                     (xLength * halfX + 2 + startX ), ly - point + startY );

            ly = ly - point;
        }

        if (XYAvailible) {
            int my = maxY;
            int lY = 0;
            pointCount = maxY/2;
            for (int i = 0; i < pointCount; i++) {
                g.drawString(Integer.toString( my),  (xLength * halfX + startX ) - 20,  (lY + pointY + startY ));
                my = my - betweenPointsY;
                lY = lY + pointX;
            }
            my = 0 - betweenPointsY;
            lY =  (yLength * halfY) -(my*betweenPointsY)+margin;
            for (int i = 0; i < pointCount; i++) {
                g.drawString(Integer.toString( my),  (xLength * halfX + startX ) - 20,  (lY + pointY + startY ));
                my = my - betweenPointsY;
                lY = lY + pointX;
            }
        }*/
    }

    public void paintOX(Graphics2D d2) {
        // Ось Х
        d2.drawLine(startX, halfY + startY, startX + xLength, halfY + startY);
        //стрелки
        d2.drawLine(xLength + startX, halfY + startY, xLength + startX - pointer,
                halfY + startY - pointer);
        d2.drawLine(xLength + startX, halfY + startY, xLength + startX - pointer,
                halfY + startY + pointer);
        // Надпись
        d2.drawString(xName, xLength + startX - 10, halfY + startY - 10);

        //Деления
       /* int lx = xLength;
        pointCount = (int) xLength / point;
        for (int i = 1; i < pointCount + 1; i++) {
            g.drawLine((lx - point + startX ), (int) (yLength * halfY + 2 + startY ),
                    (lx - point + startX ), (int) (yLength * halfY - 2 + startY ));
            lx = lx - point;
        }
        if (XYAvailible) {
            int mx = maxX;
            lx = xLength;
            pointCount = mx;
            for (int i = 0; i < pointCount; i++) {
                g.drawString(Integer.toString((int) mx), (lx + startX ) - point, (int) (yLength * halfY + 20 + startY ));
                mx = mx - betweenPointsX;
                lx = lx - pointX;
            }
            lx = (int) (xLength * halfX) ;
            mx = 0 - betweenPointsX;
            for (int i = 0; i < pointCount; i++) {
                g.drawString(Integer.toString((int) mx), (lx + startX ) - pointX, (int) (yLength * halfY + 20 + startY ));
                mx = mx - betweenPointsX;
                lx = lx - pointX;
            }
        }*/
    }

    public void setCoordinats(Model.Point point) {
        this.X = point.getX();
        this.Y = point.getY();
        xList.add(startX + halfX + this.X);
        yList.add(startY + halfY - (this.Y * 10));

        XYAvailible = true;
    }

    public void paintGraphich() {
        try {

            Iterator xIterator = xList.iterator();
            Iterator yIterator = yList.iterator();

            int nextX = (int) xIterator.next();
            int nextY = (int) yIterator.next();

            int currentX;
            int currentY;

            while (xIterator.hasNext() && yIterator.hasNext()) {
                //loadPreviousState();
                // Graphics2D d2 = (Graphics2D) img.getGraphics();
                currentX = nextX;
                currentY = nextY;
                nextX = (int) xIterator.next();
                nextY = (int) yIterator.next();
                if (nextX > this.getWidth()) {
                    setSize(nextX, getHeight());
                    setPreferredSize(new Dimension(nextX, getHeight()));
                }
                if (nextY > this.getHeight()) {
                    this.setSize(nextX, getHeight());
                    this.setPreferredSize(new Dimension(this.getWidth(), nextY));
                }
                d2.drawLine(currentX, currentY, nextX, nextY);
                /*Graphics g = img.getGraphics();
                g.drawImage(img, 0, 0, this);
                repaint();*/
            }
        } catch (ConcurrentModificationException e) {
        }
    }

    public void zoomIn(int width, int height) {

        this.setSize(width, height);
        this.setPreferredSize(new Dimension(width, height));

        xLength = xLength + zoomLengthX;
        yLength = yLength + zoomLengthY;

        halfX = (int) (xLength * 0.5);
        halfY = (int) (yLength * 0.5);

        pointer++;

        startX += zoomStartX;
        startY += zoomStartY;

    }

    public void zoomIn() {

        xLength = xLength + zoomLengthX;
        yLength = yLength + zoomLengthY;

        halfX = (int) (xLength * 0.5);
        halfY = (int) (yLength * 0.5);

        pointer++;

        startX += zoomStartX;
        startY += zoomStartY;

    }

    public void zoomOut(int width, int height) {

        this.setSize(width, height);
        this.setPreferredSize(new Dimension(width, height));

        xLength = xLength - zoomLengthX;
        yLength = yLength - zoomLengthY;

        halfX = (int) (xLength * 0.5);
        halfY = (int) (yLength * 0.5);

        if (pointer > 1)
            pointer--;

        startX -= zoomStartX;
        startY -= zoomStartY;
    }


    public void paintComponent(Graphics g) {

        super.paintComponent(g);
        d2 = (Graphics2D) g;
        d2.setColor(Color.white);
        d2.fillRect(0, 0, this.getWidth(), this.getHeight());
        d2.setColor(Color.BLACK);
        paintOY(d2);
        paintOX(d2);
        d2.drawString("0", halfX + startX - 10, halfY + startY + 15);
        if (XYAvailible)
            paintGraphich();
        super.repaint();
       /* if (img == null) {
            img = new BufferedImage(this.getWidth(), this.getHeight(), BufferedImage.TYPE_INT_RGB);
            Graphics2D d2 = (Graphics2D) img.createGraphics();
            d2.setColor(Color.white);
            d2.fillRect(0, 0, this.getWidth(), this.getHeight());
            d2.setColor(Color.BLACK);
            paintOY(d2);
            paintOX(d2);
            d2.drawString("0", halfX + startX - 10, halfY + startY + 15);

        }
        super.paintComponent(g);
        g.drawImage(img, 0, 0, this);
        if (XYAvailible)
            paintGraphich();
        super.repaint();*/


        /*super.paintComponent(g);
        Graphics2D d2 = (Graphics2D) g;
        d2.setColor(Color.WHITE);
        d2.fillRect(0, 0, this.getWidth(), this.getHeight());*/

    }

}

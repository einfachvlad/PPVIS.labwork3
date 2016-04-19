package View;

import Model.*;
import Model.Point;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.*;
import java.util.List;

public class GraphicPanel extends JPanel {
    BufferedImage img;


    private int pointWidth, nx, oyk, oyx, oxk, oxy, yLength, xLength, sw, xln, l2, pointX, maxY, maxX, betweenPointsX, pointY, betweenPointsY;
    private int halfX, halfY;
    private int hX, hY;
    private boolean XYAvailible = false;
    private List<Model.Point> points;
    private int X;
    private int Y;
    int zoomLengthX;
    int zoomLengthY;
    List<Point> zoomPoints;
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
        xLength = getWidth();
        yLength = getHeight();
        halfX = xLength / 2;
        halfY = yLength / 2;
        pointer = 5;

        zoomLengthX = xLength / 4;
        zoomLengthY = yLength / 4;

        defaultSize = new Dimension(this.getWidth(), this.getHeight());

        points = new ArrayList<>();
        zoomPoints = new ArrayList<>();
    }

    public void paintOY(Graphics2D d2) {
        //осьY
        d2.drawLine(halfX, 0, halfX, yLength);
        //стрелки
        d2.drawLine(halfX, 0, halfX - pointer, pointer);

        d2.drawLine(halfX, 0, halfX + pointer, pointer);

        // Надпись
        d2.drawString(yName, halfX - 15, 10);
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
        d2.drawLine(0, halfY, xLength, halfY);
        //стрелки
        d2.drawLine(xLength, halfY, xLength - pointer,
                halfY - pointer);
        d2.drawLine(xLength, halfY, xLength - pointer,
                halfY + pointer);
        // Надпись
        d2.drawString(xName, xLength - 50, halfY + 15);

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


        points.add(new Point((halfX + this.X / 15), (halfY - (this.Y * 10))));
        zoomPoints.add(new Point((halfX + this.X / 15) / 4, (halfY - (this.Y * 10)) / 4));

        XYAvailible = true;
    }

    public void paintGraphich() {
        try {

            Iterator iterator = points.iterator();
            Point nextPoint = (Model.Point) iterator.next();
            Point currentPoint;

            while (iterator.hasNext()) {
                currentPoint = nextPoint;
                nextPoint = (Model.Point) iterator.next();
                if (nextPoint.getX() > this.getWidth()) {
                    setSize(nextPoint.getX(), getHeight());
                    setPreferredSize(new Dimension(nextPoint.getX(), getHeight()));
                }
                if (nextPoint.getY() > this.getHeight()) {
                    this.setSize(nextPoint.getY(), getHeight());
                    this.setPreferredSize(new Dimension(this.getWidth(), nextPoint.getY()));
                }
                d2.drawLine(currentPoint.getX(), currentPoint.getY(), nextPoint.getX(), nextPoint.getY());
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

        if (XYAvailible) {
            List<Point> localPointsArray = new ArrayList<>();
            Iterator iterator = points.iterator();
            Iterator zoomIterator = zoomPoints.iterator();

            while (iterator.hasNext() && zoomIterator.hasNext()) {

                Point currentPoint = (Point) iterator.next();
                Point zoomPoint = (Point) zoomIterator.next();
                localPointsArray.add(new Point(currentPoint.getX() + zoomPoint.getX(), currentPoint.getY() + zoomPoint.getY()));
            }
            points.clear();
            points.addAll(localPointsArray);
        }

    }

    public void zoomIn() {

        xLength = xLength + zoomLengthX;
        yLength = yLength + zoomLengthY;

        halfX = (int) (xLength * 0.5);
        halfY = (int) (yLength * 0.5);

        pointer++;

        if (XYAvailible) {
            List<Point> localPointsArray = new ArrayList<>();
            Iterator iterator = points.iterator();
            Iterator zoomIterator = zoomPoints.iterator();

            while (iterator.hasNext() && zoomIterator.hasNext()) {

                Point currentPoint = (Point) iterator.next();
                Point zoomPoint = (Point) zoomIterator.next();
                localPointsArray.add(new Point(currentPoint.getX() + zoomPoint.getX(), currentPoint.getY() + zoomPoint.getY()));
            }
            points.clear();
            points.addAll(localPointsArray);
        }

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
        if (XYAvailible) {
            List<Point> localPointsArray = new ArrayList<>();
            Iterator iterator = points.iterator();
            Iterator zoomIterator = zoomPoints.iterator();

            while (iterator.hasNext() && zoomIterator.hasNext()) {

                Point currentPoint = (Point) iterator.next();
                Point zoomPoint = (Point) zoomIterator.next();
                localPointsArray.add(new Point(currentPoint.getX() - zoomPoint.getX(), currentPoint.getY() - zoomPoint.getY()));
            }
            points.clear();
            points.addAll(localPointsArray);
        }
    }


    public void paintComponent(Graphics g) {

        super.paintComponent(g);
        d2 = (Graphics2D) g;
        d2.setColor(Color.white);
        d2.fillRect(0, 0, this.getWidth(), this.getHeight());
        d2.setColor(Color.BLACK);
        paintOY(d2);
        paintOX(d2);
        d2.drawString("0", halfX - 10, halfY + 15);
        if (XYAvailible)
            paintGraphich();
        super.repaint();

    }

}

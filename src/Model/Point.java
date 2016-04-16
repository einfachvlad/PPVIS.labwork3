package Model;

import javax.management.ObjectName;
import java.io.*;
import java.util.*;

public class Point {
    int X;
    int Y;

    public Point() {
    }

    public Point(int X, int Y) {
        this.X = X;
        this.Y = Y;
    }

    public void setMeasures(int X, int Y) {
        this.X = X;
        this.Y = Y;
    }

    public int getX() {
        return X;
    }

    public int getY() {
        return Y;
    }

}
package Controller;

import Model.Point;

public class BuildThread implements Runnable {

    public void run(){
        System.out.println("Привет из побочного потока!");
        Point point=new Point();
        point.setMeasures(3,100);
        point.initArray(150);
        point.sort();
    }
}

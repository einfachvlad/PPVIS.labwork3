package View;


import Model.Point;

import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


public class GraphTableModel {
    private DefaultTableModel model=new DefaultTableModel();
    private List<Integer> xArray=new ArrayList<>();
    private List<Integer> yArray=new ArrayList<>();
   private List<String> header = new ArrayList<>();;

    public GraphTableModel(String x,String y){
        model = new DefaultTableModel(xArray.size(),2);

        header.add(x);
        header.add(y);
        model.setColumnIdentifiers(header.toArray());

    }

    public void addPoint(Point point) {
        xArray.add(point.getX());
        yArray.add(point.getY());

        model = new DefaultTableModel(xArray.size(),2);

        Iterator yIterator=yArray.iterator();

        for (int x : xArray) {
            model.setValueAt(x,xArray.indexOf(x),0);
            Object y=yIterator.next();
            model.setValueAt(y,xArray.indexOf(x), 1);

        }
        model.setColumnIdentifiers(header.toArray());
    }



    public DefaultTableModel getModel() {
        return model;
    }
}

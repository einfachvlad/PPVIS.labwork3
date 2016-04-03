package View;


import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


public class GraphTableModel {
    private DefaultTableModel model;
    private List<Integer> xArray = new ArrayList<>();
    private List<Integer> yArray = new ArrayList<>();

    public GraphTableModel(){
        model = new DefaultTableModel(xArray.size(),2);
        List<String> header = new ArrayList<>();

        header.add("x");
        header.add("y");
        model.setColumnIdentifiers(header.toArray());

    }

    public GraphTableModel(List<Integer>xArray,List<Long>yArray) {
        this.xArray=xArray;
        for(long item:yArray)
        this.yArray.add((int)item);

        model = new DefaultTableModel(xArray.size(),2);
        List<String> header = new ArrayList<>();

        header.add("x");
        header.add("y");

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

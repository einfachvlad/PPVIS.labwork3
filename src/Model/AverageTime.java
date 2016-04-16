package Model;

import java.util.ArrayList;
import java.util.List;

public class AverageTime {
    List<Integer> averageTime;

    public AverageTime(){
        averageTime=new ArrayList<>();
    }

    public void add(int time){
        averageTime.add(time);
    }

    public int size(){
        return averageTime.size();
    }

    public int getAverageTime(){
        int averageSortTime;
        int time = 0;
        for (int element : averageTime) {
            time += element;
        }
        averageSortTime = time / averageTime.size();
        return averageSortTime;
    }

}

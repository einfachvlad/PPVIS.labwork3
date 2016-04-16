package Model;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ArraysGenerator {

    Random random;
    List<Integer> array;

    public List<Integer> generate(int countOfElements){
        random = new Random();
        array = new ArrayList<>();

        for (int data = 0; data < countOfElements; data++)
            array.add(random.nextInt(2147483647));
        return array;
    }

}

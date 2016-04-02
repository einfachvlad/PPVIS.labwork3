import View.Window;

import javax.swing.*;

public class Main {

    public static void main(String[] args){
        try{
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        }catch(Exception e){
            throw new RuntimeException(e);
        }

        Window window=new Window();
    }
}

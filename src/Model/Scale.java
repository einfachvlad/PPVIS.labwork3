package Model;

public class Scale {
    private int procent;
    private int amountOfScale;
    private String scale;

    public Scale() {
       procent=100;
        amountOfScale=25;
        scale=procent+"%";
    }

    public void zoomIn(){
        procent+=amountOfScale;
        scale=procent+"%";
    }

    public void zoomOut(){
        procent-=amountOfScale;
        scale=procent+"%";
    }

    public String getCurrentScale(){
        return scale;
    }

    public int getProcent(){
        return procent;
    }
}

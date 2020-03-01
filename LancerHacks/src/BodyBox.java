import java.awt.Color;
import java.awt.Graphics;

public class BodyBox {

    private int x, y, width, height;

    private String name;

    private String data;


    public BodyBox(int x, int y, int width, int height, String name, String data, boolean twoPoints){

        this.x = x;
        this.y = y;
        this.data = data;
        if(twoPoints){
            this.width = width-x;
            this.height = height-y;
        }
        else {
            this.width = width;
            this.height = height;
        }
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void draw(Graphics g){
        //g.setColor(new Color(255,0,0,75));
       // g.fillRect(x, y, width, height);
    }

    public boolean containsPoint(int px, int py){
        return px>=x && px<=x+width && py>=y && py<=y+height;
    }

    public String getData() {
        return data;
    }
}

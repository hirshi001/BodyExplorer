import io.github.pixee.security.HostValidator;
import io.github.pixee.security.Urls;
import javax.swing.ImageIcon;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Desktop;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.util.LinkedList;
import java.util.Scanner;



public class InteractCanvas extends Canvas implements MouseMotionListener, KeyListener {

    private Image body = new ImageIcon(Main.imagesPath + "BetterBody.jpg").getImage();

    private LinkedList<BodyBox> bodyBoxes;

    private LinkedList<DataReceiver> dataReceivers;
    private LinkedList<InformationReceiver> infoReceivers;

    private int mx, my;

    private int minX, maxX, minY, maxY;

    private boolean edit;

    public InteractCanvas(boolean edit){
        this.edit = edit;

        //openWebpage("https://en.wikipedia.org/wiki/Peroneus_longus");
        requestFocusInWindow();
        addMouseMotionListener(this);
        addKeyListener(this);

        dataReceivers = new LinkedList<DataReceiver>();
        infoReceivers = new LinkedList<InformationReceiver>();

        minX = Integer.MAX_VALUE;
        maxX = Integer.MIN_VALUE;
        minY = Integer.MAX_VALUE;
        maxY = Integer.MIN_VALUE;

        try {
            createBodyBoxes();
        } catch(FileNotFoundException e){
            e.printStackTrace();
        }
    }

    private void createBodyBoxes() throws FileNotFoundException {
        bodyBoxes = new LinkedList<BodyBox>();
        Scanner scanner = new Scanner(new File("src/BodyBoxes.txt"));
        int x1, y1, x2, y2;
        while(scanner.hasNextLine()){
            x1 = scanner.nextInt();
            minX = Math.min(minX, x1);
            y1 = scanner.nextInt();
            minY = Math.min(minY, y1);
            x2 = scanner.nextInt();
            maxX = Math.max(maxX, x2);
            y2 = scanner.nextInt();
            maxY = Math.max(maxY, y2);
            bodyBoxes.add(new BodyBox(x1,y1,x2,y2,scanner.nextLine(),scanner.nextLine(),true));
        }
    }

    public static void openWebpage(String urlString) {
        try {
            Desktop.getDesktop().browse(Urls.create(urlString, Urls.HTTP_PROTOCOLS, HostValidator.DENY_COMMON_INFRASTRUCTURE_TARGETS).toURI());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Override
    public void paint(Graphics g) {
        super.paint(g);
        paintBackground(g);
        paintBody(g);
        paintBodyBoxes(g);
        g.setColor(new Color(0,0,0,100));
        g.fillRect(0,0,minX,getHeight());
        g.fillRect(minX,0,getWidth()-minX,minY);
        g.fillRect(maxX,minY,getWidth()-maxX,getHeight()-minY);
        g.fillRect(minX,maxY,maxX-minX,getHeight()-maxY);
       // g.fillRect(minX, minY, maxX-minX, maxY-minY);
    }

    private void paintBackground(Graphics g){
        g.setColor(Color.white);
        g.fillRect(0,0,getWidth(),getHeight());
    }

    private void paintBody(Graphics g){
        g.drawImage(body , 0, 0, this);
    }

    private void paintBodyBoxes(Graphics g){
        for(BodyBox b:bodyBoxes){
            b.draw(g);
        }
    }

    @Override
    public void mouseDragged(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseMoved(MouseEvent mouseEvent) {
        mx = mouseEvent.getX();
        my = mouseEvent.getY();
        //System.out.println(mx + " : " + my);
        String name;
        for(BodyBox b:bodyBoxes){
            name = b.getName();
            if(b.containsPoint(mx, my)){
                for(DataReceiver receiver:dataReceivers){
                    receiver.dataUpdate(name);
                }
            }
            else{
                for(DataReceiver receiver:dataReceivers){
                    receiver.dataRemove(name);
                }
            }
        }
    }

    public void addDataReceiver(DataReceiver receiver){
        dataReceivers.add(receiver);
    }

    public void addInfoReceiver(InformationReceiver receiver){
        infoReceivers.add(receiver);
    }

    @Override
    public void keyTyped(KeyEvent keyEvent) {
        int code = keyEvent.getKeyCode();

        if(edit) {
            if (code == KeyEvent.VK_SPACE) {

                try (FileWriter f = new FileWriter("src/BodyBoxes.txt", true);
                     BufferedWriter b = new BufferedWriter(f);
                     PrintWriter p = new PrintWriter(b);) {
                    System.out.println(mx + " : " + my);
                    p.print(mx + " " + my + " ");

                } catch (IOException i) {
                    i.printStackTrace();
                }
            }
            if (code == KeyEvent.VK_ENTER) {
                System.out.println("Entered");
                try (FileWriter f = new FileWriter("src/BodyBoxes.txt", true);
                     BufferedWriter b = new BufferedWriter(f);
                     PrintWriter p = new PrintWriter(b);) {

                    p.print("\n");

                } catch (IOException i) {
                    i.printStackTrace();
                }
            }
        }
        else{
            if (code == KeyEvent.VK_ENTER) {
                for (InformationReceiver receiver : infoReceivers) {
                    receiver.clear();
                    for(BodyBox b:bodyBoxes) {
                        if(b.containsPoint(mx, my)){
                            receiver.infoUpdate(b);
                        }
                    }
                    receiver.update();
                }
            }
        }
    }

    @Override
    public void keyPressed(KeyEvent keyEvent) {

    }

    @Override
    public void keyReleased(KeyEvent keyEvent) {

    }
}

import javax.swing.JFrame;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

public class MainFrame extends JFrame {

    private DataDisplayPanel ddp;
    private InteractPanel ip;
    private InfoDisplayPanel idp;

    public MainFrame(boolean edit){
        Dimension size = new Dimension(1500,1000);
        setSize(size);
        setMinimumSize(new Dimension(size));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        setBackground(Color.white);

        ddp = new DataDisplayPanel();
        add(ddp, BorderLayout.WEST);

        ip = new InteractPanel(edit);
        add(ip, BorderLayout.CENTER);

        ip.addDataReceiver(ddp);

        idp = new InfoDisplayPanel();
        add(idp,BorderLayout.EAST);

        ip.addInfoReceiver(idp);

        setVisible(true);
    }


}

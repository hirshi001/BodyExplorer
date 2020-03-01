import javax.swing.JPanel;
import javax.swing.border.Border;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

public class InteractPanel extends JPanel {

    private InteractCanvas ic;


    public InteractPanel(boolean edit){
        setLayout(new BorderLayout());
        ic = new InteractCanvas(edit);
        add(ic, BorderLayout.CENTER);
        setBackground(Color.white);
        setMinimumSize(new Dimension(900,100));
    }

    public void addDataReceiver(DataReceiver receiver){
        ic.addDataReceiver(receiver);
    }
    public void addInfoReceiver(InformationReceiver receiver){
        ic.addInfoReceiver(receiver);
    }


}

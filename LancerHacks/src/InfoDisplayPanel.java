import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.border.Border;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Rectangle;
import java.util.LinkedList;

public class InfoDisplayPanel extends JPanel implements InformationReceiver{

    private JPanel area;
    private JLabel title;

    private LinkedList<TitleTextPanel> ttp;

    public InfoDisplayPanel(){
        setPreferredSize(new Dimension(400,100));
        Border inner = BorderFactory.createEtchedBorder();
        Border outer = BorderFactory.createEmptyBorder(5,5,5,5);
        setBorder(BorderFactory.createCompoundBorder(outer, inner));

        ttp = new LinkedList<TitleTextPanel>();

        setLayout(new BorderLayout());
        //setBackground(Color.white);

        title = new JLabel("<HTML><U>Information</U></HTML>", SwingConstants.CENTER);
        title.setFont(new Font("Serif", Font.ROMAN_BASELINE, 20));



        add(title, BorderLayout.NORTH);

        area = new JPanel();
        area.setLayout(new GridBagLayout());
        add(area,BorderLayout.CENTER);

    }

    @Override
    public void clear(){
        area.removeAll();
        ttp.clear();
    }

    @Override
    public void infoUpdate(BodyBox b) {
        ttp.add(new TitleTextPanel(b.getName(), b.getData()));

        //System.out.println(b.getName());
    }


    @Override
    public void update() {

        GridBagConstraints gc = new GridBagConstraints();
        gc.gridx = 0;
        gc.gridy = 0;
        gc.anchor = GridBagConstraints.LINE_START;

        for(TitleTextPanel panel:ttp){
            area.add(panel, gc);
            gc.gridy++;
        }
    }
}

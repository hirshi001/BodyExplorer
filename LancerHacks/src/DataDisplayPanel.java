import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.Border;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.util.LinkedList;

public class DataDisplayPanel extends JPanel implements DataReceiver{

    private DataDisplay dataDisplay;
    private JPanel panel;
    private JLabel enter;

    public DataDisplayPanel(){
        Border inner = BorderFactory.createEtchedBorder();
        Border outer = BorderFactory.createEmptyBorder(5,5,5,5);
        setBorder(BorderFactory.createCompoundBorder(outer, inner));
        setPreferredSize(new Dimension(200,10));
        setBackground(Color.white);

        setLayout(new BorderLayout());


        dataDisplay = new DataDisplay();
        dataDisplay.updateLabel();

        panel = new JPanel();
        panel.add(dataDisplay, BorderLayout.CENTER);
        panel.setBackground(Color.white);

        enter = new JLabel("<html>Press Enter<br>to get Information<html>");
        enter.setFont(new Font("Serif", Font.ROMAN_BASELINE, 20));
        enter.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createEmptyBorder(5,5,5,5), BorderFactory.createEtchedBorder()));
        add(enter, BorderLayout.SOUTH);

        add(panel);

        //dataDisplay.removeData("head");
    }




    @Override
    public void dataUpdate(String data) {
        //System.out.println("dataUpdated");
        dataDisplay.dataUpdate(data);
        dataDisplay.updateLabel();
    }

    @Override
    public void dataRemove(String data) {
        dataDisplay.removeData(data);
        dataDisplay.updateLabel();
    }
}

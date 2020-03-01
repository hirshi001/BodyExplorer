import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.Border;
import java.awt.BorderLayout;

public class TitleTextPanel extends JPanel {

    private String title, text;

    private JLabel textLabel;
    private JTextArea area;

    public TitleTextPanel(String title, String text){
        this.title = title;
        this.text = text;

        Border inner = BorderFactory.createTitledBorder(title);
        Border outer = BorderFactory.createEmptyBorder(2,2,2,2);
        Border space = BorderFactory.createEmptyBorder(5,5,5,2);

        setBorder(BorderFactory.createCompoundBorder(space,BorderFactory.createCompoundBorder(inner, outer)));

        JPanel p1 = new JPanel();
        p1.setLayout(new BorderLayout());


        area = new JTextArea(10, 20);
        area.append(text);
        area.setEditable(false);
        area.setLineWrap(true);
        area.setWrapStyleWord(true);
        add(new JScrollPane(area), BorderLayout.CENTER);



    }

}

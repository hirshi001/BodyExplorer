import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;

public class DataDisplay extends JPanel implements DataReceiver{

    private HashSet<String> list;
    private JLabel title;

    private JLabel text;

    public DataDisplay(){
        setLayout(new GridBagLayout());
        setBackground(Color.white);

        GridBagConstraints gc = new GridBagConstraints();


        title = new JLabel("<HTML><U>Selected Body Parts</U></HTML>");
        title.setFont(new Font("Serif", Font.ROMAN_BASELINE, 20));

        gc.gridx = 0;
        gc.gridy = 0;
        gc.weighty = 0.1;
        gc.anchor = GridBagConstraints.CENTER;
        add(title, gc);

        list = new HashSet<String>();

        text = new JLabel();
        text.setBorder(BorderFactory.createEmptyBorder(10,5,5,5));


        gc.gridy++;
        //gc.anchor = GridBagConstraints.LINE_START;
        gc.weighty = 0.1;
        add(text,gc);
    }

    public void updateLabel(){


        StringBuilder sb = new StringBuilder();
        sb.append("<html>");
        for(String s:list){
            sb.append(s);
            sb.append("<br><br>");
        }
        sb.append("<html>");
        text.setText(sb.toString());
    }

    public void addData(String name){
        list.add(name);
    }

    public void removeData(String name){
        list.remove(name);
    }

    @Override
    public void dataUpdate(String data) {
        addData(data);
    }

    @Override
    public void dataRemove(String data) {
        removeData(data);
    }
}

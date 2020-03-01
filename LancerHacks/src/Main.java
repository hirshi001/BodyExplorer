import javax.swing.SwingUtilities;

public class Main {

    public static final String imagesPath = "src/Images/";

    public Main(){
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new MainFrame(false);
            }
        });
    }

    public static void main(String[] args){
        new Main();
    }

}

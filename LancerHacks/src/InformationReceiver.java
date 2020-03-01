import java.util.EventListener;

public interface InformationReceiver extends EventListener {

    public void infoUpdate(BodyBox b);

    public void update();

    public void clear();

}

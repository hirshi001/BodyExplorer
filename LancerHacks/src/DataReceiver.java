import java.util.EventListener;

public interface DataReceiver extends EventListener {

    public void dataUpdate(String data);

    public void dataRemove(String data);

}

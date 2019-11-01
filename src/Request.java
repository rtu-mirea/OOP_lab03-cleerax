import java.util.List;
import java.util.ArrayList;

public class Request {

    private List<RequestPart> parts = new ArrayList<>();

    public void addPart(Instrument instrument, int time) {
        RequestPart part = new RequestPart(instrument, time);
        this.parts.add(part);
    }

    public List<RequestPart> getParts() { return this.parts; }
}

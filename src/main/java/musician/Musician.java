package musician;

public class Musician {

    private String uuid;
    private Instrument instrument;
    private long lastActivity;

    public Musician(Instrument instrument, long lastActivity) {
        this.uuid = java.util.UUID.randomUUID().toString();
        this.instrument = instrument;
        this.lastActivity = lastActivity;
    }
}

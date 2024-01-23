package auditor;

import java.time.Instant;

public class MusicianInfo {

    private String uuid;
    private Instrument instrument;
    private long lastActivity; // Using long to store timestamp as milliseconds since epoch

    public MusicianInfo(String uuid, Instrument instrument) {
        this.uuid = uuid;
        this.instrument = instrument;
        this.lastActivity = Instant.now().toEpochMilli();
    }

    public String getUuid() {
        return uuid;
    }

    public Instrument getInstrument() {
        return instrument;
    }

    public long getLastActivity() {
        return lastActivity;
    }

    public void updateLastActivity() {
        this.lastActivity = Instant.now().toEpochMilli();
    }
}

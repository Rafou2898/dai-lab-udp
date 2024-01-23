package auditor.models;

import auditor.models.Instrument;

import java.time.Instant;
import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        return o == this || o.getClass() == getClass() && ((MusicianInfo) o).uuid.equals(uuid) && ((MusicianInfo) o).instrument.equals(instrument);
    }

    @Override
    public int hashCode() {
        return Objects.hash(uuid, instrument);
    }
}

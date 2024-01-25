package auditor.models;

import java.time.Instant;
import java.util.Objects;

/**
 * Class representing the information about a musician that will be sent through TCP
 * @author : Eva Ray
 * @author : Rafael Dousse
 */
public class MusicianInfo {

    private String uuid;
    private Instrument instrument;
    private long lastActivity; // Using long to store timestamp as milliseconds since epoch

    /**
     * Constructor of the class MusicianInfo.
     * @param uuid the uuid of the musician
     * @param instrument the instrument played by the musician
     */
    public MusicianInfo(String uuid, Instrument instrument) {
        this.uuid = uuid;
        this.instrument = instrument;
        this.lastActivity = Instant.now().toEpochMilli();
    }

    /**
     * @return the uuid of the musician
     */
    public String getUuid() {
        return uuid;
    }

    /**
     * @return the instrument played by the musician
     */
    public Instrument getInstrument() {
        return instrument;
    }

    /**
     * @return the timestamp of the last activity of the musician
     */
    public long getLastActivity() {
        return lastActivity;
    }

    /**
     * Redefinition of the equals method for the class MusicianInfo.
     * @param o the object to compare
     * @return true if the objects are equal, false otherwise
     */
    @Override
    public boolean equals(Object o) {
        return o == this || o.getClass() == getClass() && ((MusicianInfo) o).uuid.equals(uuid) && ((MusicianInfo) o).instrument.equals(instrument);
    }

    /**
     * Redefinition of the hashCode method for the class MusicianInfo.
     * @return the hashcode of the object
     */
    @Override
    public int hashCode() {
        return Objects.hash(uuid, instrument);
    }
}

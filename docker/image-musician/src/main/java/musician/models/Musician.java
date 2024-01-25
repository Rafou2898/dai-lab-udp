package musician.models;

/**
 * Class representing a musician in the orchestra.
 * @author : Eva Ray
 * @author : Rafael Dousse
 */
public class Musician {

    public String uuid;
    public String sound;

    /**
     * Constructor of the class Musician.
     * @param sound the sound made by the musician
     */
    public Musician(String sound) {
        this.uuid = java.util.UUID.randomUUID().toString();
        this.sound = sound;
    }
}

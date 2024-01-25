package musician.models;

/**
 * Enum representing the different instruments that can be present in the orchestra
 * and the sound they make.
 * @author : Eva Ray
 * @author : Rafael Dousse
 */
public enum Instrument {

    piano("ti-ta-ti"),
    trumpet("pouet"),
    flute("trulu"),
    violin("gzi-gzi"),
    drum("boum-boum");

    private final String sound;

    /**
     * Constructor of the enum Instrument.
     * @param sound the sound made by the instrument
     */
    Instrument(String sound) {
        this.sound = sound;
    }

    /**
     * @return the sound made by the instrument
     */
    public String getSound() {
        return sound;
    }
}

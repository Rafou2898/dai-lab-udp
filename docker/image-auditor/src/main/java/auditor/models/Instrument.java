package auditor;

public enum Instrument {

    piano("ti-ta-ti"),
    trumpet("pouet"),
    flute("trulu"),
    violin("gzi-gzi"),
    drum("boum-boum");

    private final String sound;

    Instrument(String sound) {
        this.sound = sound;
    }

    public String getSound() {
        return sound;
    }

    public static Instrument getInstrumentBySound(String sound) {
        for (Instrument instrument : Instrument.values()) {
            if (instrument.getSound().equals(sound)) {
                return instrument;
            }
        }
        return null;
    }
}

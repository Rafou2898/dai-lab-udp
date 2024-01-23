package auditor;


public class Musician {

    public String uuid;
    public String sound;


    public Musician(String sound) {
        this.uuid = java.util.UUID.randomUUID().toString();
        this.sound = sound;
    }

    String getSound() {
        return sound;
    }

    String getUuid() {
        return uuid;
    }



}

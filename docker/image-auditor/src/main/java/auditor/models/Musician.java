package auditor.models;


public class Musician {

    public String uuid;
    public String sound;


    public Musician(String sound) {
        this.uuid = java.util.UUID.randomUUID().toString();
        this.sound = sound;
    }

    public String getSound() {
        return sound;
    }

    public String getUuid() {
        return uuid;
    }

}

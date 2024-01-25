package musician;

import java.io.IOException;
import java.net.DatagramSocket;
import java.net.DatagramPacket;
import java.net.InetSocketAddress;
import java.util.Timer;
import java.util.TimerTask;

import static java.nio.charset.StandardCharsets.*;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import musician.models.Instrument;
import musician.models.Musician;

/**
 * Class that sends the information about a sound made by a musician through UDP multicast.
 * @author : Eva Ray
 * @author : Rafael Dousse
 */
public class MusicianMulticastSender {

    final static String IPADDRESS = "239.255.22.5";
    final static int PORT = 9904;

    public static void main(String[] args) {

        Musician musician = new Musician(Instrument.valueOf(args[0]).getSound());
        Gson gson = new GsonBuilder().create();
        String message = gson.toJson(musician);
        InetSocketAddress dest_address = new InetSocketAddress(IPADDRESS, PORT);

        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                try (DatagramSocket socket = new DatagramSocket()) {
                    System.out.println("Sending: " + message);
                    byte[] payload = message.getBytes(UTF_8);
                    DatagramPacket packet = new DatagramPacket(payload, payload.length, dest_address);
                    socket.send(packet);
                } catch (IOException ex) {
                    System.out.println(ex.getMessage());
                }
            }
        }, 0, 1000);
    }

}
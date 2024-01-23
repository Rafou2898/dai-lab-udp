package musician;

import java.io.IOException;
import java.net.DatagramSocket;
import java.net.DatagramPacket;
import java.net.InetSocketAddress;
import static java.nio.charset.StandardCharsets.*;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;


public class MusicianMulticastSender {

    final static String IPADDRESS = "239.255.22.5";
    final static int PORT = 9904;

    public static void main(String[] args) {
        try (DatagramSocket socket = new DatagramSocket()) {

            Musician musician = new Musician(Instrument.piano.getSound());
            Gson gson = new GsonBuilder().create();
            String message  = gson.toJson(musician);
            System.out.println(message);

            byte[] payload = message.getBytes(UTF_8);
            InetSocketAddress dest_address = new InetSocketAddress(IPADDRESS, PORT);
            DatagramPacket packet = new DatagramPacket(payload, payload.length, dest_address);
            socket.send(packet);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

}

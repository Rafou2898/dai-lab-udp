package auditor;
import java.io.IOException;
import java.net.MulticastSocket;
import java.net.InetSocketAddress;
import java.net.NetworkInterface;
import java.net.DatagramPacket;
import java.time.Instant;

import static java.nio.charset.StandardCharsets.*;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class  AuditorMulticastReceiver {
        final static String IPADDRESS = "239.255.22.5";
        final static int PORT = 9904;

    public static void main(String[] args) {

        // à faire sur les threads
        while(true) {
            try (MulticastSocket socket = new MulticastSocket(PORT)) {
                InetSocketAddress group_address = new InetSocketAddress(IPADDRESS, PORT);
                NetworkInterface netif = NetworkInterface.getByName("eth0");
                socket.joinGroup(group_address, netif);

                byte[] buffer = new byte[2048];
                DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
                socket.receive(packet);
                String message = new String(packet.getData(), 0, packet.getLength(), UTF_8);
                Gson gson = new GsonBuilder().create();
                Musician musician = gson.fromJson(message, Musician.class);
                Orchestra.musicians.add(new MusicianInfo(musician.getUuid(), Instrument.getInstrumentBySound(musician.getSound())));

                System.out.println("Received message: " + message + " from " + packet.getAddress() + ", port " + packet.getPort());
                for (MusicianInfo info : Orchestra.musicians) {
                    System.out.println(info.getInstrument());
                }
                socket.leaveGroup(group_address, netif);
            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }
}

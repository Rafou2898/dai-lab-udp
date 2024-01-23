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
                    System.out.println(message);
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

/* TODO: pourquoi version avc ScheduledExecutorService ne fonctionne pas ?

try (DatagramSocket socket = new DatagramSocket(); ScheduledExecutorService executor = Executors.newSingleThreadScheduledExecutor()) {
            System.out.println("Sending message: " + message + " to " + destAddress.getAddress() + ", port " + destAddress.getPort());

            executor.scheduleAtFixedRate(() -> {
                try {
                    byte[] payload = message.getBytes(UTF_8);
                    DatagramPacket packet = new DatagramPacket(payload, payload.length, destAddress);
                    socket.send(packet);
                    System.out.println(message);
                } catch (IOException ex) {
                    System.out.println(ex.getMessage());
                }
            }, 0, 1, java.util.concurrent.TimeUnit.SECONDS);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
*/
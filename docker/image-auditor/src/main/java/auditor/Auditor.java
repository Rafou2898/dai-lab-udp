package auditor;

import java.io.IOException;
import java.net.MulticastSocket;
import java.net.InetSocketAddress;
import java.net.NetworkInterface;
import java.net.DatagramPacket;
import java.time.Instant;
import java.util.HashMap;

import static java.nio.charset.StandardCharsets.*;

import auditor.models.Instrument;
import auditor.models.Musician;
import auditor.models.MusicianInfo;
import com.google.gson.GsonBuilder;
import java.io.*;
import java.net.*;


public class Auditor {
    final static String IPADDRESS = "239.255.22.5";
    final static int UDP_PORT = 9904;
    final static int TCP_PORT = 2205;
    public static HashMap<String, MusicianInfo> orchestra = new HashMap<>();


    public static void main(String[] args) {

        Auditor auditor = new Auditor();
        auditor.udpMulticastReceiver();
        auditor.tcpServer();
    }

    public void udpMulticastReceiver() {

        new Thread(() -> {
            try (MulticastSocket socket = new MulticastSocket(UDP_PORT)) {
                System.out.println("Server: listening on port " + UDP_PORT);
                InetSocketAddress group_address = new InetSocketAddress(IPADDRESS, UDP_PORT);
                NetworkInterface netif = NetworkInterface.getByName("eth0");
                socket.joinGroup(group_address, netif);

                while (!Thread.interrupted()) {
                    byte[] buffer = new byte[2048];
                    DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
                    socket.receive(packet);
                    String message = new String(packet.getData(), 0, packet.getLength(), UTF_8);
                    Musician musician = new GsonBuilder().create().fromJson(message, Musician.class);
                    orchestra.put(musician.getUuid(), new MusicianInfo(musician.getUuid(), Instrument.getInstrumentBySound(musician.getSound())));
                    System.out.println("Received message: " + message + " from " + packet.getAddress() + ", port " + packet.getPort());
                    displayOrchestra();
                }
            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            }
        }).start();
    }

    private void tcpServer() {
        new Thread(() -> {
            try (ServerSocket serverSocket = new ServerSocket(TCP_PORT)) {
                System.out.println("Server: listening on port " + TCP_PORT);
                while (!Thread.interrupted()) {

                    try (Socket socket = serverSocket.accept(); var out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream(), UTF_8))) {

                        removeInactiveMusicians();
                        displayOrchestra();
                        out.write(new GsonBuilder().create().toJson(orchestra.values()));
                        out.flush();

                    } catch (IOException e) {
                        System.out.println("Server: socket ex.: " + e);
                    }
                }
            } catch (IOException e) {
                System.out.println("Server: server socket ex.: " + e);
            }
        }).start();
    }

    public void removeInactiveMusicians() {
        for (MusicianInfo info : orchestra.values()) {
            if (Instant.now().toEpochMilli() - info.getLastActivity() > 5000) {
                orchestra.remove(info.getUuid());
            }
        }
    }

    public static void displayOrchestra() {
        for (MusicianInfo info : orchestra.values()) {
            System.out.println(info.getInstrument());
        }
    }

}

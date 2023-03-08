package com.tattyhost.cocktail_mixer.network;


import android.os.AsyncTask;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.appcompat.view.menu.MenuWrapperICS;

import com.tattyhost.cocktail_mixer.buttons.ConnectESPButton;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.Reader;
import java.io.Writer;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class NetworkTask extends AsyncTask<Void, Void, Void> {
    private final ConnectESPButton espOutPut;
    private final Map<String, NetworkCMD> commands = new HashMap<>();
    private PrintWriter out;
    private boolean quit;
    private List<String> messageQueue = new ArrayList<>();

    public NetworkTask(ConnectESPButton connectESPButton) {
        this.espOutPut = connectESPButton;
    }

    public void addCMD(String command, NetworkCMD cmd) {
        commands.put(command, cmd);
    }

    protected Void doInBackground(Void... params) {
        try {
            espOutPut.println("Connecting . . . ");
            DatagramSocket socket = new DatagramSocket();
            socket.setSoTimeout(1000);
            socket.setBroadcast(true);
            byte[] sendData = "ESP32S2 Discovery".getBytes();
            byte[] receiveData = new byte[1024];
            int portTCP = 8080;
            int portUDP = 8888;

            InetAddress broadcastAddress = InetAddress.getByName("255.255.255.255");
            DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, broadcastAddress, portUDP);

            espOutPut.println("Sending UDP Packet: " + new String(sendData));
            socket.send(sendPacket);

            DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
            socket.receive(receivePacket);
            String ipAddress = receivePacket.getAddress().getHostAddress();
            espOutPut.println("ESP32S2 IP-Adresse: " + ipAddress);

            Socket tcpSocket = new Socket(ipAddress, portTCP);
            tcpSocket.setTcpNoDelay(true);
            BufferedReader in = new BufferedReader(new InputStreamReader(tcpSocket.getInputStream()));
            out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(tcpSocket.getOutputStream())), true);

            sendMessage("Hello ESP32S2!");
            readIncomingMessages(tcpSocket, in, out);

            if (tcpSocket.isConnected())
                close(tcpSocket, in, out);
            // Verbindung beenden
            espOutPut.println("Verbindungen getrennt");
        } catch (Exception e) {
            espOutPut.println("EXCEPTION: " + e);
        }

        return null;
    }

    public void sendMessage(String msg) {
        messageQueue.add(msg);
    }

    private void readIncomingMessages(Socket tcpSocket, BufferedReader in, PrintWriter out) throws Exception {
        while (!tcpSocket.isClosed()) {
            if(quit) return;
            if(messageQueue.size() > 0) {
                String msg = messageQueue.get(0);
                messageQueue.remove(0);
                espOutPut.println("Sending TCP to ESP32S2: " + msg);
                out.println(msg);
            }
            if (in.ready()) {
                String msg = getMessage(in.readLine());

                if (msg.startsWith("exit")) {
                    close(tcpSocket, in, out);
                    break;
                }

                espOutPut.println("Received Command from ESP32S2: " + msg);
                String command = getMessageCommand(msg);
                String[] args = getMessageArgs(msg);

                NetworkCMD cmd = getCMD(command);
                if(cmd == null) continue;
                cmd.execute(args); 
            }
        }
        Log.d("TCP", "readIncomingMessages: Connection ended" );
    }

    private String getMessageCommand(@NonNull String msg) {
        String [] split = msg.split("\\s+");
        return split[0];
    }

    @NonNull
    private String[] getMessageArgs(@NonNull String msg) {
        String [] split = msg.split("\\s+");
        int size = split.length;
        String [] args = new String[size-1];
        System.arraycopy(split, 1, args, 0, size - 1);
        return args;
    }

    @NonNull
    private String getMessage(@NonNull String readLine) {
        int len = readLine.indexOf('\n');
        if (len == -1) return readLine;

        return readLine.substring(0, len);
    }

    private NetworkCMD getCMD(String cmd) {
        return commands.getOrDefault(cmd, null);
    }

    private void close(@NonNull Socket tcpSocket, @NonNull Reader in, @NonNull Writer out) throws Exception {
        in.close();
        out.close();
        tcpSocket.close();
        out = null;
    }

    public void quit(boolean b) {
        quit = b;
    }
}

package com.tattyhost.cocktail_mixer.buttons;

import android.os.AsyncTask;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.tattyhost.cocktail_mixer.helper.ButtonAction;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.Socket;

public class ConnectESPButton extends ButtonAction {

    private final Button button;
    private final TextView output;

    public ConnectESPButton(Button button, TextView output) {
        this.button = button;
        this.output = output;
    }

    @Override
    public Button getButton() {
        return button;
    }

    @Override
    public void getOnClickListener(View view) {

        new NetworkTask(this).execute();

    }

    public void println(String s) {
        String newBuild = output.getText() + s + "\n";
        output.setText(newBuild);
    }
}

class NetworkTask extends AsyncTask<Void, Void, Void> {
    ConnectESPButton espOutPut;

    public NetworkTask(ConnectESPButton println) {
        this.espOutPut = println;
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

            espOutPut.println("Sending UDP Packet: " + sendData);
            socket.send(sendPacket);

            DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
            socket.receive(receivePacket);
            String ipAddress = receivePacket.getAddress().getHostAddress();
            espOutPut.println("ESP32S2 IP-Adresse: " + ipAddress);

            Socket tcpSocket = new Socket(ipAddress, portTCP);
            BufferedReader in = new BufferedReader(new InputStreamReader(tcpSocket.getInputStream()));
            PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(tcpSocket.getOutputStream())), true);

            // Hier können Sie Daten an den ESP32S2 senden und empfangen
            espOutPut.println("Sending TCP to ESP32S2: Hello ESP32S2!");
            out.println("Hello ESP32S2!");
            String response = in.readLine();
            espOutPut.println("Received from ESP32S2: " + response);

            // Verbindung beenden
            in.close();
            out.close();
            tcpSocket.close();
        } catch (Exception e) {
            espOutPut.println("EXCEPTION: " + e);

        }

        return null;
    }
}

package server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ServerSocketThread implements Runnable {

    private final Socket socket;

    public ServerSocketThread(Socket socket) {

        this.socket = socket;
    }

    public void run() {

        try (socket) {

            // Input stream
            var input = socket.getInputStream();
            var reader = new BufferedReader(new InputStreamReader(input));

            // Output stream
            var output = socket.getOutputStream();
            var writer = new PrintWriter(output, true);

            while (true) {
                var text = reader.readLine();
                text = text.toUpperCase();
                writer.println(text);
            }

        } catch (IOException e) {
            System.out.println("Socket server connection exception : " + e.getMessage());
            e.printStackTrace();
        }

    }

}

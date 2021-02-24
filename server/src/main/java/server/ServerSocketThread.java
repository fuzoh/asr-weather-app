package server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Objects;

public class ServerSocketThread implements Runnable {

    private final Socket socket;

    public ServerSocketThread(Socket socket) {

        this.socket = socket;
    }

    public void run() {

        System.out.println("New client connected.");

        try (socket) {

            // Input stream
            var input = socket.getInputStream();
            var reader = new BufferedReader(new InputStreamReader(input));

            // Output stream
            var output = socket.getOutputStream();
            var writer = new PrintWriter(output, true);

            while (true) {
                var text = reader.readLine();

                // Check if socket is active
                if (text == null)
                    throw new ClientLeftException("The client left.");

                System.out.println("Test received :" + text);
                text = text.toUpperCase();

                writer.println(text);
            }

        } catch (ClientLeftException e) {

            System.out.println("Connection ended :" + e.getMessage());

        } catch (IOException e) {

            System.out.println("An error occurred with the socket connection :" + e.getMessage());
            e.printStackTrace();

        }

        System.out.println("Client disconnected");

    }

}

import server.ServerSocketThread;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.concurrent.Executors;

public class Main {

    private static final int PORT = 8080;

    public static void main(String[] args) {

        System.out.println("Welcome to weather APP server");

        // Prepares an executor service with fixed thread pool depending on host processors
        var executor = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());

        // Start the server
        try (var serverSocket = new ServerSocket(PORT)) {

            System.out.println("Listening on port : " + PORT);

            // Wait for connections
            while (true) {
                try {

                    var socket = serverSocket.accept();
                    System.out.println("New client connected.");

                    executor.execute(new ServerSocketThread(socket));

                } catch (IOException e) {
                    throw new IOException("Error while sending new client to an executor thread", e);
                }
            }

        } catch (IOException ex) {
            System.out.println("Server exception : " + ex.getMessage());
            ex.printStackTrace();
        }

    }

}

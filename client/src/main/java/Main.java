import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class Main {

    private static final int PORT = 8080;
    private static final String HOST = "localhost";

    public static void main(String[] args) {

        System.out.println("Welcome to weather APP client");

        try (var socket = new Socket(HOST, PORT)) {

            System.out.println("Connected to :" + socket.getRemoteSocketAddress());

            var writer = new PrintWriter(socket.getOutputStream(), true);
            var reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            writer.println("Hello from :" + socket.getLocalAddress());
            System.out.println("Received :" + reader.readLine());

        } catch (UnknownHostException e) {
            System.out.println("Unknown host :" + e.getMessage());
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("IO exception" + e.getMessage());
            e.printStackTrace();
        }

    }

}

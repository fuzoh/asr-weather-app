package server;

import java.io.IOException;

public class ClientLeftException extends IOException {

    public ClientLeftException(String message) {

        super(message);
    }

}

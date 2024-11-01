import java.io.*;
import java.net.*;

public class ServerSide {
    private ServerSocket serverSocket;

    public ServerSide(int port) throws IOException {
        serverSocket = new ServerSocket(port);
        System.out.println("Server started on port " + port);
    }

    public void start() {
        while (true) {
            try {
                Socket clientSocket = serverSocket.accept();
                new ClientHandler(clientSocket).start();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private class ClientHandler extends Thread {
        private Socket clientSocket;
        private PrintWriter out;
        private BufferedReader in;

        public ClientHandler(Socket socket) {
            this.clientSocket = socket;
        }

        public void run() {
            try {
                in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                out = new PrintWriter(clientSocket.getOutputStream(), true);

                // Read input from the client
                String username = in.readLine();
                String email = in.readLine();
                String phoneNumber = in.readLine();
                String password = in.readLine();
                String plateNumber = in.readLine();

                // Optionally process the received data (e.g., save to a database, validate,
                // etc.)
                // Respond to the client after reading all input
                out.println("Registration successful.");

                in.close();
                out.close();
                clientSocket.close();

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        try {
            ServerSide server = new ServerSide(12345);
            server.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

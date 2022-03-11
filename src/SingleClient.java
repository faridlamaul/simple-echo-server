import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Inet4Address;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SingleClient {
    public static void main(String[] args) throws Exception {
        try {
            Inet4Address addr = (Inet4Address) Inet4Address.getLoopbackAddress();
            ServerSocket server = new ServerSocket(6666, 5, addr);
            System.out.println("1 - Server started");
            Socket client = server.accept();
            System.out.println("2 - Client connected");
            
            BufferedReader br = new BufferedReader(new InputStreamReader(client.getInputStream()));
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(client.getOutputStream()));
            System.out.println("3 - BufferedReader and BufferedWriter created");
            System.out.println("4 - Waiting for client to send data");

            // read the message from client (readline lebih baik karena text-based protocol ex. HTTP)
            String message = br.readLine();
            System.out.println("5 - Message received");
            // print the message
            System.out.println("Message from client: " + message);

            bw.write(message);
            bw.flush();
            // write the message to client
            // close the connection

            client.close();
            server.close();
        } catch (Exception e) {
            Logger.getLogger(SingleClient.class.getName()).log(Level.SEVERE, null, e);
        }
    }
}

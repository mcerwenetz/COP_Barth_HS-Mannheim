package network;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.Socket;

public class Client {

	InetSocketAddress address;
	int port;
	
	public static void main(String[] args) {
		Client meinClient = new Client("localhost", 8000);
		meinClient.sendMessage("Hallo vom Client");
	}
	
	
	
	public Client(String address, int port) {
		this.address = new InetSocketAddress(address, port);
	}
	
	public void sendMessage(String message) {
		System.out.println("[Client] Verbindung wird aufgebaut" );
		Socket socket = new Socket();
		try {
			socket.connect(address, 10000);
			System.out.println("[Client]Client verbunden");
			PrintWriter pw = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));
			pw.println(message);
			System.out.println("Nachricht wird gesendet");
			pw.flush();
			pw.close();
			socket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}

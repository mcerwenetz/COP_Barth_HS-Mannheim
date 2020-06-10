package network;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;

public class Client {

	InetSocketAddress address;
	int port;

	public static void main(String[] args) {
		Client meinClient = new Client("localhost", 8000);
		meinClient.sendMessage("Neue Nachricht");
	}

	public Client(String address, int port) {
		this.address = new InetSocketAddress(address, port);
	}

	public void sendMessage(String message) {
		System.out.println("[Client] Verbindung wird aufgebaut");
		Socket socket = new Socket();
		try {
			socket.connect(address, 10000);
			System.out.println("[Client]Client verbunden");

			ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
			objectOutputStream.writeObject(message);

			socket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}

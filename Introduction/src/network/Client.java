package network;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;

public class Client {

	InetSocketAddress address;
	int port;

	public static void main(String[] args) {
		Client meinClient = new Client("localhost", 8000);
		meinClient.sendMessage(2153, 1515);
	}

	public Client(String address, int port) {
		this.address = new InetSocketAddress(address, port);
	}

	public void sendMessage(Integer zahl1, Integer zahl2) {
		System.out.println("[Client] Verbindung wird aufgebaut");
		Socket socket = new Socket();
		try {
			socket.connect(address, 10000);
			System.out.println("[Client]Client verbunden");

			ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
			ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());
			
			
			objectOutputStream.writeObject(zahl1);
			objectOutputStream.writeObject(zahl2);
			
			
			Integer answer =(Integer) objectInputStream.readObject();
			System.out.println("Answer is: " + answer.toString());
			
			socket.close();
		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
}

package network;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

	private int port;

	public static void main(String[] args) {
		Server meinserver = new Server(8000);
		meinserver.startListening();
	}

	public Server(int port) {
		this.port = port;
	}

	public void startListening() {
		new Thread(new Runnable() {

			@Override
			public void run() {
				while (true) {
					try {
						System.out.println("[Server]Server starten");
						ServerSocket ss = new ServerSocket(port);
						System.out.println("[Server]Warten auf verbindung");
						Socket client = ss.accept();
						System.out.println("[Server] Client " + client.getRemoteSocketAddress() + " verbunden");

						ObjectInputStream objectInputStream = new ObjectInputStream(client.getInputStream());
						ObjectOutputStream objectOutputStream = new ObjectOutputStream(client.getOutputStream());
						
						Integer zahl1 = (Integer) objectInputStream.readObject();
						Integer zahl2 = (Integer) objectInputStream.readObject();
						Integer ergebnis = zahl1 + zahl2;
						
						objectOutputStream.writeObject(ergebnis);

						client.close();
						ss.close();
					} catch (IOException e) {
						e.printStackTrace();
					} catch (ClassNotFoundException e) {
						e.printStackTrace();
					}
				}
			}
		}).start();
	}
}

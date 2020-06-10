package network;

import java.io.BufferedReader;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

import jdk.internal.jline.internal.InputStreamReader;

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
						
						Scanner scanner = new Scanner(client.getInputStream());
						if (scanner.hasNextLine()) {
							System.out.println("[Server] Message from client: " + scanner.nextLine());
						}
						
						scanner.close();
						client.close();
						ss.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		}).start();
	}
}

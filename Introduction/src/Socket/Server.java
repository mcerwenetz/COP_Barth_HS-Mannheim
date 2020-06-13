package Socket;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server extends Thread {

	public void run() {
		ServerSocket ss  = null;

		while (true) {
			Socket client = null;
			try {
				ss = new ServerSocket(30123);
				ss.setReuseAddress(true);

				client = ss.accept();
				
				System.out.println("New Client connected: " + client);

				ObjectOutputStream oos = new ObjectOutputStream(client.getOutputStream());
				ObjectInputStream ois = new ObjectInputStream(client.getInputStream());
				
				System.out.println("Creating new Thread for client " + client);

				Thread t = new ClientHandler(ois,oos,client);
				t.start();

			} catch (Exception e) {
				// TODO: handle exception
			} finally {
				try {
					ss.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
}

package script_examples.chap8.sockets;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
	volatile static boolean done=false;

	public static void main(String[] args) {
		try {
			ServerSocket server = new ServerSocket(20123);
			serve(server);
			try {
				if (server != null) {
					server.close();
				}
			} catch (IOException e1) {
				System.err.println("Error");
			}
		} catch (IOException e) {
			throw new RuntimeException();
		}
	}
	public static void serve(ServerSocket server) throws IOException {
		NumberHandler handler = new NumberHandler();
		while (!done) {
			Socket client = server.accept();
			showClient(client);
			try {
				handler.serverSession(client);
				System.out.println("done");
			} catch (IOException e) {
				System.err.println(e);
			}finally {
				if (client != null) {
					try {
						client.close();
					} catch (IOException e2) {
						System.out.println("Error");
					}
				}
			}
		}
	}
	
	static void showClient(Socket client) {
		InetAddress addr = client.getInetAddress();
		String hostaddr = addr.getHostAddress();
		int port = client.getPort();
		System.out.println("Listening on client on " + hostaddr + "port" + port);
	}
}

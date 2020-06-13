package Socket;

import java.io.IOException;

public class Main {

	public static void main(String[] args) throws IOException {
		Thread server = new Server();
		server.start();
		
				
		for (int i = 0; i < 100; i++) {
			Thread client = new Client();
			client.start();
		}
		
		
	}
}

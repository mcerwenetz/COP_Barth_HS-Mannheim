package Socket;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.Random;

public class Client extends Thread {

	public void run() {

		Random random = new Random();

		Socket server = null;
		Integer faktor1 = random.nextInt() % 10;
		Integer faktor2 = random.nextInt() % 10;

		try {
			server = new Socket();
			server.connect(new InetSocketAddress("localhost", 30123), 1000);

			ObjectOutputStream oos = new ObjectOutputStream(server.getOutputStream());
			ObjectInputStream ois = new ObjectInputStream(server.getInputStream());

			oos.writeObject(faktor1);
			oos.writeObject(faktor2);

			Integer result = (Integer) ois.readObject();

			System.out.println(faktor1 + "*" + faktor2 + "=" + result);

			ois.close();
			oos.close();
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			if (server != null) {
				try {
					server.close();
				} catch (Exception e2) {
				}
			}
		}
	}

}

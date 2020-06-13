package Socket;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class ClientHandler extends Thread {

	Integer faktor1;
	Integer faktor2;
	
	final ObjectInputStream ois;
	final ObjectOutputStream oos;
	final Socket client;
	
	
	public ClientHandler(ObjectInputStream ois, ObjectOutputStream oos, Socket client) {
		super();
		this.ois = ois;
		this.oos = oos;
		this.client = client;
	}


	@Override
	public void run() {
		try {
			
			Integer faktor1 = (Integer) ois.readObject();
			Integer faktor2 = (Integer) ois.readObject();
			
			util.Util.sleep(3000);
			Integer result = faktor1 * faktor2;
			
			oos.writeObject(result);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			try {
				oos.close();
				ois.close();
				client.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
	}
	
	
}

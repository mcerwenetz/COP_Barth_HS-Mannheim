package a1.t3;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

// Instances of ConsumeNumbers may retrieve several numbers (one each 
// by calling next) from the SocketServer provided by ServeNumbers. 
// Do not change this file. Look at main for a simple usage of ConsumeNumbers.

public class ConsumeNumbers {
	private final static int PORT = ServeNumbers.PORT;

	// Horrible design on purpose, one connection per number
	public Long next() {
		Socket socket =null;
		DataOutputStream dos=null;
		DataInputStream dis=null;
		long value;
		boolean read = false;
		try {
			socket = new Socket("localhost", PORT);
			dos = new DataOutputStream(socket.getOutputStream());
			dis = new DataInputStream(socket.getInputStream());
			dos.writeInt(ServeNumbers.CMD_NEXT);
			value = dis.readLong();
			read = true;
			dos.writeInt(ServeNumbers.CMD_END);
		} catch (IOException e) {
			try {
				if (dos != null) dos.close();
			} catch (IOException ignore) {
			}
			try {
				if (dis != null) dis.close();
			} catch (IOException ignore) {
			}
			throw new RuntimeException(e);
		} finally {
			try {
				if (socket != null) socket.close();			
			} catch (IOException ignore) {
			}
		}
		if (read) {
			return value;
		} else {
			throw new RuntimeException("read but could not send CMD_END");
		}
	}
	
	public static void main(String[] args) {
		final ConsumeNumbers consumeNumbers = new ConsumeNumbers();
		for (int i=0; i<17; i++) {
		    long number = consumeNumbers.next();
			System.out.format("%d ",  number);
			System.out.flush();
		}
		System.out.println("done");
	}
}
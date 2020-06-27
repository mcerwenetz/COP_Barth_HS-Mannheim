package script_examples.chap8.sockets;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.nio.ByteBuffer;

public class Client {

	public static void clientSession(Socket socket) throws IOException {
		InputStream is = socket.getInputStream();
		OutputStream os = socket.getOutputStream();

		writeInt(os, 16);
		System.out.println("sent");
		int value = readInt(is);
		System.out.println("recieved");
		System.out.println("value = " + value);

		is.close();
		os.close();
	}

	static int readInt(InputStream is) throws IOException {
		ByteBuffer bb = ByteBuffer.allocate(4);
		int need = 4, read;
		do {
			read = is.read(bb.array(), 4 - need, need);
			if (read == -1) {
				throw new IOException("EOF");
			}
			need -= read;
		} while (need > 0);
		int val = bb.getInt();
		return val;
	}

	static void writeInt(OutputStream os, int i) throws IOException {
		ByteBuffer bb = java.nio.ByteBuffer.allocate(4);
		bb.putInt(i);
		os.write(bb.array());
	}

	public static void main(String[] args) {
		Socket socket = null;
		try {
			socket = new Socket("localhost", 20123);
			InetAddress addr = socket.getInetAddress();
			String hostaddr = addr.getHostAddress();
			int port = socket.getLocalPort();
			System.out.println("client runs on "+hostaddr +" port "+ port);
			clientSession(socket);
		} catch (IOException e) {
			throw new RuntimeException(e);
		} finally {
			try {
				if (socket != null) {
					socket.close();
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}

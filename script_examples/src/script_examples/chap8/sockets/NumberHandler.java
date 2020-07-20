package script_examples.chap8.sockets;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class NumberHandler implements Handler {

	@Override
	public void serverSession(Socket socket) throws IOException {
		InputStream is = socket.getInputStream();
		OutputStream os = socket.getOutputStream();
		
		while (is != null) {
			try {
				int val = Client.readInt(is);
				int newVal = val*2;
				Client.writeInt(os, newVal);
			} catch (IOException e) {
				try {
					is.close();
					os.close();
				} catch (Exception ignore) {
				}finally {
					is=null;
				}
			}
		}
	}
	
}

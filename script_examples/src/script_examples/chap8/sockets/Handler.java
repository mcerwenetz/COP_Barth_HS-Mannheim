package script_examples.chap8.sockets;

import java.io.IOException;
import java.net.Socket;

public interface Handler {
	void serverSession(Socket socket) throws IOException;

}

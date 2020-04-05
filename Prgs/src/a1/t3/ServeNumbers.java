package a1.t3;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicLong;

// Run ServeNumbers as is independently. It serves numbers from 1 to MAX 
// serialized as a String via a SocketServer on the port PORT.
// Each request will take 50-150 ms to complete.
// Use an instance of ConsumeNumbers to consume a number.
// Do not change this file.

public class ServeNumbers {
	final static int MAX = 10_000;
	final static int PORT = 50235;

	final static int CMD_NEXT = 1;
	final static int CMD_END = 0;	
	
	private AtomicLong number;
	private Random random;
	private long max;
	private ServeNumbers(int max) {
		number = new AtomicLong(0);
		random = new Random();
		this.max = max;
	}

	// serve the next number
	public long next() {
		long ret = number.incrementAndGet();
		try {
			int sleepTime = 50 + random.nextInt(101);
			Thread.sleep(sleepTime); // sleep 50-150 ms, serve slowly
		} catch (InterruptedException ignore) {
		}
		return ret > max ? 0 : ret;
	}
	
	private class Handler implements Runnable {
		final private Socket socket;
		final private DataOutputStream dos;
		final private DataInputStream dis;
		public Handler(Socket socket) {
			this.socket = socket;
			try {
				dos = new DataOutputStream(socket.getOutputStream());
				dis = new DataInputStream(socket.getInputStream());
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		}
		@Override public void run() {
			try {
				boolean done = false;
				while (!done) {
					int command = dis.readInt();
					switch (command) {
					case CMD_NEXT:
						long value = next();
						dos.writeLong(value);
						break;
					case CMD_END:
						done = true;
						break;
					default:
						throw new RuntimeException("unknown command: " + command);
					}
				}
			} catch (EOFException ignore) {
				// ignore, did not set DONE
			} catch (IOException e) {
				throw new RuntimeException(e);
			} finally {
				try {
					dis.close();
				} catch (IOException e) {
					throw new RuntimeException(e);
				}
				try {
					dos.close();
				} catch (IOException e) {
					throw new RuntimeException(e);
				}
				try {
					socket.close();
				} catch (IOException e) {
					throw new RuntimeException(e);
				}
			}
		}
	}

	public void serve() throws IOException {
		ExecutorService processor = Executors.newCachedThreadPool();
		ServerSocket server = new ServerSocket(PORT);
		try {
			while (!Thread.interrupted()) {
				Socket socket = server.accept();
				Handler handler = new Handler(socket);
				processor.execute(handler);
			}
		} catch (IOException e) {
			throw new RuntimeException(e);
		} finally {
			server.close();
		}
	}
	
	public static void main(String[] args) {
		ServeNumbers serveNumbers = new ServeNumbers(MAX);
		try {
			serveNumbers.serve();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
}

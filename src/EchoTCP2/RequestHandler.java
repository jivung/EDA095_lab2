package EchoTCP2;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;

public class RequestHandler extends Thread {
	private Socket clientSocket;
	private InputStream clientInputStream = null;
	private OutputStream clientOutputStream = null;
	private static final String response = "Message sent!";
	private static final String clientConnected = "You have been connected to the server!";

	/**
	 * Constructor. Creates a RequestHandler that handles the clients.
	 * 
	 * @param clientSocket
	 *            the socket object of the client.
	 */
	public RequestHandler(Socket clientSocket) {
		this.clientSocket = clientSocket;
	}

	/**
	 * Run method of the thread. Prints the address of the client in the server
	 * console. Also gets the IO-streams from the clients socket.
	 */
	public void run() {
		System.out.println("Client connected: ");
		System.out.println(clientSocket.getInetAddress());
		try {
			clientInputStream = clientSocket.getInputStream();
			clientOutputStream = clientSocket.getOutputStream();
			clientOutputStream.write(clientConnected.getBytes());
		} catch (IOException e) {
			e.printStackTrace();
		}
		readRequests();
	}

	/**
	 * Listens for msgs from the client. Prints the message in the server
	 * console, and echoes it back to the client.
	 */
	private void readRequests() {
		String msg = new String();
		int b;
		while (true) {
			try {
				do {
					b = clientInputStream.read();
					msg += (char) b;
				} while (b != '\n');

				System.out.println(msg);
				clientOutputStream.write(msg.getBytes());
				msg = new String();

			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

}

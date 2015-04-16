package echoTCP;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;

public class EchoTCP1 {
	private ServerSocket serverSocket = null;
	private ExecutorService threadPool = null;
	private InputStream clientInputStream = null;
	private OutputStream clientOutputStream = null;
	private Socket clientSocket = null;
	private Scanner msgScanner = null;
	private static final String response = "Message sent!";
	private static final String clientConnected = "You have been connected to the server!";
	private static final int portNbr = 3000;

	/**
	 * Simple Echo-server using TCP.
	 */
	public EchoTCP1() {
		try {
			serverSocket = new ServerSocket(portNbr);
		} catch (IOException e) {
			e.printStackTrace();
			System.exit(1);
		}
		System.out.println("Server setup successfully!");
		System.out.println("Waiting for clients to connect...");
	}

	/**
	 * Listen for incoming connections from clients.
	 */
	public void listenForConnections() {
		while (true) {
			try {
				clientSocket = serverSocket.accept();
				System.out.println("Client connected: ");
				System.out.println(clientSocket.getInetAddress());
				clientInputStream = clientSocket.getInputStream();
				clientOutputStream = clientSocket.getOutputStream();
				clientOutputStream.write(clientConnected.getBytes());
				readRequests();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * Read messages from the client Display them on server console, also echo
	 * the msgs in the client.
	 */
	private void readRequests() {
		String msg = new String();
		int b;
		while (clientSocket.isConnected()) {
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

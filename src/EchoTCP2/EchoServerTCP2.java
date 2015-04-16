package EchoTCP2;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;

public class EchoServerTCP2 {
	private ServerSocket serverSocket = null;
	private Socket clientSocket = null;
	private static final int portNbr = 3000;

	/**
	 * Simple echo server using TCP. Using threaded clients.
	 */
	public EchoServerTCP2() {
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
	 * Listen for incoming connections, start a new thread of the RequestHandler
	 * object for each client.
	 */
	public void listenForConnections() {
		try {
			while ((clientSocket = serverSocket.accept()) != null) {
				new RequestHandler(clientSocket).start();
				System.out.println("yes");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
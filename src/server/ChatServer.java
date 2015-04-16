package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import mailbox.Mailbox;

public class ChatServer {
	private ServerSocket serverSocket = null;
	private Socket clientSocket = null;
	private ClientManager cm;
	private Mailbox mailbox;
	private static final int portNbr = 3000;

	/**
	 * Constructor. Creates a ChatServer object.
	 */
	public ChatServer() {
		cm = new ClientManager();
		mailbox = new Mailbox();
		new DisplayHandler(cm, mailbox).start();
		try {
			serverSocket = new ServerSocket(portNbr);
		} catch (IOException e) {
			e.printStackTrace();
			System.exit(1);
		}
		System.out.println("Server setup successfully!");
		System.out.println("Waiting for clients to connect...");
		listenForConnections();
	}

	/**
	 * Listen for incoming client connections to the server. Forwards the
	 * connected clients to the ClientManager, along with the mailbox.
	 */
	public void listenForConnections() {
		try {
			while ((clientSocket = serverSocket.accept()) != null) {
				cm.connect(new ClientRequestHandler(clientSocket, mailbox));
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
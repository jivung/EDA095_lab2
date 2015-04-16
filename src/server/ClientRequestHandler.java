package server;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;

import mailbox.Mailbox;

public class ClientRequestHandler extends Thread {
	private Socket clientSocket;
	private Mailbox mailbox;
	private InputStream clientInputStream = null;
	private OutputStream clientOutputStream = null;
	private static final String response = "Message sent!";
	private static final String clientConnected = "You have been connected to the server!";

	/**
	 * Constructor. Creates a threaded ClientRequestHandler object, that handles
	 * requests from clients.
	 * 
	 * @param clientSocket
	 *            the Socket of the client.
	 * @param mailbox
	 *            the mailbox to be used.
	 */
	public ClientRequestHandler(Socket clientSocket, Mailbox mailbox) {
		this.clientSocket = clientSocket;
		this.mailbox = mailbox;
	}

	/**
	 * Run method. Gets the IO-streams from the socket of the client.
	 * 
	 * @override
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
		sendMsg();
	}

	/**
	 * Display a msg on the client.
	 * 
	 * @param msg
	 *            the message to be displayed.
	 */
	public void display(String msg) {
		try {
			clientOutputStream.write(msg.getBytes());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Send a msg to the mailbox (or echo to oneself).
	 */
	private void sendMsg() {
		String msg = new String();
		int b;
		while (true) {
			try {
				do {
					b = clientInputStream.read();
					msg += (char) b;
				} while (b != '\n');
				if (msg.startsWith("M:")) {
					mailbox.storeMessage(msg);
				} else if (msg.startsWith("E:")) {
					clientOutputStream.write(msg.getBytes());
				} else if (msg.startsWith("Q:")) {
					mailbox.storeMessage(msg);
					clientSocket.close();
					break;
				}

				msg = new String();

			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
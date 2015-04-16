package client;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.Scanner;

import server.ChatServer;

public class Client {

	private Socket socket = null;
	private InputStream inputStream = null;
	private OutputStream outputStream = null;

	public static void main(String[] args) {
		Client client = new Client();
		
	}
	
	public Client() {
		this.connect();
		new ClientSender(outputStream).start();
		new ClientReceiver(inputStream).start();
		System.out.println("jorpd");
	}

	private void connect() {
		try {
			socket = new Socket("localhost", 3000);
			outputStream = socket.getOutputStream();
			inputStream = socket.getInputStream();
		} catch (IOException e) {
			System.out.println(e);
			System.exit(1);
		}
		System.out.println("Ansluten. Chatta på!");
	}

	private void disconnect() {
		try {
			socket.close();
		} catch (IOException e) {
			System.out.println(e);
			System.exit(1);
		}
	}

	public String getAddress() {
		return socket.getInetAddress().getHostName()
				+ ":"
				+ ((InetSocketAddress) socket.getLocalSocketAddress())
						.getPort();
	}

	public void send(String string) {
		try {
			outputStream.write(string.getBytes());
			outputStream.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public String getUpdate() {
		try {
			byte[] bytes = new byte[1024];
			inputStream.read(bytes);
			return new String(bytes);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

}

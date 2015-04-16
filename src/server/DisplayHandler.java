package server;

import java.net.Socket;
import java.util.ArrayList;

import mailbox.Mailbox;

public class DisplayHandler extends Thread {
	ClientManager cm;
	Mailbox mailbox;

	/**
	 * Creates a threaded DisplayHandler object, that handles displaying of msgs
	 * on clients.
	 * 
	 * @param cm
	 *            the ClientManager to be used.
	 * @param mailbox
	 *            the Mailbox to be used.
	 */
	public DisplayHandler(ClientManager cm, Mailbox mailbox) {
		this.mailbox = mailbox;
		this.cm = cm;
	}

	/**
	 * Run method. Get the connected clients from the ClientManager. Send msgs
	 * in the mailbox to all clients, if there are any. Sleep with random
	 * intervals.
	 * 
	 * @override
	 */
	public void run() {
		ArrayList<ClientRequestHandler> clients = cm.getClients();
		while (true) {
			String msg = mailbox.getMessageString();
			for (ClientRequestHandler client : clients) {
				client.display(msg);
			}
			try {
				Thread.sleep((long) Math.random() * 10000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}
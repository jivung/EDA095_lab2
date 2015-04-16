package mailbox;

import java.util.ArrayList;

public class Mailbox {
	private ArrayList<String> inbox;

	/**
	 * Constructor. Creates a mailbox object.
	 */
	public Mailbox() {
		inbox = new ArrayList<String>();
	}

	/**
	 * Store msgs in the mailbox. Notifies other threads if a msg has been sent
	 * from a client, and stored in the mailbox.
	 * 
	 * @param msg
	 *            the message to be stored.
	 */
	public synchronized void storeMessage(String msg) {
		System.out.println("messsage: " + msg);
		inbox.add(msg);
		notifyAll();
	}

	/**
	 * Get msgs from the inbox. Prints them in the terminal window. Thereafter
	 * removes the msg from the inbox. Waits until notified.
	 */
	public synchronized void getMessage() {
		while (inbox.isEmpty()) {
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		while (!inbox.isEmpty()) {
			System.out.println(inbox.remove(inbox.size() - 1));
		}
	}

	/**
	 * Get the msg as a String object.
	 * 
	 * @return the message.
	 */
	public synchronized String getMessageString() {
		while (inbox.isEmpty()) {
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		return inbox.remove(inbox.size() - 1);
	}
}

package mailbox;

import java.util.Random;

public class SendMessageThread extends Thread {
	Mailbox mailbox;

	/**
	 * Constructor. Creates a SendMessageThread thread object.
	 * 
	 * @param name
	 *            the name of the thread.
	 * @param mailbox
	 *            the mailbox to be used.
	 */
	public SendMessageThread(String name, Mailbox mailbox) {
		super(name);
		this.mailbox = mailbox;
	}

	/**
	 * Run method. Sends a msg to the mailbox.
	 * 
	 * @override
	 */
	public void run() {
		for (int i = 0; i < 5; i++) {
			mailbox.storeMessage(getName());
			try {
				Thread.sleep((long) Math.random() * 10000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}

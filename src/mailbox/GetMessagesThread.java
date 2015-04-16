package mailbox;

public class GetMessagesThread extends Thread {
	Mailbox mailbox;

	/**
	 * Constructor. Creates a GetMessagesThread thread object.
	 * 
	 * @param mailbox
	 *            the mailbox to be used.
	 */
	public GetMessagesThread(Mailbox mailbox) {
		this.mailbox = mailbox;
	}

	/**
	 * Run method. Gets msgs from the mailbox. Sleeps with a random interval.
	 * 
	 * @override
	 */
	public void run() {
		while (true) {
			mailbox.getMessage();
			try {
				Thread.sleep((long) Math.random() * 10000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}
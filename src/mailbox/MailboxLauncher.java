package mailbox;


public class MailboxLauncher {
	public static void main(String[] args) {
		Mailbox inbox = new Mailbox();

		for (int i = 0; i < 10; i++) {
			new SendMessageThread("Tråd" + new Integer(i).toString(), inbox).start();
		}
		new GetMessagesThread(inbox).start();		
	}
}

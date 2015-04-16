package threadNames;

import java.util.Random;

public class EchoThreadNameWithSleep extends Thread {

	/**
	 * Constructor.
	 * 
	 * @param name
	 *            the name of the thread.
	 */
	public EchoThreadNameWithSleep(String name) {
		super(name);
	}

	/**
	 * Run method. Prints the name of the thread five times in the servers
	 * console. The thread sleeps with a random interval.
	 * 
	 * @override
	 */
	public void run() {
		Random rand = new Random();
		for (int i = 0; i < 5; i++) {
			System.out.println(getName());
			try {
				Thread.sleep((long) Math.random() * 10000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}

package threadNames;

public class ThreadEchoLauncher {

	public static void main(String[] args) {
		for (int i = 0; i < 10; i++) {
			new EchoThreadNameWithSleep("Tråd" + new Integer(i).toString()).start();
		}
	}

}

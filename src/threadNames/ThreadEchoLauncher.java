package threadNames;

public class ThreadEchoLauncher {

	public static void main(String[] args) {
		for (int i = 0; i < 10; i++) {
			new EchoThreadNameWithSleep("Tr�d" + new Integer(i).toString()).start();
		}
	}

}

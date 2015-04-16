package client;

import java.io.IOException;
import java.io.InputStream;

public class ClientReceiver extends Thread {
	
	private InputStream inputStream;
	
	public ClientReceiver(InputStream inputStream){
		this.inputStream = inputStream;
	}
	
	public void run(){
		String msg = new String();
		int b;
		while (true) {
			try {
				do {
					b = inputStream.read();
					msg += (char) b;
				} while (b != '\n');
				System.out.println("Input: " + msg);
				msg = new String();

			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
}

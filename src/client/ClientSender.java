package client;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Scanner;

public class ClientSender extends Thread {
	
	private OutputStream outputStream;
	private Scanner scanner;
	
	public ClientSender(OutputStream outputStream){
		this.outputStream = outputStream;
		scanner = new Scanner(System.in);
	}
	
	public void run(){
		
		while(true){
			try {
				String msg = scanner.next();
				msg = msg + "\n";
				outputStream.write(msg.getBytes());
				outputStream.flush();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
}

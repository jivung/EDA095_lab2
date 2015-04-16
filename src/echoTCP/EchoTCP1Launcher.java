package echoTCP;


public class EchoTCP1Launcher {

	public static void main(String[] args) {
		EchoTCP1 server = new EchoTCP1();
		server.listenForConnections();
	}

}

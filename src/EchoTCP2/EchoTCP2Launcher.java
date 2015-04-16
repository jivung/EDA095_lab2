package EchoTCP2;

public class EchoTCP2Launcher {

	public static void main(String[] args) {
		EchoServerTCP2 server = new EchoServerTCP2();
		server.listenForConnections();

	}

}

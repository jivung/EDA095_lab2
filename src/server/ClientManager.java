package server;

import java.net.Socket;
import java.util.ArrayList;

public class ClientManager {

	private ArrayList<ClientRequestHandler> handlers;

	/**
	 * Constructor. Creates a ClientManager object that manages the chatclients.
	 */
	public ClientManager() {
		handlers = new ArrayList<ClientRequestHandler>();
	}

	/**
	 * Get the connected clients.
	 * 
	 * @return an ArrayList with RequestHandlers of the connected clients.
	 */
	public ArrayList<ClientRequestHandler> getClients() {
		return handlers;
	}

	/**
	 * Connect the client, by its RequestHandler. EG, store it in the
	 * ClientManager.
	 * 
	 * @param rh
	 *            the RequestHandler
	 */
	public void connect(ClientRequestHandler rh) {
		rh.start();
		handlers.add(rh);
	}

	/**
	 * Disconnect the client. EG, remove its RequestHandler from the
	 * ClientManager.
	 * 
	 * @param rh
	 *            the RequestHandler
	 */
	public void disconnect(ClientRequestHandler rh) {
		handlers.remove(rh);
	}
}

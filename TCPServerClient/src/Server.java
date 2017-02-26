import java.net.*;
import java.io.*;

public class Server {
	private ServerSocket serverSocket;

	/**
	 * Creates a server at the given port.
	 * 
	 * @param port
	 *            The number of the port where the server connects.
	 * @throws Exception
	 */
	public Server(int port) throws Exception {
		// Create a server socket at the port.
		serverSocket = new ServerSocket(port);
		//Sets a limit for how long the accept() function can wait for a connection.
		serverSocket.setSoTimeout(10000 * 60 * 2);
	}

	public void run() {
		while (true) {
			try {
				//Define input/output String variables.
				String clientSentence;
				String capitalizedSentence;

				System.out.println("Server starting up...");
				System.out.println("Waiting for client on port " + serverSocket.getLocalPort() + "...\n");
				
				//Wait for a connection to the server and save the connected socket.
				Socket connectionSocket = serverSocket.accept();
				System.out.println("Client " + connectionSocket.getRemoteSocketAddress() + " connected.");

				//Define a reader for the connected socket.
				BufferedReader inFromClient = new BufferedReader(
						new InputStreamReader(connectionSocket.getInputStream()));
				
				//Define the output stream for the connected socket.
				DataOutputStream outToClient = new DataOutputStream(connectionSocket.getOutputStream());

				//Reads a line from the connected socket. Holds the program until a message is received.
				clientSentence = inFromClient.readLine();
				System.out.println("Client says: " + clientSentence + "\nSending message to client...\n\n");

				//Change clientSentence to uppercase.
				capitalizedSentence = clientSentence.toUpperCase() + '\n';

				//Send the capitalized message to the connected socket.
				outToClient.writeBytes(capitalizedSentence);

			} catch (SocketTimeoutException s) {
				// s.printStackTrace();
				System.out.println("Socket timed out!");
				break;
			} catch (IOException e) {
				e.printStackTrace();
				break;
			}

		}
	}

	public static void main(String[] args) {
		try {
			//Creates a new server and run it.
			Server server = new Server(9234);
			server.run();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

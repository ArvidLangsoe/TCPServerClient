import java.net.*;
import java.io.*;

public class Server {
	private ServerSocket serverSocket;
	
	public Server(int port) throws Exception
	{
		serverSocket = new ServerSocket(port);
		serverSocket.setSoTimeout(10000*60*2);
	}
	
	public void run() 
	{
		while(true) 
		{
			try {
				String clientSentence;
				String capitalizedSentence; 
				
				System.out.println("Server starting up...");
				System.out.println("Waiting for client on port " + serverSocket.getLocalPort() + "...\n");
				Socket connectionSocket = serverSocket.accept();
				System.out.println("Client " + connectionSocket.getRemoteSocketAddress() + " connected.");
				
				
				BufferedReader inFromClient = new BufferedReader(new InputStreamReader(connectionSocket.getInputStream())); 
				DataOutputStream outToClient = new DataOutputStream(connectionSocket.getOutputStream());
				
				clientSentence = inFromClient.readLine();
				System.out.println("Client says: " + clientSentence + "\nSending message to client...\n\n");
				
				capitalizedSentence = clientSentence.toUpperCase() + '\n'; 

				outToClient.writeBytes(capitalizedSentence);
				
	
			} catch(SocketTimeoutException s) {
				//s.printStackTrace();
				System.out.println("Socket timed out!");
				break;
			} catch(IOException e) {
				e.printStackTrace();
				break;
			}
			
		}
	}
	
	public static void main(String[] args) {
		try {
			Server server = new Server(9999);
			server.run();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}





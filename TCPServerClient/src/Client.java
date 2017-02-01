import java.io.*;
import java.net.*;
import java.util.Scanner;
/**
 * 
 * @author Arvid Langsø<br>Jeppe T. Nielsen<br> Mikkel Holmbo Lund<br> Patrick Green knudsen
 *
 */
public class Client {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		
		//Get server ip from user.
		System.out.println("Enter server ip:");
		String server = scanner.nextLine();
		
		//Get port number from user.
		System.out.println("Enter port number:");
		int port = scanner.nextInt();
		
		
		
		try {
			String sentence;
			String modifiedSentence;
			
			//Creates a new socket that connects to the specified server.
			System.out.println("Connecting to " + server + " on port " + port);
			Socket clientSocket = new Socket(server, port);
			
			System.out.println("Connected to " + clientSocket.getRemoteSocketAddress() + " successfully!\n");
			System.out.println("Write something to the server:");
			
			//Set up readers and output stream to the server.
			BufferedReader inFromUser = new BufferedReader(new InputStreamReader(System.in));
			DataOutputStream outToServer = new DataOutputStream(clientSocket.getOutputStream());
			BufferedReader inFromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
			
			//Read the input from the user. Holds program until a message is available.
			sentence = inFromUser.readLine();
			
			//Write message to the server.
			outToServer.writeBytes(sentence + '\n');
			//Read the answer from the server. Holds program until a message is available.
			modifiedSentence = inFromServer.readLine();
			
			//Print message from the server.
			System.out.println("Server says: " + modifiedSentence);
			clientSocket.close();
			scanner.close();
			
		} catch(IOException e) {
			e.printStackTrace();
		}
		
		
	}

}
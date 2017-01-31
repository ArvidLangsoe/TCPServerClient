import java.io.*;
import java.net.*;
import java.util.Scanner;

public class Client {
	
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		
		System.out.println("Enter server ip:");
		String server = scanner.nextLine();
		
		
		System.out.println("Enter port number:");
		int port = scanner.nextInt();
		
		
		
		try {
			String sentence;
			String modifiedSentence;
			
			System.out.println("Connecting to " + server + " on port " + port);
			Socket clientSocket = new Socket(server, port);
			
			System.out.println("Connected to " + clientSocket.getRemoteSocketAddress() + " successfully!\n");
			System.out.println("Write something to the server:");
			BufferedReader inFromUser = new BufferedReader(new InputStreamReader(System.in));
			DataOutputStream outToServer = new DataOutputStream(clientSocket.getOutputStream());
			BufferedReader inFromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
			
			sentence = inFromUser.readLine();
			
			outToServer.writeBytes(sentence + '\n');
			modifiedSentence = inFromServer.readLine();

			System.out.println("Server says: " + modifiedSentence);
			clientSocket.close();
			scanner.close();
			
		} catch(IOException e) {
			e.printStackTrace();
		}
		
		
	}

}
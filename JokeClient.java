/*--------------------------------------------------------

1. Name / Date: Elizabeth Pavlicek 9/26/20121

2. Java version used (java -version), if not the official version for the class: I used amazon-corretto-11 version.

3. Precise command-line compilation examples / instructions:
javac JokeClient.java
java JokeClient

4. Precise examples / instructions to run this program:
In seperate shell windows run:
java JokeClient
java JokeServer
java JokeAdminClient

the order should not matter but they must be in seperate windows.


5. List of files needed for running the program.
 JokeLog.txt
 JokeChecklist.html
 For running:
 JokeServer.java
 JokeClient.java
 JokeAdminClient.java

5. Notes:
For some reason as I was typing in (input.indexOf("quit")<0) as used
in inet eclipse was giving me some type of error.
I don't believe it would have affected my program if I did use (input.indexOf("quit")<0)
but I didn't want to chance it.  My new version works just fine and I think that
it is cool that I was able to customize it myself.


----------------------------------------------------------*/



import java.io.*; 

import java.net.*; 
import java.io.InputStreamReader; 
import java.util.*;
import java.net.ServerSocket;
import java.io.BufferedReader;
import java.io.IOException;
import java.net.Socket;

	public class JokeClient {

		public static void main (String args[]) throws IOException {
		
			String serverName; 	// String where "localhost" will be stored	
			if (args.length < 1) serverName = "localhost"; 
			else serverName = args[0];
			
			System.out.println("Elizabeth Pavlicek's Joke Client, 1.0. \n"); //Starting up 
			System.out.println("Using Server "+serverName+", Port 4545"); 
			BufferedReader in=new BufferedReader(new InputStreamReader(System.in));
		
			System.out.println("Type Your name if you want to hear a joke or proverb!");  // asking for name to use later when sending back j/p
			System.out.flush();
			String name;  //where the users name will be held
			name=in.readLine(); //name is going to be used later to add to j/p to make them personal
			
			try{
				String input; //where enter or 'quit' will be stored
			
				do{ 
				System.out.println("Press ENTER to see the joke/proverb or type 'quit' to end.");
				System.out.flush();
				input=in.readLine(); //checking to see if they had pressed enter or not
				// if user has pressed enter then it will send them over to sendToJokeServ to complete their j/p request.
				if(input != "quit") //for some reason, the inet version of this was causing me problems so I had to adjust it.
					sendToJokeServ(name,serverName);
				}while (input != "quit"); //for some reason, the inet version of this was causing me problems so I had to adjust it.
				System.out.println("You're no fun! joke/proverbs cancelled."); //user typed 'quit' so this will print out
			}catch(IOException x) {x.printStackTrace();} //if there is some kind of error it will catch it here
		}

		static void sendToJokeServ(String name, String serverName) {
			Socket sock;		
			BufferedReader fromServer;	
			PrintStream toServer;
			String jpFromServer;
			
			try{ 			
				sock= new Socket(serverName, 4545);
				fromServer=new BufferedReader(new InputStreamReader(sock.getInputStream()));
				toServer=new PrintStream(sock.getOutputStream());
				
				toServer.println(name); //giving the jserver the users name to initiate j/p and to use in the j/p
				jpFromServer=fromServer.readLine(); //reading what j/p has sent over
				System.out.println(jpFromServer); //giving the user the j/p that the jserver has chosen
				
				
				sock.close();
				}catch(IOException x){ 
				System.out.println("ERROR"); //telling user if some type of error has occured
				x.printStackTrace();
			}
		}

	
	}


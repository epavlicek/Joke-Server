/*--------------------------------------------------------

1. Name / Date: Elizabeth Pavlicek 9/26/20121

2. Java version used (java -version), if not the official version for the class: I used amazon-corretto-11 version.

3. Precise command-line compilation examples / instructions:
javac JokeAdminClient.java
java JokeAdminClient

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
No notes to share.


----------------------------------------------------------*/

import java.io.*; 
import java.net.*; 
import java.io.InputStreamReader; 
import java.io.BufferedReader;
import java.io.IOException;
import java.util.*;
import java.net.ServerSocket;
import java.net.Socket;

public class JokeAdminClient {

	public static void main(String[] args) throws IOException {
		
		String aServerName; //initizaling the var that will hold 'localhost'
		if (args.length<1) aServerName="localhost";
		else aServerName=args[0];
		System.out.println("Elizabeth Pavlicek's Admin Client 1.0 \n"); //printing this out when admins tarts
		BufferedReader in =new BufferedReader(new InputStreamReader(System.in));
		try{
			String adminMode; //will hold the mode that the user wants to switch to
			do{
				//let the user know what to type in order to switch the mode
				System.out.println("Which mode would you like to switch to? \n For joke type 1 \n For proverb type 2 \n For admin type 3 \n");
				System.out.flush();
				adminMode=in.readLine(); //reading what the user has typed in order to determine if you should coninue or quit
				if (adminMode.indexOf("quit")<0) 
				switchServerMode(aServerName,adminMode); //user has not typed quit so this will be sent to jserver to tell which mode to switch to 
			}while (adminMode.indexOf("quit")<0);
			System.out.println("Cancelled by user request"); //user has entered quit and want to shut it down
		} catch (IOException x) {x.printStackTrace();} //catching any errors
	}
	//this will be sent to the jserver to continue on with the mode switch
	public static void switchServerMode(String aServerName, String adminMode) {
		Socket sock; // the socket will be reffered to as sock
		BufferedReader fromServer; //the BufferedReader will be reffered to as fromServer
		PrintStream toServer; //the printstream will be refered to as toServer
		String textFromServer; 
		try{
			sock =new Socket(aServerName,5050); 
			fromServer=new BufferedReader(new InputStreamReader(sock.getInputStream()));
			toServer=new PrintStream(sock.getOutputStream());
			toServer.println(adminMode); //sending the mode 1,2,3 to jserver
			toServer.flush();
			textFromServer=fromServer.readLine(); //find out what server said
			System.out.println(textFromServer); //printing out what servr said
		sock.close(); //now closing socket
		}catch (IOException x) {
			System.out.println("ERROR"); //catching some type of error that has occured
			x.printStackTrace();
		}
	}
}

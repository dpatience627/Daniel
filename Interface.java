
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Interface {

	/*
	    * Interface Control: Server to Client
	    * Char Position		Size        Description
	    * 0 - 1				2			Message Number 
	    * 2 - 101			100			Data separated by # 
	    *
	    * Message 1:Player Turn:		01#<player>
	    * Message 2:Terrain:			02#<X1#Y1#X2#Y2#X3#Y3#X4#Y4#X5#Y5#X6#Y6...X21#Y21>
	    * Message 3:Tank Position		03#Xpos#Ypos#Color#Xpos#Ypos#Color#Xpos#Ypos#Color#Xpos#Ypos#Color
	    * Message 4:Turret Position 	04#player#Xpos#Ypos#Xpos#Ypos
	    * Message 5:Cannonball Pos		05#<Xpos>#<Ypos>
	    * Message 6:Player died			06#<player>
	    * Message 7:Player health 		07#<player>#<Health Score>
	    * Message 8:Player score		08#<player>#<score>
	    *
	    * Message 11: Send Number of players 11#<number>
	    * Message 12: Draw Explosion    12#Xpos#Ypos#Radius#Color
	    * 
	    * Interface Control: Server to Client
	    * Char Position		Size        Description
	    * 0 - 1				2			Message Number 
	    * 2 - 101			100			Data separated by # 
	    *
	    * Message 10:Player Angle:	#01#<player>
	    * Message 11: 
	    * */
	static Boolean client = false;
	
	static final int LISTENING_PORT1 = 32001;
	static final int LISTENING_PORT2 = 32002;
	static final int LISTENING_PORT3 = 32003;
	static final int LISTENING_PORT4 = 32004;
	
	static ServerSocket listener1;  //  Listens for incoming connections.
	static ServerSocket listener2;  //  Listens for incoming connections.
	static ServerSocket listener3;  //  Listens for incoming connections.
	static ServerSocket listener4;  //  Listens for incoming connections.
   
	static Socket connection1;      // For communication with the connecting program.
    static Socket connection2;      // For communication with the connecting program.
    static Socket connection3;      // For communication with the connecting program.
    static Socket connection4;      // For communication with the connecting program.
    
    static BufferedReader incoming[] = new BufferedReader[4];
    static String messageIn1;
    static String messageIn2;
    static PrintWriter outgoing[] = new PrintWriter[4];
    		
	public static boolean serverInit(int player) {
         
	 if (player == 0)
	 {
		// Connection to Client #1
         try {
        	 
	             listener1 = new ServerSocket(LISTENING_PORT1);
	             System.out.println("Listening on port " + LISTENING_PORT1);
	             connection1 = listener1.accept();
	             
	             try {
	                 System.out.println("Connection from " + connection1.getInetAddress().toString() );
	                    
	                 
	                 outgoing[0] = new PrintWriter( connection1.getOutputStream() );
	                 incoming[0] = new BufferedReader(new InputStreamReader (connection1.getInputStream()) );
	                 
	                 outgoing[0].println("Test");
	                 outgoing[0].flush();
	                 
	                 client = true;	                 
	                 
	             }
	             catch (Exception e){
	                 System.out.println("Error: " + e);
	            
	                 connection1.close();
	             }
         }
	     catch (Exception e) {
	     	 System.out.println("Sorry, the server has shut down.");
	       	 System.out.println("Error:  " + e);
	       	 
	     }
	 }
	 else if (player == 1)
	 {
       
      // Connection to Client #2
         try {
        	 
	             listener2 = new ServerSocket(LISTENING_PORT2);
	             System.out.println("Listening on port " + LISTENING_PORT2);
	             connection2 = listener2.accept();
	             
	             try {
	                 System.out.println("Connection from " + connection2.getInetAddress().toString() );
	                    
	                 
	                 outgoing[1] = new PrintWriter( connection2.getOutputStream() );
	                 incoming[1] = new BufferedReader(new InputStreamReader (connection2.getInputStream()) );
	                 
	                 outgoing[1].println("Test");
	                 outgoing[1].flush();
	                 
	                 client = true;	                 
	                 
	             }
	             catch (Exception e){
	                 System.out.println("Error: " + e);
	            
	                 connection2.close();
	             }
         }
	     catch (Exception e) {
	     	 System.out.println("Sorry, the server has shut down.");
	       	 System.out.println("Error:  " + e);
	     }
	     
	 }
	 else if (player == 2)
	 {
		// Connection to Client #3
         try {
        	 
	             listener3 = new ServerSocket(LISTENING_PORT3);
	             System.out.println("Listening on port " + LISTENING_PORT3);
	             connection3 = listener3.accept();
	             
	             try {
	                 System.out.println("Connection from " + connection3.getInetAddress().toString() );
	                    
	                 
	                 outgoing[2] = new PrintWriter( connection3.getOutputStream() );
	                 incoming[2] = new BufferedReader(new InputStreamReader (connection3.getInputStream()) );
	                 
	                 outgoing[2].println("Test");
	                 outgoing[2].flush();
	                 
	                 client = true;	                 
	                 
	             }
	             catch (Exception e){
	                 System.out.println("Error: " + e);
	            
	                 connection3.close();
	             }
         }
	     catch (Exception e) {
	     	 System.out.println("Sorry, the server has shut down.");
	       	 System.out.println("Error:  " + e);
	     }
	     
		 
	 }
	 else if (player == 3)
	 {
		// Connection to Client #4
         try {
        	 
	             listener4 = new ServerSocket(LISTENING_PORT4);
	             System.out.println("Listening on port " + LISTENING_PORT4);
	             connection4 = listener4.accept();
	             
	             try {
	                 System.out.println("Connection from " + connection4.getInetAddress().toString() );
	                    
	                 
	                 outgoing[3] = new PrintWriter( connection4.getOutputStream() );
	                 incoming[3] = new BufferedReader(new InputStreamReader (connection4.getInputStream()) );
	                 
	                 outgoing[3].println("Test");
	                 outgoing[3].flush();
	                 
	                 client = true;	                 
	                 
	             }
	             catch (Exception e){
	                 System.out.println("Error: " + e);
	            
	                 connection4.close();
	             }
         }
	     catch (Exception e) {
	     	 System.out.println("Sorry, the server has shut down.");
	       	 System.out.println("Error:  " + e);
	     }
	     
		 
	 }
         return client;

	}
	public static boolean messageToClient(int player, String message) {

		if ((player >= 0) && (player  < 4)) 
		{
			//System.out.println("Message to client " + player);
		    outgoing[player].println(message);
            outgoing[player].flush();
            
            return true;
		}
		else
		    return false;
	}
	
	
	public static int[] getAnglePowerFromClient(int player) {
		
		Boolean success = false;
		String messageIn = null;
		int[] AP = {0,0};
		
		try {       
             
             while (!success) {       
                messageIn = incoming[player].readLine();
             
                if (messageIn != null) {
             	   System.out.println(messageIn);
             	   success = true;
                }
                
                String delims = "[#]";
          		String[] tokens = messageIn.split(delims);
          		AP[0] = Integer.parseInt(tokens[0]);
          		AP[1] = Integer.parseInt(tokens[1]);
          		
             }	                
          }
          catch (Exception e){
                System.out.println("Error: " + e);
           
         }
	
	 	 return AP;
	}
}

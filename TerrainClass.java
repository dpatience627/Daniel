import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.Ellipse2D;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.util.Scanner;
import java.io.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class TerrainClass extends JPanel
{ // class
		static boolean[] Cannon_Fire = new boolean[4];
		static boolean[] playeralive = new boolean[4];
		static double[] turretX = new double[4];
		static double[] turretY = new double[4];
		static int[] anglepower1 = new int[2];
		
		static double UserAngle = 90;
		
		static double Change_In_TurretX = 15 * Math.cos(Math.toRadians(UserAngle));
		static double Change_In_TurretY = 15 * Math.sin(Math.toRadians(UserAngle));
		
		static double CannonballX = 0;
		static double CannonballY = 0;
		
		static double Hypotenuse = 10.0;
		static double Change_In_Cannonball_X = Hypotenuse * Math.cos(Math.toRadians(UserAngle));
		static double Change_In_Cannonball_Y = Hypotenuse * Math.sin(Math.toRadians(UserAngle));
		
		static double T = 0.0;
		static double G = 1.75;
		static double Gamespeed = 10;
		static double Gravity_Force = 10;
		
		static double deadTank = 0;
		static boolean blueExplosion = false;
		static boolean redExplosion = false;
		static boolean magentaExplosion = false;
		static boolean greenExplosion = false;
		static int explosionColor = 0;
		
		static int[] xpoints = new int[38];
		static int[] ypoints = new int[38];
		
	public void paint(Graphics g)
	{ // paint
		super.paint(g);
		Graphics2D g2d = (Graphics2D) g;
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);
		
		// draw turrets
		g2d.drawLine(75, 750, (int)turretX[0], (int)turretY[0]);
		g2d.drawLine(745, 690, (int)turretX[1], (int)turretY[1]);
		g2d.drawLine(1125, 630, (int)turretX[2], (int)turretY[2]);
		g2d.drawLine(1425, 790, (int)turretX[3], (int)turretY[3]);
		
		
		//draw tanks
		g.setColor(Color.BLUE);
		g2d.fillOval(60, 745, 30, 30);
		g.setColor(Color.RED);
		g2d.fillOval(730, 685, 30, 30);
		g.setColor(Color.MAGENTA);
		g2d.fillOval(1110, 625, 30, 30);
		g.setColor(Color.GREEN);
		g2d.fillOval(1410, 785, 30, 30);      
		
		
		// draw terrain
		g.setColor(Color.BLACK);
		g.fillPolygon(xpoints, ypoints, 38);
		
		// draw cannonballs
		g2d.fillOval((int)CannonballX-4, (int)CannonballY-4, 10, 10);
		
		// explosions when a tank dies
		if(blueExplosion == true)
		{
			if(explosionColor == 0)
			{
				g.setColor(Color.ORANGE);
				g2d.fillOval(60, 745, 30, 30);
				String Message3 = null;
				String tankString = "03";
				tankString = tankString + "#60#745#5#730#685#1#1110#625#2#1410#785#3";
				Message3 = tankString;
				System.out.println(Message3);
				Interface.messageToClient(0, Message3);
				Interface.messageToClient(1, Message3);
				Interface.messageToClient(2, Message3);
				Interface.messageToClient(3, Message3);
			}
			
			if(explosionColor == 1)
			{
				g.setColor(Color.RED);
				g2d.fillOval(60, 745, 30, 30);
				String Message3 = null;
				String tankString = "03";
				tankString = tankString + "#60#745#1#730#685#1#1110#625#2#1410#785#3";
				Message3 = tankString;
				System.out.println(Message3);
				Interface.messageToClient(0, Message3);
				Interface.messageToClient(1, Message3);
				Interface.messageToClient(2, Message3);
				Interface.messageToClient(3, Message3);
			}
			
			if(explosionColor == 2)
			{
				g.setColor(Color.YELLOW);
				g2d.fillOval(60, 745, 30, 30);
				String Message3 = null;
				String tankString = "03";
				tankString = tankString + "#60#745#4#730#685#1#1110#625#2#1410#785#3";
				Message3 = tankString;
				System.out.println(Message3);
				Interface.messageToClient(0, Message3);
				Interface.messageToClient(1, Message3);
				Interface.messageToClient(2, Message3);
				Interface.messageToClient(3, Message3);
			}
			
			blueExplosion = false;
		}
		
		if(redExplosion == true)
		{
			if(explosionColor == 0)
			{
				g.setColor(Color.ORANGE);
				g2d.fillOval(730, 685, 30, 30);
				String Message3 = null;
				String tankString = "03";
				tankString = tankString + "#60#745#0#730#685#5#1110#625#2#1410#785#3";
				Message3 = tankString;
				System.out.println(Message3);
				Interface.messageToClient(0, Message3);
				Interface.messageToClient(1, Message3);
				Interface.messageToClient(2, Message3);
				Interface.messageToClient(3, Message3);
			}
			
			if(explosionColor == 1)
			{
				g.setColor(Color.RED);
				g2d.fillOval(730, 685, 30, 30);
				String Message3 = null;
				String tankString = "03";
				tankString = tankString + "#60#745#0#730#685#1#1110#625#2#1410#785#3";
				Message3 = tankString;
				System.out.println(Message3);
				Interface.messageToClient(0, Message3);
				Interface.messageToClient(1, Message3);
				Interface.messageToClient(2, Message3);
				Interface.messageToClient(3, Message3);
			}
			
			if(explosionColor == 2)
			{
				g.setColor(Color.YELLOW);
				g2d.fillOval(730, 685, 30, 30);
				String Message3 = null;
				String tankString = "03";
				tankString = tankString + "#60#745#0#730#685#4#1110#625#2#1410#785#3";
				Message3 = tankString;
				System.out.println(Message3);
				Interface.messageToClient(0, Message3);
				Interface.messageToClient(1, Message3);
				Interface.messageToClient(2, Message3);
				Interface.messageToClient(3, Message3);
			}
			
			redExplosion = false;
		}
		
		if(magentaExplosion == true)
		{
			if(explosionColor == 0)
			{
				g.setColor(Color.ORANGE);
				g2d.fillOval(1110, 625, 30, 30);
				String Message3 = null;
				String tankString = "03";
				tankString = tankString + "#60#745#0#730#685#1#1110#625#5#1410#785#3";
				Message3 = tankString;
				System.out.println(Message3);
				Interface.messageToClient(0, Message3);
				Interface.messageToClient(1, Message3);
				Interface.messageToClient(2, Message3);
				Interface.messageToClient(3, Message3);
			}
			
			if(explosionColor == 1)
			{
				g.setColor(Color.RED);
				g2d.fillOval(1110, 625, 30, 30);
				String Message3 = null;
				String tankString = "03";
				tankString = tankString + "#60#745#0#730#685#1#1110#625#1#1410#785#3";
				Message3 = tankString;
				System.out.println(Message3);
				Interface.messageToClient(0, Message3);
				Interface.messageToClient(1, Message3);
				Interface.messageToClient(2, Message3);
				Interface.messageToClient(3, Message3);
			}
			
			if(explosionColor == 2)
			{
				g.setColor(Color.YELLOW);
				g2d.fillOval(1110, 625, 30, 30);
				String Message3 = null;
				String tankString = "03";
				tankString = tankString + "#60#745#0#730#685#1#1110#625#4#1410#785#3";
				Message3 = tankString;
				System.out.println(Message3);
				Interface.messageToClient(0, Message3);
				Interface.messageToClient(1, Message3);
				Interface.messageToClient(2, Message3);
				Interface.messageToClient(3, Message3);
			}
			
			magentaExplosion = false;
		}
		
		if(greenExplosion == true)
		{
			if(explosionColor == 0)
			{
				g.setColor(Color.ORANGE);
				g2d.fillOval(1410, 785, 30, 30);
				String Message3 = null;
				String tankString = "03";
				tankString = tankString + "#60#745#0#730#685#1#1110#625#2#1410#785#5";
				Message3 = tankString;
				System.out.println(Message3);
				Interface.messageToClient(0, Message3);
				Interface.messageToClient(1, Message3);
				Interface.messageToClient(2, Message3);
				Interface.messageToClient(3, Message3);
			}
			
			if(explosionColor == 1)
			{
				g.setColor(Color.RED);
				g2d.fillOval(1410, 785, 30, 30);
				String Message3 = null;
				String tankString = "03";
				tankString = tankString + "#60#745#0#730#685#1#1110#625#2#1410#785#1";
				Message3 = tankString;
				System.out.println(Message3);
				Interface.messageToClient(0, Message3);
				Interface.messageToClient(1, Message3);
				Interface.messageToClient(2, Message3);
				Interface.messageToClient(3, Message3);
			}
			
			if(explosionColor == 2)
			{
				g.setColor(Color.YELLOW);
				g2d.fillOval(1410, 785, 30, 30);
				String Message3 = null;
				String tankString = "03";
				tankString = tankString + "#60#745#0#730#685#1#1110#625#2#1410#785#4";
				Message3 = tankString;
				System.out.println(Message3);
				Interface.messageToClient(0, Message3);
				Interface.messageToClient(1, Message3);
				Interface.messageToClient(2, Message3);
				Interface.messageToClient(3, Message3);
			}
			
			greenExplosion = false;
		}
		
		
	} // end paint
	
	public static void main(String[] args) throws InterruptedException
	{ // main
		
		int i = 0;
		int value = 0;
		
		
		// turretX/Y is the changing point of turret
		turretX[0] = 75  + Change_In_TurretX;
		turretY[0] = 750 - Change_In_TurretY;
		turretX[1]  = 745 + Change_In_TurretX;
		turretY[1]  = 690 - Change_In_TurretY;
		turretX[2]  = 1125 + Change_In_TurretX;
		turretY[2]  = 630 - Change_In_TurretY;
		turretX[3]  = 1425 + Change_In_TurretX;
		turretY[3]  = 790 - Change_In_TurretY;
		
		
		Cannon_Fire[0] = true;
		Cannon_Fire[1] = true;
		Cannon_Fire[2] = true;
		Cannon_Fire[3] = true;
		
		playeralive[0] = true;
		playeralive[1] = true;
		playeralive[2] = true;
		playeralive[3] = true;
		
		
		
		// increase x value by 50
		for(i = 0; i < 38; i++)
	    {
			xpoints[i] = value;
			value = value + 50;
	    }

		ypoints [0] = 820;
		ypoints [1] = 760;
		ypoints [2] = 760;
		ypoints [3] = 800;
		ypoints [4] = 780;
		ypoints [5] = 770;
		ypoints [6] = 770;
		ypoints [7] = 750;
		ypoints [8] = 760;
		ypoints [9] = 770;
		ypoints [10] = 790;
		ypoints [11] = 790;
		ypoints [12] = 750;
		ypoints [13] = 720;
		ypoints [14] = 700;
		ypoints [15] = 700;
		ypoints [16] = 700;
		ypoints [17] = 730;
		ypoints [18] = 760;
		ypoints [19] = 760;
		ypoints [20] = 720;
		ypoints [21] = 680;
		ypoints [22] = 640;
		ypoints [23] = 640;
		ypoints [24] = 660;
		ypoints [25] = 680;
		ypoints [26] = 730;
		ypoints [27] = 770;
		ypoints [28] = 800;
		ypoints [29] = 800;
		ypoints [30] = 760;
		ypoints [31] = 780;
		ypoints [32] = 760;
		ypoints [33] = 770;
		ypoints [34] = 760;
		ypoints [35] = 790;
		ypoints [36] = 790;
		ypoints [37] = 820;
		
		
		JFrame frame = new JFrame("Terrain Maker");
		TerrainClass terrainclass = new TerrainClass();
		frame.add(terrainclass);
		frame.setSize(1875, 875);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
				
		terrainclass.repaint();
		
		// start the socket server to talk with client PCs
		Interface.serverInit(0);
		Interface.serverInit(1);
		Interface.serverInit(2);
		Interface.serverInit(3);
		
		// number of players
		String Message11 = "11#4";
		Interface.messageToClient(0, Message11);	
		Interface.messageToClient(1, Message11);	
		Interface.messageToClient(2, Message11);	
		Interface.messageToClient(3, Message11);	
		
		
		
		String Message2 = null;
		String terrainString = "02";
		
		// this is going to build terrain message for client
		for(i = 0; i < 38; i++)
		{
			terrainString = terrainString + "#" + xpoints[i] + "#" + ypoints[i];
		}
		
		// send terrain
		Message2 = terrainString;
		System.out.println(Message2);
		Interface.messageToClient(0, Message2);
		Interface.messageToClient(1, Message2);	
		Interface.messageToClient(2, Message2);	
		Interface.messageToClient(3, Message2);	

		// this is going to build the tank message for client
		String Message3 = null;
		String tankString = "03";
		tankString = tankString + "#60#745#0#730#685#1#1110#625#2#1410#785#3";
		Message3 = tankString;
		System.out.println(Message3);
		Interface.messageToClient(0, Message3);
		Interface.messageToClient(1, Message3);
		Interface.messageToClient(2, Message3);
		Interface.messageToClient(3, Message3);
		
		// this is going to build first turret for client
		String Message4 = null;
		String turretString = "04#0";
		turretString = turretString + "#75#750#" + (int)turretX[0] + "#" + (int)turretY[0];
		Message4 = turretString;
		System.out.println(Message4);
		Interface.messageToClient(0, Message4);
		Interface.messageToClient(1, Message4);
		Interface.messageToClient(2, Message4);
		Interface.messageToClient(3, Message4);
		
		// this is going to build second turret for client
		turretString = "04#1";
		turretString = turretString + "#745#690#" + (int)turretX[1] + "#" + (int)turretY[1];
		Message4 = turretString;
		System.out.println(Message4);
		Interface.messageToClient(0, Message4);
		Interface.messageToClient(1, Message4);
		Interface.messageToClient(2, Message4);
		Interface.messageToClient(3, Message4);
		
		// this is going to build third turret for client
		turretString = "04#2";
		turretString = turretString + "#1125#630#" + (int)turretX[2] + "#" + (int)turretY[2];
		Message4 = turretString;
		System.out.println(Message4);
		Interface.messageToClient(0, Message4);
		Interface.messageToClient(1, Message4);
		Interface.messageToClient(2, Message4);
		Interface.messageToClient(3, Message4);
		
		// this is going to build fourth turret for client
		turretString = "04#3";
		turretString = turretString + "#1425#790#" + (int)turretX[3] + "#" + (int)turretY[3];
		Message4 = turretString;
		System.out.println(Message4);
		Interface.messageToClient(0, Message4);
		Interface.messageToClient(1, Message4);
		Interface.messageToClient(2, Message4);
		Interface.messageToClient(3, Message4);
		
		int playerturn = 0;
		while(true)
		{
			if (playeralive[playerturn])
			{ // changing turrets + shooting cannonballs
				// Get Angle and Power from other PC.
	     	    Interface.messageToClient(playerturn,"09#");
	     	    anglepower1 = Interface.getAnglePowerFromClient(playerturn);

	     	    
	     	    // turn on cannon ball flag
	     	   Cannon_Fire[playerturn] = true;
				
	     	    // Convert from Dad's 'int' to 'double' for my code
	     	    double Angle = (double) anglepower1[0];				
				Hypotenuse = (double) anglepower1[1];
			
				Change_In_TurretX = 15 * Math.cos(Math.toRadians(Angle));
				Change_In_TurretY = 15 * Math.sin(Math.toRadians(Angle));
				Change_In_Cannonball_X = Hypotenuse * Math.cos(Math.toRadians(Angle));
				Change_In_Cannonball_Y = Hypotenuse * Math.sin(Math.toRadians(Angle));
			
				// this is so computer knows which turret to change
				// debug this: cannonball starts from weird spots
				if (playerturn == 0)
				{
					turretX[0] = 75  + Change_In_TurretX;
					turretY[0] = 750 - Change_In_TurretY;
				}
				if (playerturn == 1)
				{
					turretX[1] = 745  + Change_In_TurretX;
					turretY[1] = 690 - Change_In_TurretY;
				}
				if (playerturn == 2)
				{
					turretX[2] = 1125  + Change_In_TurretX;
					turretY[2] = 630 - Change_In_TurretY;
				}
				if (playerturn == 3)
				{
					turretX[3] = 1425  + Change_In_TurretX;
					turretY[3] = 790 - Change_In_TurretY;
				}
				
				// this makes the blue turret change position when we fire
				turretString = "04#0";
				turretString = turretString + "#75#750#" + (int)turretX[0] + "#" + (int)turretY[0];
				Message4 = turretString;
				System.out.println(Message4);
				Interface.messageToClient(0, Message4);
				Interface.messageToClient(1, Message4);
				Interface.messageToClient(2, Message4);
				Interface.messageToClient(3, Message4);
				
				// this makes the red turret change position when we fire
				turretString = "04#1";
				turretString = turretString + "#745#690#" + (int)turretX[1] + "#" + (int)turretY[1];
				Message4 = turretString;
				System.out.println(Message4);
				Interface.messageToClient(0, Message4);
				Interface.messageToClient(1, Message4);
				Interface.messageToClient(2, Message4);
				Interface.messageToClient(3, Message4);
				
				// this makes the magenta turret change position when we fire
				turretString = "04#2";
				turretString = turretString + "#1125#630#" + (int)turretX[2] + "#" + (int)turretY[2];
				Message4 = turretString;
				System.out.println(Message4);
				Interface.messageToClient(0, Message4);
				Interface.messageToClient(1, Message4);
				Interface.messageToClient(2, Message4);
				Interface.messageToClient(3, Message4);
				
				// this makes the green turret change position when we fire
				turretString = "04#3";
				turretString = turretString + "#1425#790#" + (int)turretX[3] + "#" + (int)turretY[3];
				Message4 = turretString;
				System.out.println(Message4);
				Interface.messageToClient(0, Message4);
				Interface.messageToClient(1, Message4);
				Interface.messageToClient(2, Message4);
				Interface.messageToClient(3, Message4);
				
				
				// cannonball starts from tank
				CannonballX = turretX[playerturn];
				CannonballY = turretY[playerturn];
			
				T = 0.0;
			
				// dead check
				if(playeralive[playerturn] == true)
				{
					
					
					// SHOOT!
					while(Cannon_Fire[playerturn])
					{ // cannonball calculations
						CannonballX = CannonballX + Change_In_Cannonball_X;
						CannonballY = CannonballY - Change_In_Cannonball_Y - Gravity_Force;
						terrainclass.repaint();
						
						// send cannon ball position to all clients
						String message5 = "05#"+ (int) CannonballX + "#" + (int) CannonballY;   
			     	    for (int k1=0; k1<4; k1++)
				    	{
				     	    Interface.messageToClient(k1, message5);
				    	}
			     	    
						
						// Sleep
						Thread.sleep((long)Gamespeed);
						// Update gravity value
						T = T + 0.05;
						Gravity_Force = 0.5 * -G * T * T;
						
						
						if(CannonballX > 1875 || CannonballX < 0)
						{
							Cannon_Fire[playerturn] = false;
						}
					
						if(CannonballY > 875 || CannonballY < 0)
						{
							Cannon_Fire[playerturn] = false;
						}
						
						
						if(playerturn != 0)
						{ // blue tank death detection
							if(CannonballX > 60.0 && CannonballX < 90.0)  // BLUE CIRCLE:(60, 745), diameter = 30
							{
								if(CannonballY > 745.0 && CannonballY < 775.0)  // X's and Y's are drawn from the corner
								{
									
									playeralive[0] = false;
									blueExplosion = true;
									System.out.println("BLUE TANK IS DEAD!");
									// make server send a message to the computer that was hit
									
										
									for(deadTank = 0; deadTank < 25; deadTank++)  // player 0's tank explodes
									{
											
										Thread.sleep((long)Gamespeed);
										Thread.sleep((long)Gamespeed);
										Thread.sleep((long)Gamespeed);
										Thread.sleep((long)Gamespeed);
										Thread.sleep((long)Gamespeed);
										Thread.sleep((long)Gamespeed);
										Thread.sleep((long)Gamespeed);
										Thread.sleep((long)Gamespeed);
	
											
										int remainder = (int)deadTank % 3;
											
										if(remainder == 0)
										{
											explosionColor = 1;
										}
											
										if(remainder == 1)
										{
											explosionColor = 4;
										}
										
										if(remainder == 2)
										{
											explosionColor = 5;
										}
										
										// Message 12: Draw Explosion
										String Message12 = "12#0#" + explosionColor;
										System.out.println(Message12);
										Interface.messageToClient(0, Message12);
										Interface.messageToClient(1, Message12);
										Interface.messageToClient(2, Message12);
										Interface.messageToClient(3, Message12);
										
										terrainclass.repaint();
									}
								}
							}
						} // end blue tank death detection
						
						
						if(playerturn != 1)
						{ // red tank death detection 
							
							if(CannonballX > 730.0 && CannonballX < 760.0)  // RED CIRCLE:(730, 685), diameter = 30
							{
								if(CannonballY > 685.0 && CannonballY < 715.0)  // X's and Y's are drawn from the corner
								{
									playeralive[1] = false;
									redExplosion = true;
									System.out.println("RED TANK IS DEAD!");
									// make server send a message to the computer that was hit
										
										
									for(deadTank = 0; deadTank < 25; deadTank++)  // tank explodes
									{
										Thread.sleep((long)Gamespeed);
										Thread.sleep((long)Gamespeed);
										Thread.sleep((long)Gamespeed);
										Thread.sleep((long)Gamespeed);
										Thread.sleep((long)Gamespeed);
										Thread.sleep((long)Gamespeed);
										Thread.sleep((long)Gamespeed);
										Thread.sleep((long)Gamespeed);
	
											
										int remainder = (int)deadTank % 3;
											
										if(remainder == 0)
										{
											explosionColor = 1;
										}
											
										if(remainder == 1)
										{
											explosionColor = 4;
										}
										
										if(remainder == 2)
										{
											explosionColor = 5;
										}
											
										//Message 12: Draw Explosion
										String Message12 = "12#1#" + explosionColor;
										System.out.println(Message12);
										Interface.messageToClient(0, Message12);
										Interface.messageToClient(1, Message12);
										Interface.messageToClient(2, Message12);
										Interface.messageToClient(3, Message12);
										
										terrainclass.repaint();
									}
								}
							}
						} // end red tank death detection
						
						
						if(playerturn != 2)
						{ // magenta tank death detection 
							
							if(CannonballX > 1110.0 && CannonballX < 1140.0)  // MAGENTA CIRCLE:(1110, 625), diameter = 30
							{
								if(CannonballY > 625.0 && CannonballY < 655.0)  // X's and Y's are drawn from the corner
								{
									playeralive[2] = false;
									magentaExplosion = true;
									System.out.println("MAGENTA TANK IS DEAD!");
									// make server send a message to the computer that was hit
										
										
									for(deadTank = 0; deadTank < 25; deadTank++)  // tank explodes
									{
										Thread.sleep((long)Gamespeed);
										Thread.sleep((long)Gamespeed);
										Thread.sleep((long)Gamespeed);
										Thread.sleep((long)Gamespeed);
										Thread.sleep((long)Gamespeed);
										Thread.sleep((long)Gamespeed);
										Thread.sleep((long)Gamespeed);
										Thread.sleep((long)Gamespeed);
	
											
										int remainder = (int)deadTank % 3;
											
										if(remainder == 0)
										{
											explosionColor = 1;
										}
											
										if(remainder == 1)
										{
											explosionColor = 4;
										}
										
										if(remainder == 2)
										{
											explosionColor = 5;
										}
											
										//Message 12: Draw Explosion
										String Message12 = "12#2#" + explosionColor;
										System.out.println(Message12);
										Interface.messageToClient(0, Message12);
										Interface.messageToClient(1, Message12);
										Interface.messageToClient(2, Message12);
										Interface.messageToClient(3, Message12);
										
										terrainclass.repaint();
									}
								}
							}
						} // end magenta tank death detection
						
						
						if(playerturn != 3)
						{ // green tank death detection 
							
							if(CannonballX > 1410.0 && CannonballX < 1440.0)  // GREEN CIRCLE:(1410, 785), diameter = 30
							{
								if(CannonballY > 785.0 && CannonballY < 815.0)  // X's and Y's are drawn from the corner
								{
									playeralive[3] = false;
									greenExplosion = true;
									System.out.println("GREEN TANK IS DEAD!");
									// make server send a message to the computer that was hit
									
										
									for(deadTank = 0; deadTank < 25; deadTank++)  // tank explodes
									{
										Thread.sleep((long)Gamespeed);
										Thread.sleep((long)Gamespeed);
										Thread.sleep((long)Gamespeed);
										Thread.sleep((long)Gamespeed);
										Thread.sleep((long)Gamespeed);
										Thread.sleep((long)Gamespeed);
										Thread.sleep((long)Gamespeed);
										Thread.sleep((long)Gamespeed);
	
											
										int remainder = (int)deadTank % 3;
											
										if(remainder == 0)
										{
											explosionColor = 1;
										}
											
										if(remainder == 1)
										{
											explosionColor = 4;
										}
										
										if(remainder == 2)
										{
											explosionColor = 5;
										}
											
										//Message 12: Draw Explosion
										String Message12 = "12#3#" + explosionColor;
										System.out.println(Message12);
										Interface.messageToClient(0, Message12);
										Interface.messageToClient(1, Message12);
										Interface.messageToClient(2, Message12);
										Interface.messageToClient(3, Message12);
										
										terrainclass.repaint();
									}
								}
							}
						} // end green tank death detection
						
					} // end cannonball calculations
				} // end dead check
				
				
			} // end changing turrets + shooting cannonballs
			
			playerturn = playerturn + 1;
			if(playerturn > 3)
			{
				playerturn = 0;
			}
		}// while loop
	} // end main
} // end class

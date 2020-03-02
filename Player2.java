import java.awt.*;
import javax.swing.*;
import java.util.*;
import java.awt.event.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import javax.swing.event.*;

/*
swing vs awt
    -swing is more "modern", hip thing to do.
    -awt is a little rougher around the edges.
both have their advantages, (speed vs ease, etc)
not really that important.
*/
public class Player2 extends JFrame implements ActionListener, ChangeListener
{
    //lesson 1: everything in javax starts with "J".
    //(almost)
    private JSlider power_slider;
    private JSlider angle_slider;
    private static MyPanel1 drawing_area;    
    private JRadioButton black;
    private JRadioButton blue;
    private JRadioButton red;

    private JLabel color_label;
    private JLabel width_label;
    private JLabel area_label;

    //awt, no J!  This is typically done with a JPanel.
    //However containers can work too.
    private Container content_panel;
    private Container top_area; //holds all of left
    private Container color_container;
    private Container power_container;
    private Container angle_container;

    //layout managers
    private GridBagLayout full_layout;
    private GridBagLayout top_layout;

    //new stuff
    private static JButton shoot_button;
    private static Container shoot_container;
    private static boolean shootEnable;
    private static boolean shootPressed;
  

    private static final long serialVersionUID = 1L;
	private static int LISTENING_PORT = 32002;
	private static int[] tankX = new int[4];
	private static int[] tankY = new int[4];
	private static int[] tankColor = new int[4];
	
	private static int bulletY = 0;
	private static int bulletX = 0;
	private static int numberOfPlayers = 0;
	
	private static int[] turretX1 = new int[4];
	private static int[] turretY1 = new int[4];
	private static int[] turretX2 = new int[4];
	private static int[] turretY2 = new int[4];
	private static int[] terrainX = new int[38];
	private static int[] terrainY = new int[38];
	
	public static BufferedReader incoming;     // Stream for reading data from the connection.
	public static PrintWriter out;
	public static Scanner keyboard;
	 
	private static boolean debug = true;
	private static int delta = 300;
	
	private static int angleValue;
	private static int powerValue;
	
    //dont just throw everything in the constructor!!!
    //ORGANIZE!!!!
        //create relevant objects
        //create layouts and constraints
        //add objects to containers
        //deal with events
    public Player2() throws InterruptedException
    {
        super("java.  Tank Battle:2");
        

        this.setDefaultCloseOperation(EXIT_ON_CLOSE);

        this.full_layout = new GridBagLayout();
        this.content_panel = new Container();
        this.content_panel.setLayout(full_layout);
        this.setContentPane(this.content_panel);
        this.content_panel.setVisible(true);
        
        shootEnable = false;
        shootPressed = false;
       
        
        init_top();
        init_panel();
        this.setSize(1875, 875); //big enough to pack down.
        this.pack();
              
    }
    public static void main(String [] args) throws InterruptedException
    {
        new Player2().setVisible(true);
        Boolean gameOn = true;
    	String computer;     // Name of the computer to connect to.
    	Socket connection;   // A socket for communicating with that computer.
    	 
        // Set computer name. 
        System.out.println("Using localhost");
        computer = "127.0.0.1";
        keyboard = new Scanner(System.in);
  	     
        
        
        // Make the connection            
        try {
           connection = new Socket( computer, LISTENING_PORT );
           out = new PrintWriter(connection.getOutputStream(), true);
           incoming = new BufferedReader(new InputStreamReader (connection.getInputStream()) );
           
           
        }
        catch (IOException e) {
      	  System.out.println("Error:  " + e);
        }
        
        
        
        while (gameOn) {        	
        	
        	String messageIn = null;
       	    
          	//check if new message from the Server
          	try {
    			messageIn = incoming.readLine();
    		} catch (IOException e) {
    			// TODO Auto-generated catch block
    			e.printStackTrace();
    		}
          	if (messageIn != null) {
              		
              		//if (debug) System.out.println("--------------------" + messageIn);
              		
              		String delims = "[#]";
              		String[] tokens = messageIn.split(delims);
              		//if (debug)  System.out.println("Message from Server: " + tokens[0]);
              		
              		if (tokens[0].equals("01")) {
              			
              		}
              		//terrain
              		if (tokens[0].equals("02")) {
              			
              			if (debug) System.out.println("Terrain message processing.");
              			
              			int j=1;
              			for (int i=0; i<38; i++) {
              				terrainX[i] = Integer.parseInt(tokens[j]);
              				j++;
              				
              				terrainY[i] = Integer.parseInt(tokens[j]);
              				j++;
              			
              				//if (debug) System.out.println(terrainX[i] + ", " + terrainY[i]);
              			}
              			
              			drawing_area.SetTerrain(terrainX,terrainY);
              		}
              		// Tank draw message
              		if (tokens[0].equals("03")) {
              			
              			int j=1;
              			if (debug) System.out.println("Tank (03) message processing.");
              			for (int i=0; i<numberOfPlayers; i++) {
              				tankX[i] = Integer.parseInt(tokens[j]);
              				j++;
              				
              				tankY[i] = Integer.parseInt(tokens[j]);
              				j++;
              				
              				tankColor[i] = Integer.parseInt(tokens[j]);
              				j++;
              				
              				drawing_area.SetTank(i,tankX[i], tankY[i], tankColor[i]);
              				
              				if (debug)  System.out.println("Tank = "+tankX[i] + ", " + tankY[i] );     				
              				
              			}
              			
              			
              		}   
              		// Turret draw message
              		if (tokens[0].equals("04")) {
              			
              			int player = Integer.parseInt(tokens[1]);
              			
              			int j=2;
              		
              			turretX1[player] = Integer.parseInt(tokens[j]);
              			j++;
              				
              			turretY1[player] = Integer.parseInt(tokens[j]);
              			j++;
              				
              			turretX2[player] = Integer.parseInt(tokens[j]);
              			j++;
              				
              			turretY2[player] = Integer.parseInt(tokens[j]);
              			if (debug)  
              			{
              				//System.out.println("Turret message processing. Player = " + player);
              				//System.out.println(turretX1[player] + ", " + turretY1[player] );
              				//System.out.println(turretX2[player] + ", " + turretY2[player] );
              			}
              			j++;
              			
              			drawing_area.SetTurret(player,turretX1[player], turretY1[player], 
              					turretX2[player], turretY2[player]);
              		}   	
              		// Bullet draw message
              		if (tokens[0].equals("05")) {
              			//if (debug)  System.out.println("Bullet message processing.");
              			
              			bulletX = Integer.parseInt(tokens[1]);
              			bulletY = Integer.parseInt(tokens[2]);
             				
              			drawing_area.SetCannonBall(bulletX,bulletY);
              			
              			//if (debug) System.out.println("Bullet = " + bulletX + ", " + bulletY);
              		}   
              		if (tokens[0].equals("09")) {
              			
              			
              			// Enable Shoot button
              			shootEnable = true;
              			System.out.println("Shoot enable flag from Server");
              			
              			shoot_button.setEnabled(true);
              			
              			
              		}        		
              		
              		
              		if (tokens[0].equals("11")) {
              			
              			numberOfPlayers = Integer.parseInt(tokens[1]);
              			System.out.println("Players = "+numberOfPlayers);
              		}
              		
              		if (tokens[0].equals("12")) {		// Change - add message 12 processing
              			
              			
              			int playerNumber = Integer.parseInt(tokens[1]);
          				          				
          				int color = Integer.parseInt(tokens[2]);
          				          				
          				drawing_area.SetTankColor(playerNumber, color);
          				
          				System.out.println("Tank color = " + color );     				
          				
              		}
           	
            }          	
          	
        	drawing_area.repaint();
            Thread.sleep(100);
        }
      	
        try {
			incoming.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        
    }
    
    
    private void init_panel()
    {
        this.drawing_area = new MyPanel1();
        this.drawing_area.setPreferredSize(new Dimension(1800,900));
        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.BOTH;
        c.gridy = 1;
        c.weighty = 20;
        this.drawing_area.setBackground(Color.white);
        this.content_panel.add(this.drawing_area, c);
        this.drawing_area.setVisible(true);
    }
    
    private void init_top()
    {
        this.top_area = new Container();
        GridBagConstraints left_c = new GridBagConstraints();
        left_c.gridx = 0;
        left_c.insets = new Insets(10, 10, 10, 10);
        this.content_panel.add(this.top_area, left_c);
        this.top_layout = new GridBagLayout();
        this.top_area.setLayout(top_layout);

       
        
        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 0.5;
        c.insets = new Insets(10,0,10,0);
        c.gridy = 0;
        c.gridx = 1;
        c.anchor = GridBagConstraints.NORTH;
        init_power();
        this.top_area.add(this.power_container, c);
        
        c.weightx = 0.5;
        c.gridy = 0;
        c.gridx = 2;
        c.gridwidth = 2;
        init_angle();
        this.top_area.add(this.angle_container, c);
        
        c.gridy = 0;
        c.gridx = 4;
        c.weightx = 0.5;
        shoot();
        this.top_area.add(this.shoot_container, c);
/*
        GridBagConstraints a_c = new GridBagConstraints();
        a_c.insets = new Insets(10,0,10,0);
        c.weightx = 0.5;
        a_c.gridy = 0;
        a_c.gridx = 2;
        //a_c.gridwidth = 5;
        a_c.anchor = GridBagConstraints.NORTH;
        init_angle();
        this.top_area.add(this.angle_container, a_c);

        
        GridBagConstraints i_c = new GridBagConstraints();
        i_c.gridy = 0;
        i_c.gridx = 3;
        i_c.insets = new Insets(10,0,10,0);
        i_c.anchor = GridBagConstraints.NORTH;
        shoot();
        this.top_area.add(this.shoot_container, i_c);
*/        
    }


    private void init_power()
    {
        this.power_container = new Container();
        GridLayout g = new GridLayout(2,1);
        this.power_container.setLayout(g);
        JLabel width = new JLabel("Power");
        this.power_slider = new JSlider(JSlider.HORIZONTAL, 0, 20, 5);
        this.power_slider.addChangeListener(this);
        power_slider.setMajorTickSpacing(5);
        power_slider.setMinorTickSpacing(1);
        power_slider.setPaintTicks(true);
        power_slider.setPaintLabels(true);
        this.power_container.add(width);
        this.power_container.add(this.power_slider);
        this.power_container.setVisible(true);
    }
    private void init_angle()
    {
        this.angle_container = new Container();
        GridLayout g = new GridLayout(2,1);
        this.angle_container.setLayout(g);
        JLabel angle = new JLabel("Angle");
        this.angle_slider = new JSlider(JSlider.HORIZONTAL, 0, 360, 180);
        this.angle_slider.addChangeListener(this);
        angle_slider.setMajorTickSpacing(60);
        angle_slider.setMinorTickSpacing(30);
        angle_slider.setPaintTicks(true);
        angle_slider.setPaintLabels(true);
        this.angle_container.add(angle);
        this.angle_container.add(this.angle_slider);
        this.angle_container.setVisible(true);
    }

    private void shoot()
    {
      
        this.shoot_button = new JButton("Shoot");
        this.shoot_container = new Container();

        GridBagLayout g = new GridBagLayout();
        this.shoot_container.setLayout(g);

        GridBagConstraints lc = new GridBagConstraints();
        lc.gridy = 0;

        GridBagConstraints bc = new GridBagConstraints();
        bc.gridy = 1;
        
        this.shoot_container.add(this.shoot_button, bc);
        this.shoot_button.setActionCommand("invert");
        this.shoot_button.addActionListener(this);
        
        this.shoot_button.setEnabled(false);
        
    }

    //radio buttons
    public void actionPerformed(ActionEvent e)
    {
        if (e.getSource() == black)
            this.drawing_area.SetColor(Color.black);
        else if (e.getSource() == blue)
            this.drawing_area.SetColor(Color.blue);
        else if (e.getSource() == blue)
            this.drawing_area.SetColor(Color.red);
        else if (e.getSource() == this.shoot_button)
        {
            Color c = this.drawing_area.GetColor();
            Color new_c = new Color((255 - c.getRed()), (255 - c.getGreen()),(255 - c.getBlue()));
            this.drawing_area.SetColor(new_c);
           
      		
            //Get angle and power from sliders and send to Server as integers
            angleValue = this.angle_slider.getValue();
            powerValue = this.power_slider.getValue();
      		String message9 = Integer.toString(angleValue);
      		String message10 = Integer.toString(powerValue);
      		String message = message9 + "#" + message10;
      		System.out.println("Shoot message back to Server, " + message);
      		
      		out.println(message);
      		
      		
      		this.shoot_button.setEnabled(false);
      		
        }
        this.drawing_area.repaint();
    }

    //slider
    public void stateChanged(ChangeEvent e)
    {
        if (e.getSource() == this.power_slider)
        {
            powerValue = this.power_slider.getValue();
            
        }
        
        if (e.getSource() == this.angle_slider)
        {
            angleValue = this.angle_slider.getValue();
           
        }

        this.drawing_area.repaint();
    }
    
}
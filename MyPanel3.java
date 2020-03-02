import java.awt.*;
import javax.swing.*;

public class MyPanel3 extends JPanel
{
    
	private static final long serialVersionUID = 1L;
	private int width;
    private Color color;
    private int[] xpoints = new int[38];
	private int[] ypoints = new int[38];
	private int[] tankX = new int[4];
	private int[] tankY = new int[4];
	private int[] tankColor = new int[4];
	private int[] turretX1 = new int[4];
	private int[] turretY1 = new int[4];
	private int[] turretX2 = new int[4];
	private int[] turretY2 = new int[4];
	private int cannonballX = 0;
	private int cannonballY = 0;
	
	private int numberOfPlayers = 4;

    public MyPanel3()
    {
        super();
        this.width = 12;
        this.color = Color.black;
        
        this.tankY[0] = 40;
        this.tankY[1] = 40;
        this.tankY[2] = 40;
        this.tankY[3] = 40;
    }

    public void SetWidth(int width)
    {
        this.width = width;
        this.tankX[1] = width*2;
        this.tankX[2] = width*3;
        this.tankX[3] = width*4;
        this.tankX[0] = width*5;
    }

    public void SetTankColor(int player, int color)  //Change
    {
    	this.tankColor[player] = color;
    }
    
    public void SetColor(Color color)
    {
        this.color = color;
    }

    public Color GetColor()
    {
        return this.color;
    }
    public void SetCannonBall(int x, int y)
    {
    	this.cannonballX = x;
    	this.cannonballY = y;
    }
    public void SetTank(int player, int x1, int y1, int color)
    {
    	this.tankX[player] = x1;
    	this.tankY[player] = y1;
    	this.tankColor[player] = color;
    }
    public void SetTurret(int player, int x1, int y1, int x2, int y2)
    {
    	this.turretX1[player] = x1;
    	this.turretY1[player] = y1;
    	this.turretX2[player] = x2;
    	this.turretY2[player] = y2;
    }
    public void SetTerrain(int[] x, int[] y)
    {
    	int i;
    	for (i = 0; i<38; i++)
    	{
    		this.xpoints[i] = x[i];
    		this.ypoints[i] = y[i];
    	}
    }

    //override
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        int x = this.getWidth();
        int y = this.getHeight();
        
        g.clearRect(0,0,x,y);
        g.setColor(this.color);
        x = (x - this.width*10) / 2;
        y = (y - this.width*10) / 2;
        //g.fillOval(x,y,this.width*10,this.width*10);
        
       
        //Draw new tank turret for player 
        g.setColor(Color.BLACK);        
       	//g.setStroke(new BasicStroke(7));  // make turret lines thicker
        
        int k =0;
        for (k=0; k<numberOfPlayers; k++)
        {
        	//System.out.println("turret " + k + " = " + turretX2[k] + "  " +  turretY2[k]);
        	g.drawLine(turretX1[k],turretY1[k],turretX2[k],turretY2[k]);

        }
        
        //Draw tanks
        for (k=0; k<4; k++) {
        	if (tankColor[k] == 0)
        	{
        		  g.setColor(Color.BLUE);
        	}
        	else if (tankColor[k] == 1)
        	{
        		g.setColor(Color.RED);
        	}
        	else if (tankColor[k] == 2)
        	{
        		g.setColor(Color.MAGENTA);
        	}
        	else if (tankColor[k] == 3)
        	{
        		g.setColor(Color.GREEN);
        	}
        	else if (tankColor[k] == 4)
        	{
        		g.setColor(Color.YELLOW);
        	}
        	else if (tankColor[k] == 5)
        	{
        		g.setColor(Color.ORANGE);
        	}
        	
        	
        	 g.fillOval(tankX[k], tankY[k], 30, 30);
        }
        
        
        
       
        // draw terrain
        g.setColor(Color.BLACK);
        g.fillPolygon(xpoints, ypoints, 38);
              
        // draw cannon ball
        g.fillOval(cannonballX, cannonballY, 10, 10);
        
        
        //debug
        g.setColor(Color.GREEN);
        int j;
        for (j=0; j<38; j++) {
        	g.fillOval(j*50, 700, 5, width);
        }
    }

}
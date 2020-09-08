//Name - Matthew Ho	
//Date - 2/12
//Period- 2nd 


import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JComponent;
import java.util.*;

/**
   This component draws pictures.
*/
public class PictureComponent extends JComponent
{  

   
   private ElevatorTrack t1;
   private ElevatorTrack t2;
   private Elevator e1;
   private Elevator e2;
   private Building b; 
   private Ground gr;
   private Sky s;
   private Clouds c;
   private Clouds c2;
   private Clouds c3;
   private Sun sn;
   private RoundBoat bt;
   private RoundBoat bt2;
   private Airplane a;
   private RectBoats rbt;
   private RectBoats rbt2;
   
	/**
	 * Adds pictures to the component
	 */
   public PictureComponent()
   {
      t1 = new ElevatorTrack(900,0);
      t2 = new ElevatorTrack(794,0);
      e1 = new Elevator(900,300,(int)(Math.random()*10)-5);
      e2 = new Elevator(794,300,(int)(Math.random()*10)-5);
      b = new Building(330,0);
      gr = new Ground(0,300);
      s = new Sky(0,0,1,600);
      c = new Clouds(50,100);
      c2 = new Clouds(100,200);
      c3 = new Clouds(150,25);
      sn = new Sun(150,400);
      bt = new RoundBoat(200,350);
      bt2 = new RoundBoat(300,500);
      a = new Airplane(50,100);
      rbt = new RectBoats(450,350);
      rbt2 = new RectBoats(200,600);
   }
   
   /**
    * Draws the pictures, moves them, draws them again.
    */
   public void paintComponent(Graphics g)
   {  

      Graphics2D g2 = (Graphics2D) g;
      s.draw(g2);
      sn.draw(g2);
      gr.draw(g2);
      c.draw(g2);
      c2.draw(g2);
      c3.draw(g2);
      a.draw(g2);
      bt.draw(g2);
      bt2.draw(g2);
      rbt.draw(g2);
      rbt2.draw(g2);
      b.draw(g2);
      t1.draw(g2);
      t2.draw(g2);
      e1.draw(g2);
      e2.draw(g2);
      
   }
   
   /**
    * moves the objects and refreshes the component
    */
   public void animate()
   {
	    Thread elev1 = new Thread(e1);
	    Thread elev2 = new Thread(e2);
	    Thread cloud1 = new Thread(c);
	    Thread cloud2 = new Thread(c2);
	    Thread cloud3 = new Thread(c3);
	    Thread air = new Thread(a);
	    Thread sun = new Thread(sn);
	    Thread roundbt = new Thread(bt);
	    Thread roundbt2 = new Thread(bt2);
	    Thread rectbt = new Thread(rbt);
	    Thread rectbt2 = new Thread(rbt2);
	    elev1.start();
	    elev2.start();
	    cloud1.start();
	    cloud2.start();
	    cloud3.start();
	    air.start();
	    sun.start();
	    roundbt.start();
	    roundbt2.start();
	    rectbt.start();
	    rectbt2.start();
	    s.changeSky();
	    
		while(true)
		{
			repaint(); // refreshes the window
		}
   		
   	} 
 }

   
   

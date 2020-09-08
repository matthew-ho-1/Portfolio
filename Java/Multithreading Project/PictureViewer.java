//Name - Matthew Ho	
//Date - 2/12
//Period- 2nd 



import javax.swing.JFrame;

/**
 * Shows an animation of elevators moving and the scenery in the background
 */
public class PictureViewer
{
   public static void main(String[] args)
   {
      JFrame frame = new JFrame();

      final int FRAME_WIDTH = 1280;
      final int FRAME_HEIGHT = 900;

      frame.setSize(FRAME_WIDTH, FRAME_HEIGHT);
      frame.setTitle("Picture Viewer");
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

      PictureComponent component = new PictureComponent();
      frame.add(component);

      frame.setVisible(true);
      component.animate();
 
   }
}


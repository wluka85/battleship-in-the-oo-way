import java.awt.*;
import javax.swing.*;


public class ShipsPositioningDialog extends JDialog implements MouseListener {
    private static final int DEFAULT_WIDTH = 500;
    private static final int DEFAULT_HEIGHT = 550;




    public ShipsPositioningDialog(JFrame owner) {
        super(owner, "Add ships", true);


    }




    public Dimension getPrefferedSize() {
      return new Dimension(DEFAULT_WIDTH, DEFAULT_HEIGHT);
    }
}

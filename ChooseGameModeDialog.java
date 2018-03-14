import java.awt.*;
import javax.swing.*;
import java.awt.event.*;


public class ChooseGameModeDialog {
    private static final int DEFAULT_WIDTH = 200;
    private static final int DEFAULT_HEIGHT = 400;



    public ChooseGameModeDialog(JFrame owner) {
        super(owner, "Choose game mode", true);
    }



    public Dimension getPrefferedSize() {
        return new Dimension(DEFAULT_WIDTH, DEFAULT_HEIGHT);
    }

}

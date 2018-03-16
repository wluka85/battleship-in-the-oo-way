import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class ChooseGameModeDialog extends JDialog {
    private static final int DEFAULT_WIDTH = 180;
    private static final int DEFAULT_HEIGHT = 150;
    private JPanel buttonPanel;
    private JLabel informationLabel;
    private JButton pvAIButton, pvpButton, aivAIButton;
    private String gameMode = "aivAI";

    public ChooseGameModeDialog(JFrame owner) {
        super(owner, "Choose game mode", true);
        setLayout(new BorderLayout());
        informationLabel = new JLabel("Choose game mode: ");
        add(informationLabel, BorderLayout.NORTH);
        buttonPanel = new JPanel();
        pvAIButton = new JButton("Player vs AI");
        pvpButton = new JButton("Player vs Player");
        aivAIButton = new JButton("Simulation");
        pvAIButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                gameMode = "pvAI";
                dispose();
            }
        });
        pvpButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                gameMode = "pvp";
                dispose();
            }
        });
        aivAIButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                gameMode = "aivAI";
                dispose();
            }
        });
        buttonPanel.add(pvAIButton);
        buttonPanel.add(pvpButton);
        buttonPanel.add(aivAIButton);
        add(buttonPanel, BorderLayout.CENTER);
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        setSize(getPrefferedSize());
        setVisible(true);
    }

    public Dimension getPrefferedSize() {
        return new Dimension(DEFAULT_WIDTH, DEFAULT_HEIGHT);
    }

    public String getGameMode() {
        return gameMode;
    }

}

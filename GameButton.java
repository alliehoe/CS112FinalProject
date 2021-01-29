import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameButton extends JButton implements ActionListener {

    private AFrame frame;
    private Container gameContainer;
    private int gameNumber;
    private JPanel gridContainer;
    private JPanel optionsContainer;
    private JPanel nameOfGame;

    public GameButton(String name, AFrame f, Container cont, int gameNum, JPanel grid, JPanel title, JPanel option) {
        super(name);
        frame = f;
        gameContainer = cont;
        gameNumber = gameNum;
        gridContainer = grid;
        nameOfGame = title;
        optionsContainer = option;
        addActionListener(this);
    }

    public void actionPerformed(ActionEvent e) {

        gridContainer.removeAll();
        optionsContainer.removeAll();


        if (gameNumber == 1) {
            AGame g = new Hitori(frame);
            g.createGrid(gameContainer, gridContainer, optionsContainer);
        }
        if (gameNumber == 2) {
            AGame g = new Skyscraper(frame);
            g.createGrid(gameContainer, gridContainer, optionsContainer);
        }

        frame.validate();
        frame.repaint();
    }
}

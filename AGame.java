import javax.swing.*;
import java.awt.*;

public abstract class AGame extends AFrame {

    public AGame() {}

    public abstract void blankGrid();

    public abstract void createGrid(Container gameContainer, JPanel gridContainer, JPanel optionsContainer);

    //public abstract void createOptions(Container optionsContainer);

    public abstract void setValue(int row, int column);

    public abstract boolean check();

    public abstract boolean completeCheck();

    public abstract void solveGrid();

    public abstract boolean solved();

}

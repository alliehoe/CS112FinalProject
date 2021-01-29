import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ASolver extends JButton implements ActionListener {

    private AGame g;
    private int[][] gameGrid;
    private int[] possibleValues;

    public ASolver(String text, AGame game, int[][] grid, int[] val) {
        super(text);
        g = game;
        gameGrid = grid;
        possibleValues = val;
        addActionListener(this);
    }

    private boolean print(int[][] gameGrid, AGame g, int[] possibleValues, int row, int column) {

        if (row == gameGrid.length) {

            System.out.println("the row is equal to the the grid length");
            return g.completeCheck();
        }

        for (int v : possibleValues) {
            gameGrid[row][column] = v;
            if (g.check()) {
                int column2 = column + 1;
                int row2 = row;

                if (column2 == gameGrid[row].length) {
                    column2 = 0;
                    row2 = row + 1;
                }

                if (print(gameGrid, g, possibleValues, row2, column2)) {
                    return true;
                }
            }
        }
        gameGrid[row][column] = -1;
        return false; // this is originally false
    }

    public void actionPerformed(ActionEvent e) {
        g.blankGrid();
        System.out.println("Solve Button got Pressed");
        boolean answer = print(gameGrid, g, possibleValues, 0,0); // this is somehow false
        if (answer) {
            System.out.println("solver button works");
            g.solveGrid();
        }
    }
}

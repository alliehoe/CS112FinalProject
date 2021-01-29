import javax.swing.*;
import java.awt.*;

public class Hitori extends AGame {

    private int[][] hitoriGrid = new int[5][5];
    private int[][] givenNumbers = {{5, 5, 3, 1, 1},
                                    {2, 1, 4, 4, 3},
                                    {2, 5, 2, 4, 4},
                                    {4, 1, 1, 2, 5},
                                    {3, 2, 4, 2, 1}};
    private HitoriButtons[][] buttons = new HitoriButtons[5][5];
    private int[] possibleValues = {0,1};
    private AFrame frame;

    public Hitori(AFrame f) {
        frame = f;
        this.blankGrid();
    }

    public void blankGrid() {
        for (int row = 0; row < hitoriGrid.length; row++) {
            for (int column = 0; column < hitoriGrid[row].length; column++) {
                hitoriGrid[row][column] = -1;
            }
        }
    }

    public void createGrid(Container gameContainer, JPanel gridContainer, JPanel optionsContainer) {

        int i = 0;
        int j = 0;
        for (int row = 0; row < 7; row++) {
            for (int column = 0; column < 7; column++) {
                if (row == 0 || row == 6 || column == 0 || column == 6) {
                    gridContainer.add(Box.createRigidArea(new Dimension()));
                } else {
                    JButton hitButtons = new HitoriButtons(Integer.toString(givenNumbers[j][i]), this, row - 1, column - 1);
                    hitButtons.setBorder(BorderFactory.createEtchedBorder());
                    gridContainer.add(hitButtons);
                    gridContainer.setBackground(new Color(206,149,255));
                    buttons[j][i] = (HitoriButtons) hitButtons;
                    i++;
                    if (i > 4) {
                        j++;
                        i = 0;
                    }
                }
            }
        }

        GridBagConstraints constraints = new GridBagConstraints();

        // How To Play Button
        JButton howToPlayHitori = new HowToPlayHitori("How to Play?");
        howToPlayHitori.setBackground(new Color(249, 238, 133));
        howToPlayHitori.setOpaque(true);
        howToPlayHitori.setFont(new Font("Monaco", Font.PLAIN, 14));
        howToPlayHitori.setBorder(BorderFactory.createEtchedBorder());
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.ipadx = 20;
        constraints.ipady = 20;
        constraints.insets = new Insets(30,20,30,20);
        optionsContainer.add(howToPlayHitori, constraints);

        // Solve button
        JButton solveHitori = new ASolver("Solve",this, hitoriGrid, possibleValues);
        solveHitori.setBackground(new Color(240,239,133));
        solveHitori.setOpaque(true);
        solveHitori.setFont(new Font("Monaco", Font.PLAIN, 14));
        solveHitori.setBorder(BorderFactory.createEtchedBorder());
        constraints.gridx = 0;
        constraints.gridy = 1;
        constraints.ipadx = 20;
        constraints.ipady = 20;
        constraints.insets = new Insets(30,20,30,20);
        optionsContainer.add(solveHitori, constraints);

        gameContainer.add(optionsContainer, BorderLayout.EAST);
        gameContainer.add(gridContainer, BorderLayout.CENTER);

    }

    public boolean selected(int row, int column) {
        if (hitoriGrid[row][column] == 1) {
            return true;
        } else {
            return false;
        }
    }

    public void setValue(int row, int column) {
        if (hitoriGrid[row][column] == 1) {
            hitoriGrid[row][column] = 0;
        } else {
            hitoriGrid[row][column] = 1;
        }
    }


    // HITORI SPECIFIC METHODS //

    private boolean crosswordConstraints() {
        int[][] clone = new int[hitoriGrid.length][hitoriGrid[0].length];
        for (int row = 0; row < clone.length; row++) {                              // for every row
            for (int column = 0; column < clone[row].length; column++) {            // and every column
                clone[row][column] = hitoriGrid[row][column];                       // value = hitori value
            }
        }
        if (hitoriGrid[0][0] != 1) {                                                // if it isn't pressed
            if (crosswordRecursion(clone, 0, 0)) {                      // of
                return true;
            } else {
                return false;
            }
        } else {
            if (crosswordRecursion(clone,0,1)) {
                return true;
            } else {
                return false;
            }
        }
    }

    // recursively calls itself on all connected squares. Marks a square if its
    // already "been" there by assigning it a value of 2.
    private boolean crosswordRecursion(int[][] clone, int row, int column) {
        clone[row][column] = 2;
        if (column - 1 >= 0 && clone[row][column - 1] < 1) {
            crosswordRecursion(clone, row, column - 1);
        }
        if (column + 1 < clone[row].length && clone[row][column + 1] < 1) {
            crosswordRecursion(clone, row, column + 1);
        }
        if (row - 1 >= 0 && clone[row - 1][column] < 1) {
            crosswordRecursion(clone, row - 1, column);
        }
        if (row + 1 < clone.length && clone[row + 1][column] < 1) {
            crosswordRecursion(clone, row + 1, column);
        }
        if (allCorrect(clone)) {
            return true;
        } else {
            return false;
        }


    }

    // compares theGrid and copy to see if all available squares
    // have been reached
    private boolean allCorrect(int[][] correct) {
        for (int row = 0; row < hitoriGrid.length; row++) {
            for (int column = 0; column < hitoriGrid.length; column++) {
                if (hitoriGrid[row][column] != 1 && correct[row][column] != 2) {
                    return false;
                }
            }
        }
        return true;
    }


    // checks adjacency constraint. Checks four corners first, then four sides,
    // then all the middle squares
    public boolean notBeside() {
        for (int row = 0; row < hitoriGrid.length; row++) {
            for (int column = 0; column < hitoriGrid[row].length; column ++) {
                int count = 0;
                if (row == 0 && column == 0) {
                    if (selected(row + 1, column) && selected(row, column)) {
                        count++;
                    }
                    if (selected(row,column + 1) && selected(row, column)) {
                        count++;
                    }
                } else if (row==0 && column ==hitoriGrid[row].length-1) {
                    if (selected(row,column -1) && selected(row,column)) {
                        count++;
                    }
                    if (selected(row+1,column ) && selected(row,column)) {
                        count++;
                    }
                } else if (row==hitoriGrid.length-1 && column ==0) {
                    if (selected(row-1,column ) && selected(row,column)) {
                        count++;
                    }
                    if (selected(row,column +1) && selected(row,column)) {
                        count++;
                    }
                } else if (row==hitoriGrid.length-1 && column ==hitoriGrid[row].length-1) {
                    if (selected(row-1,column ) && selected(row,column)) {
                        count++;
                    }
                    if (selected(row,column -1) && selected(row,column)) {
                        count++;
                    }
                } else if (row==0) {
                    if (selected(row,column -1) && selected(row,column)) {
                        count++;
                    }
                    if (selected(row,column +1) && selected(row,column)) {
                        count++;
                    }
                    if (selected(row+1,column ) && selected(row,column)) {
                        count++;
                    }
                } else if (row==hitoriGrid.length-1) {
                    if (selected(row,column -1) && selected(row,column)) {
                        count++;
                    }
                    if (selected(row,column +1) && selected(row,column)) {
                        count++;
                    }
                    if (selected(row-1,column ) && selected(row,column)) {
                        count++;
                    }
                } else if (column ==0) {
                    if (selected(row-1,column ) && selected(row,column)) {
                        count++;
                    }
                    if (selected(row+1,column ) && selected(row,column)) {
                        count++;
                    }
                    if (selected(row,column +1) && selected(row,column)) {
                        count++;
                    }
                } else if (column ==hitoriGrid[row].length-1) {
                    if (selected(row-1,column ) && selected(row,column)) {
                        count++;
                    }
                    if (selected(row+1,column ) && selected(row,column)) {
                        count++;
                    }
                    if (selected(row,column -1) && selected(row,column)) {
                        count++;
                    }
                } else {
                    if (selected(row,column -1) && selected(row,column)) {
                        count++;
                    }
                    if (selected(row,column +1) && selected(row,column)) {
                        count++;
                    }
                    if (selected(row+1,column ) && selected(row,column)) {
                        count++;
                    }
                    if (selected(row-1,column ) && selected(row,column)) {
                        count++;
                    }
                }
                if (count >= 1) {
                    return false;
                }
            }
        }
        return true;
    }

    // constraint checked by the solver
    public boolean check() {
        return notBeside();
    }

    public boolean completeCheck() {
        return solved();
    }

    public boolean solved() {
        for (int row = 0; row < hitoriGrid.length; row++) {
            int[] remaining = new int[hitoriGrid[row].length + 1];
            for (int column = 0; column < hitoriGrid[row].length; column++) {
                if (!selected(row, column)) {
                    remaining[givenNumbers[row][column]]++;
                }
            }

            for (int i = 0; i < remaining.length; i++) {
                if (remaining[i] > 1) {
                    return false;
                }
            }
        }

        for (int column = 0; column < hitoriGrid[0].length; column++) {
            int[] remaining = new int[hitoriGrid.length + 1];
            for (int row = 0; row < hitoriGrid.length; row++) {
                if (!selected(row, column)) {
                    remaining[givenNumbers[row][column]]++;
                }
            }
            for (int i = 0; i < remaining.length; i++) {
                if (remaining[i] > 1) {
                    return false;
                }
            }
        }
        return true && notBeside() && crosswordConstraints();
    }

    public void solveGrid() {
        for (int row = 0; row < hitoriGrid.length; row++) {
            for (int column = 0; column < hitoriGrid[row].length; column++) {
                if (selected(row, column)) {
                    buttons[row][column].solveAnswers();
                }
            }
        }
    }
}


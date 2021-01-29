import javax.swing.*;
import java.awt.*;

public class Skyscraper extends AGame {

    private int[][] skyGrid = new int[5][5];
    private int[] possibleValues =      {1, 2, 3, 4, 5};                            // numbers allowed
    private int[] topConstraints =      {5, 3, 2, 1, 2};                            // constraints gotta follow
    private int[] bottomConstraints =   {1, 2, 3, 4, 2};                            // ^
    private int[] leftConstraints =     {4, 3, 3, 2, 1};                            // ^
    private int[] rightConstraints =    {2, 3, 1, 3, 3};                            // ^
    private SkyscraperButtons[][] buttons = new SkyscraperButtons[5][5];            // make a grid of skyButtons
    private AFrame frame;

    public Skyscraper(AFrame f) {                                                   // Skyscraper method
        frame = f;
        this.blankGrid();                                                           // make the grid blank
    }

    // initializes all grid values to -1
    public void blankGrid() {                                                       // grid blank-er
        for (int row = 0; row < skyGrid.length; row++) {                            // for every row
            for (int column = 0; column < skyGrid[row].length; column++) {          // and every column
                skyGrid[row][column] = -1;                                          // the value of each button is -1
            }
        }
    }


    // double checked
    public boolean solved() {
        int[] rightVisible =   {0, 0, 0, 0, 0};                                     // initial values
        int[] leftVisible =    {0, 0, 0, 0, 0};                                     // ^
        int[] topVisible =     {0, 0, 0, 0, 0};                                     // ^
        int[] bottomVisible =  {0, 0, 0, 0, 0};                                     // ^


        //assigns values to topVisible
        for (int column = 0; column < skyGrid[0].length; column++) {                // for every column
            int tallestBuilding = 0;                                                // initialize tall building size
            for (int row = 0; row < skyGrid.length; row++) {                        // for every row
                if (skyGrid[row][column] > tallestBuilding) {                       // if value > tallest building
                    topVisible[column]++;                                           // increment topVisible
                    tallestBuilding = skyGrid[row][column];                         // tallest building = value
                }
            }
        }

        //assigns values to bottomVisible
        for (int column = 0; column < skyGrid[0].length; column++) {                // for every column
            int tallestBuilding = 0;                                                // initialize tall building
            for (int row = skyGrid.length-1; row >= 0; row--) {                     // for every row from the right
                if (skyGrid[row][column] > tallestBuilding) {                       // if value > tallest building
                    bottomVisible[column]++;                                        // increment bottomVisible
                    tallestBuilding = skyGrid[row][column];                         // tallest building = value
                }
            }
        }

        //assigns values to leftVisible
        for (int row = 0; row < skyGrid.length; row++) {                            // for every row
            int tallestBuilding = 0;                                                // initialize tall building
            for (int column = 0; column < skyGrid[row].length; column++) {          // for every column from the top
                if (skyGrid[row][column] > tallestBuilding) {                       // if value > tallest building
                    leftVisible[row]++;                                             // increment leftVisible
                    tallestBuilding = skyGrid[row][column];                         // tallest building = value
                }
            }
        }

        //assigns values to rightVisible
        for (int row = 0; row < skyGrid.length; row++) {                            // for every row
            int tallestBuilding = 0;                                                // initialize tall building
            for (int column = skyGrid[row].length-1; column >= 0; column--) {       // for every column from the bottom
                if (skyGrid[row][column] > tallestBuilding) {                       // if value > tallest building
                    rightVisible[row]++;                                            // increment rightVisible
                    tallestBuilding = skyGrid[row][column];                         // tallest building = value
                }
            }
        }

        // this compares the values of the constraints to the visible
        boolean solved = true;
        for (int i = 0; i < 5; i++) {
            if (topVisible[i]!=topConstraints[i] ||                                 // if topVis value not topCon
                    bottomVisible[i]!=bottomConstraints[i] ||                       // if bottomVis value not bottomCon
                    leftVisible[i]!=leftConstraints[i] ||                           // if leftVis value not leftVis
                    rightVisible[i]!=rightConstraints[i]) {                         // if rightVis value not rightVis
                solved = false;
            }
        }
        return solved && noRepeatedValue();
    }

    public void solveGrid() {                                                       // solve the grid!
        for (int row = 0; row < skyGrid.length; row++) {                            // for every row
            for (int column = 0; column < skyGrid[row].length; column++) {          // and every column
                buttons[row][column].solveAnswers();                                // solve them please
            }
        }
    }

    //Double checked
    public boolean check() {
        return checkTopConstraints() &&                                             // top constraint true
                checkBottomConstraints() &&                                         // bottom constraint true
                checkLeftConstraints() &&                                           // left constraint true
                checkRightConstraints() &&                                          // right constraint true
                noRepeatedValue();                                                  // no repeats!
    }

    public boolean completeCheck() {
        return solved();
    }

    // increments grid values      //     clicks up to 5, and then resets them back to -1
    public void setValue(int row, int column) {
        if (skyGrid[row][column] == -1) {                                           // if value = -1
            skyGrid[row][column] = 1;                                               // value = 1
        } else {                                                                    // or else
            skyGrid[row][column]++;                                                 // increment value
        }
        if (skyGrid[row][column] > 5) {                                             // if value > 5
            skyGrid[row][column] = -1;                                              // reinitialize = -1
        }
    }

    // returns current selected value
    public int getElement(int row, int column) {
        return skyGrid[row][column];
    }

    // checks that what is visible is less than the constraint
    private boolean checkTopConstraints() {                                         // from the top
        for (int column = 0; column < skyGrid[0].length; column++) {                // for every column
            int tallestBuilding = 0;                                                // initialize tallest building
            int visibleBuildings = 0;                                               // initialize visible buildings
            for (int row = 0; row < skyGrid.length; row++) {                        // for every row from the top
                if (skyGrid[row][column] > tallestBuilding) {                       // if building-height > tallest building
                    visibleBuildings++;                                             // increment visible buildings
                    tallestBuilding = skyGrid[row][column];                         // tallest building = height of building
                }
            }
            if (visibleBuildings > topConstraints[column]) {                        // if visible buildings > constraint
                return false;                                                       // false!
            }
        }
        return true;                                                                // otherwise is true!
    }

    // checks that what is visible is less than the constraint
    private boolean checkBottomConstraints() {                                      // from the bottom
        for (int column = 0; column < skyGrid[0].length; column++) {                // for every column
            int tallestBuilding = 0;                                                // initialize tallest building
            int visibleBuildings = 0;                                               // initialize visible buildings
            for (int row = skyGrid.length-1; row >= 0; row--) {                     // for every row from the bottom
                if (skyGrid[row][column] > tallestBuilding) {                       // if height of building > tallest
                    visibleBuildings++;                                             // increment visible buildings
                    tallestBuilding = skyGrid[row][column];                         // tallest building = height of building
                }
            }
            if (visibleBuildings > bottomConstraints[column]) {                     // if visible buildings > constraints
                return false;                                                       // false!
            }
        }
        return true;                                                                // otherwise is true!
    }

    // checks that what is visible is less than the constraint
    private boolean checkLeftConstraints() {                                        // from the left!
        for (int row = 0; row < skyGrid.length; row++) {                            // for every row
            int tallestBuilding = 0;                                                // initialize tall building
            int visibleBuildings = 0;                                               // initialize visible buildings
            for (int column = 0; column < skyGrid[row].length; column++) {          // for every column
                if (skyGrid[row][column] > tallestBuilding) {                       // if value > tallest building
                    visibleBuildings++;                                             // increment visible buildings
                    tallestBuilding = skyGrid[row][column];                         // tallest building is that value
                }
            }
            if (visibleBuildings > leftConstraints[row]) {                          // if visible buildings > constraint
                return false;                                                       // false!
            }
        }
        return true;                                                                // otherwise is true!
    }

    // checks that what is visible is less than the constraint
    private boolean checkRightConstraints() {                                       // for the right!
        for (int row = 0; row < skyGrid.length; row++) {                            // for every row
            int tallestBuilding = 0;                                                // initialize tallest building
            int visibleBuildings = 0;                                               // initialize visible buildings
            for (int column = skyGrid[row].length-1; column >= 0; column--) {       // for every column from the right
                if (skyGrid[row][column] > tallestBuilding) {                       // if height of building > tallest building
                    visibleBuildings++;                                             // incrememnt visible buildings
                    tallestBuilding = skyGrid[row][column];                         // tallest building = height of building
                }
            }
            if (visibleBuildings > rightConstraints[row] && skyGrid[row][skyGrid[row].length-1] != -1) {
                return false;
            }
        }
        return true;
    }

    // double checked
    private boolean noRepeatedValue() {
        for (int row = 0; row < skyGrid.length; row++) {
            int[] visibleBuildings = new int[6];
            for (int column = 0; column < skyGrid[row].length; column++) {
                if (skyGrid[row][column] != -1) {
                    visibleBuildings[skyGrid[row][column]]++;
                }
            }
            for (int i = 1; i < visibleBuildings.length; i++) {
                if (visibleBuildings[i] > 1) {
                    return false;
                }
            }
        }
        for (int column = 0; column < skyGrid[0].length; column++) {
            int[] visibleBuildings = new int[6];
            for (int row = 0; row < skyGrid.length; row++) {
                if (skyGrid[row][column] != -1) {
                    visibleBuildings[skyGrid[row][column]]++;
                }
            }
            for (int i = 1; i < visibleBuildings.length; i++) {
                if (visibleBuildings[i] > 1) {
                    return false;
                }
            }
        }
        return true;
    }

    // double checked
    // builds skyGrid for the GUI
    public void createGrid(Container gameContainer, JPanel centerContainer, JPanel optionsContainer) {

        int topConstraint = 0;
        int bottomConstraint = 0;
        int leftConstraint = 0;
        int rightConstraint = 0;
        for (int row = 0; row < 7; row++) {
            for (int column = 0; column < 7; column++) {
                if ((row==0 && column==0) ||
                        (row==0 && column==6) ||
                        (row==6 && column==0) ||
                        (row==6 && column==6)) {
                    centerContainer.add(Box.createRigidArea(new Dimension(5, 0)));
                } else if (row==0) {

                    JLabel target = new JLabel(Integer.toString(topConstraints[topConstraint]));
                    target.setHorizontalAlignment(JTextField.CENTER);
                    centerContainer.add(target);
                    topConstraint++;
                } else if (column==0) {
                    JLabel target = new JLabel(Integer.toString(leftConstraints[leftConstraint]));
                    target.setHorizontalAlignment(JTextField.CENTER);
                    centerContainer.add(target);
                    leftConstraint++;
                } else if (column==6) {
                    JLabel target = new JLabel(Integer.toString(rightConstraints[rightConstraint]));
                    target.setHorizontalAlignment(JTextField.CENTER);
                    centerContainer.add(target);
                    rightConstraint++;
                } else if (row==6) {
                    JLabel target = new JLabel(Integer.toString(bottomConstraints[bottomConstraint]));
                    target.setHorizontalAlignment(JTextField.CENTER);
                    centerContainer.add(target);
                    bottomConstraint++;
                } else {
                    JButton jb = new SkyscraperButtons(this, row-1, column-1);
                    centerContainer.add(jb);
                    buttons[row-1][column-1] = (SkyscraperButtons) jb;
                }
            }
        }

        GridBagConstraints constraints = new GridBagConstraints();

        // How To Play Button
        JButton howToPlaySkyscraper = new HowToPlaySkyscraper("How to Play?");
        howToPlaySkyscraper.setBackground(new Color(249, 238, 133));
        howToPlaySkyscraper.setOpaque(true);
        howToPlaySkyscraper.setFont(new Font("Monaco", Font.PLAIN, 14));
        howToPlaySkyscraper.setBorder(BorderFactory.createEtchedBorder());
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.ipadx = 20;
        constraints.ipady = 20;
        constraints.insets = new Insets(30,20,30,20);
        optionsContainer.add(howToPlaySkyscraper, constraints);

        JButton skySolve = new ASolver("Solve",this, skyGrid, possibleValues);
        skySolve.setBackground(new Color(249,238,133));
        skySolve.setOpaque(true);
        skySolve.setFont(new Font("Monaco", Font.PLAIN, 14));
        skySolve.setBorder(BorderFactory.createEtchedBorder());
        constraints.gridx = 0;
        constraints.gridy = 2;
        constraints.ipadx = 20;
        constraints.ipady = 20;
        constraints.insets = new Insets(30,20,30,20);
        optionsContainer.add(skySolve, constraints);


        gameContainer.add(optionsContainer, BorderLayout.EAST);
        gameContainer.add(centerContainer, BorderLayout.CENTER);
    }
}

/*
public class Skyscraper extends AGame {

    private int[][] skyGrid =           new int[5][5];                          //makes grid values, 5 x 5 sky game
    private int[] values =              {1, 2, 3, 4, 5};                        //possible values
    private int[] topConstraints =      {5, 3, 2, 1, 2};                        //hardcoded constraints (top)
    private int[] bottomConstraints =   {1, 2, 3, 4, 2};                        //hardcoded constraints (bottom)
    private int[] leftConstraints =     {4, 3, 3, 2, 1};                        //hardcoded constraints (left)
    private int[] rightConstraints =    {2, 3, 1, 3, 3};                        //hardcoded constraints (right)

    private SkyscraperButtons[][] buttons = new SkyscraperButtons[5][5];        //puts the buttons into a grid
    private AFrame frame;


    public Skyscraper(AFrame skyFrame) {                                        //method to call on the game
        frame = skyFrame;                                                       //frame is now the skyFrame
        this.blankGrid();                                                       //making the grid blank
    }

    // GAME METHODS //

    // double checked
    // sets all the values of the grid to -1
    public void blankGrid() {
        for (int row = 0; row < skyGrid.length; row++) {                        //for all rows
            for (int column = 0; column < skyGrid[row].length; column++) {      //and for all columns
                skyGrid[row][column] = -1;                                       //make em -1
            }
        }
    }

    // double checked
    // makes the grid and puts it into the gridContainer / center Container
    public void createGrid(Container gameContainer, JPanel gridContainer, JPanel optionsContainer) {

        int topConstraint =     0;
        int bottomConstraint =  0;
        int leftConstraint =    0;
        int rightConstraint =   0;

        for (int row = 0; row < 7; row++) {
            for (int column = 0; column < 7; column++) {
                if ((row == 0 && column == 0) || (row == 0 && column == 6) ||
                        (row == 6 && column == 0) || (row == 6) && (column == 6)) {
                    gridContainer.add(Box.createRigidArea(new Dimension(5, 5)));
                } else if (row == 0) {
                    JLabel number = new JLabel(Integer.toString(topConstraints[topConstraint]));
                    number.setFont(new Font("Monaco", Font.PLAIN, 16));
                    number.setHorizontalAlignment(SwingConstants.CENTER);
                    gridContainer.add(number);
                    topConstraint++;
                } else if (column == 0) {
                    JLabel number = new JLabel(Integer.toString(leftConstraints[leftConstraint]));
                    number.setFont(new Font("Monaco", Font.PLAIN, 16));
                    number.setHorizontalAlignment(SwingConstants.CENTER);
                    gridContainer.add(number);
                    leftConstraint++;
                } else if (row == 6) {
                    JLabel number = new JLabel(Integer.toString(bottomConstraints[bottomConstraint]));
                    number.setFont(new Font("Monaco", Font.PLAIN, 16));
                    number.setHorizontalAlignment(SwingConstants.CENTER);
                    gridContainer.add(number);
                    bottomConstraint++;
                } else if (column == 6) {
                    JLabel number = new JLabel(Integer.toString(rightConstraints[rightConstraint]));
                    number.setFont(new Font("Monaco", Font.PLAIN, 16));
                    number.setHorizontalAlignment(SwingConstants.CENTER);
                    gridContainer.add(number);
                    rightConstraint++;
                } else {
                    JButton skyButtons = new SkyscraperButtons(this, row - 1, column - 1);
                    gridContainer.add(skyButtons);
                    buttons[row - 1][column - 1] = (SkyscraperButtons) skyButtons;
                }
            }
        }

        optionsContainer.setBackground(new Color(117, 141, 255));
        GridBagConstraints constraints = new GridBagConstraints();

        // How To Play Button
        JButton howToPlay = new HowToPlayButton("How to Play?");
        howToPlay.setBackground(new Color(249, 238, 133));
        howToPlay.setOpaque(true);
        howToPlay.setFont(new Font("Monaco", Font.PLAIN, 14));
        howToPlay.setBorder(BorderFactory.createEtchedBorder());
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.ipadx = 20;
        constraints.ipady = 20;
        constraints.insets = new Insets(30,20,30,20);
        optionsContainer.add(howToPlay, constraints);

        // SOLVE BUTTON //
        JButton skySolve = new ASolver("Solve", this, skyGrid, values);
        skySolve.setBackground(new Color(249, 238, 133));
        skySolve.setOpaque(true);
        skySolve.setFont(new Font("Monaco", Font.PLAIN, 14));
        skySolve.setBorder(BorderFactory.createEtchedBorder());
        constraints.gridx = 0;
        constraints.gridy = 2;
        constraints.ipadx = 20;
        constraints.ipady = 20;
        constraints.insets = new Insets(30,20,30,20);
        optionsContainer.add(skySolve, constraints);

        gameContainer.add(gridContainer, BorderLayout.CENTER);
        gameContainer.add(optionsContainer, BorderLayout.EAST);
    }

    // double checked
    // getter method of values for skyscraperButtons
    public int getValue(int row, int column) {
        return skyGrid[row][column];
    }

    // double checked
    // increments the values of the buttons when you click on them. When it's >5 it restarts again
    public void setValue(int row, int column) {

        if (skyGrid[row][column] == -1) {                                       //if the value is -1
            skyGrid[row][column] = 1;                                           //it's now 1
        } else {                                                                //if it ain't 0
            skyGrid[row][column]++;                                             //make it increase
        }

        if (skyGrid[row][column] > 5) {                                         //if value is > 5
            skyGrid[row][column] = -1;                                          //it's now 0 again, repeat
        }
    }

    // SKYSCRAPER SPECIFIC METHODS //

    //double checked
    // checking if each row or column doesn't have a repeating value
    private boolean notSameNumber() {
        for (int row = 0; row < skyGrid.length; row++) {                        //in all rows
            int[] visible = new int[6];                                         //new int array of 6
            for (int column = 0; column < skyGrid[row].length; column++) {      //in all columns
                if (skyGrid[row][column] != -1) {                                //if value ain't 0
                    visible[skyGrid[row][column]]++;                            //increment
                }
            }
            for (int i = 1; i < visible.length; i++) {                          //
                if (visible[i] > 1) {
                    return false;
                }
            }
        }

        for (int column = 0; column < skyGrid[0].length; column++) {
            int[] visible = new int[6];
            for (int row = 0; row < skyGrid.length; row++) {
                if (skyGrid[row][column] != -1) {
                    visible[skyGrid[row][column]]++;
                }
            }
            for (int i = 1; i < visible.length; i++) {
                if (visible[i] > 1) {
                    return false;
                }
            }
        }
        return true;
    }

    // double checked
    // checks the top constraints       — that what is visible is less than what is constrained
    private boolean checkTopConstraints() {
        for (int column = 0; column < skyGrid[0].length; column++) {
            int tallestBuilding = 0;
            int visible = 0;
            for (int row = 0; row < skyGrid.length; row++) {
                if (skyGrid[row][column] > tallestBuilding) {
                    visible++;
                    tallestBuilding = skyGrid[row][column];
                }
            }
            if (visible > topConstraints[column]) {
                return false;
            }
        }
        return true;
    }

    // double checked
    // checks the bottom constraints    — that what is visible is less than what is constrained
    private boolean checkBottomConstraints() {
        for (int column = 0; column < skyGrid[0].length; column++) {
            int tallestBuilding = 0;
            int visible = 0;
            for (int row = skyGrid.length - 1; row >= 0; row--) {
                if (skyGrid[row][column] > tallestBuilding) {
                    visible++;
                    tallestBuilding = skyGrid[row][column];
                }
            }
            if (visible > bottomConstraints[column]) {
                return false;
            }
        }
        return true;
    }

    // double checked
    // checks the left constraints      — that what is visible is less than what is constrained
    private boolean checkLeftConstraints() {
        for (int row = 0; row < skyGrid.length; row++) {
            int tallestBuilding = 0;
            int visible = 0;
            for (int column = 0; column < skyGrid[row].length; column++) {
                if (skyGrid[row][column] > tallestBuilding) {
                    visible++;
                    tallestBuilding = skyGrid[row][column];
                }
            }
            if (visible > leftConstraints[row]) {
                return false;
            }
        }
        return true;
    }

    // double checked
    // checks the right constraints     — that what is visible is less than what is constrained
    private boolean checkRightConstraints() {
        for (int row = 0; row < skyGrid.length; row++) {
            int tallestBuilding = 0;
            int visible = 0;
            for (int column = skyGrid[row].length-1; column >= 0; column--) {
                if (skyGrid[row][column] > tallestBuilding) {
                    visible++;
                    tallestBuilding = skyGrid[row][column];
                }
            }
            if (visible > rightConstraints[row] && skyGrid[row][skyGrid[row].length-1] != 1) {
                return false;
            }
        }
        return true;
    }

    // GAME METHODS //

    // double checked
    public boolean solved() {
        int[] topVisible =      {0,0,0,0,0};
        int[] bottomVisible =   {0,0,0,0,0};
        int[] leftVisible =     {0,0,0,0,0};
        int[] rightVisible =    {0,0,0,0,0};

        // solve function for topVisible
        for (int column = 0; column < skyGrid[0].length; column++) {
            int tallestBuilding = 0;
            for (int row = 0; row < skyGrid.length; row++) {
                if (skyGrid[row][column] > tallestBuilding) {
                    topVisible[column]++;
                    tallestBuilding = skyGrid[row][column];
                }
            }
        }

        // solve function for bottomVisible
        for (int column = 0; column < skyGrid[0].length; column++) {
            int tallestBuilding = 0;
            for (int row = skyGrid.length-1; row >= 0; row--) {
                if (skyGrid[row][column] > tallestBuilding) {
                    bottomVisible[column]++;
                    tallestBuilding = skyGrid[row][column];
                }
            }
        }

        // solve function for leftVisible
        for (int row = 0; row > skyGrid.length; row++) {
            int tallestBuilding = 0;
            for (int column = 0; column < skyGrid[row].length; column++) {
                if (skyGrid[row][column] > tallestBuilding) {
                    leftVisible[row]++;
                    tallestBuilding = skyGrid[row][column];
                }
            }
        }

        // solve function for rightVisible
        for (int row = 0; row < skyGrid.length; row++) {
            int tallestBuilding = 0;
            for (int column = skyGrid[row].length-1; column >= 0; column--) {
                if (skyGrid[row][column] > tallestBuilding) {
                    rightVisible[row]++;
                    tallestBuilding = skyGrid[row][column];
                }
            }
        }

        boolean solved = true;
        for (int i = 0; i < 5; i++) {
            if (topVisible[i] != topConstraints[i] || bottomVisible[i] != bottomConstraints[i] ||
                    leftVisible[i] != leftConstraints[i] || rightVisible[i] != rightConstraints[i]) {
                solved = false;
            }
        }

        return solved && notSameNumber();
    }

    //Double checked
    public boolean check() {
        return notSameNumber() && checkTopConstraints() && checkBottomConstraints() && checkLeftConstraints() && checkRightConstraints();
    }

    //Double checked
    public boolean completeCheck() {
        return solved();
    }

    // double checked
    // when the solver is clicked, it'll use this to print out the answers
    public void solveGrid() {
        for (int row = 0; row < skyGrid.length; row++) {
            for (int column = 0; column < skyGrid[row].length; column++) {
                buttons[row][column].solveAnswers();
            }
        }
    }


}

 */

import javax.swing.*;
import javax.swing.plaf.basic.BasicBorders;
import java.awt.*;

public class AFrame extends JFrame {

    //shows the GUI and all the JFrame components
    public static void makeGUI() {

        AFrame frame = new AFrame();                                    //making the frame
        frame.setTitle("Allie's Game!");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);           //making the code stop when exiting

        frame.addToPane(frame.getContentPane());                        //get the content of the pane
        frame.setPreferredSize(new Dimension(750, 750));   //set preferred size of the window
        frame.pack();                                                   //sizes the elements to its preferred sizes
        frame.setLocationRelativeTo(null);                              //sets it to the middle of the screen
        frame.setVisible(true);                                         //setting it visible

        ImageIcon icon = new ImageIcon("sun.png");              //setting an icon for fun
        frame.setIconImage(icon.getImage());                            //getting the icon

        frame.repaint();                                                //this is responding to user input

    }


    public void addToPane(Container pane) {

        /*
         *      9 x 8
         *        LEFT CONTAINER                                               RIGHT CONTAINER
         *      +———————————————+—————————————————————————————————————————————+———————————————+
         *      |               |       |        ALLIE'S GAMES        |       |               |     TOP CONTAINER
         *      + - - - - - - - + - - - - - - - - - - - - - - - - - - - - - - + - - - - - - - +
         *      |               |       |  x  |  x  |  x  |  x  |  x  |       |               |
         *      +———————————————+ - - - +—————+—————+—————+—————+—————+ - - - +———————————————+
         *      |     HITORI    |   y   |     |     |     |     |     |   y   | How To Play?  |
         *      +———————————————+ - - - +—————+—————+—————+—————+—————+ - - - +———————————————+
         *      |               |   y   |     |     |     |     |     |   y   |               |
         *      +———————————————+ - - - +—————+—————+—————+—————+—————+ - - - +———————————————+
         *      |    KAKURASU   |   y   |     |CENTER  CONTAINER|     |   y   |     Check     |     //nah no more, no time
         *      +———————————————+ - - - +—————+—————+—————+—————+—————+ - - - +———————————————+
         *      |               |   y   |     |     |     |     |     |   y   |               |
         *      +———————————————+ - - - +—————+—————+—————+—————+—————+ - - - +———————————————+
         *      |   SKYSCRAPER  |   y   |     |     |     |     |     |   y   |     Solve     |
         *      +———————————————+ - - - +—————+—————+—————+—————+—————+ - - - +———————————————+
         *      |               |       |  x  |  x  |  x  |  x  |  x  |       |               |
         *      + - - - - - - - + - - - - - - - - - - - - - - - - - - - - - - + - - - - - - - +
         */

        Container frameContainer = getContentPane();
        frameContainer.setLayout(new BorderLayout());

        JPanel gridContainer = new JPanel(new GridLayout(7,7));
        JPanel gameContainer = new JPanel(new GridBagLayout());
        JPanel optionsContainer = new JPanel(new GridBagLayout());
        JPanel titleContainer = new JPanel(new GridBagLayout());
        JPanel infoContainer = new JPanel(new GridBagLayout());

        GridBagConstraints constraints = new GridBagConstraints();

        gameContainer.setBackground(new Color(117, 141, 255));


        ///// THIS IS THE TITLE AND THE TOP CONTAINER /////
        JLabel title;
        title = new JLabel("Allie's Games");
        title.setBackground(new Color(249, 125, 104));
        title.setOpaque(true);
        title.setBorder(BorderFactory.createBevelBorder(1));
        titleContainer.setBackground(new Color(117, 141, 255));
        title.setFont(new Font("Monaco", Font.PLAIN, 20));
        title.setForeground(new Color(52,83,203));
        title.setHorizontalAlignment(SwingConstants.CENTER);
        constraints.ipady = 30;
        constraints.ipadx = 50;
        constraints.insets = new Insets(30,20,30,20);
        titleContainer.add(title, constraints);

        // HITORI BUTTON //
        GameButton hitori = new GameButton("   Hitori   ", this, frameContainer,
                1, gridContainer, titleContainer, optionsContainer);
        hitori.setBackground(new Color(249,238,133));
        hitori.setOpaque(true);
        hitori.setFont(new Font("Monaco", Font.PLAIN, 14));
        hitori.setForeground(new Color(52,83,203));
        hitori.setBorder(BorderFactory.createEtchedBorder());
        constraints.ipady = 20;
        constraints.ipadx = 20;
        constraints.gridx = 0;
        constraints.gridy = 1;
        constraints.insets = new Insets(30,20,30,20);
        gameContainer.add(hitori, constraints);

        // SKYSCRAPER BUTTON //
        GameButton skyscraper = new GameButton(" Skyscraper ", this, frameContainer,
                2, gridContainer, titleContainer, optionsContainer);
        skyscraper.setBackground(new Color(249,238,133));
        skyscraper.setOpaque(true);
        skyscraper.setFont(new Font("Monaco", Font.PLAIN, 14));
        skyscraper.setForeground(new Color(52,83,203));
        skyscraper.setBorder(BorderFactory.createEtchedBorder());
        constraints.ipady = 20;
        constraints.ipadx = 20;
        constraints.gridx = 0;
        constraints.gridy = 3;
        constraints.insets = new Insets(20,20,20,20);
        gameContainer.add(skyscraper, constraints);

        // RIGHT SIDE //
        optionsContainer.setBackground(new Color(117, 141, 255));

        BernieButton meme1 = new BernieButton("  Bernie  ", this, frameContainer);
        meme1.setBackground(new Color(249,238,133));
        meme1.setOpaque(true);
        meme1.setFont(new Font("Monaco", Font.PLAIN, 14));
        meme1.setForeground(new Color(52,83,203));
        meme1.setBorder(BorderFactory.createEtchedBorder());
        constraints.ipadx = 32;
        constraints.ipady = 20;
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.insets = new Insets(30,20,30,20);
        optionsContainer.add(meme1, constraints);

        RagerButton meme2 = new RagerButton("  Rager  ", this, frameContainer);
        meme2.setBackground(new Color(249,238,133));
        meme2.setOpaque(true);
        meme2.setFont(new Font("Monaco", Font.PLAIN, 14));
        meme2.setForeground(new Color(52,83,203));
        meme2.setBorder(BorderFactory.createEtchedBorder());
        constraints.ipadx = 32;
        constraints.ipady = 20;
        constraints.gridx = 0;
        constraints.gridy = 1;
        constraints.insets = new Insets(30,20,30,20);
        optionsContainer.add(meme2, constraints);

        // SETTING BACKGROUND COLOR //
        gridContainer.setBackground(new Color(206,149,255));

        // TEMPORARY GAME CONTAINER STUFF //
        JLabel nothing = new JLabel(" ");
        nothing.setFont(new Font("Monaco", Font.PLAIN, 100));
        infoContainer.setBackground(new Color(117, 141, 255));
        infoContainer.add(nothing);



        // ADD CONTAINERS TO FRAME IN BORDER LAYOUT //
        frameContainer.add(titleContainer, BorderLayout.NORTH);
        frameContainer.add(gridContainer, BorderLayout.CENTER);
        frameContainer.add(gameContainer, BorderLayout.WEST);
        frameContainer.add(optionsContainer, BorderLayout.EAST);
        frameContainer.add(infoContainer, BorderLayout.SOUTH);

    }
}


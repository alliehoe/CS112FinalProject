import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HowToPlaySkyscraper extends JButton implements ActionListener {


    public HowToPlaySkyscraper(String text) {
        super(text);
        addActionListener(this);
    }


    public void actionPerformed(ActionEvent e) {

        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        frame.setVisible(true);
        frame.setSize(300,300);
        frame.setLocationRelativeTo(null);
        frame.setLayout(new BorderLayout());

        JPanel topCon = new JPanel();
        JPanel bottomCon = new JPanel();
        JPanel leftCon = new JPanel();
        JPanel rightCon = new JPanel();

        GridBagConstraints constraints = new GridBagConstraints();


        JLabel top = new JLabel(" ");
        constraints.ipady = 20;
        topCon.setBackground(new Color(52, 83, 203));
        topCon.setOpaque(true);
        topCon.add(top, constraints);
        frame.add(topCon, BorderLayout.NORTH);

        JLabel bottom = new JLabel(" ");
        constraints.ipady = 20;
        bottomCon.setBackground(new Color(52, 83, 203));
        bottomCon.setOpaque(true);
        bottomCon.add(bottom, constraints);
        frame.add(bottomCon, BorderLayout.SOUTH);

        JLabel left = new JLabel( " ");
        constraints.ipadx = 30;
        leftCon.setBackground(new Color(52, 83, 203));
        leftCon.setOpaque(true);
        leftCon.add(left, constraints);
        frame.add(leftCon, BorderLayout.WEST);

        JLabel right = new JLabel(" ");
        constraints.ipadx = 30;
        rightCon.setBackground(new Color(52, 83, 203));
        rightCon.setOpaque(true);
        rightCon.add(right, constraints);
        frame.add(rightCon, BorderLayout.EAST);

        JLabel skyInstructions = new JLabel("<html><div style='text-align: center;'>" +
                "SKYSCRAPER INSTRUCTIONS: <br/><br/>" +
                "Building heights range from 1 to 5, click on the grid to change building heights <br/><br/>" +
                "The numbers on the borders decide how many buildings are visible from each spot<br/><br/>" +
                "Each row and each column can't have duplicates of any building<br/><br/>" +
                "Now figure out how to solve the puzzle!</html>");
        skyInstructions.setFont(new Font("Monaco", Font.PLAIN, 11));
        skyInstructions.setBackground(new Color(250,178,103));
        skyInstructions.setOpaque(true);
        frame.add(skyInstructions, BorderLayout.CENTER);

        JOptionPane.setRootFrame(frame);
    }
}

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RagerButton extends JButton implements ActionListener {

    private AFrame frame;
    private Container container;

    public RagerButton(String word, AFrame f, Container cont) {
        super(word);
        addActionListener(this);


    }
    public void actionPerformed(ActionEvent e) {

        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        frame.setVisible(true);
        frame.setSize(420, 420);
        frame.setLocationRelativeTo(null);
        frame.setLayout(new BorderLayout());

        JPanel topCon = new JPanel();
        JPanel bottomCon = new JPanel();
        JPanel leftCon = new JPanel();
        JPanel rightCon = new JPanel();
        JPanel centerCon = new JPanel();

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

        JLabel left = new JLabel(" ");
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

        JLabel heyRager = new JLabel("<html><div style='text-align: center;'>" +
                "HEY PROFESSOR RAGER!<br/><br/>" +
                "I know that we haven't met in person so you don't know me well across a screen<br/>" +
                "But I just wanted to say, thanks for teaching and working over this short January term<br/>" +
                "I've definitely learned a lot in a short amount of time!<br/>" +
                "Unfortunately I've only got two games but my brain doesn't have any more power to figure out more...<br/>" +
                "I hope you have a good mini break and perhaps I'll see you sometime on campus soon!<br/><br/>" +
                "Cheers!<br/>" +
                "Allie<br/><br/>" +
                "P.S. Have fun with my puzzles!</html>");
        heyRager.setFont(new Font("Monaco", Font.PLAIN, 11));
        heyRager.setBackground(new Color(250, 178, 103));
        heyRager.setOpaque(true);
        frame.add(heyRager, BorderLayout.CENTER);

        JOptionPane.setRootFrame(frame);
    }
}

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BernieButton extends JButton implements ActionListener {

    private AFrame frame;
    private Container container;

    public BernieButton(String word, AFrame f, Container cont) {
        super(word);
        addActionListener(this);


    }
    public void actionPerformed(ActionEvent e) {

        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        frame.setVisible(true);
        frame.setSize(420,420);
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

        JLabel meme = new JLabel();
        meme.setIcon(new ImageIcon("bernieRager.png"));
        centerCon.add(meme);
        frame.add(centerCon, BorderLayout.CENTER);
        JOptionPane.setRootFrame(frame);
    }
}

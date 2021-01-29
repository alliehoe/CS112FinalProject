import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HitoriButtons extends JButton implements ActionListener {

    private Hitori hitori;
    private Color darken = new Color(116,203,254);
    private int row;
    private int column;

    public HitoriButtons(String text, Hitori hit, int ro, int co) {
        super(text);
        hitori = hit;
        row = ro;
        column = co;
        addActionListener(this);
    }

    public void actionPerformed(ActionEvent e) {
        hitori.setValue(row, column);
        if (hitori.selected(row, column)) {
            this.setBackground(darken);
            this.setBorder(BorderFactory.createEtchedBorder());
            this.setOpaque(true);
        } else {
            this.setBackground(new JButton().getBackground());
            this.setBackground(new Color(206,149,255));
            this.setBorder(BorderFactory.createEtchedBorder());

        }
        if (hitori.solved()) {
            JOptionPane.showMessageDialog(null, "Smartiepants, you solved hitori!");
        }
    }

    public void solveAnswers() {

        if (hitori.selected(row, column)) {
            this.setBackground(darken);
            this.setOpaque(true);
        } else {
            this.setBackground(new JButton().getBackground());
            this.setOpaque(true);
        }
    }
}

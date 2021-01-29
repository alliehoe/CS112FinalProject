import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class SkyscraperButtons extends JButton implements ActionListener {

    private int row;
    private int column;
    private Skyscraper s;

    public SkyscraperButtons(Skyscraper sky, int ro, int co) {
        s = sky;
        row = ro;
        column = co;
        addActionListener(this);
    }

    public void actionPerformed(ActionEvent e) {
        s.setValue(row, column);
        if (s.getElement(row, column) == -1) {
            this.setText("");
        } else if (s.getElement(row,column) > 0) {
            this.setText((Integer.toString(s.getElement(row, column))));
        }
        if (s.solved()) {
            JOptionPane.showMessageDialog(null, "Smartiepants, you solved the puzzle!");
        }
    }

    // should update the GUI with the answers
    public void solveAnswers() {
        if (s.getElement(row, column) == -1) {
            this.setText("");
        } else if (s.getElement(row, column) > 0) {
            this.setText(Integer.toString(s.getElement(row, column)));
        }
    }
}

import javax.swing.*;

/**
 * Created by Aditya on January 23, 2018.
 */
public class Window {
    private JPanel contentPane;
    private JLabel bestSentenceView;
    private JTextField input;

    public JTextField getInput() {
        return input;
    }

    public JButton getEvolveButton() {
        return evolveButton;
    }

    private JButton evolveButton;

    public JPanel getContentPane() {
        return contentPane;
    }

    public JLabel getBestSentenceView() {
        return bestSentenceView;
    }
}

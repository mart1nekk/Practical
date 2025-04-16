package GUI;

import javax.swing.*;

public class NoveOkno extends JFrame {
    public NoveOkno() {
        super("Nové okno");
        setSize(250, 150);
        setLayout(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE); // nezavře hlavní okno

        JLabel label = new JLabel("Tohle je nové okno");
        label.setBounds(50, 40, 150, 30);
        add(label);
    }
}

package GUI;

import javax.swing.*;

public class HlavniOkno {
    public static void main(String[] args) {
        JFrame hlavni = new JFrame("Hlavní okno");
        hlavni.setSize(300, 200);
        hlavni.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        hlavni.setLayout(null);

        JButton otevrit = new JButton("Otevři nové okno");
        otevrit.setBounds(60, 60, 160, 30);
        hlavni.add(otevrit);

        otevrit.addActionListener(e -> {
            NoveOkno noveOkno = new NoveOkno(); // vytvoří nové okno
            noveOkno.setVisible(true);          // zobrazí ho
        });

        hlavni.setVisible(true);
    }
}

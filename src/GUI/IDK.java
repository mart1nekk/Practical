package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class IDK extends JFrame {

    IDK() {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(400, 350);
        setTitle("Darování krve");
        setLayout(new BorderLayout());

        JPanel formPanel = new JPanel(new GridLayout(5, 2, 25, 20));

        // věk
        JLabel ageLabel = new JLabel("Věk");
        ageLabel.setFont(new Font("Arial", Font.BOLD, 17));
        JTextField ageField = new JTextField("20");
        formPanel.add(ageLabel);
        formPanel.add(ageField);

        // výška
        JLabel heightLabel = new JLabel("Výška");
        heightLabel.setFont(new Font("Arial", Font.BOLD, 17));
        JTextField heightField = new JTextField("185");
        formPanel.add(heightLabel);
        formPanel.add(heightField);

        // váha
        JLabel weightLabel = new JLabel("Váha");
        weightLabel.setFont(new Font("Arial", Font.BOLD, 17));
        JTextField weightField = new JTextField("90");
        formPanel.add(weightLabel);
        formPanel.add(weightField);

        // pohlaví
        JLabel genderLabel = new JLabel("Pohlaví");
        genderLabel.setFont(new Font("Arial", Font.BOLD, 17));
        JPanel panelForPohlavi = new JPanel(new FlowLayout(FlowLayout.LEFT));

        JRadioButton male = new JRadioButton("Muž");
        male.setFont(new Font("Arial", Font.BOLD, 17));
        JRadioButton female = new JRadioButton("Žena");
        female.setFont(new Font("Arial", Font.BOLD, 17));
        ButtonGroup group = new ButtonGroup();
        group.add(male);
        group.add(female);
        panelForPohlavi.add(male);
        panelForPohlavi.add(female);

        formPanel.add(genderLabel);
        formPanel.add(panelForPohlavi);

        // poslední darování
        JLabel lastDonationLabel = new JLabel("Poslední darování");
        lastDonationLabel.setFont(new Font("Arial", Font.BOLD, 17));
        JTextField donationField = new JTextField("01.04.2024");
        formPanel.add(lastDonationLabel);
        formPanel.add(donationField);

        add(formPanel, BorderLayout.CENTER);

        JButton submit = new JButton("Mohu dnes darovat?");
        submit.setFont(new Font("Arial", Font.BOLD, 17));
        add(submit, BorderLayout.SOUTH);

        submit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String message = "Nemůžete darovat";

                try {
                    int vek = Integer.parseInt(ageField.getText());
                    int vaha = Integer.parseInt(weightField.getText());

                    if ((vek >= 18 && vek <= 65) && (vaha >= 50 && vaha <= 190)) {
                        LocalDate dnesniDatum = LocalDate.now();
                        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
                        LocalDate posledniDarovani = LocalDate.parse(donationField.getText(), formatter);

                        long dnyOdPosledniho = ChronoUnit.DAYS.between(posledniDarovani, dnesniDatum);

                        if (male.isSelected() && dnyOdPosledniho >= 90) {
                            message = "Můžete darovat";
                        } else if (female.isSelected() && dnyOdPosledniho >= 120) {
                            message = "Můžete darovat";
                        } else {
                            message = "Ještě musíte počkat";
                        }
                    }

                } catch (Exception ex) {
                    message = "Chybný vstup – zkontrolujte čísla a datum!";
                }

                JOptionPane.showMessageDialog(submit, message, "Výsledek", JOptionPane.INFORMATION_MESSAGE);
            }
        });
    }

    public static void main(String[] args) {
        new IDK().setVisible(true);
    }
}

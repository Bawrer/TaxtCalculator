import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

public class TaxCalculatorUI {

    private JTextField incomeField;
    private JTextArea resultArea;

    public TaxCalculatorUI() {
        // Create the JFrame
        JFrame frame = new JFrame("Tax Calculator");
        frame.setSize(500, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Create a label for the image
        ImageIcon icon = createImageIcon("TAX-JUDGMENTS-ARE-NO-ORDINARY-JUDGMENTS-660x440-1.jpg");

        if (icon != null) {
            JLabel imageLabel = new JLabel(icon);
            // Set preferred size for the image label
            imageLabel.setPreferredSize(new Dimension(300, 150));

            // Create the components
            JLabel incomeLabel = new JLabel("Enter your annual income:");
            incomeField = new JTextField(10);
            JButton calculateButton = new JButton("Calculate Tax");
            resultArea = new JTextArea(5, 20);
            resultArea.setEditable(false);

            // Create a panel with GridBagLayout
            JPanel panel = new JPanel(new GridBagLayout());
            GridBagConstraints gbc = new GridBagConstraints();

            // Add components to the panel with GridBagConstraints
            gbc.gridx = 0;
            gbc.gridy = 0;
            gbc.gridwidth = 2;
            panel.add(imageLabel, gbc);

            gbc.gridx = 0;
            gbc.gridy = 1;
            gbc.gridwidth = 1;
            panel.add(incomeLabel, gbc);

            gbc.gridx = 1;
            panel.add(incomeField, gbc);

            gbc.gridx = 0;
            gbc.gridy = 2;
            gbc.gridwidth = 2;  // Span across 2 columns
            panel.add(calculateButton, gbc);

            gbc.gridx = 0;
            gbc.gridy = 3;
            gbc.gridwidth = 3;  // Span across 3 columns
            panel.add(resultArea, gbc);

            // Add action listener for the calculate button
            calculateButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    calculateTaxAndUpdateUI();
                }
            });

            // Add the panel to the frame
            frame.add(panel);

            // Set the frame visibility
            frame.setVisible(true);
        } else {
            System.out.println("Error loading image. Please check the image path.");
        }
    }

    private void calculateTaxAndUpdateUI() {
        try {
            // Get user input for annual income
            double annualIncome = Double.parseDouble(incomeField.getText());

            // Calculate tax obligation based on tax brackets
            double taxObligation = calculateTaxBasedOnBrackets(annualIncome);

            // Display the result
            resultArea.setText("Your tax obligation is: $" + taxObligation);
        } catch (NumberFormatException ex) {
            resultArea.setText("Invalid input. Please enter a valid numeric value for annual income.");
        }
    }

    // Tax calculation method based on specified brackets and rates
    private double calculateTaxBasedOnBrackets(double annualIncome) {
        if (annualIncome <= 237100) {
            return 0.18 * annualIncome;
        } else if (annualIncome <= 370500) {
            return 42678 + 0.26 * (annualIncome - 237100);
        } else if (annualIncome <= 512800) {
            return 77362 + 0.31 * (annualIncome - 370500);
        } else if (annualIncome <= 673000) {
            return 121475 + 0.36 * (annualIncome - 512800);
        } else if (annualIncome <= 857900) {
            return 179147 + 0.39 * (annualIncome - 673000);
        } else if (annualIncome <= 1817000) {
            return 251258 + 0.41 * (annualIncome - 857900);
        } else {
            return 644489 + 0.45 * (annualIncome - 1817000);
        }
    }

    private ImageIcon createImageIcon(String path) {
        URL imgURL = getClass().getResource(path);
        if (imgURL != null) {
            return new ImageIcon(imgURL);
        } else {
            System.err.println("Could not find image: " + path);
            return null;
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new TaxCalculatorUI();
            }
        });
    }
}

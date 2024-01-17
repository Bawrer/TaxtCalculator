import java.awt.*;
import javax.swing.*;

public class TaxCalculatorUI {

    private JTextField taxableIncomeField;
    private JTextField ageField;
    private JComboBox<String> yearComboBox;
    private JTextArea resultArea;

    public TaxCalculatorUI() {
        JFrame frame = new JFrame("Income Tax and Payroll Calculator");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);

        // Set the background color for the main content pane
        frame.getContentPane().setBackground(Color.GRAY);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new GridBagLayout());
        mainPanel.setBackground(Color.GRAY); // Set panel background color
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20)); // Add padding

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.insets = new Insets(5, 5, 5, 5);

        mainPanel.add(new JLabel("Tax Year:"), gbc);

        gbc.gridx = 1;
        yearComboBox = new JComboBox<>(new String[]{"2022", "2023", "2024"});
        mainPanel.add(yearComboBox, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        mainPanel.add(new JLabel("Taxable Income:"), gbc);

        gbc.gridx = 1;
        taxableIncomeField = new JTextField(15);
        mainPanel.add(taxableIncomeField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        mainPanel.add(new JLabel("Age:"), gbc);

        gbc.gridx = 1;
        ageField = new JTextField(15);
        mainPanel.add(ageField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2; // Span across 2 columns
        JButton calculateButton = new JButton("Calculate");
        calculateButton.addActionListener(e -> calculateTax());
        calculateButton.setBackground(new Color(46, 154, 254)); // Set button background color
        calculateButton.setForeground(Color.WHITE); // Set button text color
        mainPanel.add(calculateButton, gbc);

        gbc.gridy = 4;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weighty = 1.0; // Allow vertical expansion
        resultArea = new JTextArea(10, 30);
        resultArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(resultArea);
        mainPanel.add(scrollPane, gbc);

        // Set the main panel's background color
        mainPanel.setBackground(Color.GRAY);

        frame.getContentPane().add(BorderLayout.CENTER, mainPanel);
        frame.setVisible(true);
    }

    private void calculateTax() {
        try {
            double taxableIncome = Double.parseDouble(taxableIncomeField.getText().trim());
            int age = Integer.parseInt(ageField.getText().trim());
            int year = Integer.parseInt((String) yearComboBox.getSelectedItem());

            if (taxableIncome < 0 || age < 0) {
                resultArea.setText("Please enter non-negative values.");
                return;
            }

            double taxThreshold = getTaxThreshold(age, year);

            if (taxableIncome > taxThreshold) {
                double paye = calculateIncomeTax(taxableIncome, year, age);
                double ageRebate = calculateAgeRebate(age, year);
                double afterRebateTax = paye - ageRebate;

                StringBuilder result = new StringBuilder();
                result.append("Taxable income for the year: R").append(String.format("%.2f", taxableIncome)).append("\n");
                result.append("Tax you will pay / PAYE: R").append(String.format("%.2f", paye)).append("\n");
                result.append("Age-based rebate: R").append(String.format("%.2f", ageRebate)).append("\n");
                result.append("Tax after age-based rebate: R").append(String.format("%.2f", afterRebateTax)).append("\n");

                if (age < 65) {
                    result.append("You qualify for the primary rebate.");
                } else if (age >= 65 && age < 75) {
                    result.append("You qualify for the primary and secondary rebates.");
                } else {
                    result.append("You qualify for the primary, secondary, and tertiary rebates.");
                }

                resultArea.setText(result.toString());
            } else {
                resultArea.setText("No tax payable or income below the tax threshold.");
            }

        } catch (NumberFormatException ex) {
            resultArea.setText("Please enter valid numeric values.");
        }
    }

    private double calculateIncomeTax(double taxableIncome, int year, int age) {
        double incomeTax = 0;

        if (year == 2024) {
            // Tax calculation logic for the year 2024
            if (taxableIncome <= 237100) {
                incomeTax = 0.18 * taxableIncome;
            } else if (taxableIncome <= 370500) {
                incomeTax = 42678 + 0.26 * (taxableIncome - 237100);
            } else if (taxableIncome <= 512800) {
                incomeTax = 77682 + 0.31 * (taxableIncome - 370500);
            } else if (taxableIncome <= 673000) {
                incomeTax = 115739 + 0.36 * (taxableIncome - 512800);
            } else if (taxableIncome <= 857900) {
                incomeTax = 179379 + 0.39 * (taxableIncome - 673000);
            } else if (taxableIncome <= 1817000) {
                incomeTax = 253676 + 0.41 * (taxableIncome - 857900);
            } else {
                incomeTax = 644689 + 0.45 * (taxableIncome - 1817000);
            }
        } else if (year == 2023) {
            // Similar tax calculation logic for the year 2023
            if (taxableIncome <= 226000) {
                incomeTax = 0.18 * taxableIncome;
            } else if (taxableIncome <= 353100) {
                incomeTax = 40680 + 0.26 * (taxableIncome - 226000);
            } else if (taxableIncome <= 488700) {
                incomeTax = 73726 + 0.31 * (taxableIncome - 353100);
            } else if (taxableIncome <= 641400) {
                incomeTax = 115762 + 0.36 * (taxableIncome - 488700);
            } else if (taxableIncome <= 817600) {
                incomeTax = 170734 + 0.39 * (taxableIncome - 641400);
            } else if (taxableIncome <= 1731600) {
                incomeTax = 239452 + 0.41 * (taxableIncome - 817600);
            } else {
                incomeTax = 614192 + 0.45 * (taxableIncome - 1731600);
            }
        } else if (year == 2022) {
            // Similar tax calculation logic for the year 2022
            if (taxableIncome <= 216200) {
                incomeTax = 0.18 * taxableIncome;
            } else if (taxableIncome <= 337800) {
                incomeTax = 38916 + 0.26 * (taxableIncome - 216200);
            } else if (taxableIncome <= 467500) {
                incomeTax = 70532 + 0.31 * (taxableIncome - 337800);
            } else if (taxableIncome <= 613600) {
                incomeTax = 110739 + 0.36 * (taxableIncome - 467500);
            } else if (taxableIncome <= 782200) {
                incomeTax = 163335 + 0.39 * (taxableIncome - 613600);
            } else if (taxableIncome <= 1656600) {
                incomeTax = 229089 + 0.41 * (taxableIncome - 782200);
            } else {
                incomeTax = 645089 + 0.45 * (taxableIncome - 1656600);
            }
        } else {
            throw new IllegalArgumentException("Invalid tax year");
        }

        return incomeTax;
    }

    private double calculateAgeRebate(int age, int year) {
        double primaryRebate;
        double secondaryRebate;
        double tertiaryRebate;

        // Set rebate values based on the tax year
        if (year == 2024) {
            primaryRebate = 17235;
            secondaryRebate = 9444;
            tertiaryRebate = 3145;
        } else if (year == 2023) {
            primaryRebate = 16425;
            secondaryRebate = 9000;
            tertiaryRebate = 2997;
        } else if (year == 2022) {
            primaryRebate = 15714;
            secondaryRebate = 8613;
            tertiaryRebate = 2871;
        } else {
            throw new IllegalArgumentException("Invalid tax year");
        }

        // Apply age-based rebates
        if (age < 65) {
            return primaryRebate;
        } else if (age >= 65 && age < 75) {
            return (primaryRebate + secondaryRebate);
        } else {
            return (primaryRebate + secondaryRebate + tertiaryRebate);
        }
    }

    private double getTaxThreshold(int age, int year) {
        double threshold;

        // Set tax thresholds based on the tax year and age group
        if (year == 2024) {
            if (age < 65) {
                threshold = 95750;
            } else if (age >= 65 && age < 75) {
                threshold = 148217;
            } else {
                threshold = 165689;
            }
        } else if (year == 2023) {
            if (age < 65) {
                threshold = 95259;
            } else if (age >= 65 && age < 75) {
                threshold = 147667;
            } else {
                threshold = 165150;
            }
        } else if (year == 2022) {
            if (age < 65) {
                threshold = 90561;
            } else if (age >= 65 && age < 75) {
                threshold = 140758;
            } else {
                threshold = 157799;
            }
        } else {
            throw new IllegalArgumentException("Invalid tax year");
        }

        return threshold;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new TaxCalculatorUI());
    }
}

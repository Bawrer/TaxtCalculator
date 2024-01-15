import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TaxCalculatorUI extends JFrame {

    private JComboBox<String> yearComboBox;
    private JTextField incomeTextField;
    private JTextField ageTextField;
    private JTextArea resultTextArea;

    public TaxCalculatorUI() {
        initializeComponents();
    }

    private void initializeComponents() {
        setTitle("Income Tax and Payroll Calculator");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Create form elements
        JLabel yearLabel = new JLabel("Tax Year:");
        yearComboBox = new JComboBox<>(new String[]{"2022", "2023", "2024"});

        JLabel incomeLabel = new JLabel("Taxable Income:");
        incomeTextField = new JTextField();

        JLabel ageLabel = new JLabel("Age:");
        ageTextField = new JTextField();

        JButton calculateButton = new JButton("Calculate");

        // Create layout
        JPanel inputPanel = new JPanel(new GridLayout(4, 2, 10, 10));
        inputPanel.add(yearLabel);
        inputPanel.add(yearComboBox);
        inputPanel.add(incomeLabel);
        inputPanel.add(incomeTextField);
        inputPanel.add(ageLabel);
        inputPanel.add(ageTextField);
        inputPanel.add(new JLabel()); // Empty label for spacing
        inputPanel.add(calculateButton);

        // Create result display area
        resultTextArea = new JTextArea();
        resultTextArea.setEditable(false);

        // Set actions for the Calculate button
        calculateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                calculateButtonClicked();
            }
        });

        // Create main layout
        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        mainPanel.add(inputPanel, BorderLayout.NORTH);
        mainPanel.add(new JScrollPane(resultTextArea), BorderLayout.CENTER);

        add(mainPanel);
    }

    private void calculateButtonClicked() {
        try {
            int taxYear = Integer.parseInt((String) yearComboBox.getSelectedItem());
            double taxableIncome = Double.parseDouble(incomeTextField.getText());
            int age = Integer.parseInt(ageTextField.getText());

            // Check if eligible to pay tax
            if (taxableIncome >= getTaxThreshold(age, taxYear)) {
                // Calculate PAYE based on the provided PAYE tables
                double paye = calculateIncomeTax(taxableIncome, taxYear, age);

                // Calculate age-based rebate
                double ageRebate = calculateAgeRebate(age, taxYear);

                // Display results
                resultTextArea.setText(String.format("Taxable income for the year: R%.2f\n", taxableIncome) +
                        String.format("Tax you will pay / PAYE: R%.2f\n", paye) +
                        String.format("Age-based rebate: R%.2f\n", ageRebate) +
                        String.format("Tax after age-based rebate: R%.2f\n", paye - ageRebate) +
                        (age < 65 ? "You qualify for the primary rebate." :
                                (age < 75 ? "You qualify for the primary and secondary rebates." :
                                        "You qualify for the primary, secondary, and tertiary rebates.")));
            } else {
                resultTextArea.setText("No tax payable. You do not qualify for any rebates.");
            }
        } catch (NumberFormatException ex) {
            resultTextArea.setText("Invalid input. Please enter valid numbers.");
        } catch (IllegalArgumentException ex) {
            resultTextArea.setText(ex.getMessage());
        }
    }

    public static double calculateIncomeTax(double taxableIncome, int year, int age) {
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

    public static double calculateAgeRebate(int age, int year) {
        double primaryRebate;
        double secondaryRebate;
        double tertiaryRebate;

        // Your age rebate logic for different years
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

    public static double getTaxThreshold(int age, int year) {
        double threshold;
        
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
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new TaxCalculatorUI().setVisible(true);
            }
        });
    }
}

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TaxCalculatorUI {

    private JTextField taxableIncomeField;
    private JTextField ageField;
    private JComboBox<String> yearComboBox;
    private JTextArea resultArea;

    public TaxCalculatorUI() {
        JFrame frame = new JFrame("Income Tax and Payroll Calculator");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayout(5, 2));

        mainPanel.add(new JLabel("Tax Year:"));
        String[] years = {"2022", "2023", "2024"};
        yearComboBox = new JComboBox<>(years);
        mainPanel.add(yearComboBox);

        mainPanel.add(new JLabel("Taxable Income:"));
        taxableIncomeField = new JTextField();
        mainPanel.add(taxableIncomeField);

        mainPanel.add(new JLabel("Age:"));
        ageField = new JTextField();
        mainPanel.add(ageField);

        JButton calculateButton = new JButton("Calculate");
        calculateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                calculateTax();
            }
        });

        mainPanel.add(calculateButton);

        resultArea = new JTextArea();
        resultArea.setEditable(false);
        mainPanel.add(resultArea);

        frame.getContentPane().add(BorderLayout.CENTER, mainPanel);
        frame.setVisible(true);
    }

    private void calculateTax() {
        try {
            double taxableIncome = Double.parseDouble(taxableIncomeField.getText());
            int age = Integer.parseInt(ageField.getText());
            int year = Integer.parseInt((String) yearComboBox.getSelectedItem());

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

        } catch (NumberFormatException ex) {
            resultArea.setText("Please enter valid numeric values.");
        }
    }

    private double calculateIncomeTax(double taxableIncome, int year, int age) {
        // Your existing tax calculation logic here
        // ...

        return 0; // Placeholder, replace with the actual calculation
    }

    private double calculateAgeRebate(int age, int year) {
        // Your existing age rebate calculation logic here
        // ...

        return 0; // Placeholder, replace with the actual calculation
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

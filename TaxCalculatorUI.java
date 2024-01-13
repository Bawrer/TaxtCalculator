import java.util.Scanner;

public class TaxCalculatorUI {

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

        double totalRebate = 0;

        // Apply age-based rebates
        if (age < 65) {
            totalRebate += primaryRebate;
        }
        if (age >= 65 && age < 75) {
            totalRebate += (primaryRebate+secondaryRebate);
        }
        if (age >= 75) {
            totalRebate += (primaryRebate+secondaryRebate+tertiaryRebate);
        }

        return totalRebate;
    }

    public static double getTaxThreshold(int age, int year) {
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
        Scanner scanner = new Scanner(System.in);

        System.out.println("Income Tax and Payroll Calculator");

        // Input section
        System.out.println("Enter the tax year (2022, 2023, or 2024): ");
        int taxYear = scanner.nextInt();

        System.out.println("Enter your annual gross salary: ");
        double grossSalary = scanner.nextDouble();

        System.out.println("Enter your pension/provident/RAF (limited to 27.5% of salary, limited to R350k): ");
        double pension = Math.min(scanner.nextDouble(), 0.275 * grossSalary);
        pension = Math.min(pension, 350000); // Limit to R350k

        System.out.println("Enter your travel allowance: ");
        double travelAllowance = scanner.nextDouble();

        System.out.println("Enter your age: ");
        int age = scanner.nextInt();

        // Calculate taxable income
        double taxableIncome = grossSalary - pension - 0.2 * travelAllowance;

        // Check if the taxpayer meets the requirements to pay tax
        if (taxableIncome >= getTaxThreshold(age, taxYear)) {
            // Calculate PAYE based on the provided PAYE tables
            double paye = calculateIncomeTax(taxableIncome, taxYear, age);

            // Calculate age-based rebate
            double ageRebate = calculateAgeRebate(age, taxYear);

            // Subtract age-based rebate from the calculated tax
            double afterRebateTax = paye - ageRebate;

            // Calculate UIF (Unemployment Insurance Fund)
            double uif = Math.min(0.01 * grossSalary, 177.12); // UIF capped at R177.12

            // Calculate take-home pay after subtracting age-based rebate and UIF
            double takeHomePay = grossSalary - afterRebateTax - uif;

            // Display results
            System.out.printf("Taxable income for the year: R%.2f%n", taxableIncome);
            System.out.printf("Tax you will pay / PAYE: R%.2f%n", paye);
            System.out.printf("Age-based rebate subtracted: R%.2f%n", ageRebate);
            System.out.printf("Take home pay: R%.2f per year%n", takeHomePay);
        } else {
            // If taxable income is below the threshold, subtract UIF from the gross salary
            double takeHomePay = grossSalary - Math.min(0.01 * grossSalary, 177.12);
            System.out.printf("No tax payable. Take home pay: R%.2f per year%n", takeHomePay);
        }

        // Close the scanner to avoid resource leaks
        scanner.close();
    }
}

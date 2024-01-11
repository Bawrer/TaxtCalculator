import java.util.Scanner;

public class TaxCalculatorUI {

    public static double calculateIncomeTax(double taxableIncome, int year, int age) {
        double incomeTax = 0;

        if (year == 2024) {
            // Tax calculation logic for the year 2024
            // (Please replace this with the updated logic if needed)
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
                return 0.18 * taxableIncome;
            } else if (taxableIncome <= 337800) {
                return 38916 + 0.26 * (taxableIncome - 216200);
            } else if (taxableIncome <= 467500) {
                return 70532 + 0.31 * (taxableIncome - 337800);
            } else if (taxableIncome <= 613600) {
                return 110739 + 0.36 * (taxableIncome - 467500);
            } else if (taxableIncome <= 782200) {
                return 163335 + 0.39 * (taxableIncome - 613600);
            } else if (taxableIncome <= 1656600) {
                return 229089 + 0.41 * (taxableIncome - 782200);
            // ...
        } else {
            throw new IllegalArgumentException("Invalid tax year");
        }

        return incomeTax;
    
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

        // Apply age-based rebates
        if (age < 65) {
            return primaryRebate;
        } else if (age >= 65 && age < 75) {
            return secondaryRebate;
        } else {
            return tertiaryRebate;
        }
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
            // Similar logic for the year 2023
            // ...
        } else if (year == 2022) {
            // Similar logic for the year 2022
            // ...
        } else {
            throw new IllegalArgumentException("Invalid tax year");
        }

        return threshold;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Income Tax Calculator");

        // Enter tax year
        System.out.println("Enter the tax year (2022, 2023, or 2024): ");
        int taxYear = scanner.nextInt();

        // Enter annual income
        System.out.println("Enter your annual income: ");
        double taxableIncome = scanner.nextDouble();

        // Enter age
        System.out.println("Enter your age: ");
        int age = scanner.nextInt();

        // Check if the person is eligible to pay tax
        double taxThreshold = getTaxThreshold(age, taxYear);
        if (taxableIncome >= taxThreshold) {
            // Calculate income tax
            double incomeTax = calculateIncomeTax(taxableIncome, taxYear, age);

            // Calculate age-based rebate
            double ageRebate = calculateAgeRebate(age, taxYear);

            // Display results
            System.out.printf("Income Tax: R%.2f%n", incomeTax);
            System.out.printf("Age-based Rebate: R%.2f%n", ageRebate);
        } else {
            System.out.println("No tax payable or income below the tax threshold.");
        }

        // Close the scanner to avoid resource leaks
        scanner.close();
    }
}

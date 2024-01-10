import java.util.Scanner;

public class TaxCalculatorUI {

    public static double calculateIncomeTax(double taxableIncome, int year) {
        double[][] taxBrackets;
        if (year == 2024) {
            taxBrackets = new double[][]{
                    {237100, 0.18, 0},
                    {370500, 0.26, 42678},
                    {512800, 0.31, 77362},
                    {673000, 0.36, 121475},
                    {857900, 0.39, 179147},
                    {1817000, 0.41, 251258},
                    {Double.POSITIVE_INFINITY, 0.45, 644489}
            };
        } else if (year == 2023) {
            taxBrackets = new double[][]{
                    {226000, 0.18, 0},
                    {353100, 0.26, 40680},
                    {488700, 0.31, 73726},
                    {641400, 0.36, 115762},
                    {817600, 0.39, 170734},
                    {1731600, 0.41, 239452},
                    {Double.POSITIVE_INFINITY, 0.45, 614192}
            };
        } else if (year == 2022) {
            taxBrackets = new double[][]{
                    {216200, 0.18, 0},
                    {337800, 0.26, 38916},
                    {467500, 0.31, 70532},
                    {613600, 0.36, 110739},
                    {782200, 0.39, 163335},
                    {1656600, 0.41, 229089},
                    {Double.POSITIVE_INFINITY, 0.45, 587593}
            };
        } else {
            throw new IllegalArgumentException("Invalid tax year");
        }

        // Calculate income tax
        double incomeTax = 0;
        double remainingIncome = taxableIncome;

        for (double[] bracket : taxBrackets) {
            double limit = bracket[0];
            double rate = bracket[1];
            double base = bracket[2];

            if (remainingIncome <= limit) {
                incomeTax += base + rate * remainingIncome;
                break;
            } else {
                incomeTax += base + rate * (limit - (remainingIncome - 1));
                remainingIncome -= limit;
            }
        }

        return incomeTax;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Income Tax Calculator");

        // Choose tax year
        System.out.println("Choose the tax year (2022, 2023, or 2024): ");
        int taxYear = scanner.nextInt();

        // Enter annual income
        System.out.println("Enter your annual income: ");
        double taxableIncome = scanner.nextDouble();

        // Calculate and display income tax only
        double incomeTax = calculateIncomeTax(taxableIncome, taxYear);
        System.out.printf("Income Tax: R%.2f%n", incomeTax);

        // Close the scanner to avoid resource leaks
        scanner.close();
    }
}

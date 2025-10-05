package com.calculator.app;

import com.calculator.math_lib.MathLib;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		MathLib mathLib = new MathLib();
		Scanner sc = new Scanner(System.in);


		while(true) {
			System.out.println("----------------------Scientific Calculator Menu-------------------");
			System.out.println("1. Square Root");
			System.out.println("2. Factorial");
			System.out.println("3. Natural Logarithm");
			System.out.println("4. Power Function");
			System.out.println("5. Exit");
			System.out.print("Enter choice: ");

			int shd_break = 0;
			int choice = sc.nextInt();

			try {
				switch (choice) {
					case 1:
						System.out.print("Enter number: ");
						double x1 = sc.nextDouble();
						System.out.println("Result: " + mathLib.sqrt(x1));
						break;
					case 2:
						System.out.print("Enter integer: ");
						int n = sc.nextInt();
						System.out.println("Result: " + mathLib.factorial(n));
						break;
					case 3:
						System.out.print("Enter number: ");
						double x2 = sc.nextDouble();
						System.out.println("Result: " + mathLib.ln(x2));
						break;
					case 4:
						System.out.print("Enter base: ");
						double base = sc.nextDouble();
						System.out.print("Enter exponent: ");
						double exp = sc.nextDouble();
						System.out.println("Result: " + mathLib.power(base, exp));
						break;
					case 5:
						System.out.println("Exiting...");
						shd_break=1;
						break;
					default:
						shd_break=1;
						System.out.println("Invalid choice");
			    }
			} catch (Exception e) {
				System.out.println("Error: " + e.getMessage());
			}
			System.out.println("\n");

			if(shd_break==1) {
				break;
			}
		}

		sc.close();
	}
}


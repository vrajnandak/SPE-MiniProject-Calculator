package com.calculator.math_lib;

public class MathLib {

	// Square root
	public double sqrt(double x) {
		if (x < 0) throw new IllegalArgumentException("Negative input not allowed");
		return Math.sqrt(x);
	}

	// Factorial
	public long factorial(int n) {
		if (n < 0) throw new IllegalArgumentException("Negative input not allowed");
		long result = 1;
		for (int i = 2; i <= n; i++) {
			result *= i;
		}
		return result;
	}

	// Natural logarithm
	public double ln(double x) {
		if (x <= 0) throw new IllegalArgumentException("Non-positive input not allowed");
		return Math.log(x);
	}

	// Power function
	public double power(double base, double exponent) {
		return Math.pow(base, exponent);
	}
}


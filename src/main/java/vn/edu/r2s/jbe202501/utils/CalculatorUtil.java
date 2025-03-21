package vn.edu.r2s.jbe202501.utils;

public class CalculatorUtil {

	static double divide(int a, int b) {
		if (b == 0) {
			throw new ArithmeticException("b la so 0!");
		}

		return 1.0 * a / b;
	}
}

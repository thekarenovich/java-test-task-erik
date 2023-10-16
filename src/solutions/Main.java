package solutions;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {

    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.print("Input arithmetic expression ('1 + 2' or 'VI / III'): ");
            String input = scanner.nextLine();

            if (input.equalsIgnoreCase("exit")) {
                break;
            }

            String result = calc(input);
            System.out.println("Result: " + result);
        }

        scanner.close();
    }

    public static String calc(String input) throws Exception {
        String[] parts = input.split(" ");

        if (parts.length != 3) {
            throw new Exception("throws Exception");
        }

        String operand1 = parts[0];
        String operator = parts[1];
        String operand2 = parts[2];

        boolean isRoman1 = isRomanNumber(operand1);
        boolean isRoman2 = isRomanNumber(operand2);

        if (isRoman1 && isRoman2 || (!isRoman1 && !isRoman2)) {
            int num1 = isRoman1 ? romanToArabic(operand1) : Integer.parseInt(operand1);
            int num2 = isRoman2 ? romanToArabic(operand2) : Integer.parseInt(operand2);

            if ((num1 < 1 || num1 > 10) || (num2 < 1 || num2 > 10)) {
                throw new Exception("throws Exception");
            }

            int result;
            switch (operator) {
                case "+":
                    result = num1 + num2;
                    break;
                case "-":
                    result = num1 - num2;
                    if (isRoman1 && result <= 0) {
                        throw new Exception("throws Exception");
                    }
                    break;
                case "*":
                    result = num1 * num2;
                    break;
                case "/":
                    if (num2 == 0) {
                        throw new Exception("throws Exception");
                    }
                    result = num1 / num2;
                    break;
                default:
                    throw new Exception("throws Exception");
            }

            return isRoman1 ? arabicToRoman(result) : String.valueOf(result);
        } else {
            throw new Exception("throws Exception");
        }
    }


    private static boolean isRomanNumber(String s) {
        String romanPattern = "^(M{0,3}(CM|CD|D?C{0,3})(XC|XL|L?X{0,3})(IX|IV|V?I{0,3}))$";
        Pattern pattern = Pattern.compile(romanPattern);
        Matcher matcher = pattern.matcher(s);
        return matcher.matches();
    }

    private static int romanToArabic(String s) {
        Map<Character, Integer> romanValues = new HashMap<>();
        romanValues.put('I', 1);
        romanValues.put('V', 5);
        romanValues.put('X', 10);
        romanValues.put('L', 50);
        romanValues.put('C', 100);
        romanValues.put('D', 500);
        romanValues.put('M', 1000);

        int result = 0;
        int prevValue = 0;

        for (int i = s.length() - 1; i >= 0; i--) {
            int value = romanValues.get(s.charAt(i));
            if (value < prevValue) {
                result -= value;
            } else {
                result += value;
            }
            prevValue = value;
        }

        return result;
    }

    private static String arabicToRoman(int number) {
        String[] romanNumerals = {"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};
        int[] values = {1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};
        StringBuilder roman = new StringBuilder();

        for (int i = 0; i < values.length; i++) {
            while (number >= values[i]) {
                number -= values[i];
                roman.append(romanNumerals[i]);
            }
        }

        return roman.toString();
    }
}

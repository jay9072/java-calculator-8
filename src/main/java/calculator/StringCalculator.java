package calculator;

public class StringCalculator {

    public static int add(String input) {
        if (input == null || input.trim().isEmpty()) {
            return 0;
        }

        if (input.contains("\\n")) {
            input = input.replace("\\n", "\n");
        }

        String delimiter = "[,:]";
        String numbers = input;

        if (input.startsWith("//")) {
            String[] parts = input.split("\n", 2);

            String customDelimiter = parts[0].substring(2);
            delimiter = java.util.regex.Pattern.quote(customDelimiter); //문자 그대로 인식하게 하는 안전장치
            numbers = parts[1];
        }

        String[] tokens = numbers.split(delimiter);

        int sum =0;
        for (String number : tokens) {
            sum += Integer.parseInt(number);
        }


        return sum;
    }
}

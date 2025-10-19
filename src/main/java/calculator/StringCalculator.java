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

            int newlineIndex = input.indexOf('\n');
            if (newlineIndex < 0) {
                throw new IllegalArgumentException("잘못된 입력입니다: 커스텀 구분자 형식은 \"//<구분자>\\n<숫자들>\" 이어야 합니다.");
            }

            String customDelimiter = input.substring(2, newlineIndex);
            if (customDelimiter.isEmpty()) {
                throw new IllegalArgumentException("잘못된 입력입니다: 커스텀 구분자가 비어 있습니다.");
            }

            delimiter = java.util.regex.Pattern.quote(customDelimiter); //문자 그대로 인식하게 하는 안전장치
            numbers = input.substring(newlineIndex + 1);
            if (numbers.trim().isEmpty()) {
                return 0;
            }
        }

        String[] tokens = numbers.split(delimiter);
        int sum = 0;

        for (String raw : tokens) {
            String token = raw.trim();

            if (token.isEmpty()) {
                throw new IllegalArgumentException("잘못된 입력입니다: 빈 값이 포함되었습니다.");
            }

            if (!token.matches("[1-9]\\d*")) {
                throw new IllegalArgumentException("잘못된 입력입니다: 양수만 허용됩니다 → " + token);
            }

            final int value;
            try {
                value = Integer.parseInt(token);
            } catch (NumberFormatException e) {
                throw new IllegalArgumentException("잘못된 입력입니다: 정수 범위를 벗어났습니다 → " + token);

            }


            sum += value;

        }
        return sum;
    }
}
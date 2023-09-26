package model;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TextNormalizerModel {

    public String normalizeText(String input) {
        // Chuẩn hóa văn bản bằng cách áp dụng các quy tắc đã chỉ định
        String[] lines = input.split("\n"); // Tách văn bản thành các dòng

        StringBuilder str = new StringBuilder();

        // Cờ để theo dõi xem dòng hiện tại có phải là dòng đầu tiên không
        boolean isFirstLine = true;

        for (String line : lines) {
            // Loại bỏ khoảng trắng ở đầu và cuối mỗi dòng
            line = line.trim();

            if (!line.isEmpty()) { // Bỏ qua các dòng trống
                if (!isFirstLine) {
                    str.append(" "); // Thêm khoảng trắng giữa các dòng không trống
                }

                // Chuẩn hóa dòng hiện tại
                line = normalizeLine(line);

                str.append(line);
                isFirstLine = false;
            }
        }

        // Thêm dấu chấm vào cuối văn bản đã chuẩn hóa
        str.append(".");

        return str.toString();
    }

    private String normalizeLine(String line) {
        // Loại bỏ các khoảng trắng thừa giữa các từ
        line = line.replaceAll("\\s+", " ");

        // Chuẩn hóa dấu câu: một khoảng trắng sau dấu phẩy, dấu chấm, và dấu hai chấm
        line = line.replaceAll("\\s*,\\s*", ", ");
        line = line.replaceAll("\\s*\\.\\s*", ". ");
        line = line.replaceAll("\\s*:\\s*", ": ");

        // Viết hoa ký tự đầu tiên của câu
        line = capitalizeFirstCharacter(line);

        // Loại bỏ khoảng trắng trước và sau dấu ngoặc kép
        line = line.replaceAll("\\s*\"\\s*", "\"");

        return line;
    }

    private String capitalizeFirstCharacter(String line) {
        Pattern pattern = Pattern.compile("(^|\\.[\\s\"])([a-z])");
        Matcher matcher = pattern.matcher(line);

        StringBuffer result = new StringBuffer();

        while (matcher.find()) {
            matcher.appendReplacement(result, matcher.group(1) + matcher.group(2).toUpperCase());
        }

        matcher.appendTail(result);

        return result.toString();
    }
}


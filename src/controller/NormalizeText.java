package controller;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import model.TextNormalizerModel;
import view.Menu;

public class NormalizeText extends Menu<String>{
    static String[] mc = {"Normalize Text","Exit"};
    private TextNormalizerModel model;
    
    public NormalizeText(TextNormalizerModel model) {
        super("NORMALIZE TEXT PROGRAMMING", mc);
        this.model = model;
    }
    
     public void normalizeText(String inputFilePath, String outputFilePath) {
        try {
            // Đọc văn bản từ tệp đầu vào
            String inputText = readFile(inputFilePath);

            // Chuẩn hóa văn bản
            String normalizedText = model.normalizeText(inputText);

            // Ghi văn bản đã chuẩn hóa vào tệp đầu ra
            writeFile(outputFilePath, normalizedText);

            System.out.println("Text normalization completed. Result saved to " + outputFilePath);
        } catch (IOException e) {
            System.err.println("An error occurred: " + e.getMessage());
        }
    }

    private String readFile(String filePath) throws IOException {
        StringBuilder text = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                text.append(line).append("\n");
            }
        }
        return text.toString();
    }

    private void writeFile(String filePath, String content) throws IOException {
        try (FileWriter writer = new FileWriter(filePath)) {
            writer.write(content);
        }
    }
    
    @Override
    public void execute(int n) {
        switch (n) {
            case 1:
                normalizeText("input.txt", "output.txt");
                break;
            case 2:
                System.out.println("Exit the program successfully!");
                System.exit(0);
                break;
        }
    }   
    

}
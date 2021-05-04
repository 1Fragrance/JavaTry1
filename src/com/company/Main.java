package com.company;
import java.io.*;

public class Main {

    private static final String _inputFileDefaultValue = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.";
    private static final String _inputFileName = "input.txt";
    private static final String _processedDataFileName = "processedData.txt";
    private static final String _resultFileName = "result.txt";

    public static void main(String[] args) {
        try {
            ensureFilesCreated();
            processInput(parseFile());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void processInput(String inputStr) throws IOException {
        System.out.println("Input string: " + inputStr);

        int vowelsCount = 0;
        int spacesCount = 0;
        int lettersCount = 0;
        for (int i = 0; i < inputStr.length(); i++) {
            char ch = inputStr.charAt(i);

            if (Character.isLetter(ch)) {
                lettersCount++;

                if (ch == 'a' || ch == 'e' || ch == 'i' || ch == 'o' || ch == 'u') {
                    vowelsCount++;
                }
            } else if (Character.isWhitespace(ch)) {
                spacesCount++;
            }
        }

        String vowelsCountStr = "Vowels count: " + vowelsCount;
        String spacesCountStr = "Spaces count: " + spacesCount;
        String lettersCountStr = "Letters count: " + lettersCount;
        System.out.println(vowelsCountStr);
        System.out.println(spacesCountStr);
        System.out.println(lettersCountStr);

        writeToFile(_processedDataFileName, vowelsCountStr + "\n" + spacesCountStr + "\n" + lettersCountStr);
        writeToFile(_resultFileName, inputStr + "\n" + vowelsCountStr + "\n" + spacesCountStr + "\n" + lettersCountStr);
    }

    private static void writeToFile(String filePath, String content) throws IOException {
        FileOutputStream fos = new FileOutputStream(filePath);
        fos.write(content.getBytes());
        fos.close();
    }

    private static String parseFile() throws IOException {
        File file = new File(_inputFileName);
        FileInputStream fis = new FileInputStream(file);
        byte[] data = new byte[(int) file.length()];
        fis.read(data);
        fis.close();

        return new String(data, "UTF-8");
    }

    private static void ensureFilesCreated() throws IOException {
        File inputFile = new File(_inputFileName);
        if (!inputFile.exists() || !inputFile.isFile()) {
            writeToFile(_inputFileName, _inputFileDefaultValue);
            System.out.println("\n** Created input file **\n");
        }

        File processedDataFile = new File(_processedDataFileName);
        if (!processedDataFile.exists() || !processedDataFile.isFile()) {
            processedDataFile.createNewFile();
            System.out.println("\n** Created empty processed data file **\n");
        }

        File resultFile = new File(_resultFileName);
        if (!resultFile.exists() || !resultFile.isFile()) {
            resultFile.createNewFile();
            System.out.println("\n** Created empty result file **\n");
        }
    }
}



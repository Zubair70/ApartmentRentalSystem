package dao.file_handler;

import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FileHandler {
    public static void updateFile(String fileLocation, String header, String data) throws Exception {
        BufferedWriter writer = new BufferedWriter(new FileWriter(fileLocation));
        writer.write(header);
        writer.newLine();
        writer.write(data);
        writer.close();
    }

    public static List<String> fetchData(String fileLocation) throws Exception {
        List<String> data = new ArrayList<>();
        Scanner scanner = new Scanner(new FileReader(fileLocation));
        scanner.forEachRemaining(line -> data.add(line));
        scanner.close();
        return data;
    }
}

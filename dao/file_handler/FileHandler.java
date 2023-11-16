package dao.file_handler;

import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FileHandler {

    private static List<String> data = new ArrayList<>();

    public static void updateFile(String fileLocation, String header, String data) throws Exception {
        BufferedWriter writer = new BufferedWriter(new FileWriter("data.txt"));
        writer.write(header);
        writer.newLine();
        writer.write(data);
        writer.close();
    }

    public static List<String> fetchData() throws Exception {
        if(data.isEmpty()) {
            Scanner scanner = new Scanner(new FileReader("data.txt"));
            scanner.forEachRemaining(line -> data.add(line));
            scanner.close();
        }
        return data;
    }
}

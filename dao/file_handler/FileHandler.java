package dao.file_handler;

import constants.IConstants;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FileHandler {

    private static List<String> data = new ArrayList<>();

    public static void updateFile(String data) throws Exception {
        BufferedWriter writer = new BufferedWriter(new FileWriter(IConstants.DATA_FILE, true));
        writer.write(data);
        writer.close();
    }

    public static void clearFile() throws Exception {
        BufferedWriter writer = new BufferedWriter(new FileWriter(IConstants.DATA_FILE));
        writer.write("");
        writer.close();
    }

    public static List<String> fetchData() throws Exception {
        if(data.isEmpty()) {
            File file = new File(IConstants.DATA_FILE);
            if(!file.exists()) {
                file.createNewFile();
            }
            Scanner scanner = new Scanner(new FileReader(file));
            while (scanner.hasNextLine()) {
                data.add(scanner.nextLine());
            }
            scanner.close();
        }
        return data;
    }
}
